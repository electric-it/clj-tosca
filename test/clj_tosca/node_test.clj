(ns clj-tosca.node-test
  (:require [clj-tosca.node :as node]
            [clojure.test :refer :all]))

(deftest test-node-creates-returns-map
  (let [node (node/build)]
    (is (= (class node) clojure.lang.PersistentArrayMap))))

(deftest test-node-has-key-node-types
  (let [node (node/build)]
    (is (= (first (keys node)) :node_types))))

(deftest test-type-is-set
  (let [node (node/build)]
    (is (= (:type (:Node (:node_types node))) "tosca.nodes.Compute"))))

(deftest test-adding-one-property
  (let [node (-> (node/build)
                 (node/add-property "state" "starting"))
        added-props (->> (:node_types node)
                         (:Node)
                         (:properties))]
    (is (= added-props {:state "starting"}))))

(deftest test-adding-two-properties
  (let [node (-> (node/build)
                 (node/add-property "state" "starting")
                 (node/add-property "size" 1))
        added-props (->> (:node_types node)
                         (:Node)
                         (:properties))]
    (is (= added-props {:state "starting", :size 1}))))

(deftest test-adding-one-attribute
  (let [node (-> (node/build)
                 (node/add-attribute "size" "medium"))
        added-attrs (->> (:node_types node)
                         (:Node)
                         (:attributes))]
    (is (= added-attrs {:size "medium"}))))

(deftest test-adding-two-attributes
  (let [node (-> (node/build)
                 (node/add-attribute "size" "medium"))
        node-w-attrs (node/add-attribute node "memory" "8gb")
        added-attrs (->> (:node_types node-w-attrs)
                         (:Node)
                         (:attributes))]
    (is (= added-attrs {:size "medium" :memory "8gb"}))))

(deftest test-adding-one-requirement
  (let [node (-> (node/build)
                 (node/add-requirement "SLA" "Level 1"))
        added-req (->> (:node_types node)
                       (:Node)
                       (:requirements))]
    (is (= added-req {:SLA "Level 1"}))))

(deftest test-adding-one-capability
  (let [node (-> (node/build)
                 (node/add-capability "hdd" "500gb"))
        added-cap (->> (:node_types node)
                       (:Node)
                       (:capabilities))]
    (is (= added-cap {:hdd "500gb"}))))

(deftest test-add-one-interface
  (let [node (-> (node/build)
                 (node/add-interface "static" "true" ))
        added-inf (->> (:node_types node)
                       (:Node)
                       (:interfaces))]
    (is (= added-inf {:static "true"}))))

(deftest test-add-one-artifact
  (let [node (-> (node/build)
                 (node/add-artifact "purpose" "test"))
        added-art (->> (:node_types node)
                       (:Node)
                       (:artifacts))]
    (is (= added-art {:purpose "test"}))))

(deftest test-add-two-things
  (let [node (-> (node/build)
                 (node/add-capability "size" "medium")
                 (node/add-interface "network" "large"))]
    (is (= node {:node_types {:Node {:type "tosca.nodes.Compute", :properties {}, :attributes {}, :requirements {}, :capabilities {:size "medium"}, :interfaces {:network "large"}, :artifacts {}, :metadatas {}}}}))))

(deftest test-add-metadata
  (let [node (-> (node/build)
                 (node/add-metadata "name" "r2EW9012"))
        added-data (->> (:node_types node)
                        (:ServerNode)
                        (:metadatas))]
    (is (= node {:node_types {:Node {:type "tosca.nodes.Compute", :properties {}, :attributes {}, :requirements {}, :capabilities {}, :interfaces {}, :artifacts {}, :metadatas {:name "r2EW9012"}}}}))))

(deftest test-adding-nil-value
  (let [node (node/build)
        empty-node (node/add-property node "something" nil)]
    (is (= node empty-node))))
     
(deftest test-different-node
  (let [node (node/build "tosca.nodes.S3Storage")]
    (is (= node {:node_types {:Node {:type "tosca.nodes.S3Storage", :properties {}, :attributes {}, :requirements {}, :capabilities {}, :interfaces {}, :artifacts {}, :metadatas {}}}}))))
