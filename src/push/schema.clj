(ns push.schema
  (require
   [schema.core :as s]
   [push.utils :refer [not-nil?]]))

(def List
  "A Clojure list."
  (s/pred list? 'list?))

(def NotNil
  "Anything but `nil`"
  (s/pred not-nil? 'not-nil?))

(def PushState
  ":exec must map to a list, and any other keys must map to lists as well"
  {(s/required-key :exec) List s/Any List})
