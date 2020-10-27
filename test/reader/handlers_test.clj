(ns reader.handlers_test
  (:require [clojure.test :refer :all]
            [reader.handlers :as h]
            [reader.html :as html]))

(deftest handler-test
  (testing "Given url query parameter, return status 200 response"
    (with-redefs [html/html-without-images (constantly "hello world")]
      (let [request {:query-params {"url" "foo.bar"}}
            response (h/fetch-page request)]
        (is (= 200 (:status response))))))
  (testing "Given request without url parameter, return status 400 response"
    (let [response (h/fetch-page {})]
      (is (= 400 (:status response))))))