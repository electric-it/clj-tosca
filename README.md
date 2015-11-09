# clj-tosca

A Clojure library designed to build up a node or node-instance in tosca. You can publish a collection of nodes as TOSCA document with `template/build` and `template/publish` and export the document as yaml (default), json, or edn.

## Usage

```
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
```


```
#### outputs yaml

(template/publish tosca)  ;; default is yaml


tosca_definitions_version: tosca_simple_yaml_1_0
  node_instance:
    properties: {instance_name: 4U321PO}
         state: started
    node_types:
      ServerNode:
        type: tosca.nodes.Compute
          properties: {mem_size: 4 MB, disk_size: 10 GB}
          attributes: {}
          requirements: {}
          capabilities: {}
          interfaces: {}
          artifacts: {}
          metadatas: {}


#### outputs edn

(template/publish tosca "edn")

{:tosca_definitions_version "tosca_simple_yaml_1_0"
  :node_instance {:properties
                   {:instance_name "4U321PO"}
                    :state \"started"}
  :node_types {:ServerNode
                {:type "tosca.nodes.Compute"
                 :properties {:mem_size "4 MB", :disk_size \"10 GB\"}
                 :attributes {}
                 :requirements {}
                 :capabilities {}
                 :interfaces {}
                 :artifacts {}
                 :metadatas {}}}}

#### outputs json

(template/publish tosca "json")

{"tosca_definitions_version" : "tosca_simple_yaml_1_0",
  "node_instance" : {"properties" : {"instance_name" : "4U321PO"},
                     "state" : "started"},\
  "node_types" : { "ServerNode" : {"type" : "tosca.nodes.Compute",
                                   "properties" : { "mem_size" : "4 MB", "disk_size" : "10 GB"}
                                   "attributes" : {},
                                   "requirements" : {},
                                   "capabilities" : {},
                                   "interfaces" : {},
                                   "artifacts" : {},
                                   "metadatas" : {}
                                   }
                 }
                                   
}
```

## License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

