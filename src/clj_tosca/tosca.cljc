(ns clj-tosca.tosca
  (:require [clj-tosca.node :as node]
            [clj-tosca.node-instance :as nodei]
            [clj-tosca.template :as template]))

(def node-build node/build)

(def node-add-property node/add-property)

(def node-add-attribute node/add-attribute)

(def node-add-requirement node/add-requirement)

(def node-add-capability node/add-capability)

(def node-add-interface node/add-interface)

(def node-add-artifact node/add-artifact)

(def node-add-metadata node/add-metadata)

(def instance-build nodei/build)

(def instance-add-state nodei/add-state)

(def instance-add-property nodei/add-property)

(def template-build template/build)

(def template-publish template/publish)
