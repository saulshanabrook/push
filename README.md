# push

[![Build Status](https://travis-ci.org/saulshanabrook/push.png)](https://travis-ci.org/saulshanabrook/push)
[![Coverage Status](https://coveralls.io/repos/saulshanabrook/push/badge.png)](https://coveralls.io/r/saulshanabrook/push)

An implementation of the
[Push programming language](http://faculty.hampshire.edu/lspector/push.html).

>Push is a programming language designed for evolutionary computation, to be
used as the programming language within which evolving programs are expressed.
A  concise introduction to the most recent standardized version of the language
("Push3") is contained in
[The Push 3.0 Programming Language Description](http://faculty.hampshire.edu/lspector/push3-description.html).

## Usage

```clojure
user=> (require '[push.core :refer [push]])
nil
user=> (push 1 2 3)
{java.lang.Long (3 2 1), :exec ()}
user=> (push 1 2 3 "hi" "hello")
{java.lang.String ("hello" "hi"), java.lang.Long (3 2 1), :exec ()}
user=> (push 1 2 3 "hi" "hello" identity)
{java.lang.String ("hello" "hi"), java.lang.Long (3 2 1), :exec ()}
user=> (push 1 2 3 "hi" "hello" #(assoc % :hey "hi"))
{:hey "hi", java.lang.String ("hello" "hi"), java.lang.Long (3 2 1), :exec ()}
user=> (push 1 2 3 "hi" "hello" #(assoc % :exec '()))
{java.lang.String ("hello" "hi"), java.lang.Long (3 2 1), :exec ()}
user=> (push 1 2 #(assoc % :exec '()) 3 "hi" "hello" #(assoc % :exec '()))
{java.lang.Long (2 1), :exec ()}

; push is just a shortcut for push-exec, followed by execute
user=> (push-exec [1 2 3 4])
{:exec (1 2 3 4)}
user=> (execute (push-exec [1 2 3 4]))
{java.lang.Long (4 3 2 1), :exec ()}
user=>
```
