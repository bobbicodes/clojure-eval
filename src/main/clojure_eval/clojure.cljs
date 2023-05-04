(ns clojure-eval.clojure
  (:require
   [codemirror :refer [basicSetup]]
   ["@codemirror/history" :refer [history historyKeymap]]
   ["@codemirror/state" :refer [EditorState]]
   ["@codemirror/view" :as view :refer [EditorView]]
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

(def fold-node-props
  (let [coll-span (fn [^js tree] #js{:from (inc (n/start tree))
                                     :to (dec (n/end tree))})]
    (j/lit
     {:Vector coll-span
      :Map coll-span
      :List coll-span})))

(def style-tags
  (clj->js {:NS (.-keyword tags)
            :DefLike (.-keyword tags)
            "Operator/Symbol" (.-keyword tags)
            "VarName/Symbol" (.definition tags (.-variableName tags))
            :Boolean (.-atom tags)
            "DocString/..." (.-emphasis tags)
            :Discard! (.-comment tags)
            :Number (.-number tags)
            :StringContent (.-string tags)
            ;; need to pass something, that returns " when being parsed as JSON
            ;; also #js doesn't treat this correctly, hence clj->js above
            "\"\\\"\"" (.-string tags)
            :Keyword (.-atom tags)
            :Nil (.-null tags)
            :LineComment (.-lineComment tags)
            :RegExp (.-regexp tags)}))

(defn syntax [^js parser]
  (.define LRLanguage
           #js {:parser (.configure parser #js {:props #js [format/props
                                                            (.add language/foldNodeProp fold-node-props)
                                                            (highlight/styleTags style-tags)]})}))

(def ^js/Array complete-keymap keymap/complete)

(def default-extensions
  #js[(close-brackets/extension)
      (match-brackets/extension)
      (sel-history/extension)
      (format/ext-format-changed-lines)
      (eval-region/extension {:modifier "Alt"})])

(def extensions
  #js
   [;(history)
    ;(syntaxHighlighting defaultHighlightStyle)
    ;(view/drawSelection)
    ;(foldGutter)
    ;(.. EditorState -allowMultipleSelections (of true))
    ;default-extensions
    ;(.of view/keymap complete-keymap)
    ;(.of view/keymap historyKeymap)
    sci/extension
    ])

(def clojureLanguage (syntax lezer-clj/parser))

(defn clojure []
  (LanguageSupport. clojureLanguage))
