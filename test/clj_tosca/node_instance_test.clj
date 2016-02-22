(ns clj-tosca.node-instance-test
  (:require [clj-tosca.tosca :refer :all]
            [clojure.test :refer :all]))

(deftest builds-instance-node-is-map
  (let [node (instance-build)]
    (is (= (class node) clojure.lang.PersistentArrayMap))))

(deftest builds-instance-nodes-init
  (let [node (instance-build)]
    (is (= node {:node_instance {:properties {}}}))))

(deftest adds-state
  (let [node (-> (instance-build)
                 (instance-add-state "started"))]
    (is (= node {:node_instance {:properties {} :state "started"}}))))

(deftest adds-properties
  (let [node (-> (instance-build)
                 (instance-add-property "instanceName" "ERTA132")
                 (instance-add-property "region" "us-east-1"))]
    (is (= node {:node_instance {:properties {:instanceName "ERTA132" :region "us-east-1"}}}))))
