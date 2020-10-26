(ns reader.handlers_test
  (:require [clojure.test :refer :all]
            [reader.handlers :as h]))

(deftest handler-test
  (testing "Given url query parameter, return status 200 response"
    (let [response (h/fetch-page {:query-params {"url" "https://www.example.com"}})]
      (is (= 200 (:status response)))))
  (testing "Given request without url parameter, return status 400 response"
    (let [response (h/fetch-page {})]
      (is (= 400 (:status response))))))