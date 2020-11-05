# reader

Display webpages in reader mode

## usage

### setup

Install [Redis](https://redis.io/download) and run its server.

Then install [leiningen](https://leiningen.org/#install) and run reader's ring 
jetty server from command line:

```
$ lein deps
$ lein run
```

Then navigate to http://localhost:8080 and pass webpage urls as url query
parameters.

Start, stop or restart the reader server from REPL:
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
