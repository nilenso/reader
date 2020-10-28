(ns reader.handlers
  (:require [reader.html :as html]
            [ring.util.response :as response]
            [ring.middleware.params :refer [wrap-params]]))

(defn fetch-page
  [{:keys [query-params]}]
  (if-let [url (get query-params "url")]
    (response/response (html/new-html (html/fetch-html url)))
    (response/bad-request "URL query missing")))

(def handler (wrap-params #'fetch-page))
