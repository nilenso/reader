(ns reader.redis
  (:require [taoensso.carmine :as car]))

(def server-conn {:pool {} :spec {:host "127.0.0.1" :port 6379}})

(defmacro wcar*
  [& body]
  `(car/wcar server-conn ~@body))

(defn set-html
  [url html]
  (wcar* (car/set url html)))

(defn get-html
  [url]
  (when (= (wcar* (car/exists url)) 1)
    (wcar* (car/get url))))
