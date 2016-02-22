(ns clj-tosca.node-test
  (:require [clj-tosca.tosca :refer :all]
            [clojure.test :refer :all]))

(deftest test-node-creates-returns-map
  (let [node (node-build)]
    (is (= (class node) clojure.lang.PersistentArrayMap))))

(deftest test-node-has-key-node-types
  (let [node (node-build)]
    (is (= (first (keys node)) :node_types))))

(deftest test-type-is-set
  (let [node (node-build)]
    (is (= (:type (:Node (:node_types node))) "tosca.nodes.Compute"))))

(deftest test-adding-one-property
  (let [node (-> (node-build)
                 (node-add-property "state" "starting"))
        expected-props (get-in node [:node_types :Node :properties])]
    (is (= expected-props {:state "starting"}))))

(deftest test-adding-two-properties
  (let [node (-> (node-build)
                 (node-add-property "state" "starting")
                 (node-add-property "size" 1))
        expected-props (get-in node [:node_types :Node :properties])]
    (is (= expected-props {:state "starting", :size 1}))))

(deftest test-adding-one-attribute
  (let [node (-> (node-build)
                 (node-add-attribute "size" "medium"))
        expected-attrs (get-in node [:node_types :Node :attributes])]
    (is (= expected-attrs {:size "medium"}))))

(deftest test-adding-two-attributes
  (let [node (-> (node-build)
                 (node-add-attribute "size" "medium")
                 (node-add-attribute "memory" "8gb"))
        expected-attrs (get-in node [:node_types :Node :attributes])]
    (is (= expected-attrs {:size "medium" :memory "8gb"}))))

(deftest test-adding-one-requirement
  (let [node (-> (node-build)
                 (node-add-requirement "SLA" "Level 1"))
        expected-req (get-in node [:node_types :Node :requirements])]
    (is (= expected-req {:SLA "Level 1"}))))

(deftest test-adding-one-capability
  (let [node (-> (node-build)
                 (node-add-capability "hdd" "500gb"))
        expected-cap (get-in node [:node_types :Node :capabilities])]
    (is (= expected-cap {:hdd "500gb"}))))

(deftest test-add-one-interface
  (let [node (-> (node-build)
                 (node-add-interface "static" "true" ))
        expected-inf (get-in node [:node_types :Node :interfaces])]
    (is (= expected-inf {:static "true"}))))

(deftest test-add-one-artifact
  (let [node (-> (node-build)
                 (node-add-artifact "purpose" "test"))
        expected-art (get-in node [:node_types :Node :artifacts])]
    (is (= expected-art {:purpose "test"}))))

(deftest test-add-two-things
  (let [node (-> (node-build)
                 (node-add-capability "size" "medium")
                 (node-add-interface "network" "large"))]
    (is (= node {:node_types {:Node {:type "tosca.nodes.Compute", :properties {}, :attributes {}, :requirements {}, :capabilities {:size "medium"}, :interfaces {:network "large"}, :artifacts {}, :metadatas {}}}}))))

(deftest test-add-metadata
  (let [node (-> (node-build)
                 (node-add-metadata "name" "r2EW9012"))
        added-data (get-in node [:node_types :ServerNode :metadata])]
    (is (= node {:node_types {:Node {:type "tosca.nodes.Compute", :properties {}, :attributes {}, :requirements {}, :capabilities {}, :interfaces {}, :artifacts {}, :metadatas {:name "r2EW9012"}}}}))))

(deftest test-adding-nil-value
  (let [node (node-build)
        empty-node (node-add-property node "something" nil)]
    (is (= node empty-node))))
     
(deftest test-different-node-type
  (let [node (node-build "tosca.nodes.S3Storage")]
    (is (= node {:node_types {:Node {:type "tosca.nodes.S3Storage", :properties {}, :attributes {}, :requirements {}, :capabilities {}, :interfaces {}, :artifacts {}, :metadatas {}}}}))))
