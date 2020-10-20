(ns reader.scraper
  (:require [net.cgrand.enlive-html :as html]
            [clojure.string :as str]))

(defn fetch-url
  [url]
  (let [html (html/html-resource (java.net.URL. url))]
    (if (= 1 (count html)) (first html)
        (second html))))

(defn trimmer
  [string]
  (->> string
       (str/split-lines)
       (map str/trim)
       (remove empty?)
       (str/join "\n\n")))

(defn get-text
  [url]
  (trimmer (html/text (fetch-url url))))
