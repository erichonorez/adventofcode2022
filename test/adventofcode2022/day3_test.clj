(ns adventofcode2022.day3-test
  (:require [adventofcode2022.day3 :as sut]
            [clojure.test :as t]))

(t/deftest part1
  (t/testing "example should work"
    (t/is (= 157
             (sut/rucksacks-total-priorities
              ["vJrwpWtwJgWrhcsFMMfFFhFp"
               "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
               "PmmdzqPrVvPwwTWBwg"
               "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
               "ttgJtRGJQctTZtZT"
               "CrZsJsPPZsGzwwsLwLmpwMDw"])))))

(t/deftest part2
  (t/testing "example should work"
    (t/is (= 18
             (sut/groups-total-priorities
              ["vJrwpWtwJgWrhcsFMMfFFhFp"
               "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
               "PmmdzqPrVvPwwTWBwg"])))))
