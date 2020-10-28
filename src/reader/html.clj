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

(defn remove-node-css
  [node]
  (if (and (map? node)
           (or (contains? node :style)
               (= (:tag node) :style)
               (= (:tag node) :link)))
    ""
    node))

(defn change-font
  [node]
  (if (and (map? node)
           (= (:tag node) :head))
    (assoc node :content
           (conj (:content node) {:type    :element
                                  :attrs   nil
                                  :tag     :style
                                  :content ["body {background-color: #fff;
                                                        color: #000;
                                                        font-size: 24;
                                                        font-family: Arial;}"]}))
    node))

(defn change-hickory
  ([hickory]
   (change-hickory hickory [change-font remove-node-css remove-node-images]))
  ([hickory & [fns]]
   (w/postwalk (reduce comp fns) hickory)))

(defn new-html
  [html & fns]
  (-> html
      html-to-hickory
      (change-hickory fns)
      hr/hickory-to-html))
