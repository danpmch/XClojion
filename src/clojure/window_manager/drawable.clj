(ns window-manager.drawable
  (:import (com.sun.jna Pointer
                        NativeLong
                        Callback)
           (com.danmchenry.windowmanager XErrorHandler
                                         SigHandler))
  (:require [window-manager.cstdlib :as c]
            [window-manager.x11 :as x11]
            [window-manager.screen :as scr])
  (:gen-class))

(defrecord Drawable
           [display
            screen
            drawable
            graphics-context
            scheme
            fonts])

(defn create [display screen]
  (let [drawable (x11/XCreatePixmap
                  display
                  (:root-window screen)
                  (:width screen)
                  (:height screen)
                  (x11/get_default_depth display (:ctype screen)))
        gc (x11/XCreateGC display (:root-window screen) 0 Pointer/NULL)
        drw (Drawable.
             display
             screen
             drawable
             gc
             nil
             nil)]
    (x11/XSetLineAttributes display gc 1 x11/LineSolid x11/CapButt x11/JoinMiter)
    drw))

