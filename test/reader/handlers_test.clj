(ns reader.handlers-test
  (:require [clojure.test :refer :all]
            [reader.handlers :as h]
            [reader.html :as html]
            [reader.redis :as r]
            [reader.test-utils :as tu]
            [reader.fixtures :as f]))

(deftest fetch-page-test
  (testing "Given url query parameter, return status 200 response"
    (with-redefs [html/new-html (constantly "hello world")]
      (let [request {:query-params {"url" "foo.bar"}}
            response (h/fetch-page request)]
        (is (= 200 (:status response))))))
  (testing "Given request without url parameter, return status 400 response"
    (let [response (h/fetch-page {})]
      (is (= 400 (:status response)))))
  (testing "If html already exists in cache, fetch it and return status 200"
    (tu/with-fixture f/clear-cache
      (r/set-html "foo.bar" "<html></html>")
      (let [request {:query-params {"url" "foo.bar"}}
            response (h/fetch-page request)]
        (is (= "<html></html>" (:body response)))
        (is (= 200 (:status response))))))
  (testing "If html does not exist in cache, fetch it, store in cache and return status 200"
    (tu/with-fixture f/clear-cache
      (with-redefs [html/new-html (constantly "hello world")]
        (let [request {:query-params {"url" "foo.bar"}}
              response (h/fetch-page request)]
          (is (= "hello world" (r/get-html "foo.bar")))
          (is (= 200 (:status response))))))))
