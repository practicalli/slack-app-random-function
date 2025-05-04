;; ---------------------------------------------------------
;; practicalli.random-function-slack-app.-test
;;
;; Example unit tests for practicalli.random-function-slack-app
;;
;; - `deftest` - test a specific function
;; - `testing` logically group assertions within a function test
;; - `is` assertion:  expected value then function call
;; ---------------------------------------------------------


(ns practicalli.random-function-slack-app-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [practicalli.random-function-slack-app :as random-function-slack-app]))


(deftest application-test
  (testing "TODO: Start with a failing test, make it pass, then refactor"

    ;; TODO: fix greet function to pass test
    (is (= "practicalli application developed by the secret engineering team"
           (random-function-slack-app/greet)))

    ;; TODO: fix test by calling greet with {:team-name "Practicalli Engineering"}
    (is (= (random-function-slack-app/greet "Practicalli Engineering")
           "practicalli service developed by the Practicalli Engineering team"))))
