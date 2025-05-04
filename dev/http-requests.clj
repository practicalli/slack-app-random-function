(ns http-requests)

;; ---------------------------------------------------------
#_{:clj-kondo/ignore [:redefined-var]}
(comment
  (let [slack-authentication-token (System/getenv "SLACK_AUTHENTICATION_TOKEN")])

;; Exceptions 
  (client/get "http://example.com/broken" {:throw-entire-message? true})

;;fire and forget, returns immediately[1], returned promise is ignored
    ;; (http/post "http://host.com/path")

  #_(let [response1 (http/get "http://http-kit.org/")
          response2 (http/get "http://clojure.org/")])
      ;; Handle responses one-by-one, blocking as necessary
      ;; Other keys :headers :body :error :opts
      ;; (println "response1's status: " (:status @response1))
      ;; (println "response2's status: " (:status @response2)

  #_(def options {:timeout 200             ; ms
                  :basic-auth ["user" "pass"]
                  :query-params {:param "value" :param2 ["value1" "value2"]}
                  :user-agent "User-Agent-string"
                  :headers {"X-Header" "Value"}})
  #_(http/get "http://host.com/path" options
              (fn [{:keys [status headers body error]}] ;; asynchronous response handling
                (if error
                  (println "Failed, exception is " error)
                  (println "Async HTTP GET: " status))))
        ; [1] may not always true, since DNS lookup maybe slow

  #_()) ; End of rich comment
