;; ---------------------------------------------------------
;; practicalli.random-function-slack-app
;;
;; TODO: Provide a meaningful description of the project
;; ---------------------------------------------------------


(ns practicalli.random-function-slack-app
  (:gen-class)
  (:require
   [org.httpkit.client :as http]
   [com.brunobonacci.mulog :as mulog]))

;; ---------------------------------------------------------
;; Start Mulog publisher - only once
(defonce mulog-publisher
  (mulog/start-publisher! {:type :console :pretty? true}))
;; ---------------------------------------------------------

;; ---------------------------------------------------------
;; Application

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn greet
  "Greeting message via Clojure CLI clojure.exec"
  ([] (greet {:team-name "secret engineering"}))
  ([{:keys [team-name]}]
   (str "practicalli random-function-slack-app service developed by the " team-name " team")))

(defn -main
  "Entry point into the application via clojure.main -M"
  [& args]
  (let [team (first args)]
    (mulog/set-global-context!
     {:app-name "practicalli random-function-slack-app" :version  "0.1.0-SNAPSHOT"})
    (mulog/log ::application-starup :arguments args)
    (if team
      (greet team)
      (greet))))

;; ---------------------------------------------------------

;; ---------------------------------------------------------
;; Rick Comment
#_{:clj-kondo/ignore [:redefined-var]}
(comment

  (-main)
  (-main {:team-name "Clojure Engineering"})

  ;; Stop mulog publisher
  (mulog-publisher)

  (let [slack-authentication-token (System/getenv "SLACK_AUTHENTICATION_TOKEN")]
    slack-authentication-token)

  #_()) ; End of rich comment block
;; ---------------------------------------------------------
