(defproject push "0.0.1-SNAPSHOT"
  :description "Cool new project to do things and stuff"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [prismatic/schema "0.2.1"]
                 [com.taoensso/timbre "3.1.6"]]
  :min-lein-version "2.0.0"
  :eastwood {:exclude-namespaces [:test-paths]}
  :profiles {:test {:plugins [[jonase/eastwood "0.1.0"]
                              [lein-cloverage "1.0.2"]
                              [lein-midje "3.1.1"]]
                    :dependencies [[midje "1.5.0"]]}})
