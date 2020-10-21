(ns reader.server
  (:require [org.httpkit.server :as httpkit]
            [reader.config :as config]
            [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :as response]
            [reader.scraper :as scraper]))

(defonce server (atom nil))

(defn handler
  [{:keys [query-params]}]
  (if-let [url (get query-params "url")]
    (response/response (scraper/get-html url))
    (response/bad-request "URL query missing")))

(def app (wrap-params handler))

(defn start-app!
  []
  (reset! server (httpkit/run-server app {:port (config/port)}))
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
  (start-app!))

