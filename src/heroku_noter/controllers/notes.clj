(ns heroku-noter.controllers.notes
  (:use [compojure.core :only (defroutes GET POST)])
  (:require [clojure.string :as str]
            [ring.util.response :as ring]
            [heroku-noter.views.notes :as view]
            [heroku-noter.models.note :as model]))

(defn index []
  (view/index (model/all)))

(defn create
  [note]
  (when-not (str/blank? note)
    (model/create note))
  (ring/redirect "/"))

(defroutes routes
           (GET "/" [] (index))
           (POST "/" [note] (create note)))