(ns reader.redis
  (:require [taoensso.carmine :as car]
            [reader.config :as c]))

(defonce server-conn (atom nil))

(defn set-conn-opts!
  []
  (reset! server-conn {:pool {}, :spec {:host (:redis-host @c/config)}}))

(defmacro wcar*
  [& body]
  `(car/wcar @server-conn ~@body))

(defn set-to-cache!
  [key value]
  (wcar* (car/set key value)
         (car/expire key 300)))

(defn get-from-cache
  [key]
  (when (= (wcar* (car/exists key)) 1)
    (wcar* (car/get key))))

(defn delete-key!
  [key]
  (wcar* (car/del key)))

(defn delete-all-keys!
  []
  (wcar* (car/flushall)))
