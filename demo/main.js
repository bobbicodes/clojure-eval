import './style.css'
import {EditorView, basicSetup, minimalSetup} from 'codemirror'
import {c, cLanguage} from './../src/clojure.ts'
import {clojure} from './../public/js/clojure.js'

console.log(c, cLanguage)
console.log(clojure())

new EditorView({
  extensions: [basicSetup, c()],
  parent: document.querySelector('#app')
}).focus()
