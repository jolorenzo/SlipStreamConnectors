(ns com.sixsq.slipstream.ssclj.resources.spec.credential-template-cloud-opennebula-test
  (:require
    [clojure.test :refer :all]
    [clojure.spec.alpha :as s]
    [com.sixsq.slipstream.ssclj.resources.credential :as p]
    [com.sixsq.slipstream.ssclj.resources.credential-template :as ct]
    [com.sixsq.slipstream.ssclj.resources.spec.credential-template-cloud-opennebula]
    [com.sixsq.slipstream.ssclj.resources.credential-template-cloud :as ctc]
    [com.sixsq.slipstream.ssclj.resources.credential-template-cloud-opennebula :as ctco]))

(def valid-acl ctc/resource-acl-default)

(deftest test-credential-template-cloud-opennebula-create-schema-check
  (let [root {:resourceURI        p/resource-uri
              :credentialTemplate {:key       "foo"
                                   :secret    "bar"
                                   :quota     7
                                   :connector {:href "connector/xyz"}}}]
    (is (s/valid? :cimi/credential-template.cloud-opennebula-create root))
    (doseq [k (into #{} (keys (dissoc root :resourceURI)))]
      (is (not (s/valid? :cimi/credential-template.cloud-opennebula-create (dissoc root k)))))))

(deftest test-credential-template-cloud-opennebula-schema-check
  (let [timestamp "1972-10-08T10:00:00.0Z"
        root      {:id          (str ct/resource-url "/uuid")
                   :resourceURI p/resource-uri
                   :created     timestamp
                   :updated     timestamp
                   :acl         valid-acl
                   :type        ctco/credential-type
                   :method      ctco/method
                   :key         "foo"
                   :secret      "bar"
                   :quota       7
                   :connector   {:href "connector/xyz"}}]
    (is (s/valid? :cimi/credential-template.cloud-opennebula root))
    (doseq [k (into #{} (keys root))]
      (is (not (s/valid? :cimi/credential-template.cloud-opennebula (dissoc root k)))))))
