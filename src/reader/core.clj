(ns reader.core
  (:gen-class)
  (:require [org.httpkit.server :as h]
            [reader.config :as config]))

(defonce server (atom nil))

(defn handler
  [request]
  {:status 200
   :body "mic check"})

(defn start-app!
  []
  (reset! server (h/run-server handler {:port (config/port)}))
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

(defn -main
  [& args]
  (start-app!))
