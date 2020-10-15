(ns reader.core
  (:gen-class)
  (:require [reader.server :as server]))

(defn -main
  [& args]
  (server/start-app!))
