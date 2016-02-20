(ns vidseq.app
  (:require [reagent.core :as r]
            [ajax.core :refer [GET]]))

(enable-console-print!)

(def duration (r/atom 0))

(def query (r/atom {:query  ""
                    :offset 0}))

(def source-url (r/atom ""))

(def voice-1 (r/atom "My first show show"))

(defn get-random-int [min max]
      (.floor js/Math (+ min (* (- max min) (.random js/Math)))))


(defn refresh! [q & [offset txt]]
      ;; http://api.giphy.com/v1/gifs/search?q=cat+funny&api_key=dc6zaTOxFJmzC&limit=1&offset=2
      (let [off (get-random-int 1 12)]
           (if txt (reset! voice-1 txt))
           (GET "http://api.giphy.com/v1/gifs/search"
                {:params          {:q       q
                                   :api_key "dc6zaTOxFJmzC"
                                   :limit   1
                                   :offset  (or offset 0)}
                 :handler         (fn [res]
                                      (reset! source-url
                                              (:mp4 (:looping (:images (first (:data res)))))))
                 :response-format :json
                 :keywords?       true})))

(add-watch duration :duration-watcher
           (fn [_ _ _ t]
               (case t
                     2 (refresh! "nyan+cat+robot")
                     4 (refresh! "funny+cat" 11 "this is the story")
                     8 (refresh! "robot+love" 5 "of a robot")
                     12 (refresh! "robot+walking" nil "who falls in love")
                     16 (refresh! "black+friday" 1 "with material needs")
                     19 (refresh! "black+friday" 11)
                     22 (refresh! "black+friday" 12 "this song is called needings")
                     24 (refresh! "rickandmorty")
                     30 (refresh! "computer+art" 10 "by Gangol und mit")
                     32 (refresh! "computer+cat" 12)
                     34 (refresh! "robot+crying" nil "enjoy")
                     50 (refresh! "robot+crying" 4 "")
                     62 (refresh! "funny+robot" 10)
                     66 (refresh! "house+robot")
                     68 (refresh! "dog+funny")
                     72 (refresh! "kite")
                     78 (refresh! "funny+robot" 2)
                     86 (refresh! "computer+fail" 5)        ;; upgrade
                     95 (refresh! "funny+dog" 3)
                     99 (refresh! "cat+robot")
                     113 (refresh! "computer+fail" 3)       ;; upgrade
                     118 (refresh! "robot+home" 2)          ;; help
                     126 (refresh! "robot+good" 3)
                     156 (refresh! "comgputer+ram")         ;; upgrade
                     163 (refresh! "computer+ram")          ;; home
                     182 (refresh! "matrix+funny" 1)
                     200 (refresh! "matrix+funny" 2)
                     "default")))


(defn count-up []
      (let [mm (str (.floor js/Math (/ @duration 60)) ":" (mod @duration 60))]
           [:div.count-up
            [:div.clock @duration]]))

(defn gif-comp []
      [:div.row.gifComp
       [:div.col-lg-12
        [:video {:autoPlay true
                 :loop     true
                 :src      @source-url}]]])

(defn audio-comp []
      [:div.row.audio-tag
       [:div.col-lg-12
        [:audio {:src      "media/needings.wav"
                 :id       "audio-el"
                 ;; :controls "controls"
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
        [:p.text-center.voices
         [:em @voice-1]]
        [c {:class "align-right"}]
        [:p.pull-right
         [b]]]])

(defn main-component []
      (let [_ (refresh! "nyan+cat+robot")]
           (r/create-class
             {:component-did-mount
              (fn [])
              :reagent-render
              (fn []
                  [:div.container-fluid
                   [gif-comp]
                   [audio-comp]
                   [text-comp count-up play-btn]])})))

(defn init []
      (r/render-component [main-component]
                          (.getElementById js/document "mount")))
