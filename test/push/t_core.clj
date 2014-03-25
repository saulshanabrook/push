(ns push.t-core
  (:require
    [clojure.test :refer :all]
    [midje.sweet :refer :all]
    schema.macros
    [push.core :as core]))


(schema.macros/with-fn-validation
  (facts "about `push-exec`"
    (fact "passing in no arguments should return an empty exec stack"
      (core/push-exec) => {:exec (list)})

    (tabular
      (fact "items in first argument list should be added to exec stack"
        (core/push-exec ?push-list) => {:exec ?resulting-exec-stack})
      ?push-list                  ?resulting-exec-stack
      (list)                      (list)
      (list .i.)                  (list .i.)
      (list .i1. .i2.)            (list .i1. .i2.))

    (tabular
      (fact "has-map passed in as second argument should be used as starting push stacks"
        (core/push-exec ?push-list {:exec ?existing-exec-stack}) => {:exec ?resulting-exec-stack})
      ?push-list               ?existing-exec-stack       ?resulting-exec-stack
      (list)                   (list .existing-item.)    (list .existing-item.)
      (list .push-item.)       (list .existing-item.)    (list .push-item. .existing-item.))))

(schema.macros/with-fn-validation
  (facts "about `push-other`"
    (tabular
      (fact "types should create stack of that type"
        (core/push-other ?item {:exec ()}) => {:exec () (type ?item) (list ?item)})
      ?item
      "string"
      true
      1
      1.0
      'symbol)

    (fact "should add to end of stack, if it exists"
      (core/push-other 1 {:exec () (type 1) '(2)}) => {:exec () (type 1) '(1 2)})))

(schema.macros/with-fn-validation
  (facts "about `execute`"
    (fact "calls exec-item on each item in :exec stack"
      (let [fn1 #(assoc % :fn1called ())
            fn2 #(assoc % :fn2called ())
            initial-exec-stack (list fn1 fn2)
            final-stacks {:exec () :fn1called () :fn2called ()}]
        (core/execute {:exec initial-exec-stack }) => final-stacks
        (provided
          (core/execute-item fn1 {:exec (list fn2)}) => {:exec (list fn2) :fn1called ()}
          (core/execute-item fn2 {:exec () :fn1called ()}) => final-stacks)))))

(schema.macros/with-fn-validation
  (facts "about `execute-item"
    (fact "functions should be called on stack"
      (core/execute-item identity {:exec ()}) => {:exec ()})

    (tabular
      (fact "lists should be expanded and added on to the end"
        (core/execute-item ?item {:exec ()}) => {:exec ?result-exec-stack})
      ?item                 ?result-exec-stack
      (list)                (list)
      (list .i.)            (list .i.)
      (list .i1. .i2.)      (list .i1. .i2.))

    (fact "other types should be pushed to stack with the key as that type"
      (core/execute-item 1 {:exec ()}) => {:exec () (type 1) '(1)}
        (provided
          (core/push-other 1 {:exec ()}) => {:exec () (type 1) '(1)}))))

(schema.macros/with-fn-validation
  (facts "about `push"
    (fact "called with any number of args and should push and execute them all"
      (core/push 1 2) => {:exec () (type 1) (list 2 1)})))
