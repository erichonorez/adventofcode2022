(ns adventofcode2022.day5
  (:require [clojure.string :as str]))

(defn move''
  "Move x elements from arr at idx a - 1 to arr at idx b - 1, one a time or all in bulk"
  [coll mode a b x]
  (let [a' (dec a)
        b' (dec b)
        els (cond-> (take x (get coll a'))
              (= :bulk mode) reverse)]
    (-> (update coll b' (fn [xs] (apply conj xs els)))
        (update a' (fn [xs] (drop x xs))))))

(defn apply-moves
  [stacks moves mode]
  (loop [stacks stacks
         [current-move & rest] moves]
    (if (nil? current-move)
      stacks
      (recur (apply move'' stacks mode current-move) rest))))

(defn top-crates
  [coll]
  (map first coll))

(defn- letter?
  []
  #(and (<= 65 (int %))
        (>= 90 (int %))))

(defn- row->coll
  [row width]
  (->> (partition 4 4 [\space] row)
       (map (fn [coll]
              (filter (letter?)
                      coll)))
       (#(take width (concat % (repeat '()))))))

(defn rows->stacks
  [coll width]
  (->> (map #(row->coll % width) coll)
       (apply map vector)
       (map flatten)
       (map reverse)
       vec))

(defn parse-stacks
  [a]
  (let [lines (str/split-lines a)
        stacks (take (dec (count lines)) lines)
        width (->> (nth lines (dec (count lines)))
                   (#(str/replace % #" " ""))
                   count)]
    (rows->stacks stacks width)))

(defn s->move
  [s]
  (let [[_ cnt from to] (re-matches #"move (\d+) from (\d+) to (\d+)" s)]
    [(Integer/parseInt from)
     (Integer/parseInt to)
     (Integer/parseInt cnt)]))

(defn parse-moves
  [s]
  (->> (str/split-lines s)
       (map s->move)))

(defn run
  [filename mode]
  (let [file                (slurp filename)
        [fst-part snd-part] (str/split file #"\n\n")
        stacks              (parse-stacks fst-part)
        moves               (parse-moves snd-part)]
    (->> (apply-moves stacks moves mode)
         top-crates
         (apply str))))

;; VRWBSFZWM
(run "resources/day5/part1.txt" :aot)
(run "resources/day5/part1.txt" :bulk)
