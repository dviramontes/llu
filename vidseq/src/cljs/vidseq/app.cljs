(ns vidseq.app
  (:require [reagent.core :as r]
            [ajax.core :refer [GET]]
            [cljsjs.moment :as moment]))

(enable-console-print!)

(def duration (r/atom 0))

(defonce end 201)

(defonce time-color (r/atom "#f34"))

(declare time-updater!)

(defonce source-url (r/atom {:query  ""
                             :offset 0}))


(defn refresh! [query offset]
      (reset! source-url {:query  query
                          :offset offset}))

(add-watch duration :duration-watcher
           (fn [_ _ ot nt]
               ;; (str (.floor js/Math (/ t 60)) (mod t 60))
               (if-not (nil? nt)
                 (print (str (.floor js/Math (/ nt 60)) (mod nt 60))))))


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

(defn audio-comp []
      [:div.row.audio-tag
       [:div.col-lg-12
        [:audio {:src "media/needings.wav"
                 :id "audio-el"
                 :controls "controls"
                 :autoPlay false}]]])

(defn play-btn []
      [:button.btn.btn-danger
       {:on-click (fn []
                      (.play (.querySelector js/document "#audio-el"))
                      (js/setInterval #(swap! duration inc) 1000))}
       "play"])

(defn text-comp [c b]
      [:div.row.text-comp
       [:div.col-lg-12.jumbotron
        [:p.text-center
         [:strong "mdklsamd"]]
        [c {:class "align-right"}]
        [:p.pull-right
         [b]]]])

(defn main-component []
      (let []
           [:div.container-fluid
            [gif-comp]
            [audio-comp]
            [text-comp count-up play-btn]]))

(defn init []
      (r/render-component [main-component]
                          (.getElementById js/document "mount")))
