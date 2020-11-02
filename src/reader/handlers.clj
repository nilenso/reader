(ns reader.handlers
  (:require [reader.html :as html]
            [ring.util.response :as response]
            [ring.middleware.params :refer [wrap-params]]
            [reader.redis :as r]))

(defn fetch-page
  [{:keys [query-params]}]
  (if-let [url (get query-params "url")]
    (if-let [response (r/get-html url)]
      (response/response response)
      (let [response (html/new-html (html/fetch-html url))]
        (r/set-html url response)
        (response/response response)))
    (response/bad-request "URL query missing")))

(def handler (wrap-params fetch-page))
