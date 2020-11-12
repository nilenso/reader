(ns reader.core
  (:gen-class)
  (:require [reader.server :as server]
            [reader.config :as config]))

(defn -main
  [& args]
  (if-let [file-name (first args)]
    (config/load-config-file file-name)
    (config/load-config-file))
  (server/start-app!))
