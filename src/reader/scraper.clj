(ns reader.scraper
  (:require [hickory.core :as h]
            [hickory.render :as hr]))

(defn format-url
  [url])

(defn get-hickory
  [url]
  (-> url
      slurp
      h/parse
      h/as-hickory))

(defn manipulate-hickory
  [hickory])

(defn get-html
  [hickory]
  (hr/hickory-to-html hickory))
