# push

An implementation of the
[Push programming languagee](http://faculty.hampshire.edu/lspector/push.html).

>Push is a programming language designed for evolutionary computation, to be
used as the programming language within which evolving programs are expressed.
A  concise introduction to the most recent standardized version of the language
("Push3") is contained in
[The Push 3.0 Programming Language Descriptionn](http://faculty.hampshire.edu/lspector/push3-description.html).

## How to run the tests

`lein midje` will run all tests.

`lein midje namespace.*` will run only tests beginning with "namespace.".

`lein midje :autotest` will run all the tests indefinitely. It sets up a
watcher on the code files. If they change, only the relevant tests will be
run again.
