(ns clj-tosca.node)

(defn build
  "Builds a server node with boilerplate format of type Compute."
  []
  (let [node {:node_types
              {:ServerNode
                {:type "tosca.nodes.Compute"
                 :properties {}
                 :attributes {}
                 :requirements {} 
                 :capabilities {}
                 :interfaces {}
                 :artifacts {}
                 :metadatas {}
                 }}}]
    node))

(defn ^:no-doc add [node type property value]
  (if (nil? value)
    node
    (update-in node [:node_types :ServerNode (keyword type)]
               assoc (keyword property) value)))


(defn add-property
  "Adds a property and value to properties."
  [node property value]
  (add node :properties property value))

(defn add-attribute
  "Adds an attribute and value to attributes."
  [node attribute value]
  (add node :attributes attribute value))

(defn add-requirement
  "Adds a requrement and value to requirements."
  [node requirement value]
  (add node :requirements requirement value))

(defn add-capability
  "Adds a capability and value to capabilities."
  [node capability value]
  (add node :capabilities capability value))

(defn add-interface
  "Adds an interface and value to interfaces."
  [node interface value]
  (add node :interfaces interface value))

(defn add-artifact
  "Adds an artifact and value to artifacts."
  [node artifact value]
  (add node :artifacts artifact value))

(defn add-metadata
  "Adds a metadata and value to metadates."
  [node metadata value]
  (add node :metadatas metadata value))
