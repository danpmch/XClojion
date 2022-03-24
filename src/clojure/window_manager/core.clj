(ns window-manager.core
  (:import (com.sun.jna Pointer
                        NativeLong
                        Callback)
           com.danmchenry.windowmanager.XErrorHandler)
  (:require [window-manager.cstdlib :as c]
            [window-manager.x11 :as x11])
  (:gen-class))

(def display (atom nil))
(def original-error-handler (atom nil))

(def callback-test (x11/error-handler [display error]
                                      100))

(defn check-other-wm []
  (let [test-callback (x11/error-handler [display xerror-event]
                                         (println "exclojion: another window manager is already running")
                                         (System/exit -1)
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
    (x11/XSetErrorHandler normal-callback)
    (x11/XSync @display c/FALSE)))

(defn main- [& args]
  (println args)
  (cond
    (and (= (count args) 2)
         (= (nth args 1) "-v"))
    (do (println "exclojion-0.1")
        (System/exit 1))

    (not (= (count args) 1))
    (do (println "usage: exclojion [-v]")
        (System/exit -1))

    (not (and (c/setlocale c/LC_CTYPE "")
              (x11/XSupportsLocale)))
    (println "warning: no locale support")

    (do (swap! display (fn [_] (x11/XOpenDisplay Pointer/NULL)))
        (not @display))
    (do (println "exclojion: cannot open display")
        (System/exit -1)))
  (check-other-wm))

(comment
  (c/cosh 0)
  (c/puts "test")

  (x11/get_default_screen display)
  (x11/get_display_width display (x11/get_default_screen display))
  (x11/get_display_height display (x11/get_default_screen display))
  (x11/get_root_window display (x11/get_default_screen display)))
