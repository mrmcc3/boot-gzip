# boot-gzip

Boot task for gzip-ing files.

```clj
[mrmcc3/boot-gzip "0.1.0-SNAPSHOT"]
```

### Usage

in build.boot
```clj
(set-env! :dependencies '[[mrmcc3/boot-gzip "0.1.0-SNAPSHOT"]])
(require '[mrmcc3.boot-gzip :refer [gzip]])
```

set the files you want to gzip

```clj
(task-options! gzip {:files #{#"^.*\.(js|css|html)$"}})
```

the gzip task does not rename files it adds `:gzip true` metadata instead.

### LICENSE

Copyright © 2015 Michael McClintock

Distributed under the Eclipse Public License, the same as Clojure.
