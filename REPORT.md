# Fuzzing report: iase26 Assignment 04

Name(s) and student ID:
Rohit Sonejee - 4756184
Thanh Tran - 4209012

## Platform tested

Windows 11 x86_64

## Exercise 1: mutational fuzzer

The crash exit codes you found. For each exit code, give one representative input (or how to
generate it). Crashing inputs are grouped under `output/crashes/exit<code>/`, so the exit code is the
directory name. One representative per exit code is enough.

1. Exit Code `-1073741819` (Access Violation). Representative input:
   ```toml
   greeting="hello world
   words=["a", "b", "c"]
   matrix=[["a", "b"], ["c", "d"]]
   ```
2. Exit Code `-1073740940` (Heap Corruption). Representative input:
   ```toml
   greeting="hello world"
   words=["a", "b", "c"]
   matrix=[["a", "b"], ["c", ,d"]]
   ```

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
