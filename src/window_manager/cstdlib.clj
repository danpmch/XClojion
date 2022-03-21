(ns window-manager.cstdlib
  (:require [window-manager.jna :as jna])
  (:gen-class))

(jna/defnative c
  (Double cosh [x])
  (Integer puts [s])
  (String setlocale [category locale]))

