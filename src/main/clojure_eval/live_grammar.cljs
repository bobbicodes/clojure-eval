(ns clojure-eval.live-grammar
  (:require ["lezer-generator" :as lg]
            [shadow.resource :as rc]
            [clojure-eval.node :as n]))

;;for dev, it's useful to build the parser in the browser
(def parser
  (lg/buildParser
   (rc/inline "./clojure.grammar")
   #js{:externalProp n/node-prop}))

