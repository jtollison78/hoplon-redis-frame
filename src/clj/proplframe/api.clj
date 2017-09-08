(ns proplframe.api
  ;(:require [castra.core :refer [defrpc *session*]])
  (:refer-clojure :exclude [assert] )

  (:require [castra.core :refer [defrpc ex *request* *session*]])
  
  (:require [taoensso.carmine :as car :refer (wcar)])

  (:require [taoensso.encore       :as enc])
  (:require [taoensso.nippy :as nippy :refer [freeze thaw]])
  ) ;*request*s

  






(defrpc get-state []
  (swap! *session* update-in [:id] #(or % (rand-int 100)))
  {:random (rand-int 100) :session (:id @*session*)})
