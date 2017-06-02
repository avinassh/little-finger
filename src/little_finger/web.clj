(ns little-finger.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [ring.adapter.jetty :as jetty]
            [clojure.data.json :as json]
            [environ.core :refer [env]]))

(defn get-json-response []
    (json/write-str {:NotificationTitle (env :notification-title)
                     :NotificationText (env :notification-text)}))

(defn index []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "The Little Finger is up"})

(defn status-handler []
  {:status (Integer. (env :response-status))
   :headers {"Content-Type" "application/json"}
   :body (get-json-response)})

(defroutes app
  (GET "/" []
       (index))
  (GET "/status" []
       (status-handler))
  (ANY "*" []
       {:status 404}))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
