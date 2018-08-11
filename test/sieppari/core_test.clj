(ns sieppari.core-test
  (:require [clojure.test :refer :all]
            [testit.core :refer :all]
            [sieppari.core :refer :all])
  (:import (sieppari.core Interceptor)))

(deftest -interceptor-test
  (fact "result is  record"
    (-interceptor {}) => Interceptor)

  (fact "defaults are applied"
    (-interceptor {}) => {:name nil
                          :enter fn?
                          :leave fn?
                          :error fn?})

  (fact "functions can be made to interceptors"
    (-interceptor str) => {:enter fn?})

  (fact "functions are treated as request handlers"
    (-> inc -interceptor :enter (apply [{:request 41}])) => {:response 42})

  (let [i (-interceptor {})]
    (fact "interceptors are already interceptors"
      (-interceptor i) => (partial identical? i)))

  (fact "nil punning"
    (-interceptor nil) => nil))
