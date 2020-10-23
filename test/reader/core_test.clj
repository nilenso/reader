(ns reader.core-test
  (:require [clojure.test :refer :all]
            [reader.scraper :as s]
            [hickory.render :as hr]))

(deftest remove-images-test
  (testing "Given html with no img/svg tags, return unchanged html"
    (let [html "<html><head></head><body>hi</body></html>"]
      (is (= html (hr/hickory-to-html
                   (s/manipulate-hickory (s/get-hickory html)))))))
  (testing "Given html with img/svg tags, return html without them"
    (let [html1 "<html><head></head><body><img src='hi.jpg' alt='hi'>hi</body></html>"
          html2 "<html><head></head><body><svg width='10' height='10'></svg>hi</body></html>"
          html3 "<html><head></head><body><img src='hi.jpg' alt='hi'><svg></svg>hi</body></html>"
          changed-html "<html><head></head><body>hi</body></html>"]
      (is (= changed-html (hr/hickory-to-html
                           (s/manipulate-hickory (s/get-hickory html1)))))
      (is (= changed-html (hr/hickory-to-html
                           (s/manipulate-hickory (s/get-hickory html2)))))
      (is (= changed-html (hr/hickory-to-html
                           (s/manipulate-hickory (s/get-hickory html3))))))))
