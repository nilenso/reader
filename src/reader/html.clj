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

(defn remove-node-images
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
               (and (= (:tag node) :link)
                    (= (get-in node [:attrs :rel]) "stylesheet"))))
    ""
    node))

(defn- change-font
  [node]
  (if (and (map? node)
           (= (:tag node) :head))
    (update node :content conj {:type    :element
                                :attrs   nil
                                :tag     :style
                                :content [(str "body {background-color: #fff;"
                                               "color: #000;"
                                               "text-align: center;"
                                               "font-size: 24;"
                                               "font-family: Arial;}")]})
    node))

(defn- change-hickory
  [hickory node-fns]
  (if node-fns
    (w/postwalk (reduce comp node-fns) hickory)
    (change-hickory hickory [change-font remove-node-css remove-node-images])))

(defn new-html
  [html & node-fns]
  (-> html
      html-to-hickory
      (change-hickory node-fns)
      hr/hickory-to-html))
