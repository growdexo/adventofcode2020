(ns advent.of.code
   (:gen-class))

(defn v-byr [byr]
  (let [
        len (count byr)
        int (Integer/parseInt byr)
        ]
    (and
     (= len 4)
     (>= int 1920)
     (<= int 2002))
))

(defn v-iyr [iyr]
  (let [
        len (count iyr)
        int (Integer/parseInt iyr)
        ]
    (and
     (= len 4)
     (>= int 2010)
     (<= int 2020))
))

(defn v-eyr [eyr]
  (let [
        len (count eyr)
        int (Integer/parseInt eyr)
        ]
    (and
     (= len 4)
     (>= int 2020)
     (<= int 2030))
))

(defn v-hgt [hgt]
  (if (re-matches #"(\d+)(cm|in)" hgt)
    (let [
          suffix (apply str (take-last 2 hgt))
          amount (Integer/parseInt (apply str (drop-last 2 hgt)))
          ]
      (case suffix
        "in" (and (>= amount 59) (<= amount 76))
        "cm" (and (>= amount 150) (<= amount 193))
        ))
    false)
)

(def ecl-options #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"})
(defn v-ecl [ecl]
  (contains? ecl-options ecl))

(defn v-hcl [hcl]
  (if (re-matches #"#[0-9a-fA-F]{6}" hcl) true false))

(defn v-pid [pid]
  (if (re-matches #"[0-9]{9}" pid) true false))

(defn is-valid [passport-map]
(let [
      field-count (count passport-map)
      has-cid (if (get passport-map "cid") true false)
      ]
  (println passport-map)
  (and 
   (or 
    (if (= field-count 8) true false)
    (if (and
         (= field-count 7)
         (= has-cid false)
         ) true false))
   (v-byr (get passport-map "byr"))
   (v-iyr (get passport-map "iyr"))
   (v-eyr (get passport-map "eyr"))
   (v-hgt (get passport-map "hgt"))
   (v-ecl (get passport-map "ecl"))
   (v-hcl (get passport-map "hcl"))
   (v-pid (get passport-map "pid"))
)))

(defn parse-passport [passport]
  (into {} (map #(clojure.string/split % #":") passport)))

(defn get-passports []
  (map (comp parse-passport #(clojure.string/split % #"\s")) (clojure.string/split 
            (slurp "day4-input.txt") #"\n\n")))

(defn -main []
  (count (filter is-valid (get-passports)))
)
