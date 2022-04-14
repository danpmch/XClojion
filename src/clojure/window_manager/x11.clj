(ns window-manager.x11
  (:import (com.sun.jna Pointer
                        NativeLong)
           com.danmchenry.windowmanager.XErrorHandler)
  (:require [window-manager.jna :as jna])
  (:gen-class))

(jna/defnative X11
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
  (Integer XNextEvent [display event-return])
  (Boolean XSupportsLocale [])
  (Pointer XSetErrorHandler [handler])
  (Integer XSync [display discard?])
  (NativeLong XCreatePixmap [display drawable width height depth])
  (Pointer XCreateGC [display drawable valuemask values])
  (Integer XSetLineAttributes [display gc line-width line-style cap-style join-style])
  (NativeLong XCreateFontCursor [display shape])
  (Integer XGetWindowAttributes [display window attrs]))

(defmacro error-handler [args & body]
  (let [callback 'callback]
    `(reify XErrorHandler
      (~callback [this ~@args]
        ~@body))))

(def XA_WINDOW (NativeLong. 33))

(def LineSolid 0)
(def CapButt 1)
(def JoinMiter 0)

;; TODO: Should enums be pushed down to the shim layer to avoid duplicating c definitions?
(def modes
  {:PropModeReplace 0
   :PropModePrepend 1
   :PropModeAppend 2})

(def XC_left_ptr 68)
(def XC_sizing 120)
(def XC_fleur 52)

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

(def value-masks
  {:CWEventMask (<< 1 11)
   :CWCursor (<< 1 14)})

(jna/defnative wmshims
  (Integer get_default_screen [display])
  (Integer get_display_width [display screen])
  (Integer get_display_height [display screen])
  (Integer get_default_depth [display screen])
  (NativeLong get_root_window [display screen])
  (Integer call_error_handler [handler display event])
  (Integer set_window_attributes [display window valuemask event-mask cur]))

