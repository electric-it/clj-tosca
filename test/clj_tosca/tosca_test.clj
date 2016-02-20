(ns clj-tosca.tosca-test
  (:require [clj-tosca.tosca :refer :all]
            [clojure.test :refer :all]))

(deftest test-node-builds
  (let [node (node-build)]
    (is (= (class node) clojure.lang.PersistentArrayMap))))

(deftest test-node-add-property
  (let [node (-> (node-build)
                 (node-add-property "color" "green"))
        props (get-in node [:node_types :Node :properties])]
    (is (= (props {"color" "green"})))))

