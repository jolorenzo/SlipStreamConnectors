(ns com.sixsq.slipstream.ssclj.resources.spec.credential-cloud-openstack
    (:require
    [clojure.spec.alpha :as s]
    [com.sixsq.slipstream.ssclj.util.spec :as us]
    [com.sixsq.slipstream.ssclj.resources.spec.credential :as cred]
    [com.sixsq.slipstream.ssclj.resources.spec.credential-template :as ct]
    [com.sixsq.slipstream.ssclj.resources.spec.credential-template-cloud-openstack]))

(s/def :cimi.credential.cloud-openstack/connector :cimi.common/resource-link)
(s/def :cimi.credential.cloud-openstack/key :cimi.core/nonblank-string)
(s/def :cimi.credential.cloud-openstack/secret :cimi.core/nonblank-string)
(s/def :cimi.credential.cloud-openstack/tenant-name :cimi.core/nonblank-string)
(s/def :cimi.credential.cloud-openstack/domain-name string?)

(def credential-keys-spec
  {:req-un [:cimi.credential.cloud-openstack/connector
            :cimi.credential.cloud-openstack/key
            :cimi.credential.cloud-openstack/secret
            :cimi.credential.cloud-openstack/tenant-name]
   :opt-un  [:cimi.credential.cloud-openstack/domain-name]})

(s/def :cimi/credential.cloud-openstack
  (us/only-keys-maps cred/credential-keys-spec
                     credential-keys-spec))

(s/def :cimi/credential.cloud-openstack.create
  (us/only-keys-maps ct/create-keys-spec
                     {:req-un [:cimi.credential-template.cloud-openstack/credentialTemplate]}))
