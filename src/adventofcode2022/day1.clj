(ns adventofcode2022.day1
  [:require
   [clojure.java.io :as io]
   [clojure.string :as str]])

(defn sorted-sums [dataset]
  (->> dataset
       (map #(reduce + %))
       
       (#(for [x (range (count dataset))]
           [(inc x) (nth % x)]))
       
       (#(sort-by
          (fn [arr]
            (-> arr
                second
                (* -1))) %))))

(defn who-is-carrying-the-most? 
  [dataset]
  (first (sorted-sums dataset)))

(defn how-many-calories-carried-by-top-3?
  [dataset]
  (->> dataset
       sorted-sums
       (take 3)
       (map second)
       (reduce +)))

(defn lines->elves
  [lines]
  (loop [current-line (first lines)
         remaining-lines (rest lines)
         current-elf []
         elves []]
    (cond
      (nil? current-line) (conj elves current-elf)
      (empty? current-line) (recur
                             (first remaining-lines)
                             (rest remaining-lines)
                             []
                             (conj elves current-elf))
      :else (recur
             (first remaining-lines)
             (rest remaining-lines)
             (conj current-elf (Integer/parseInt current-line))
             elves))))

(defn run-part1 
  []
  (-> (slurp (io/resource "day1/dataset.txt"))
      str/split-lines
      lines->elves
      who-is-carrying-the-most?))

(comment
  (run-part1))

(defn run-part2
  []
  (-> (slurp (io/resource "day1/dataset.txt"))
      str/split-lines
      lines->elves
      how-many-calories-carried-by-top-3?))

(comment 
  (run-part2))