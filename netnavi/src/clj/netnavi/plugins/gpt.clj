(ns netnavi.plugins.gpt)
(require '[wkok.openai-clojure.api :as api])

(def empty-chat [{:role "system" :content "You are a helpful assistant named Sapphire.EXE. your purpose is to help me manage my schedule, projects, and ADHD"}])
(def netnavi-project-assistant-prep [{:role "system" :content "You are a helpful assistant named Sapphire.EXE. You are helping me with a clojure/clojurescript project that I am working on. The project was created using the command \"lein new luminus netnavi +shadow-cljs, +http-kit, +reitit, +sqlite, +graphql\". Most questions will come from this base frame of reference"}])

(defn add-new-message [message]
  (let [new-map {:role "user" :content message}]
        (conj empty-chat new-map)))

(defn quick-chat-with-assistant [message]
  (get-in (api/create-chat-completion {:model "gpt-3.5-turbo"
                                       :messages (add-new-message message)}) [:choices 0 :message :content]))

(defn chat-with-assistant [message]
  (get-in (api/create-chat-completion {:model "gpt-3.5-turbo"
                                       :messages (add-new-message message)}) [:choices 0 :message :content]))