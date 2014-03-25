(ns push.core
  (:require
     [clojure.test :refer [function?]]
     [taoensso.timbre :as timbre]
     [schema.core :as s]
     [push.schema :refer [PushState List]]))

(s/defn push-exec :- PushState
  "Push any number of items onto the :exec stack and return the push-state."
  ([]
   (push-exec '()))
  ([items :- List]
   (push-exec items {:exec '()}))
  ([items :- List, state :- PushState]
     (let [exec-stack (state :exec)
           new-exec-stack (apply list (concat items exec-stack))]
         (assoc state :exec new-exec-stack))))

(s/defn push-other :- PushState
  "Push a item onto the stack for its type and return the push-state."
  [item :- s/Any, state :- PushState]
     (let [stack-name (type item)]
         (update-in state [stack-name] #(conj % item))))

(s/defn execute-item :- PushState
  "Executes the item passed in on the push push-state.
  func -> call it with stack as argument and return new stack
  list -> push each item back onto stack, in reverse order
  anything else -> push onto stack of that type"
  [item :- s/Any, state :- PushState]
  (cond
    (function? item) (item state)
    (list? item) (push-exec item state)
    :else (push-other item state)))

(s/defn execute :- PushState
  "Pops off each member of the :exec stack, in order and calls `execute-item` on it"
  [initial-state :- PushState]
  (loop [state initial-state]
    (timbre/trace state)
    (let [next-exec-item (peek (state :exec))]
      (if-not next-exec-item
        state
        (recur (execute-item next-exec-item (update-in state [:exec] rest)))))))

(s/defn push :- PushState
  "Push any number of items and execute them, returning the state"
  [& args]
  (execute (push-exec (apply list args))))
