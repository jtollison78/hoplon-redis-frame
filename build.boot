(set-env!
	:dependencies '[[adzerk/boot-cljs          "1.7.228-2"]
                  [adzerk/boot-reload        "0.5.1"]
                  [compojure                 "1.6.0-beta3"]
                  [hoplon/castra             "3.0.0-alpha7"]
                  [hoplon/hoplon             "6.0.0-alpha17"]
                  [org.clojure/clojure       "1.8.0"]
                  [org.clojure/clojurescript "1.9.495"]
                  [pandeiro/boot-http        "0.7.6"]
                  [ring                      "1.5.1"]
                  [ring/ring-defaults        "0.2.3"]
                  [ring/ring-json            "0.4.0"]
                  
                  [adzerk/boot-cljs-repl     "0.3.3"]
                  [com.cemerick/piggieback   "0.2.1"  :scope "test"]
                  [weasel                    "0.7.0"      :scope "test"]
                  [org.clojure/tools.nrepl   "0.2.12" :scope "test"]

			[com.taoensso/encore              "2.90.1"]
   			[com.taoensso/timbre              "4.8.0"]
   			[com.taoensso/nippy               "2.13.0"]
   			[org.apache.commons/commons-pool2 "2.4.2"]
   			[commons-codec/commons-codec      "1.10"]
                     [com.taoensso/carmine      "2.16.0"] ;.16.0

                  
                  
                  ]
  :resource-paths #{"resources" "src/clj"}
  :source-paths   #{"src/cljs" "src/hl"})

(require
  '[adzerk.boot-cljs      :refer [cljs]]
  '[adzerk.boot-reload    :refer [reload]]
  '[hoplon.boot-hoplon    :refer [hoplon prerender]]
  '[pandeiro.boot-http    :refer [serve]])

(deftask dev
  "Build proplframe for local development."
  []
  (comp
    (serve
      :port    8000
      :handler 'proplframe.handler/app
      :reload  true)
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs)))

(deftask prod
  "Build proplframe for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (prerender)))

(deftask make-war
  "Build a war for deployment"
  []
  (comp (hoplon)
        (cljs :optimizations :advanced)
        (uber :as-jars true)
        (web :serve 'proplframe.handler/app)
        (war)
        (target :dir #{"target"})))
