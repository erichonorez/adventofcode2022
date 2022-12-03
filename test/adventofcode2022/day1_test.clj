(ns adventofcode2022.day1-test
  [:require
   [adventofcode2022.day1 :refer [who-is-carrying-the-most?
                                  lines->elves]]
   [clojure.test :refer [deftest testing is]]])

(def dataset [[1000 2000 3000]
              [4000]
              [5000 6000]
              [7000 8000 9000]
              [10000]])

(deftest day1-tests
  (testing "Who is carrying the most?"
    (is (= (who-is-carrying-the-most? dataset) [4 24000])))

  (testing "Parsing lines to an array of elves"
    (is (= (lines->elves ["2000" "1000" "" "500"])
           [[2000 1000] [500]])))) 

