(ns heroku-noter.models.note
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/something"))

(defn all []
  (into [] (sql/query spec ["select * from notes order by id desc"])))

(defn create [note]
  (sql/insert! spec :notes [:body] [note]))

