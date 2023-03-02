(ns adventofcode2022.day5
  (:require [clojure.string :as str]))

(defn move
  "Move one crate from a to b"
  ([coll a b]
   (let [a' (dec a)
         b' (dec b)
         from  (nth coll a')
         to    (nth coll b')
         from' (pop from)
         to'   (conj to (peek from))]
     (-> (assoc coll a' from')
         (assoc b' to'))))
  ([coll a b n]
   (if (= 0 n)
     coll
     (move (move coll a b) a b (dec n)))))

(defn move'
  "Move one crate from a to b"
  ([coll a b n]
   (let [a'    (dec a)
         b'    (dec b)
         from  (nth coll a')
         to    (nth coll b')
         from' (vec (drop-last n from))
         to'   (into to (vec (take-last n from)))]
     (-> (assoc coll a' from')
         (assoc b' to')))))

(defn apply-moves
  [move-fn stacks moves]
  (loop [stacks stacks
         [current-move & rest] moves]
    (if (nil? current-move)
      stacks
      (recur (apply move-fn stacks current-move) rest))))

(defn top-crates
  [coll]
  (map peek coll))

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
       (#(take width (concat % (repeat '()))))
       (map vec)))

(row->coll "[Z] [M] [P]" 3)

(defn rows->stacks
  [coll width]
  (->> (map #(row->coll % width) coll)
       (apply map vector)
       (map flatten)
       (map reverse)
       (map vec)
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

(defn part1
  [filename]
  (let [file                (slurp filename)
        [fst-part snd-part] (str/split file #"\n\n")
        stacks              (parse-stacks fst-part)
        moves               (parse-moves snd-part)]
    (->> (apply-moves move stacks moves)
         top-crates
          (apply str))))

;; VRWBSFZWM
(part1 "resources/day5/part1.txt")

(defn part2
  [filename]
  (let [file                (slurp filename)
        [fst-part snd-part] (str/split file #"\n\n")
        stacks              (parse-stacks fst-part)
        moves               (parse-moves snd-part)]
    (->> (apply-moves move' stacks moves)
         top-crates
         (apply str))))

(part2 "resources/day5/part1.txt")
