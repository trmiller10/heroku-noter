(ns heroku-noter.views.notes
  (:use [hiccup.core :only (h)]
        [hiccup.form :only (form-to label text-area submit-button)])
  (:require [heroku-noter.views.layout :as layout]))

(defn note-form []
  [:div {:id "note-form" :class "sixteen columns alpha omega"}
   (form-to [:post "/"]
            (label "note" "Enter your note here.")
            (text-area "note")
            (submit-button "Enter"))])

(defn display-notes [notes]
  [:div {:class "notes sixteen columns alpha omega"}
   (map
     (fn [note] [:h2 {:class "note"} (h (:body note))])
     notes)])

(defn index [notes]
  (layout/common "NOTER"
                 (note-form)
                 [:div {:class "clear"}]
                 (display-notes notes)))


