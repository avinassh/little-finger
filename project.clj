(defproject little-finger "0.1.0-SNAPSHOT"
  :description "The server for Little Finger app"
  :url "http://avi.im/little-finger"
  :license {:name "MIT License"
            :url "https://v.mit-license.org/"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot little-finger.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
