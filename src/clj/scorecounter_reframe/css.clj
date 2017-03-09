(ns scorecounter-reframe.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:color "black"}
   [:span {:margin-right "5px"
           :float "left"
           :display "block"}]]
)
