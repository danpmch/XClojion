(ns window-manager.cstdlib
  (:import com.danmchenry.windowmanager.SigHandler
           com.sun.jna.NativeLong)
  (:require [window-manager.jna :as jna])
  (:gen-class))

(jna/defnative c
  (Double cosh [x])
  (Integer puts [s])
  (String setlocale [category locale])
  (SigHandler signal [sig handler])
  (NativeLong wait [stat-loc]))

(def LC_CTYPE 0)

(def FALSE 0)
(def TRUE 1)

;; signals
(def SIGCHLD 17)
(def SIG_ERR (NativeLong. -1))
(def WNOHANG 1)


