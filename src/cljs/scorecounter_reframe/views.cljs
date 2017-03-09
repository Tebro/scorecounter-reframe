(ns scorecounter-reframe.views
    (:require [re-frame.core :as re-frame]))


;; Setup


(defn add-player-form []
  (let [val (reagent.core/atom "")
        save #(do
                (re-frame/dispatch [:add-player @val])
                (reset! val ""))]
    (fn []
      [:h4 "Add player"]
      [:input {:type "text"
               :value @val
               :on-change #(reset! val (-> % .-target .-value))
               :on-key-down #(case (.-which %)
                               13 (save)
                               nil)}])))
    

(defn setup-panel []
  (let [players (re-frame/subscribe [:players])]
    (fn []
      [:div
       [:h4 "Added players"]
       [:ul
        (for [player @players]
          ^{:key (first player)} [:li (get-in player [1 :name])])]
       [add-player-form]
       [:div [:a {:href "#/game"} "Start Game"]]])))


;; Game

(defn player-li [player-id]
  (fn []
    [:div
     [:span.name @(re-frame/subscribe [:player-name player-id])]
     [:span.score @(re-frame/subscribe [:player-score player-id])]
     [:button {:on-click #(re-frame/dispatch [:add-score  player-id])} "+"]]))


(defn game-panel []
  (let [players (re-frame/subscribe [:players])]
    (fn []
      [:div
       (for [player @players]
         ^{:key (first player)} [player-li (first player)])])))


;; main

(defn- panels [panel-name]
  (case panel-name
    :setup-panel [setup-panel]
    :game-panel [game-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
