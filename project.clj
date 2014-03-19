(defproject push "0.0.1-SNAPSHOT"
  :description "Cool new project to do things and stuff"
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :min-lein-version "2.0.0"
  :profiles {:test {:plugins [[jonase/eastwood "0.1.0"]
                              [lein-cloverage "1.0.2"]]
                    :dependencies [[midje "1.5.0"]]}})
