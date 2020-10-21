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

(defn- change-text
  [node]
  (if (and (map? node)
           (= 1 (count (:content node)))
           (string? (get (:content node) 0)))
    (assoc node :content ["CHANGED!"])
    node))

(defn- manipulate-hickory
  [hickory]
  (w/postwalk change-text hickory))

(defn get-html
  [url]
  (hr/hickory-to-html (manipulate-hickory (get-hickory url))))
