(ns netnavi.sapphire 
  (:require [netnavi.util :as util]
            [netnavi.assistant :as assistant]))
(require
 '[netnavi.plugins.gpt :as gpt])

(defn perpetual-loop []
  (loop []
    (let [input (read-line)]
      (println util/RED (gpt/chat-with-assistant input) util/RESET)
      (recur))))

(println util/GREEN "Sapphire.EXE initalized" util/RESET)
(perpetual-loop)

(print (:running-log assistant/assistant)) 