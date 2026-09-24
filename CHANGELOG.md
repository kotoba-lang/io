# Changelog

All notable changes to kotoba-lang/io are documented here.
Format: [Keep a Changelog](https://keepachangelog.com/). Semver per the
kotoba-lang stdlib compatibility policy (kotoba-lang/kotoba-lang/docs/lang/stdlib-versioning.md).

## [Unreleased] - 2026-09-24

### Added

- `kotoba.lang.io.file` (re-exported by `kotoba.io`): `file`, `as-file`,
  `as-relative-path`, `make-parents`, `delete-file` -- the portable subset of
  clojure.java.io. JVM delegates; Node answers with java.io.File's
  normalisation as path strings. Parity literals measured on the JVM.
- `kotoba.lang.io.file` / `kotoba.io`: the java.io.File methods as functions
  of a file value -- `exists?` `file?` `directory?` `hidden?` `absolute?`
  `file-name` `path` `parent` `parent-file` `absolute-path` `absolute-file`
  `canonical-path` `canonical-file` `mkdir` `mkdirs` `delete`
  `create-new-file` `rename-to` `length` `last-modified` `set-last-modified`
  `list-names` `list-files` `can-read?` `can-write?` `can-execute?`, and
  `file-seq`. JVM: the method itself. Node: java.io.UnixFileSystem
  transcribed (realpath(3), readdir order via opendir, JDK canonicalize).
  test/kotoba/lang/io/file_ops_test.cljk: a JVM-measured golden (raw
  java.io.File) that kotoba.io must reproduce on every host.

### Changed

- `make-parents` on Node is now `(mkdirs parent)` -- java.io.File#mkdirs
  transcribed (canonical parent) instead of `mkdir -p`.

## [0.1.0] - 2026-07-01

Initial public release. kotoba.lang.io — IReader/IWriter protocols + pure byte-buffer + copy.

### Added

- Initial library surface, tests, and CI.
