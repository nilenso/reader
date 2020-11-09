(ns reader.config
  (:require [clojure.edn :as edn]))

(defn config
  [keyword]
  (let [config (edn/read-string (slurp "resources/config.edn"))]
    (keyword config)))

