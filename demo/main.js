import './style.css'
import {EditorView, basicSetup, minimalSetup} from 'codemirror'
import {clojure, clojureLanguage} from './../src/clojure.ts'
import {foo} from './../public/js/demo.js'

console.log(clojure, clojureLanguage)
console.log(foo())

new EditorView({
  extensions: [basicSetup, clojure()],
  parent: document.querySelector('#app')
}).focus()
