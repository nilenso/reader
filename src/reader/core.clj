(ns reader.core
  (:gen-class)
  (:require [reader.server :as s]
            [reader.config :as c]
            [reader.redis :as r]))

(defn -main
  [& args]
  (if-let [file-name (first args)]
    (c/load-config! file-name)
    (c/load-config!))
  (r/set-conn-opts!)
  (s/start-app!))
