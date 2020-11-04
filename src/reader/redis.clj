(ns reader.redis
  (:require [taoensso.carmine :as car]
            [reader.config :as c]))

(def server-conn {:pool {} :spec {:host (c/config :redis-host)}})

(defmacro wcar*
  [& body]
  `(car/wcar server-conn ~@body))

(defn set-html
  [url html]
  (wcar* (car/set url html)
         (car/expire url 300)))

(defn get-html
  [url]
  (when (= (wcar* (car/exists url)) 1)
    (wcar* (car/get url))))
