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

## [0.1.0] - 2026-07-01

Initial public release. kotoba.lang.io — IReader/IWriter protocols + pure byte-buffer + copy.

### Added

- Initial library surface, tests, and CI.
