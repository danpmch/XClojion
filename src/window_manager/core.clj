(ns window-manager.core
  (:import com.sun.jna.NativeLibrary
           (com.sun.jna Pointer
                        NativeLong))
  (:gen-class))

(def libs (atom {}))

(defmacro defnative [lib-name & fns]
  (let [lib (NativeLibrary/getInstance (name lib-name))
        _ (swap! libs #(assoc % (name lib-name) lib))
        cfn (gensym "cfn")
        fn-syntax (map (fn [[return-type fn-name args]]
                         `(let [~cfn (.getFunction (get @libs ~(name lib-name)) ~(name fn-name))]
                            (defn ~fn-name ~args
                              (.invoke ~cfn ~return-type (object-array ~args)))))
                       fns)]
    `(do ~@fn-syntax)))

(defnative c
  (Double cosh [x])
  (Integer puts [s])
  (String setlocale [category locale]))

(defnative X11
  (Pointer XOpenDisplay [display-name])
  (Boolean XSupportsLocale [])
  (NativeLong XInternAtom [display atom-name only-if-exists?])
  (NativeLong XCreateSimpleWindow [display
                                   window
                                   x
                                   y
                                   width
                                   height
                                   border-width
                                   border
                                   background])
  (Integer XChangeProperty [display window property type format mode data num-elements])
  (Integer XChangeWindowAttributes [display window value-mask attributes])
  (Integer XDeleteProperty [display window property])
  (Integer XSelectInput [display window event-mask])
  (Integer XNextEvent [display event-return]))

(def XA_WINDOW (NativeLong. 33))

;; TODO: Should enums be pushed down to the shim layer to avoid duplicating c definitions?
(def modes
  {:PropModeReplace 0
   :PropModePrepend 1
   :PropModeAppend 2})

(def << bit-shift-left)

(def event-masks
  {:NoEventMask			0
   :KeyPressMask			(<< 1 0)
   :KeyReleaseMask			(<< 1 1)
   :ButtonPressMask			(<< 1 2)
   :ButtonReleaseMask		(<< 1 3)
   :EnterWindowMask			(<< 1 4)
   :LeaveWindowMask			(<< 1 5)
   :PointerMotionMask		(<< 1 6)
   :PointerMotionHintMask		(<< 1 7)
   :Button1MotionMask		(<< 1 8)
   :Button2MotionMask		(<< 1 9)
   :Button3MotionMask		(<< 1 10)
   :Button4MotionMask		(<< 1 11)
   :Button5MotionMask		(<< 1 12)
   :ButtonMotionMask		(<< 1 13)
   :KeymapStateMask			(<< 1 14)
   :ExposureMask			(<< 1 15)
   :VisibilityChangeMask		(<< 1 16)
   :StructureNotifyMask		(<< 1 17)
   :ResizeRedirectMask		(<< 1 18)
   :SubstructureNotifyMask		(<< 1 19)
   :SubstructureRedirectMask	(<< 1 20)
   :FocusChangeMask			(<< 1 21)
   :PropertyChangeMask		(<< 1 22)
   :ColormapChangeMask		(<< 1 23)
   :OwnerGrabButtonMask		(<< 1 24)})

(defnative wmshims
  (Integer get_default_screen [display])
  (Integer get_display_width [display screen])
  (Integer get_display_height [display screen])
  (NativeLong get_root_window [display screen]))

(comment
  (cosh 0)
  (puts "test")

  (def display (XOpenDisplay Pointer/NULL))

  display

  (get_default_screen display)
  (get_display_width display (get_default_screen display))
  (get_display_height display (get_default_screen display))
  (get_root_window display (get_default_screen display)))
