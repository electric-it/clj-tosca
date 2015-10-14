(ns clj-tosca.node-instance-test
  (:require [clj-tosca.node-instance :as nodei]
            [clojure.test :refer :all]))


(deftest builds-instance-node-is-map
  (let [node (nodei/build)]
    (is (= (class node) clojure.lang.PersistentArrayMap))))

(deftest builds-instance-nodes-init
  (let [node (nodei/build)]
    (is (= node {:node_instance {:properties {}}}))))

(deftest adds-state
  (let [node (-> (nodei/build)
                 (nodei/add-state "started"))]
    (is (= node {:node_instance {:properties {} :state "started"}}))))

(deftest adds-properties
  (let [node (-> (nodei/build)
                 (nodei/add-property "instanceName" "ERTA132")
                 (nodei/add-property "region" "us-east-1"))]
    (is (= node {:node_instance {:properties {:instanceName "ERTA132" :region "us-east-1"}}}))))
