(ns adventofcode2022.day1
  [:require
   [clojure.java.io :as io]
   [clojure.string :as str]])

(defn who-is-carrying-the-most? 
  [dataset]
  (->> dataset
       (map #(reduce + %))
       
       (#(for [x (range (count dataset))]
           [(inc x) (nth % x)]))
       
       (#(sort-by
          (fn [arr]
            (-> arr
                second
                (* -1))) %))
       
       first))

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

(defn run 
  []
  (-> (slurp (io/resource "day1/dataset.txt"))
      str/split-lines
      lines->elves
      who-is-carrying-the-most?))

(comment
  (run))