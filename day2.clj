(ns advent.of.code
   (:gen-class))

(defn get-min-max [len-params]
  (map #(Integer/parseInt %) 
       (clojure.string/split len-params  #"-"
        )))

(defn clean-letter [letter]
  (first letter
))

(defn is-validP1 [[len-params dirty-letter password]]
  (let 
      [
       [min max] (get-min-max len-params)
       letter (clean-letter dirty-letter)
       letter-count (count (filter #(= (compare % letter) 0) password))
       ]
    (and (>= letter-count min) (<= letter-count max))
))

(defn is-validP2 [[len-params dirty-letter password]]
(let [
      [pos1 pos2] (get-min-max len-params)
      letter (clean-letter dirty-letter)
      letter-count  (count
                     (filter #(= (compare % letter) 0)
                             [(nth password (- pos1 1)) (nth password (- pos2 1))]))
      ]
  (= letter-count 1)
))

(defn count-valid [validity-array] (count (filter (comp not false?) validity-array)))

(defn get-lines [validation-func] 
  (with-open [rdr (clojure.java.io/reader "./day2-input.txt")]
     (map #(validation-func (clojure.string/split % #" "))
          (reduce conj [] (line-seq rdr)))))

(defn Day2P1 [] 
  (count-valid (get-lines is-validP1)))
  
(defn Day2P2 []
(count-valid (get-lines is-validP2)))

(defn -main
  []
  (println (Day2P1))
  (println (Day2P2)))

  
  
