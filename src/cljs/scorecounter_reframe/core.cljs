(ns scorecounter-reframe.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [scorecounter-reframe.events]
              [scorecounter-reframe.subs]
              [scorecounter-reframe.routes :as routes]
              [scorecounter-reframe.views :as views]
              [scorecounter-reframe.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
