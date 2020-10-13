(ns reader.core
  (:gen-class)
  (:require [reader.server :as server]))

(defn -main
  [& _]
  (server/start-app!))
