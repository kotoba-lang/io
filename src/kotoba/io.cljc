(ns kotoba.io
  "Assembled from one repo per definition.

  This namespace holds no implementation. It re-exports the definitions
  that each live in their own repo, so a call site can require one name
  and a library can require only the definitions it actually uses."
  (:require [kotoba.io.ireader :as ireader-ns]
            [kotoba.io.iwriter :as iwriter-ns]
            [kotoba.io.buffer-writer :as buffer-writer-ns]
            [kotoba.io.byte-buffer :as byte-buffer-ns]
            [kotoba.io.copy :as copy-ns]
            [kotoba.io.len :as len-ns]
            [kotoba.io.put :as put-ns]
            [kotoba.io.reader-buffer :as reader-buffer-ns]
            [kotoba.io.reader-seq :as reader-seq-ns]
            [kotoba.io.to-bytes :as to-bytes-ns]))

(def buffer-writer "See kotoba.io.buffer-writer/buffer-writer." buffer-writer-ns/buffer-writer)
(def byte-buffer "See kotoba.io.byte-buffer/byte-buffer." byte-buffer-ns/byte-buffer)
(def copy "See kotoba.io.copy/copy." copy-ns/copy)
(def len "See kotoba.io.len/len." len-ns/len)
(def put "See kotoba.io.put/put." put-ns/put)
(def read! "See kotoba.io.ireader/read!." ireader-ns/read!)
(def reader-buffer "See kotoba.io.reader-buffer/reader-buffer." reader-buffer-ns/reader-buffer)
(def reader-seq "See kotoba.io.reader-seq/reader-seq." reader-seq-ns/reader-seq)
(def to-bytes "See kotoba.io.to-bytes/to-bytes." to-bytes-ns/to-bytes)
(def write! "See kotoba.io.iwriter/write!." iwriter-ns/write!)
