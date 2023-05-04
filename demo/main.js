import './style.css'
import {EditorView, basicSetup} from 'codemirror'
import {keymap} from '@codemirror/view'
import {clojure, clojureLanguage, clojureEval} from './../public/js/clojure.js'

new EditorView({
    extensions: [basicSetup, keymap.of(clojureEval())],
    parent: document.querySelector('#app')
  }).focus()