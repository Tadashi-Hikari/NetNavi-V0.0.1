(ns netnavi.plugins.features
  (:require [netnavi.util :as util] 
            [netnavi.plugins.gpt :as gpt])
  (:import [netnavi.assist Assistant]))

;(let [result (clojure.java.shell/sh "firefox")])

(defn clear-terminal
  "A simple expression to clear a bash shell"
  []
  (print "\u001b[H\u001b[2J"))

(defn init!
  "reset the assistant back to default by mutating the record"
  []
  (swap! (:running-log netnavi.plugins.gpt/assistant) (constantly netnavi.plugins.gpt/empty-chat))
  (clear-terminal)
  (println (format "%sReinitialized%s" util/RED util/RESET)))

(defn init
  "rest the assistant back to default by assigning a new value"
  []
  (def assistant (Assistant. (atom gpt/empty-chat)))
  (clear-terminal)
  (println (format "%sReinitialized%s" util/RED util/RESET)))

(defn strike-last-input!
  "This form removes the last prompt/response pair"
  []
  (if (< (count @(:running-log gpt/assistant)) 2)
    (println "Nothing to do!")
    (swap! (:running-log gpt/assistant) #(subvec % 0 (- (count %) 2)))))
    
(defn exit []
  (System/exit 0))

; I might want this to return 
(defn check-for-command? 
  "Checks if a command exists. If so, it runs the command" 
  [prompt] 
  (let [resolved (resolve (symbol "netnavi.plugins.features" prompt))]
    (if resolved
     (do 
       (println "Command" prompt "executed")
       (println util/line)
       (resolved)
       true)
      nil)))

;(check-for-command? "init!")

;(print netnavi.plugins.gpt/assistant)
;(count @(:running-log netnavi.plugins.gpt/assistant))
;(strike-last-input!)
;(init!)