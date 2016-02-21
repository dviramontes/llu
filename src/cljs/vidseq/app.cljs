(ns vidseq.app
  (:require [reagent.core :as r]
            [ajax.core :refer [GET]]))

(enable-console-print!)

(def duration (r/atom 0))

(def source-url (r/atom ""))

(def voice-1 (r/atom "My first show show"))

(defn get-random-int [min max]
      (.floor js/Math (+ min (* (- max min) (.random js/Math)))))

(defn refresh!
      "get random robot+fail gif"
      ([]
        (GET "http://api.giphy.com/v1/gifs/random"
             {:params          {:api_key "dc6zaTOxFJmzC"
                                :tag     "robot+fail"}
              :handler         #(reset! source-url
                                        (:image_original_url (:data %)))
              :response-format :json
              :keywords?       true}))
      ([q & [offset txt]]
        (let [off (get-random-int 1 12)]
             (if txt (reset! voice-1 txt))
             (GET "http://api.giphy.com/v1/gifs/search"
                  {:params          {:q       q
                                     :api_key "dc6zaTOxFJmzC"
                                     :limit   1
                                     :offset  (or offset 0)}
                   :handler         #(reset! source-url
                                             ;; (:mp4 (:looping (:images (first (:data res))))) ;; video-loop
                                             (:url (:original (:images (first (:data %))))))
                   :response-format :json
                   :keywords?       true}))))

(add-watch duration :duration-watcher
           (fn [_ _ _ t]
               (case t
                     2 (refresh! "nyan+cat+robot")
                     4 (refresh! "funny+cat" 11 "this is the story")
                     8 (refresh! "robot+love" 5 "of a robot")
                     12 (refresh! "robot+walking" nil "who falls in love")
                     16 (refresh! "black+friday" 1 "with material needs")
                     19 (refresh! "robot+funny" 46)
                     22 (refresh! "black+friday" 12 "this song is called needings")
                     24 (refresh! "rickandmorty")
                     30 (refresh! "computer+art" 10 "by Gangol und mit")
                     32 (refresh! "computer+cat" 12)
                     34 (refresh! "robot+crying" nil "enjoy")
                     37 (refresh! "robot+fail")
                     41 (refresh! "robot+fail" 2)
                     45 (refresh! "robot+fail" 4)
                     50 (refresh! "robot+crying" 4 "")
                     52 (refresh! "cat+robot" 21)
                     62 (refresh! "funny+robot" 10)
                     66 (refresh! "house+robot")
                     68 (refresh! "dog+funny")
                     72 (refresh! "kite")
                     78 (refresh! "funny+robot" 2)
                     86 (refresh! "computer+fail" 5)        ;; upgrade
                     ;; 90 (refresh! "funny+home" 49)
                     92 (refresh! "funny+home" 49)
                     95 (refresh! "funny+dog" 3)
                     99 (refresh! "cat+robot")
                     105 (refresh! "car+robot" 5)
                     107 (refresh! "god+robot" 1)
                     108 (refresh! "job+robot")
                     113 (refresh! "computer+fail" 3)       ;; upgrade
                     118 (refresh! "help+robot" 21)         ;; help
                     126 (refresh! "robot+good" 3)
                     142 (refresh! "messy+house" 5)
                     147 (refresh! "robot+car" 6)
                     153 (refresh! "god+robot" 12)
                     156 (refresh! "computer+ram")          ;; upgrade
                     164 (refresh! "god")
                     168 (refresh! "job+robot" 12)
                     182 (refresh! "matrix+funny" 1)
                     184 (refresh! "matrix+funny" 5)
                     186 (refresh! "matrix+funny" 7)
                     200 (refresh! "matrix+funny" 3)
                     "default")))


(defn count-up []
      (let [mm (str (.floor js/Math (/ @duration 60)) ":" (mod @duration 60))]
           [:div.count-up
            [:div @duration]]))

(defn gif-comp []
      [:div.row.gifComp
       [:div.col-lg-12
        [:img {:src @source-url}]]])

(defn audio-comp []
      [:div.row.audio-tag
       [:div.col-lg-12
        [:audio {:src      "media/needings.wav"
                 :id       "audio-el"
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
      (let []
           (r/create-class
             {:component-did-mount #(refresh! "nyan+cat+robot")
              :reagent-render      (fn [] [:div.container-fluid
                                           [gif-comp]
                                           [audio-comp]
                                           [text-comp count-up play-btn]])})))

(defn init []
      (r/render-component [main-component]
                          (.getElementById js/document "mount")))
