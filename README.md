# clj-tosca
[![Build Status](https://travis-ci.org/electric-it/clj-tosca.svg)](https://travis-ci.org/electric-it/clj-tosca)

A Clojure library designed to build up a node or instance in tosca. You can publish a collection of nodes as TOSCA document with `template-build` and `template-publish` in yaml (default), json, or edn. Can be used in clojure or clojurescript.

## Usage

```clj
(ns clj-tosca.example
  (:require [clj-tosca.tosca :refer :all]))

(def node (-> (node-build)
              (node-add-property "mem_size" "4 MB")
              (node-add-property "disk_size" "10 GB")))

(def instance (-> (instance-build)
                  (instance-add-state "started")
                  (instance-add-property "instance_name" "4U321PO")))

(def tosca-template (template-build instance node))
```

## Output formats

Tosca's format is yaml but for ease of use you may want to transport with json or edn between applications.


#### yaml

```yaml
(template-publish tosca)  ;; default


tosca_definitions_version: tosca_simple_yaml_1_0
  node_instance:
    properties: {instance_name: 4U321PO}
         state: started
    node_types:
      Node:
        type: tosca.nodes.Compute
          properties: {mem_size: 4 MB, disk_size: 10 GB}
          attributes: {}
          requirements: {}
          capabilities: {}
          interfaces: {}
          artifacts: {}
          metadatas: {}
```

#### edn

```edn
(template-publish tosca "edn")

{:tosca_definitions_version "tosca_simple_yaml_1_0"
  :node_instance {:properties
                   {:instance_name "4U321PO"}
                    :state "started"}
  :node_types {:Node
                {:type "tosca.nodes.Compute"
                 :properties {:mem_size "4 MB", :disk_size "10 GB"}
                 :attributes {}
                 :requirements {}
                 :capabilities {}
                 :interfaces {}
                 :artifacts {}
                 :metadatas {}}}}
```

#### json

```json
(template-publish tosca "json")

{ "tosca_definitions_version" : "tosca_simple_yaml_1_0",
  "node_instance" : { "properties" : {"instance_name" : "4U321PO"},
                      "state" : "started"},
  "node_types" : { "Node" :
                   { "type" : "tosca.nodes.Compute",
                     "properties" : { "mem_size" : "4 MB", "disk_size" : "10 GB"},
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
