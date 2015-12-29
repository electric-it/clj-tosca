(ns clj-tosca.template-test
  (:require [clj-tosca.template :as template]
            [clj-tosca.node :as node]
            [clj-tosca.node-instance :as nodei]
            [clojure.test :refer :all]))

(deftest merging-nodes
  (let [result (template/build {:one 1} {:two 2})]
    (is (= result {:one 1 :two 2}))))

(deftest merging-complex-nodes
  (let [node (-> (node/build)
                 (node/add-property "system" "on"))
        nodei (-> (nodei/build)
                  (nodei/add-property "state" "pending"))
        result (template/build nodei node)]
    (is (= result {:node_instance {:properties {:state "pending"}}, :node_types {:Node {:type "tosca.nodes.Compute", :properties {:system "on"}, :attributes {}, :requirements {}, :capabilities {}, :interfaces {}, :artifacts {}, :metadatas {}}}}))))

(deftest publishing-template
  (let [node (-> (node/build)
                 (node/add-property "system" "on"))
        nodei (-> (nodei/build)
                  (nodei/add-property "state" "pending"))
        template (template/build nodei node)
        result (template/publish template)]
    (is (= result "tosca_definitions_version: tosca_simple_yaml_1_0\nnode_instance:\n  properties: {state: pending}\nnode_types:\n  Node:\n    type: tosca.nodes.Compute\n    properties: {system: 'on'}\n    attributes: {}\n    requirements: {}\n    capabilities: {}\n    interfaces: {}\n    artifacts: {}\n    metadatas: {}\n"))))



