(defproject little-finger "0.1.0-SNAPSHOT"
  :description "The server for Little Finger app"
  :url "http://avi.im/little-finger"
  :license {:name "MIT License"
            :url "https://v.mit-license.org/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.6.1"]
                 [ring/ring-jetty-adapter "1.6.1"]
                 [compojure "1.6.0"]
                 [org.clojure/data.json "0.2.6"]
                 [environ "1.1.0"]]
  :min-lein-version "2.0.0"
  :uberjar-name "little-finger-standalone.jar"
  :profiles {:production {:env {:production true}}})
