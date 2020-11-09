(ns reader.redis
  (:require [taoensso.carmine :as car]
            [reader.config :as c]))

(def server-conn {:pool {}, :spec {:host (c/config :redis-host)}})

(defmacro wcar*
  [& body]
  `(car/wcar server-conn ~@body))

(defn set-to-cache
  [key value]
  (wcar* (car/set key value)
         (car/expire key 300)))

(defn get-from-cache
  [key]
  (when (= (wcar* (car/exists key)) 1)
    (wcar* (car/get key))))

(defn delete-key
  [key]
  (wcar* (car/del key)))

(defn delete-all-keys
  []
  (wcar* (car/flushall)))
