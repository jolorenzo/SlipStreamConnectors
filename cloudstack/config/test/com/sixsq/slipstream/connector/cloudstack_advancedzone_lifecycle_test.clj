(ns com.sixsq.slipstream.connector.cloudstack-advancedzone-lifecycle-test
  (:require
    [clojure.test :refer :all]

    [com.sixsq.slipstream.connector.cloudstack-advancedzone-template :as cit]
    [com.sixsq.slipstream.ssclj.resources.common.dynamic-load :as dyn]
    [com.sixsq.slipstream.ssclj.resources.connector-test-utils :as tu]
    [com.sixsq.slipstream.ssclj.resources.lifecycle-test-utils :as ltu]))


(use-fixtures :each ltu/with-test-es-client-fixture)

;; initialize must to called to pull in ConnectorTemplate test examples
(dyn/initialize)

#_(deftest lifecycle
  (tu/connector-lifecycle cit/cloud-service-type))

