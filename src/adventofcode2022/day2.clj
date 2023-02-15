(ns adventofcode2022.day2
  [:require
   [clojure.java.io :as io]
   [clojure.string :as str]])

;; PART 1

(defn decrypt
  [x]
  (condp some [x]
    #{:A :X} :rock
    #{:B :Y} :paper
    #{:C :Z} :scissor))

(defn round
  [opponent me]
  (case [opponent me]
    [:rock :scissor] :lose
    [:scissor :paper] :lose
    [:paper :rock] :lose
    (if (= opponent me)
      :draw
      :win)))

(def outcome-score
  {:win 6
   :draw 3
   :lose 0})

(def shape-score
  {:rock 1
   :paper 2
   :scissor 3})

(defn round-score
  [shape outcome]
  (+ (shape shape-score)
     (outcome outcome-score)))

(defn run-round
  [opponent me]
  (->> (round opponent me)
       (#(round-score me %))))

(defn run-all-rounds
  [strategies]
  (->> strategies
       (map #(apply run-round %))
       (reduce +)))

(defn line->strategy
  [line]
  (->> line
       (#(str/split % #" "))
       (map keyword)
       (map decrypt)))

(defn day2-part1
  []
  (->> (slurp "resources/day2/dataset.txt")
       str/split-lines
       (map line->strategy)
       run-all-rounds))

(comment (day2-part1))

;; PART 2

(defn shape-to-play
  [outcome opponent-shape]
  (case [outcome opponent-shape]
    [:win :scissor] :rock
    [:win :rock] :paper
    [:win :paper] :scissor
    [:lose :scissor] :paper
    [:lose :rock] :scissor
    [:lose :paper] :rock
    opponent-shape))

(def str->outcome
  {"X" :lose
   "Y" :draw
   "Z" :win})

(defn line->alt-strategy
  [s]
  (let [line (str/split s #" ")
        opponent-share (decrypt (keyword (first line)))]
    [opponent-share
     (shape-to-play (get str->outcome (second line))
                    opponent-share)]))

(defn day2-part2
  []
  (->> (slurp "resources/day2/dataset.txt")
       str/split-lines
       (map line->alt-strategy)
       run-all-rounds))

(day2-part2)
