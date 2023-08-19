(ns netnavi.sapphire)
(require
 '[netnavi.plugins.gpt :as gpt])

(defn perpetual-loop []
  (loop []
    (let [input (read-line)]
      (println (gpt/chat-with-assistant input))
      (recur))))

(perpetual-loop)