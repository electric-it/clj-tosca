(ns clj-tosca.node-instance)

(defn build []
  {:node_instance {
                   :properties {}
                   }})

(defn add [nodei property value]
  (let [prev (->> (:node_instance nodei)
                  (:properties))
        new-val (merge prev {(keyword property) value})]
    (if (nil? value)
      nodei
      (assoc-in nodei [:node_instance :properties] new-val))))


(defn add-state [node state]
  (if (nil? state)
    node
    (assoc-in node [:node_instance :state] state)))

(defn add-property [nodei name value]
  (let [node-w-prop (add nodei name value)]
    node-w-prop))
