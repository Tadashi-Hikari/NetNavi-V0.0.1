(ns netnavi.plugins.features)
(:require [
 '[clojure.java.shell :as sh]
 '[netnavi.plugins.gpt :as gpt]
 '[netnavi.assist :as assist]])
(:import '[netnavi.assist Assistant])

;(let [result (clojure.java.shell/sh "firefox")])

(defn clear-terminal []
  "A simple expression to clear a bash shell"
  (clojure.java.shell/sh "clear"))

; the record may need to be atomic?
(defn init! []
  "reset the assistant back to default"
  (swap! (:running-log netnavi.plugins.gpt/assistant) (constantlnetnavi.plugins.gpt/empty-chat)))

(defn strike-last-input! []
  "This form removes the last prompt/response pair"
  (if (< (count @(:running-log netnavi.plugins.gpt/assistant)) 2) 
    (println "No input to strike")
    (swap! (:running-log netnavi.plugins.gpt/assistant) #(subvec % 0 (- (count %) 1)))))

