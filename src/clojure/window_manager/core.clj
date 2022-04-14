(ns window-manager.core
  (:import (com.sun.jna Pointer
                        NativeLong
                        Callback)
           (com.danmchenry.windowmanager XErrorHandler
                                         SigHandler
                                         XWindowAttributes
                                         XEvent)
           window_manager.screen.Screen)
  (:require [window-manager.cstdlib :as c]
            [window-manager.x11 :as x11]
            [window-manager.drawable :as drw]
            [window-manager.screen :as scr])
  (:gen-class))

(def display (atom nil))
(def drawable (atom nil))

(defn die [msg]
  (println msg)
  (System/exit -1))

(defn sig-child []
  (doall
   (take-while pos?
               (repeatedly (fn [] (c/wait -1 Pointer/NULL c/WNOHANG))))))

(def monitor (atom nil))

(defrecord Monitor
           [master-area-factor
            master-area-clients
            layout
            width
            height])

(def default-layout nil)

(defn create-monitor [_]
  (Monitor. 0.55 1 default-layout nil nil))

(defn netatoms [display]
  (letfn [(make-atom [a] (x11/XInternAtom display a false))]
    (let [atom-names {:utf8 "UTF8_STRING"
                      :wm-protocols "WM_PROTOCOLS"
                      :wm-delete "WM_DELETE_WINDOW"
                      :wm-state "WM_STATE"
                      :wm-focus "WM_TAKE_FOCUS"
                      :net-active "_NET_ACTIVE_WINDOW"
                      :net-supported "_NET_SUPPORTED"
                      :net-wm-name "_NET_WM_NAME"
                      :net-wm-state "_NET_WM_STATE"
                      :net-wm-check "_NET_SUPPORTING_WM_CHECK"
                      :net-wm-fullscreen "_NET_WM_STATE_FULLSCREEN"
                      :net-wm-window-type "_NET_WM_WINDOW_TYPE"
                      :net-wm-window-type-dialog "_NET_WM_WINDOW_TYPE_DIALOG"
                      :net-client-list "_NET_CLIENT_LIST"}]
      (into {}
            (map (fn [[k a]]
                   [k (make-atom a)])
                 atom-names)))))

(def cursors (atom nil))

(defn setup []
  (let [signal-callback (reify SigHandler
                          (callback [this _]
                            (sig-child)))]
    (when (= c/SIG_ERR (c/signal c/SIGCHLD signal-callback))
      (die "can't install SIGCHLD handler")))
  (swap! drawable #(let [cscreen (x11/get_default_screen @display)
                         screen (scr/Screen. cscreen
                                             (x11/get_display_width @display cscreen)
                                             (x11/get_display_height @display cscreen)
                                             (x11/get_root_window @display cscreen))]
                     (drw/create @display @screen)))
  ;; TODO: font stuff
  (doseq [c [x11/XC_left_ptr
             x11/XC_sizing
             x11/XC_fleur]]
    (swap! cursors
           #(assoc % c (x11/XCreateFontCursor @display c))))
  (let [as (netatoms @display)
        win nil #_(x11/XCreateSimpleWindow @display
                                           (-> @drawable :screen :root-window)
                                           0 0 1 1 0 0 0)]
    ;; TODO: register EWMH/ICCCM properties
    #_(x11/XChangeProperty @display (:net-wm-check as) (:utf8 as) 8 (:PropModeReplace x11/modes) (Pointer. win) 1)
    (x11/XDeleteProperty @display (-> @drawable :screen :root-window) (:net-client-list as)))
  (let [event-mask (apply bit-or
                          (map #(% x11/event-masks)
                               [:SubstructureRedirectMask
                                :SubstructureNotifyMask
                                :ButtonPressMask
                                :PointerMotionMask
                                :EnterWindowMask
                                :LeaveWindowMask
                                :StructureNotifyMask
                                :PropertyChangeMask]))]
    (x11/set_window_attributes @display
                               (-> @drawable :screen :root-window)
                               (bit-or (:CWEventMask x11/value-masks)
                                       (:CWCursor x11/value-masks))
                               event-mask
                               (get @cursors x11/XC_left_ptr))
    (x11/XSelectInput @display (-> @drawable :screen :root-window) event-mask))
  ;; TODO: grab keys
  ;; TODO: focus?
  nil)

(def original-error-handler (atom nil))
(defn check-other-wm []
  (let [exit? (atom nil)
        test-callback (x11/error-handler [display xerror-event]
                                         (println "exclojion: another window manager is already running")
                                         (swap! exit? (fn [_] true))
                                         -1)
        normal-callback (x11/error-handler [display xerror-event]
                                           (x11/call_error_handler @original-error-handler display xerror-event))
        orig (x11/XSetErrorHandler test-callback)]
    (swap! original-error-handler (fn [_] orig))
    (try
      ; this causes an error if some other window manager is running
      (x11/XSelectInput @display
                        (x11/get_root_window @display (x11/get_default_screen @display))
                        (:SubstructureRedirectMask x11/event-masks))
      (x11/XSync @display c/FALSE)
      (catch Exception e
        (println e)))
    (when @exit?
      (System/exit -1))
    (x11/XSetErrorHandler normal-callback)
    (x11/XSync @display c/FALSE)))

(def handlers
  {})

(defn main-loop []
  (x11/XSync display false)
  (let [event (XEvent.)]
    (while (not (x11/XNextEvent display event))
      (println "Received event!" event)
      (when-let [handler (get handlers (.type event))]
        (handler event)))))

(defn main- [& args]
  (println args)
  (cond
    (and (= (count args) 2)
         (= (nth args 1) "-v"))
    (die "exclojion-0.1")

    (not (= (count args) 1))
    (die "usage: exclojion [-v]")

    (not (and (c/setlocale c/LC_CTYPE "")
              (x11/XSupportsLocale)))
    (println "warning: no locale support")

    (do (swap! display (fn [_] (x11/XOpenDisplay Pointer/NULL)))
        (not @display))
    (die "exclojion: cannot open display"))
  (check-other-wm)
  (setup)
  (main-loop))

(comment
  (c/cosh 0)
  (c/puts "test")

  (x11/get_default_screen display)
  (x11/get_display_width display (x11/get_default_screen display))
  (x11/get_display_height display (x11/get_default_screen display))
  (x11/get_root_window display (x11/get_default_screen display))

  (let [wa (XWindowAttributes.)
        scr (x11/get_default_screen @display)
        win (x11/get_root_window @display scr)]
    (x11/XGetWindowAttributes @display win wa)
    wa)

  (def callback-test (x11/error-handler [display error]
                                        100))

  (x11/call_error_handler callback-test Pointer/NULL Pointer/NULL))
