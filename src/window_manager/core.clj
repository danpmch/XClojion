(ns window-manager.core
  (:import (com.sun.jna Pointer
                        NativeLong))
  (:require [window-manager.cstdlib :as c]
            [window-manager.x11 :as x11])
  (:gen-class))

(comment
  (c/cosh 0)
  (c/puts "test")

  (def display (x11/XOpenDisplay Pointer/NULL))

  display

  (x11/get_default_screen display)
  (x11/get_display_width display (x11/get_default_screen display))
  (x11/get_display_height display (x11/get_default_screen display))
  (x11/get_root_window display (x11/get_default_screen display)))
