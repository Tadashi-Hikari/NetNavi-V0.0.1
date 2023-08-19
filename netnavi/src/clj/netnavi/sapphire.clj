(ns netnavi.sapphire 
  (:require [netnavi.util :as util]))
(require
 '[netnavi.plugins.gpt :as gpt]
 '[netnavi.util :as util])

(defn perpetual-loop []
  (loop []
    (let [input (read-line)]
      (println util/RED (gpt/chat-with-assistant input) util/RESET)
      (recur))))

(perpetual-loop)