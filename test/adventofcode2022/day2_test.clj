(ns adventofcode2022.day2-test
  [:require
   [clojure.test :refer [deftest testing is]]
   [adventofcode2022.day2 :refer [run-all-rounds]]])

(deftest day2-tests
  (testing "example"
    (is (= (run-all-rounds [[:A :Y]
                            [:B :X]
                            [:C :Z]])
           15))))
