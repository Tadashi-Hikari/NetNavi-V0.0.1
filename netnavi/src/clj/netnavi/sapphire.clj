(ns netnavi.sapphire 
  (:require [netnavi.util :as util]
            [netnavi.assist :as assistant]
            [netnavi.plugins.features :as features]))
(require
 '[netnavi.plugins.gpt :as gpt])

(defn perpetual-loop []
  (loop []
    (let [input (read-line)]
      (let [result (features/check-for-command? input)]
        (if result
          nil
          (do (println util/line)
              (println util/RED (gpt/chat-with-assistant input) util/RESET)
              (println util/line)))))
    (recur)))

(println util/GREEN "\n" "Sapphire.EXE initalized" util/RESET "\n" util/line)

(perpetual-loop)

;(print @(:running-log netnavi.plugins.gpt/assistant)) 