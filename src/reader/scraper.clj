(ns reader.scraper
  (:require [hickory.core :as h]
            [hickory.render :as hr]
            [clojure.walk :as w]))

(defn- get-hickory
  [url]
  (-> url
      slurp
      h/parse
      h/as-hickory))

(defn- remove-images
  [node]
  (if (and (map? node)
           (contains? node :tag)
           (= (:tag node) :img))
    (assoc-in (assoc-in node [:attrs :src] "")
              [:attrs :alt]
              " ")
    node))

(defn- manipulate-hickory
  [hickory]
  (w/postwalk remove-images hickory))

(defn get-html
  [url]
  (hr/hickory-to-html (manipulate-hickory (get-hickory url))))
