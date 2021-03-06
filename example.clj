(ns clj-tosca.example
  (require [clj-tosca.node :as node]
           [clj-tosca.node-instance :as nodei]
           [clj-tosca.template :as template]))

(def node (-> (node/build)
              (node/add-property "mem_size" "4 MB")
              (node/add-property "disk_size" "10 GB")))

(def node-instance (-> (nodei/build)
                       (nodei/add-state "started")
                       (nodei/add-property "instance_name" "4U321PO")))

(def tosca (template/build node-instance node))

(template/publish tosca)  ;; default yaml

;; outputs yaml 

;; "tosca_definitions_version: tosca_simple_yaml_1_0\nnode_instance:\n  properties: {instance_name: 4U321PO}\n  state: started\nnode_types:\n  ServerNode:\n    type: tosca.nodes.Compute\n    properties: {mem_size: 4 MB, disk_size: 10 GB}\n    attributes: {}\n    requirements: {}\n    capabilities: {}\n    interfaces: {}\n    artifacts: {}\n    metadatas: {}\n""



(template/publish tosca "edn")

;; outputs edn

;; "{:tosca_definitions_version \"tosca_simple_yaml_1_0\", :node_instance {:properties {:instance_name \"4U321PO\"}, :state \"started"}, :node_types {:ServerNode {:type \"tosca.nodes.Compute\", :properties {:mem_size \"4 MB\", :disk_size \"10 GB\"}, :attributes {}, :requirements {}, :capabilities {}, :interfaces {}, :artifacts {}, :metadatas {}}}}"

(template/publish tosca "json")

;; outputs json

;; "{\"tosca_definitions_version\":\"tosca_simple_yaml_1_0\",\"node_instance\":{\"properties\":{\"instance_name\":\"4U321PO\"},\"state\\":\"started\"},\"node_types\":{\"ServerNode\":{\"type\":\"tosca.nodes.Compute\",\"properties\":{\"mem_size\":\"4 MB\",\"disk_size\":\\"10 GB\"
