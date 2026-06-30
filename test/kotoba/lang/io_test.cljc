(ns kotoba.lang.io-test
  (:require [clojure.test :refer [deftest is testing]]
            [kotoba.lang.io :as io]))

(defn- byte-arr [coll] (byte-array (map unchecked-byte coll)))

(deftest byte-buffer-put-and-len
  (let [buf (io/byte-buffer)]
    (is (zero? (io/len buf)))
    (io/put buf (byte-arr [1 2 3]))
    (is (= 3 (io/len buf)))
    (io/put buf (byte-arr [4 5]))
    (is (= 5 (io/len buf)))))

(deftest to-bytes-roundtrip
  (let [buf (io/byte-buffer)]
    (io/put buf (byte-arr [10 20 30]))
    (let [arr (io/to-bytes buf)]
      (is (= 3 #?(:clj (alength arr) :cljs (.-length arr))))
      (is (= [10 20 30] (map int (seq arr)))))))

(deftest buffer-writer-appends
  (let [buf (io/byte-buffer)
        w   (io/buffer-writer buf)]
    (io/write! w (byte-arr [1 2 3]))
    (io/write! w (byte-arr [4]))
    (is (= 4 (io/len buf)))
    (is (= [1 2 3 4] (map int (seq (io/to-bytes buf)))))))

(deftest reader-buffer-reads-once-then-eof
  (let [buf (doto (io/byte-buffer) (io/put (byte-arr [1 2 3])))
        r (io/reader-buffer buf)]
    (is (= [1 2 3] (map int (seq (io/read! r)))))
    (is (nil? (io/read! r)))))

(deftest copy-drains-reader-into-writer
  (let [src (io/reader-buffer (doto (io/byte-buffer) (io/put (byte-arr [1 2 3 4 5]))))
        dst (io/byte-buffer)
        w   (io/buffer-writer dst)]
    (io/copy src w)
    (is (= 5 (io/len dst)))
    (is (= [1 2 3 4 5] (map int (seq (io/to-bytes dst)))))))

(deftest reader-seq
  (let [mk (fn [] (io/reader-buffer (doto (io/byte-buffer) (io/put (byte-arr [7 8])))))]
    (is (= 1 (count (io/reader-seq (mk)))))
    (let [s (io/reader-seq (mk))]
      (is (= [7 8] (map int (seq (first s))))))))
