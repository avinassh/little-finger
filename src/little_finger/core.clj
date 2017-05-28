(ns little-finger.core
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [ring.adapter.jetty :as jetty]))

(defn index []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "The Little Finger is up"})

(defn status-handler []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Kill the app"})

(defroutes app
  (GET "/" []
       (index))
  (GET "/status" []
       (status-handler)))

(defn -main [& [port]]
  (let [port (Integer. 5000)]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
