(def +version+ "3.50-SNAPSHOT")

(defproject com.sixsq.slipstream/SlipStreamConnector-CloudStack-conf "3.50-SNAPSHOT"

  :description "CloudStack connector configuration"

  :url "https://github.com/slipstream/SlipStreamConnectors"

  :license {:name "Apache 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.txt"
            :distribution :manual}

  :plugins [[lein-parent "0.3.2"]]

  :parent-project {:coords  [sixsq/slipstream-parent "5.1.1"]
                   :inherit [:min-lein-version
                             :managed-dependencies
                             :repositories
                             :deploy-repositories]}

  :source-paths ["src"]

  :resource-paths ["resources"]

  :test-paths ["test"]

  :pom-location "target/"

  :dependencies
  [[org.clojure/clojure]]

  :profiles
  {:test
   {:dependencies   [[com.sixsq.slipstream/SlipStreamCljResourcesTests-jar ~+version+]
                     [com.sixsq.slipstream/SlipStreamDbTesting-jar ~+version+]
                     [peridot]
                     [commons-logging]
                     [org.clojure/test.check]]
    :resource-paths ["test-resources"]}
   :provided
   {:dependencies [[com.sixsq.slipstream/SlipStreamServer-cimi-resources ~+version+]]}})

