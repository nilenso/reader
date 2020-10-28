(ns reader.html
  (:require [hickory.core :as h]
            [hickory.render :as hr]
            [clojure.walk :as w]
            [org.httpkit.client :as c]))

(defn fetch-html
  [url]
  (:body @(c/get url)))

(defn- html-to-hickory
  [html]
  (h/as-hickory (h/parse html)))

(defn- remove-node-images
  [node]
  (if (and (map? node)
           (or (= (:tag node) :img)
               (= (:tag node) :svg)))
    ""
    node))

(defn- remove-images
  [hickory]
  (w/postwalk remove-node-images hickory))

(defn html-without-images
  [html]
  (-> html
      html-to-hickory
      remove-images
      hr/hickory-to-html))
