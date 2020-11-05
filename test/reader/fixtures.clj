(ns reader.fixtures
  (:require [reader.redis :as r]))

(defn clear-cache
  [f]
  (r/delete-all)
  (f))
