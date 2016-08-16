(ns heroku-noter.core
  (:use [compojure.core :only (defroutes)]
        [ring.adapter.jetty :as ring])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [heroku-noter.controllers.notes :as notes]
            [heroku-noter.views.layout :as layout]
            [heroku-noter.models.migration :as schema])
  (:gen-class))

(defroutes routes
           notes/routes
           (route/resources "/")
           (route/not-found (layout/four-oh-four)))

(def application (handler/site routes))

(defn start [port]
  (run-jetty application {:port port
                          :join? false}))

(defn -main []
  (schema/migrate)
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    (start port)))