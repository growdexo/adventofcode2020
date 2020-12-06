(ns advent.of.code
   (:gen-class))

(def get-lines 
  (clojure.string/split 
   (slurp "day5-input.txt") #"\n"))

(def possible-rows (into [] (range 0 128)))
(def possible-seats (into [] (range 0 8)))

(defn take-upper [rows] 
  (drop (/ (count rows) 2) rows
        ))

(defn take-lower [rows]
  (take (/ (count rows) 2) rows
      ))

(defn take-partition [rows letter]
  (if (or 
       (= 0 (compare letter \F))
       (= 0 (compare letter \L))
) 
    (take-lower rows)
    (take-upper rows)
    ))

(defn get-row-r 
  ([rows] (first rows))
  ([rows current-letter & letters] 
   (apply 
    (partial get-row-r 
             (take-partition rows current-letter)) 
    letters)
   ))

(defn get-row [letters]
  (apply (partial get-row-r possible-rows) letters)
  )

(defn get-seat [letters]
  (apply (partial get-row-r possible-seats) letters)
)

(defn get-seat-id [[row seat]]
  (+ (* row 8) seat))

(defn get-row-and-seat [letters]
  [(get-row (take 7 letters)) (get-seat (drop 7 letters))]
)

(defn get-all-seat-ids []
  (map (comp get-seat-id get-row-and-seat) get-lines))

(defn get-my-seat-id []
   (+ 1 (first 
         (first 
          (filter #(= 
                    (- (second %) (first %))
                    2
                    ) (partition 2 1 (sort (get-all-seat-ids))
                                 ))))))

(defn -main [] 
  (apply max (get-all-seat-ids)))
