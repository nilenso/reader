(ns reader.test-utils)

(defmacro with-fixture
  [fixture & body]
  `(~fixture
    (fn []
      ~@body)))
