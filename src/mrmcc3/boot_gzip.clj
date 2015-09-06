(ns mrmcc3.boot-gzip
  (:require [boot.core :refer :all]
            [boot.util :refer [info]]
            [clojure.java.io :as io])
  (:import java.util.zip.GZIPOutputStream))


(deftask gzip
  [i input bool "use input instead of output files"
   f files #{regex} "select files to gzip"]
  (let [tmp (tmp-dir!)]
    (with-pre-wrap fs
      (empty-dir! tmp)
      (info "\nGzipping Files...\n")
      (commit!
        (reduce
          (fn [fs f]
            (let [out-path (tmp-path f)
                  out-file (doto (clojure.java.io/file tmp out-path)
                             clojure.java.io/make-parents)]
              (info "• %s\n" out-path)
              (with-open [output (-> out-file io/output-stream GZIPOutputStream.)]
                (io/copy (tmp-file f) output))
              (-> (cp fs out-file f)
                  (add-meta {out-path {:gzip true}}))))
          fs
          (by-re files (if input
                         (input-files fs)
                         (output-files fs))))))))


