(ns com.sixsq.slipstream.connector.cloudstack-template-lifecycle-test
  (:require
    [clojure.test :refer :all]

    [com.sixsq.slipstream.connector.cloudstack-template :as cit]
    [com.sixsq.slipstream.ssclj.resources.connector-test-utils :as tu]
    [com.sixsq.slipstream.ssclj.resources.lifecycle-test-utils :as ltu]))

(use-fixtures :each ltu/with-test-server-fixture)

(deftest test-connector-template-is-registered
  (tu/connector-template-is-registered cit/cloud-service-type))

(deftest lifecycle
  (tu/template-lifecycle cit/cloud-service-type))

