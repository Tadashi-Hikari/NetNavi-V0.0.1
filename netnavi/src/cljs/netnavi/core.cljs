(ns netnavi.core)

(defn ^:dev/after-load mount-components []
  (let [content (js/document.getElementById "app")]
    (while (.hasChildNodes content)
      (.removeChild content (.-lastChild content)))
    (.appendChild content (js/document.createTextNode "Welcome to netnavi"))))

(defn init! []
  (mount-components))
