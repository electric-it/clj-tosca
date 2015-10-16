(ns clj-tosca.template
  (:require [clj-tosca.node :as node]
            [clj-tosca.node-instance :as nodei]
            [clojure.data.json :as json]
            [clj-yaml.core :as yaml]))

(defn build [& nodes]
  (merge {:tosca_definitions_version "tosca_simple_yaml_1_0"} (apply merge nodes))) 
