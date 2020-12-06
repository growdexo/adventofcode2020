(ns advent.of.code
   (:gen-class))

(def get-groups 
  (clojure.string/split 
   (slurp "day6-input.txt") #"\n\n"))

(defn part1
  [group]
   (count
    (into #{} (apply str (clojure.string/split group #"\n")))))

(defn part2
  [group]
  (println group)
  (count
   (apply clojure.set/intersection
          (map #(into #{} %) (clojure.string/split group #"\n")))))

(defn -main []
  (println
   (reduce +
           (map part1 get-groups)))
  (println (reduce + (map part2 get-groups)))
)
