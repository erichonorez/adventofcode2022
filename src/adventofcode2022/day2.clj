(ns adventofcode2022.day2
  [:require 
   [clojure.java.io :as io]
   [clojure.string :as str]])

(defn round-score
  [a b]
  (case [a b]
    [:rock :paper] 6
    [:paper :scissor] 6
    [:scissor :rock] 6
    [:scissor :scissor] 3
    [:paper :paper] 3
    [:rock :rock] 3
    0))

(def shape-score {:rock 1
                  :paper 2
                  :scissor 3})

(defn mapping
  [x]
  (case x
    (:a :x) :rock
    (:b :y) :paper
    (:c :z) :scissor))

(defn decrypt
  [x]
  (->> x
       str/lower-case
       keyword
       (mapping)))

(defn round
  [opponent me]
  (+ (round-score opponent me)
     (get shape-score me)))

(defn total-score
  [strategies]
  (->> strategies
       (map (fn [[x y]] (round x y)))
       (reduce +)))

(defn str->strategy
  [line]
  (->> line 
       (#(str/split % #" "))
       (map decrypt)))

(defn run
  []
  (->> (slurp (io/resource "day2/dataset.txt"))
       str/split-lines
       (map str->strategy)
       total-score))

(run)
