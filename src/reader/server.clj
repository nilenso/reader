(ns reader.server
  (:require [reader.config :as c]
            [reader.handlers :as h]
            [ring.adapter.jetty :as r]))

(defonce server (atom nil))

(defn start-app!
  []
  (reset! server (r/run-jetty h/handler {:port (c/port), :join? false}))
  (println "Server started on port" (c/port)))

(defn stop-app!
  []
  (.stop @server)
  (reset! server nil)
  (println "Server stopped."))

(defn restart-app!
  []
  (stop-app!)
  (start-app!))
