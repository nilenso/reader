(ns reader.server
  (:require [org.httpkit.server :as httpkit]
            [reader.config :as config]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.not-modified :refer [wrap-not-modified]]
            [ring.middleware.params :refer [wrap-params]]
            [reader.scraper :as scraper]))

(defonce server (atom nil))

(defn handler
  [{:keys [query-params]}]
  {:status 200
   :body (scraper/get-text (first (vals query-params)))})

(def app
  (-> handler
      (wrap-params)
      (wrap-resource "public")))

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

