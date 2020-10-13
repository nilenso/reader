# reader

Display webpages sans images

## usage

### setup

Install [leiningen](https://leiningen.org/#install) and run server from command line:
```
$ lein deps
$ lein run
```

Start, stop or restart server from REPL:
```
(server/start-app!)

(server/stop-app!)

(server/restart-app!)
```

### linting & formatting

Install [clj-kondo](https://github.com/borkdude/clj-kondo) and run linter for source and test files:
```
clj-kondo --lint src/
clj-kondo --lint test/
```

Fix formatting of source code:
```
lein cljfmt fix 
```