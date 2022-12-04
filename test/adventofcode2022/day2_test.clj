(ns adventofcode2022.day2-test
  [:require
   [clojure.test :refer [deftest testing is]]
   [adventofcode2022.day2 :refer [total-score]]])

(def strategies [[1 2]
                 [2 1]
                 [3 3]])

(deftest day2-test
  (testing "total-score"
    (is (= (total-score strategies)
           15))))