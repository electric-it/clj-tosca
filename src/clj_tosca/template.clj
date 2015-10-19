(ns clj-tosca.template
  (:require [clj-tosca.node :as node]
            [clj-tosca.node-instance :as nodei]
            [clojure.data.json :as json]
            [clj-yaml.core :as yaml]))

(defn build [& nodes]
  (apply merge nodes)) 

(defn publish ([nodes]
               (publish nodes "json"))
              ([nodes format]
                (let [template (merge {:tosca_definitions_version "tosca_simple_yaml_1_0"} nodes)]
                  (case format
                    "yaml" (yaml/generate-string template)
                    "edn"  (pr-str template)
                    "json" (json/write-str template)))))
