# Design

## Why not clojush?
The main push implementation in Clojure is
[clojush](https://github.com/lspector/Clojush/). Since it is updated constantly,
has enough features, and is maintained by the creator of the Push language
himself, why make a whole new rewrite?

* The library's code is too verbose and hard to understand.
  [Ten lines](https://github.com/lspector/Clojush/blob/5d4239e5797087a6a8176c8e3f18b7cd5a80eb72/src/clojush/instructions/boolean.clj#L7-L15)
  to define the boolean `and` function?
* The Push language code is mixed with a genetic programming framework
  and and implementation of Push for that framework.
* No tests for the Push language code.
* No  documentation (although the GitHub wiki might count)
* I consider writing Push with Clojush to be unnecessarily verbose as well.

## Goals
* Full test coverage
    * Use the [midje](https://github.com/marick/Midje) test framework. It seems
      to read well and be easy to write and has a large user base.
    * Test each commit automatically through [Travis](https://travis-ci.org/)
    * Lint with [eastwood](https://github.com/jonase/eastwood)
    * Compute test coverage with [cloverage](https://github.com/lshift/cloverage)
    * Save test coverage to [Coveralls](https://coveralls.io/)
* Easy to understand documentation
    * Use [Autodoc](http://tomfaulhaber.github.io/autodoc/) for API documentation
        * Host on GitHub pages
    * Contribution guidelines in `docs/CONTRIBUTING.md`
    * High level docs in `docs/*.md`
* Understandable code
    * Use [clojure/core.typed](https://github.com/clojure/core.typed) for
      Push function definitions
    * Call Push interpreter in obvious RPN manner
        * i.e. `(push + 1 1)`
