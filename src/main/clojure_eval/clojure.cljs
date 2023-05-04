(ns clojure-eval.clojure
  (:require
   ["@codemirror/history" :refer [history historyKeymap]]
   ["@codemirror/state" :refer [EditorState]]
   ["@codemirror/view" :as view]
   ["@lezer/highlight" :as highlight :refer [tags]]
   ["@codemirror/language" :as language :refer [foldGutter syntaxHighlighting defaultHighlightStyle LRLanguage LanguageSupport]]
   ["@nextjournal/lezer-clojure" :as lezer-clj]
   [applied-science.js-interop :as j]
   [clojure-eval.extensions.close-brackets :as close-brackets]
   [clojure-eval.extensions.match-brackets :as match-brackets]
   [clojure-eval.extensions.formatting :as format]
   [clojure-eval.extensions.sci :as sci]
   [clojure-eval.extensions.selection-history :as sel-history]
   [clojure-eval.extensions.eval-region :as eval-region]
   [clojure-eval.keymap :as keymap]
   [clojure-eval.node :as n]))

(defn clojure []
  (js/console.log "hello world from CLJS")
  true)

(defn bar [] "bar")