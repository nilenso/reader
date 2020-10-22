(ns reader.handlers
  (:require [reader.scraper :as scraper]
            [ring.util.response :as response]
            [ring.middleware.params :refer [wrap-params]]))

(defn handler
  [{:keys [query-params]}]
  (if-let [url (get query-params "url")]
    (response/response (scraper/get-html url))
    (response/bad-request "URL query missing")))

(def app (wrap-params handler))