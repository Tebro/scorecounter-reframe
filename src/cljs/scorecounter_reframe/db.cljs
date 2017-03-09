(ns scorecounter-reframe.db)


;; A Player
;; {:name "name" :score 0}


(def default-db
  {:players (sorted-map)})


(defn next-id [players]
  (+ 1 (count players)))
