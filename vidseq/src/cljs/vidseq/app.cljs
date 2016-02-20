(ns vidseq.app
  (:require [reagent.core :as r]
            [ajax.core :refer [GET]]
            [cljsjs.moment :as moment]))

(enable-console-print!)

(defonce duration (r/atom 0))

(defonce end 201)

(defonce time-color (r/atom "#f34"))

(defonce time-updater! (js/setInterval #(swap! duration inc) 1000))

(defonce source-url (r/atom {:query  ""
                             :offset 0}))

(defn refresh! [query offset]
      (reset! source-url {:query  query
                          :offset offset}))

(add-watch duration :duration-watcher
           (fn [_ _ _ t]
               ;; (str (.floor js/Math (/ t 60)) (mod t 60))
               (print t)))

(defn count-up []
      (let [mm (str (.floor js/Math (/ @duration 60)) ":" (mod @duration 60))]
           [:div.count-up
            {:style {:color @time-color}}
            [:div.clock mm]]))

(defn gif-comp []
      [:div.row.gifComp
       [:div.col-lg-12
        [:video {:autoPlay true
                 :loop     true
                 :src      @source-url}]]])

(defn text-comp [c]
      [:div.row.text-comp
       [:div.col-lg-12.jumbotron
        [:p.text-center
         [:strong "mdklsamd"]]
        [c {:class "align-right"}]]])

(defn main-component []
      [:div.container-fluid
       [gif-comp]
       [text-comp count-up]])

(defn init []
      (r/render-component [main-component]
                          (.getElementById js/document "mount")))
