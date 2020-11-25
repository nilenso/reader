(ns reader.server
  (:require [reader.config :as c]
            [reader.handlers :as h]
            [ring.adapter.jetty :as r]))

(defonce server (atom nil))

(defn start-app!
  []
  (reset! server (r/run-jetty h/handler {:host  "localhost"
                                         :port  (:ring-server-port @c/config)
                                         :join? false}))
  (println "Server started on port" (:ring-server-port @c/config)))

(defn stop-app!
  []
  (when @server (.stop @server))
  (reset! server nil)
  (println "Server stopped."))

(defn restart-app!
  []
  (stop-app!)
  (start-app!))
