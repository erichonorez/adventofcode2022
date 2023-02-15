(ns adventofcode2022.day3
  [:require [clojure.set :refer [intersection]]]
  (:require [clojure.string :as str]))

(defn- upper-case?
  [i]
  (and (>= i 65)
       (<= i 90)))

(defn- lower-case?
  [i]
  (and (>= i 90)
       (<= i 122)))

(defn- priority
  [c]
  (let [i (int c)]
    (cond
      (upper-case? i) (+ (- i 64) 26)
      (lower-case? i) (- i 96))))

(defn- by-compartments
  [items]
  (split-at (/ (count items) 2) items))

(defn- common-items
  [xs]
  (-> (map set xs)
      (#(apply intersection %))))

(defn- rucksack-total-priority
  [items]
  (->> items
       by-compartments
       common-items
       (map priority)
       (reduce +)))

(defn rucksacks-total-priorities
  [rucksacks]
  (->> rucksacks
       (map rucksack-total-priority)
       (reduce +)))

(defn part1
  []
  (-> (slurp "resources/day3/part1.txt")
      str/split-lines
      rucksacks-total-priorities))

(comment
  (part1))

(defn group-total-priority
  [rucksacks]
  (->> (common-items rucksacks)
       (map priority)
       (reduce +)))

(defn groups-total-priorities
  [rucksacks]
  (->> (partition 3 rucksacks)
       (map group-total-priority)
       (reduce +)))

(defn part2
  []
  (-> (slurp "resources/day3/part1.txt")
      str/split-lines
      groups-total-priorities))

(comment
  (part2))
