(ns scorecounter-reframe.events
    (:require [re-frame.core :as re-frame]
              [scorecounter-reframe.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))


(re-frame/reg-event-db
 :add-player
 (fn [db [_ name]]
   (let [new-player {:name name :score 0 }
         players (:players db)
         id (db/next-id players)
         new-players (assoc players id new-player)]
   (assoc db :players new-players))))


(re-frame/reg-event-db
 :add-score
 (fn [db [_ player-id]]
   (update-in db [:players player-id :score] inc)))
 
         
