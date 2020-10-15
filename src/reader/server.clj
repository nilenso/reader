(ns reader.server
  (:require [org.httpkit.server :as httpkit]
            [reader.config :as config]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.not-modified :refer [wrap-not-modified]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :as response]
            [crouton.html :as html]))

(defonce server (atom nil))

(defn scraper-fn
  [url]
  ;; do stuff with webpage url
  (html/parse url))

(defn handler
  [{:keys [query-params]}]
  ;; add validation later
  (response/response (scraper-fn (first (vals query-params)))))

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

