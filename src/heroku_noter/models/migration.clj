(ns heroku-noter.models.migration
  (:require [clojure.java.jdbc :as sql]
            [heroku-noter.models.note :as note]))

(defn migrated? []
  (-> (sql/query note/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='notes'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands note/spec
                        (sql/create-table-ddl
                          :notes
                          [:id :serial "PRIMARY KEY"]
                          [:body :varchar "NOT NULL"]
                          [:create_at :timestamp
                           "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (println " done")))

