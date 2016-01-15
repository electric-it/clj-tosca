(ns clj-tosca.node-instance)

(defn build
  "Builds a node with state and properties."
  []
  {:node_instance {
                   :properties {}
                   }})

(defn ^:no-doc add [nodei property value]
  (if (nil? value)
    nodei
    (update-in nodei [:node_instance :properties]
               assoc (keyword property) value)))


(defn add-state
  "Adds the current state of this node."
  [node state]
  (if (nil? state)
    node
    (assoc-in node [:node_instance :state] state)))

(defn add-property
  "Adds a property and value to properties."
  [nodei name value]
  (let [node-w-prop (add nodei name value)]
    node-w-prop))
