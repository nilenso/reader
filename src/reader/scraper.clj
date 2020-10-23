(ns reader.scraper
  (:require [hickory.core :as h]
            [hickory.render :as hr]
            [clojure.walk :as w]
            [org.httpkit.client :as c]))

(defn get-url
  [url]
  (:body @(c/get url)))

(defn get-hickory
  [html]
  (h/as-hickory (h/parse html)))

(defn remove-images
  [node]
  (if (and (map? node)
           (or (= (:tag node) :img)
               (= (:tag node) :svg)))
    ""
    node))

(defn manipulate-hickory
  [hickory]
  (w/postwalk remove-images hickory))

(defn get-html
  [url]
  (-> url
      get-url
      get-hickory
      manipulate-hickory
      hr/hickory-to-html))
