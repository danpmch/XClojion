(ns window-manager.jna
  (:import com.sun.jna.NativeLibrary)
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

