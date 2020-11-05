(defproject reader "0.1.0-SNAPSHOT"
  :description "Display webpages in reader mode"
  :url "https://github.com/nilenso/reader"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring "1.8.2"]
                 [http-kit "2.5.0"]
                 [hickory "0.7.1"]
                 [com.taoensso/carmine "3.0.1"]]
  :plugins [[lein-cljfmt "0.7.0"]]
  :main reader.core
  :repl-options {:init-ns reader.core})
