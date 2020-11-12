(ns reader.redis
  (:require [taoensso.carmine :as car]
            [reader.config :as c]))

(defonce server-conn (atom nil))

(defn set-conn-opts
  []
  (reset! server-conn {:pool {}, :spec {:host (c/config :redis-host)}}))

(defmacro wcar*
  [& body]
  `(car/wcar server-conn ~@body))

(defn set-to-cache
  [key value]
  (set-conn-opts)
  (wcar* (car/set key value)
         (car/expire key 300)))

(defn get-from-cache
  [key]
  (set-conn-opts)
  (when (= (wcar* (car/exists key)) 1)
    (wcar* (car/get key))))

(defn delete-key
  [key]
  (set-conn-opts)
  (wcar* (car/del key)))

(defn delete-all-keys
  []
  (set-conn-opts)
  (wcar* (car/flushall)))
