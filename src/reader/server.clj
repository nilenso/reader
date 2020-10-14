(ns reader.server
  (:require [org.httpkit.server :as httpkit]
            [reader.config :as config]))

(defonce server (atom nil))

(defn handler
  [_]
  {:status 200
   :body "mic check"})

(defn start-app!
  []
  (reset! server (httpkit/run-server handler {:port (config/port)}))
  (println "Server started on port" (config/port)))

(defn stop-app!
  []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil))
  (println "Server stopped."))

(defn restart-app!
  []
  (stop-app!)
  (start-app!)
  (println "Server restarted on port" (config/port)))

