(ns reader.config
  (:require [clojure.edn :as edn]))

(defn port
  []
  (let [config (edn/read-string (slurp "resources/config.edn"))]
    (:port config)))
