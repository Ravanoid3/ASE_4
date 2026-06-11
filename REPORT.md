# Fuzzing report: iase26 Assignment 04

Name(s) and student ID:

## Platform tested

(The OS and architecture you ran the fuzzer on, e.g. macOS 14 arm64, Ubuntu 22.04 x86_64,
Windows 11 x86_64.)

## Exercise 1: mutational fuzzer

The crash exit codes you found. For each exit code, give one representative input (or how to
generate it). Crashing inputs are grouped under `output/crashes/exit<code>/`, so the exit code is the
directory name. One representative per exit code is enough.

1. (input and exit code or signal)
2. (input and exit code or signal)

## Exercise 2: grammar-based fuzzer

### Which crash does the grammar-based fuzzer reach?
The grammar-based fuzzer crashes because of a stack overflow. It generates
valid inputs but the problem is that it nests deeper than the parser can handle.

### Why can neither a mutational nor a lexical (regular) fuzzer reach it?
A mutational fuzzer starts from existing inputs but applies
random changes, which will most likely break the specific input structure of
nested constructs instead of extending them to large depths.

From the lecture, we know that a mutational fuzzer can deepen a nested seed
input, but not reliably to an arbitrary depth.
Furthermore, a lexical fuzzer emits characters with no memory of nesting,
so it almost never produces a deep, balanced structure.

To conclude, both approaches cannot systematically generate inputs with the
required nesting depth to trigger the crash.
