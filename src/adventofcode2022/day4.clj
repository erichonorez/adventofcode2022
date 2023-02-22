(ns adventofcode2022.day4
  (:require [clojure.string :as str]))

(defn overlap?
  "Does ys is fully contained in xs ?"
  [reduce-f xs ys]
  (let [in? (comp not
               nil?
               (set xs))]
    (->> ys
         (map in?)
         (reduce-f))))

(defn pair-fully-overlap?
  [xs ys]
  (let [overlap-fn (partial overlap? #(every? true? %))]
    (or (overlap-fn xs ys)
        (overlap-fn ys xs))))

(def pair-partial-overlap? (partial overlap? #(true? (some true? %))))

(defn str->range
  [s]
  (let [[_ l u] (re-matches #"(\d+)-(\d+)" s)
        start (Integer/parseInt l)
        end (Integer/parseInt u)]
    (range start (inc end))))

(defn line->range
  [l]
  (->> (str/split l #",")
       (map str->range)))

(defn part1
  []
  (->> (slurp "resources/day4/part1.txt")
       str/split-lines
       (map line->range)
       (map #(apply pair-fully-overlap? %))
       (map {true 1
             false 0})
       (reduce +)))

(comment
  (part1))

(defn part2
  []
  (->> (slurp "resources/day4/part1.txt")
       str/split-lines
       (map line->range)
       (map #(apply pair-partial-overlap? %))
       (map {true  1
             false 0})
       (reduce +)))

(comment
  (part2))
