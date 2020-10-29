(ns reader.html-test
  (:require [clojure.test :refer :all]
            [reader.html :as h]))

(deftest new-html-test
  (testing "Given html with no images or css, return unchanged html"
    (let [html "<html><head></head><body></body></html>"]
      (is (= html (h/new-html html h/remove-node-css h/remove-node-images)))))
  (testing "Given html with images, return html without them"
    (let [html1 "<html><head></head><body><img src='' alt=''></body></html>"
          html2 "<html><head></head><body><svg width='' height=''></svg></body></html>"
          html3 "<html><head></head><body><img src='' alt=''><svg></svg></body></html>"
          html4 "<html><head></head><body><div><div><div><div><div><img></img></div></div></div></div></div></body></html>"
          changed-html "<html><head></head><body></body></html>"
          changed-html4 "<html><head></head><body><div><div><div><div><div></div></div></div></div></div></body></html>"]
      (is (= changed-html (h/new-html html1 h/remove-node-images)))
      (is (= changed-html (h/new-html html2 h/remove-node-images)))
      (is (= changed-html (h/new-html html3 h/remove-node-images)))
      (is (= changed-html4 (h/new-html html4 h/remove-node-images)))))
  (testing "Given html with css styling, return html without"
    (let [html1 "<html><head><style></style></head><body></body></html>"
          html2 "<html><head><style></style></head><body><p style=''></p></body></html>"
          html3 "<html><head><link rel='stylesheet'></head><body></body></html>"
          html4 "<html><head><link rel=''></head><body><p style=''></p></body></html>"
          changed-html "<html><head></head><body></body></html>"
          changed-html2 "<html><head></head><body><p></p></body></html>"
          changed-html4 "<html><head><link rel=\"\"></head><body><p></p></body></html>"]
      (is (= changed-html (h/new-html html1 h/remove-node-css)))
      (is (= changed-html2 (h/new-html html2 h/remove-node-css)))
      (is (= changed-html (h/new-html html3 h/remove-node-css)))
      (is (= changed-html4 (h/new-html html4 h/remove-node-css)))))
  (testing "Given any html, remove images, css, and add font styling"
    (let [html1 "<html><head><style></style></head><body></body></html>"
          html2 "<html><head><link rel='stylesheet'></head><body><img src='' alt=''><svg></svg></body></html>"
          changed-html "<html><head><style>body {background-color: #fff;color: #000;text-align: center;font-size: 24;font-family: Arial;}</style></head><body></body></html>"]
      (is (= changed-html (h/new-html html1)))
      (is (= changed-html (h/new-html html2))))))
