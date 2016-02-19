(ns vidseq.app
  (:require [reagent.core :as reagent :refer [atom]]))

(defn gif-comp []
      [:div.row.gifComp
       [:div.col-lg-12
        [:video {:autoPlay true
                 :loop     true
                 :src      "http://media.giphy.com/media/PhmvybE2T8Iww/giphy-loop.mp4"}]]])

(defn text-comp []
      [:div.row
       [:div.col-lg-12.jumbotron
        [:p.text-center
         [:strong "I need a wife"]]]])

(defn main-component []
      [:div.container-fluid
       [text-comp]
       [gif-comp]])

(defn init []
      (reagent/render-component [main-component]
                                (.getElementById js/document "mount")))
