(ns scorecounter-reframe.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :players
 (fn [db]
   (:players db)))

(re-frame/reg-sub
 :player-score
 (fn [db [_ player-id]]
   (get-in db [:players player-id :score])))

(re-frame/reg-sub
 :player-name
 (fn [db [_ player-id]]
   (get-in db [:players player-id :name])))


(re-frame/reg-sub
 :active-panel
 (fn [db _]
   (:active-panel db)))
