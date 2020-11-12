(ns reader.config
  (:require [clojure.edn :as edn]))

(defonce config-file (atom nil))

(defn load-config-file
  ([] (load-config-file "resources/config.edn"))
  ([file-name] (reset! config-file (slurp file-name))))

(defn config
  [keyword]
  (let [config (edn/read-string @config-file)]
    (keyword config)))
