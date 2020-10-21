(ns reader.scraper
  (:require [hickory.core :as h]
            [hickory.render :as hr]))

(defn- get-hickory
  [url]
  (-> url
      slurp
      h/parse
      h/as-hickory))

(defn- manipulate-hickory
  [hickory]
  hickory)

(defn get-html
  [url]
  (hr/hickory-to-html (manipulate-hickory (get-hickory url))))
