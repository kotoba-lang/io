# kotoba-lang/io

[![CI](https://github.com/kotoba-lang/io/actions/workflows/ci.yml/badge.svg)](https://github.com/kotoba-lang/io/actions/workflows/ci.yml)

**Layer 3 (I/O) of the kotoba foundational stdlib** — the streaming abstraction
(`IReader` / `IWriter`) the other libs and actors re-roll, in one place. A pure
byte-buffer and `copy` make draining a reader into a writer a pure reduction.
Zero third-party runtime deps; every namespace is `.cljc` (JVM / SCI /
ClojureScript / GraalVM / kotoba-WASM). See
[`docs/adr/ADR-kotoba-lang-foundational-stdlib.md`](https://github.com/kotoba-lang/kotoba-lang/blob/main/docs/adr/ADR-kotoba-lang-foundational-stdlib.md).

## Why protocols

A capability-confined cell does not get a socket or a file descriptor — the
host grants a reader/writer handle. `io` defines the protocols and the pure
buffer/copy glue; the host (or `fs`/`http`'s protocol impls) supplies the
endpoints.

## Current surface

`kotoba.lang.io`:

- `IReader` protocol: `read!` → next chunk or nil (EOF)
- `IWriter` protocol: `write!` → append a chunk
- `byte-buffer` — pure growable byte buffer (`put`, `to-bytes`, `len`)
- `reader-buffer` / `buffer-writer` — adapt a byte-buffer as reader/writer
- `copy` — drain a reader into a writer (pure reduction)
- `reader-seq` — lazy seq from a reader

## Install

```clojure
io.github.kotoba-lang/io {:git/sha "<sha>"}
```

## Use

```clojure
(require '[kotoba.lang.io :as io])

(let [buf (io/byte-buffer)]
  (io/write! (io/buffer-writer buf) (byte-array (range 5)))
  (alength (io/to-bytes buf)))            ;=> 5
(let [src (io/reader-buffer (doto (io/byte-buffer) (io/put (byte-array (range 5)))))
      dst (io/byte-buffer)]
  (io/copy src (io/buffer-writer dst))
  (io/len dst))                           ;=> 5
```

## Verify

```sh
clojure -M:test
```
