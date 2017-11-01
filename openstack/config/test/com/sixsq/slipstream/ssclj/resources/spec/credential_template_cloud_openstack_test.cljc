(ns com.sixsq.slipstream.ssclj.resources.spec.credential-template-cloud-openstack-test
    (:require
    [clojure.test :refer :all]
    [clojure.spec.alpha :as s]
    [com.sixsq.slipstream.ssclj.resources.credential :as p]
    [com.sixsq.slipstream.ssclj.resources.credential-template :as ct]
    [com.sixsq.slipstream.ssclj.resources.spec.credential-template-cloud-openstack]
    [com.sixsq.slipstream.ssclj.resources.credential-template-cloud-openstack
     :refer [resource-acl credential-type method]]))

(def valid-acl resource-acl)

(deftest test-credential-template-cloud-openstack-create-schema-check
  (let [root {:resourceURI        p/resource-uri
              :credentialTemplate {:key       "foo"
                                   :secret    "bar"
                                   :connector "connector/xyz"
                                   :tenant-name "tenant"
                                   :domain-name "domain"}}]
    (is (s/valid? :cimi/credential-template.cloud-openstack-create root))
    (doseq [k (into #{} (keys (dissoc root :resourceURI)))]
      (is (not (s/valid? :cimi/credential-template.cloud-openstack-create (dissoc root k)))))))

(deftest test-credential-template-cloud-openstack-schema-check
  (let [timestamp "1972-10-08T10:00:00.0Z"
        root      {:id          (str ct/resource-url "/uuid")
                   :resourceURI p/resource-uri
                   :created     timestamp
                   :updated     timestamp
                   :acl         valid-acl
                   :type        credential-type
                   :method      method
                   :key         "foo"
                   :secret      "bar"
                   :connector   "connector/xyz"
                   :tenant-name "tenant"}]
    (is (s/valid? :cimi/credential-template.cloud-openstack root))
    (doseq [k (into #{} (keys root))]
      (is (not (s/valid? :cimi/credential-template.cloud-openstack (dissoc root k)))))))
