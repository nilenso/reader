(ns reader.server
  (:require [org.httpkit.server :as s]
            [reader.config :as c]
            [reader.handlers :as h]))

(defonce server (atom nil))

(defn start-app!
  []
  (reset! server (s/run-server  h/app {:port (c/port)}))
  (println "Server started on port" (c/port)))

(defn stop-app!
  []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil))
  (println "Server stopped."))

(defn restart-app!
  []
  (stop-app!)
  (start-app!))

