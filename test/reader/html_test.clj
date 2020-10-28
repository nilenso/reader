(ns reader.html-test
  (:require [clojure.test :refer :all]
            [reader.html :as h]))

(deftest new-html-test
  (testing "Given html with no img/svg tags, return unchanged html"
    (let [html "<html><head></head><body></body></html>"]
      (is (= html (h/new-html html h/remove-node-images)))))
  (testing "Given html with img/svg tags, return html without them"
    (let [html1 "<html><head></head><body><img src='' alt=''></body></html>"
          html2 "<html><head></head><body><svg width='' height=''></svg></body></html>"
          html3 "<html><head></head><body><img src='' alt=''><svg></svg></body></html>"
          html4 "<html><head></head><body><div><div><div><div><div><img></img></div></div></div></div></div></body></html>"
          changed-html "<html><head></head><body></body></html>"
          changed-html4 "<html><head></head><body><div><div><div><div><div></div></div></div></div></div></body></html>"]
      (is (= changed-html (h/html-without-images html1)))
      (is (= changed-html (h/html-without-images html2)))
      (is (= changed-html (h/html-without-images html3)))
      (is (= changed-html4 (h/html-without-images html4))))))
