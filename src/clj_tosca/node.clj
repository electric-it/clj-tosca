(ns clj-tosca.node)

;; Figure out later
#_(def NodeSchema
  {:node_types {:ServerNode {(s/optional-key :properties) {}
                             (s/optional-key :attributes) {}
                             (s/optional-key :requirements) {}
                             (s/optional-key :capabilities) {}
                             (s/optional-key :interfaces)   {}
                             (s/optional-key :artifacts)    {}}}})

(defn build []
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

(defn add [node type property value]
  (if (nil? value)
    node
    (update-in node [:node_types :ServerNode (keyword type)]
               assoc (keyword property) value)))


(defn add-property [node property value]
  (add node :properties property value))

(defn add-attribute [node attribute value]
  (add node :attributes attribute value))

(defn add-requirement [node requirement value]
  (add node :requirements requirement value))

(defn add-capability [node capability value]
  (add node :capabilities capability value))

(defn add-interface [node interface value]
  (add node :interfaces interface value))

(defn add-artifact [node artifact value]
  (add node :artifacts artifact value))

(defn add-metadata [node metadata value]
  (add node :metadatas metadata value))
