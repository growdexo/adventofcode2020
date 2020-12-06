(ns advent.of.code
   (:gen-class))

(defn get-lines [] (clojure.string/split-lines
                    (slurp "day3-input.txt")))
(defn is-tree [counter line]
  (let [
        line-size (count line)
        pos (mod counter line-size)
        ]
    (= 0 (compare \# (nth line pos)))
))

(defn count-trees
  ([counter tree-count ] tree-count)
  ([counter tree-count current-line & rest-lines]
   (let [
         next-call (partial count-trees 
                            (+ counter 3)
                            (if (is-tree counter current-line) (+ tree-count 1) tree-count)
                            )
         ]
     (apply next-call rest-lines)
     )))

(defn tree-counter [x-offset, y-offset]
  (fn 
    ([counter tree-count ] tree-count)
    ([counter tree-count current-line & rest-lines]
     (let [
           next-call (partial (tree-counter x-offset, y-offset) 
                              (+ counter x-offset)
                              (if (is-tree counter current-line) (+ tree-count 1) tree-count)
                              )
           ]
       (apply next-call (drop (- y-offset 1) rest-lines))
       ))))

(defn -main []
  ; part 1
  (println (apply (partial (tree-counter 3 1) 0 0) (get-lines)))
  ; part 2
  (let [
        slope1 (apply (partial (tree-counter 1 1) 0 0) (get-lines))
        slope2 (apply (partial (tree-counter 3 1) 0 0) (get-lines))
        slope3 (apply (partial (tree-counter 5 1) 0 0) (get-lines))
        slope4 (apply (partial (tree-counter 7 1) 0 0) (get-lines))
        slope5 (apply (partial (tree-counter 1 2) 0 0) (get-lines))
        ]
    (println (* slope1 slope2 slope3 slope4 slope5))))
