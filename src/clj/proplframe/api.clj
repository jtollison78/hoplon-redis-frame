(ns proplframe.api
  ;(:require [castra.core :refer [defrpc *session*]])
  (:refer-clojure :exclude [assert] )

  (:require [castra.core :refer [defrpc ex *request* *session*]])
  
    (:require [taoensso.carmine :as car :refer (wcar)])
  (:require [buddy.hashers :as hashers])
  (:require [postal.core :as postal]
            [postal.sendmail :as local]
            [postal.smtp :as smtp]
  )
  (:require [clj-time.core :as t])
  (:require [clj-http.client :as http])
  (:require [clojure.data.json :as json])
  (:require [clj-stripe.util :as util]
            [clj-stripe.common :as common]
            [clj-stripe.plans :as plans]
            [clj-stripe.coupons :as coupons]
            [clj-stripe.charges :as charges]
            [clj-stripe.cards :as cards]
            [clj-stripe.subscriptions :as subscriptions]
            [clj-stripe.customers :as customers]
            [clj-stripe.invoices :as invoices]
            [clj-stripe.invoiceitems :as invoiceitems])

	  ) ;*request*s

  






(defrpc get-state []
  (swap! *session* update-in [:id] #(or % (rand-int 100)))
  {:random (rand-int 100) :session (:id @*session*)})
