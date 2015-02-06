(ns looping-is-recursion)

(defn power [base exp]
  (let [powerhelper (fn [result base exp]
                      (if (= exp 1)
                        (* result base)
                        (recur (* result base) base (dec exp))))]
  (cond
   (= exp 0) 1
   (= exp 1) base
   :else (powerhelper 1 base exp))))

(defn last-element [a-seq]
  (if (empty? (rest a-seq))
    (first a-seq)
    (recur (rest a-seq))))

(defn seq= [seq1 seq2]
  (cond
   (and (empty? seq1) (empty? seq2)) true
   (and (empty? seq1) (not (empty? seq2))) false
   (and (empty? seq2) (not (empty? seq1))) false
   (= (first seq1) (first seq2)) (recur (rest seq1) (rest seq2))
   :else false))

(defn find-first-index [pred a-seq]
  (if (empty? a-seq)
    nil
    (loop [index 0
           a-seq a-seq]
      (if (pred (first a-seq))
        index
        (if (empty? (rest a-seq))
          nil
          (recur (inc index) (rest a-seq)))))))

(defn avg [a-seq]
  (if (empty? a-seq)
    0
    (loop [sum 0
           n 0
           a-seq a-seq]
      (if (empty? a-seq)
        (/ sum n)
        (recur (+ sum (first a-seq)) (inc n) (rest a-seq))))))

(defn toggle [a-set elem]
  (if (contains? a-set elem)
    (disj a-set elem)
    (conj a-set elem)))

(defn parity [a-seq]
  (loop [a-set #{}
         a-seq a-seq]
    (if (empty? a-seq)
      a-set
      (recur (toggle a-set (first a-seq)) (rest a-seq)))))

(defn fast-fibo [n]
  (cond
   (< n 2) n
   :else (loop [fn-2 0
                fn-1 1
                k 2]
           (cond
            (= k n) (+ fn-2 fn-1)
            :else (recur fn-1 (+ fn-2 fn-1) (inc k))))))


(defn cut-at-repetition [a-seq]
  (loop [seen-set #{}
         a-seq a-seq
         seq-until-rep []]
    (cond
     (empty? a-seq) seq-until-rep
     :else (if (contains? seen-set (first a-seq))
             seq-until-rep
             (recur
              (conj seen-set (first a-seq))
              (rest a-seq)
              (conj seq-until-rep (first a-seq)))))))
