(ns netnavi.assistant)

(defrecord Assistant [running-log tailored-prompt])


; This should be moved to GPT Module
(def assistant (->Assistant (atom empty-chat) (atom empty-chat)))

(defn format-prompt [prompt]
  (let [new-map {:role "user" :content prompt}]
    new-map))

(defn format-response [response]
  (let [new-map {:role "assistant" :content response}]
    new-map))

(defn log-prompt-update! [prompt]
  (swap! (:running-log assistant) conj (format-prompt prompt))
  prompt)

(defn log-response-update! [response]
  (swap! (:running-log assistant) conj (format-response response))
  response)

#_(let [message "This is a test"]
  (log-prompt-update! message)
  (println "Current running-log: " @(:running-log assistant))
  (log-response-update! (format-response (chat-with-assistant @(:running-log assistant)))) 
  (println "Current running-log: " @(:running-log assistant)))