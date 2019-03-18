(set-env! :dependencies '[[edu.ucdenver.ccp/file-conversion-onejar "0.1"]]
          :repositories {"bionlp" "https://svn.code.sf.net/p/bionlp/code/repo/"})
(require '[clojure.java.io :refer [file]]
         '[clojure.java.io :as io])
(import edu.ucdenver.ccp.common.file.CharacterEncoding
        edu.ucdenver.ccp.file.conversion.FileFormatConverter
        edu.ucdenver.ccp.file.conversion.InputFileFormat
        edu.ucdenver.ccp.file.conversion.OutputFileFormat
        edu.ucdenver.ccp.file.conversion.brat.BratConfigFileWriter
        edu.ucdenver.ccp.file.conversion.treebank.TreebankToDependencyConverter
        edu.ucdenver.ccp.file.conversion.treebank.HeadRule)

;;; ======== fileset contents ========
;;;
;;; :annotation-types ---------- list of annotation types to be processed. Order of types in the list will be consistent
;;;                              with ordering in lists described below.
;;;
;;; :annotation-directories ---- one for each annotation-type; the directory where the native annotation format files can be found
;;;
;;; :native-formats ------------ one for each annotation-type; the file format used to store a given annotation type in
;;;                              the specified annotation directory (above)
;;;
;;; :valid-formats ------------- valid file formats to which the specified annotation types can be converted
;;;
;;; :output-directory-base ----- used for annotation types that are derived from other annotation types, e.g. the POS
;;;                              annotations are derived from the treebank annotations; this parameter specifies a base
;;;                              directory where files for this annotation type can be written
;;;
;;; :include-extensions -------- used in conjunction with the concept annotation type to indicate use of extension
;;;                              classes for a given concept type
;;;
;;; ==================================

(def craft-file-paths {:text-file-directory               (file "articles" "txt")
                       :knowtator-hidden-directory        (file ".knowtator2")
                       :pmid-to-pmcid-map-file            (file "articles" "ids" "craft-idmappings-release")
                       :concept-annotation-base-directory (file "concept-annotation")})

(defn consolidate-formats [fileset valid-annotation-formats]
  "intersect the specified annotation formats with those already in the fileset
  to get a set of formats that are valid for all annotation types under consideration"
  (if (nil? (:valid-formats fileset))
    valid-annotation-formats
    (clojure.set/intersection (:valid-formats fileset) valid-annotation-formats)))


(defn update-fileset [fileset annotation-type native-format annotation-directory valid-formats include-extensions output-directory-base]
  "The fileset stores lists of many of the input parameters. This function merges parameters for a single
  annotation type with those already stored in the fileset."
  (let [all-annotation-types (conj (:annotation-types fileset) annotation-type)
        all-include-extensions (conj (:include-extensions fileset) include-extensions)
        all-annotation-directories (conj (:annotation-directories fileset) annotation-directory)
        all-native-formats (conj (:native-formats fileset) native-format)
        all-valid-formats (consolidate-formats fileset valid-formats)
        all-output-directory-base (conj (:output-directory-base fileset) output-directory-base)]
    (merge fileset {:annotation-types          all-annotation-types
                    :include-extensions        all-include-extensions
                    :annotation-directories    all-annotation-directories
                    :native-formats            all-native-formats
                    :valid-formats             all-valid-formats
                    :all-output-directory-base all-output-directory-base})))

;;; =================================
;;; ==== DEFINE ANNOTATION TYPES ====
;;; =================================

(deftask coreference []
         "Indicates that coreference annotations will be processed"
         (with-pre-wrap fileset
                        (let [annotation-type :coreference
                              annotation-directory (file "coreference-annotation" "knowtator")
                              native-format InputFileFormat/KNOWTATOR
                              valid-formats #{:conll-coref-ident :conll-coref-appos :pubannotation :uima :knowtator2}]
                          (update-fileset fileset annotation-type native-format annotation-directory valid-formats nil nil))))

(deftask concept
         "Indicates that concept annotations will be processed."
         [t concept-type VAL str "indicates annotation type to be processed. Must be one of CHEBI, CL, GO_BP, GO_CC, GO_MF, MOP, NCBITaxon, PR, SO, or UBERON to indicate all concept types should be processed. Note case-sensitivity in the concept types."
          x include-extensions bool "indicates that extension classes should be included"]
         (with-pre-wrap fileset
                        (let [valid-concept-types #{"CHEBI" "CL" "GO_BP" "GO_CC" "GO_MF" "MOP" "NCBITaxon" "PR" "SO" "UBERON"}
                              annotation-type (if include-extensions (str concept-type "+extensions") concept-type)
                              annotation-type-symbol (symbol (str ":" annotation-type))
                              annotation-directory (file "concept-annotation" concept-type annotation-type "knowtator")
                              native-format InputFileFormat/KNOWTATOR
                              valid-formats #{:brat :bionlp :pubannotation :uima :knowtator2}]
                          (if (not (contains? valid-concept-types concept-type))
                            (throw (IllegalArgumentException. (str "Invalid concept type requested: [" concept-type "]. Valid concept types include: "
                                                                   (clojure.string/join ", " (sort valid-concept-types))
                                                                   ". Note case-sensitivity."))))
                          (update-fileset fileset annotation-type-symbol native-format annotation-directory valid-formats include-extensions nil))))


(deftask all-concepts
         "convenience task for selecting all concept types"
         [x include-extensions bool "indicates that extension classes should be included"]
         (comp (concept :concept-type "CHEBI" :include-extensions include-extensions)
               (concept :concept-type "CL" :include-extensions include-extensions)
               (concept :concept-type "GO_BP" :include-extensions include-extensions)
               (concept :concept-type "GO_CC" :include-extensions include-extensions)
               (concept :concept-type "GO_MF" :include-extensions include-extensions)
               (concept :concept-type "MOP" :include-extensions include-extensions)
               (concept :concept-type "NCBITaxon" :include-extensions include-extensions)
               (concept :concept-type "PR" :include-extensions include-extensions)
               (concept :concept-type "SO" :include-extensions include-extensions)
               (concept :concept-type "UBERON" :include-extensions include-extensions)))

(deftask dependency []
         "Indicates that dependency parse annotations will be processed"
         (with-pre-wrap fileset
                        (let [annotation-type :dependency
                              annotation-directory (file "structural-annotation" "dependency" "conllu")
                              native-format InputFileFormat/CONLL_U
                              valid-formats #{:brat :bionlp :pubannotation :uima :knowtator2 :conll-u}]
                          (update-fileset fileset annotation-type native-format annotation-directory valid-formats false nil))))

(deftask treebank []
         "Indicates that treebank annotations will be processed"
         (with-pre-wrap fileset
                        (let [annotation-type :treebank
                              annotation-directory (file "structural-annotation" "treebank" "penn")
                              native-format InputFileFormat/TREEBANK
                              valid-formats #{:brat :bionlp :pubannotation :uima :knowtator2}]
                          (update-fileset fileset annotation-type native-format annotation-directory valid-formats false nil))))

(deftask sentence []
         "Indicates that sentence annotations will be processed"
         (with-pre-wrap fileset
                        (let [annotation-type :sentence
                              annotation-directory (file "structural-annotation" "treebank" "penn")
                              native-format InputFileFormat/TREEBANK_SENTENCE_TOKEN
                              valid-formats #{:brat :bionlp :pubannotation :uima :sentence :knowtator2}
                              output-directory-base (file "structural-annotation" "sentences")]
                          (update-fileset fileset annotation-type native-format annotation-directory valid-formats false output-directory-base))))

(deftask part-of-speech []
         "Indicates that token/POS annotations will be processed"
         (with-pre-wrap fileset
                        (let [annotation-type :part-of-speech
                              annotation-directory (file "structural-annotation" "treebank" "penn")
                              native-format InputFileFormat/TREEBANK_SENTENCE_TOKEN
                              valid-formats #{:brat :bionlp :pubannotation :uima :conll-u :conll-coref-ident :conll-coref-appos :knowtator2}
                              output-directory-base (file "structural-annotation" "part-of-speech")]
                          (update-fileset fileset annotation-type native-format annotation-directory valid-formats false output-directory-base))))

(deftask document-section []
         "Indicates that document-section annotations will be processed"
         (with-pre-wrap fileset
                        (let [annotation-type :document-section
                              annotation-directory (file "structural-annotation" "sections-and-typography" "knowtator")
                              native-format InputFileFormat/KNOWTATOR
                              valid-formats #{:brat :bionlp :pubannotation :uima :knowtator2}
                              output-directory-base (file "structural-annotation" "sections-and-typography" "sections")]
                          (update-fileset fileset annotation-type native-format annotation-directory valid-formats false output-directory-base))))

(deftask typography []
         "Indicates that typography (bold, italic, etc.) annotations will be processed"
         (with-pre-wrap fileset
                        (let [annotation-type :typography
                              annotation-directory (file "structural-annotation" "sections-and-typography" "knowtator")
                              native-format InputFileFormat/KNOWTATOR
                              valid-formats #{:brat :bionlp :pubannotation :uima :knowtator2}
                              output-directory-base (file "structural-annotation" "sections-and-typography" "typography")]
                          (update-fileset fileset annotation-type native-format annotation-directory valid-formats false output-directory-base))))


;;; ============================================
;;; === FUNCTIONS FOR PROCESSING ANNOTATIONS ===
;;; ============================================

(def pmidToPmcidMap
  "load a map of pmid-to-pmcid from the id-mapping file distributed with CRAFT. We exclude the first column
  (nxml file name) and swap the order of the pmcid and pmid columns to get the correct ordering for the map."
  (into {} (map (fn [line] (into [] (reverse (rest (clojure.string/split line #"\t")))))
                ;; rest - to skip the header
                (rest (clojure.string/split-lines (slurp (:pmid-to-pmcid-map-file craft-file-paths)))))))


(def output-format-map {:bionlp            {:file-suffix ".bionlp" :dir-name "bionlp" :format OutputFileFormat/BIONLP}
                        :brat              {:file-suffix ".ann" :dir-name "brat" :format OutputFileFormat/BRAT}
                        :conll-coref-ident {:file-suffix ".conll" :dir-name "conllcoref2012_ident" :format OutputFileFormat/CONLL_COREF_2012_IDENT}
                        :conll-coref-appos {:file-suffix ".conll" :dir-name "conllcoref2012_appos" :format OutputFileFormat/CONLL_COREF_2012_APPOS}
                        :conll-u           {:file-suffix ".conllu" :dir-name "conllu" :format OutputFileFormat/CONLL_U}
                        :knowtator2        {:file-suffix ".xml" :dir-name "knowtator-2" :format OutputFileFormat/KNOWTATOR2}
                        :pubannotation     {:file-suffix ".json" :dir-name "pubannotation" :format OutputFileFormat/PUBANNOTATION}
                        :sentence          {:file-suffix ".sentence" :dir-name "sentence" :format OutputFileFormat/SENTENCE}
                        :uima              {:file-suffix ".xmi" :dir-name "uima" :format OutputFileFormat/UIMA}})


(defn ensure-single-format-specified [bionlp brat conll-coref-ident conll-coref-appos conll-u knowtator2 pubannotation sentence uima]
  "ensure that only one of the output formats has been selected, otherwise error"
  (if (not= 1 (+ (if bionlp 1 0)
                 (if brat 1 0)
                 (if conll-coref-ident 1 0)
                 (if conll-coref-appos 1 0)
                 (if conll-u 1 0)
                 (if knowtator2 1 0)
                 (if pubannotation 1 0)
                 (if sentence 1 0)
                 (if uima 1 0)))
    (throw (IllegalArgumentException.
             "Zero or more than one output format has been requested. Please select one and only one."))))

(defn get-output-format-symbol [bionlp brat conll-coref-ident conll-coref-appos conll-u knowtator2 pubannotation sentence uima]
  "returns a symbol representing the specified output format"
  (cond
    bionlp :bionlp
    brat :brat
    conll-coref-ident :conll-coref-ident
    conll-coref-appos :conll-coref-appos
    conll-u :conll-u
    knowtator2 :knowtator2
    pubannotation :pubannotation
    sentence :sentence
    uima :uima
    :else (throw (IllegalArgumentException.
                   (str "Unhandled output format. Boot script adjustment likely required.")))))

(defn get-output-directory [fileset user-specified-output-dir default-output-dir]
  "if the user specified an output-directory using the -o parameter, then use that one; otherwise, look to see if there is
   an output-directory-base specified. Use it if so. If not, place the output directory next to the native format directory,
   unless there are more that one native formats specified. In cases when more than one native format is specified,
   the user must supply an output directory explicitly."
  (if (nil? user-specified-output-dir)
    (if (> (count (:annotation-directories fileset)) 1)
      (throw (IllegalArgumentException. "Because there is more than a single annotation type specified, the user is
      required to explicitly specify an output directory.
      Please use the 'convert -o' parameter to specify an output directory."))
      (if (nil? (first (:all-output-directory-base fileset)))
        (file (.getParentFile (file (first (:annotation-directories fileset))))
              default-output-dir)
        (file (first (:all-output-directory-base fileset))
              default-output-dir)))
    (file user-specified-output-dir)))

(defn validate-requested-format [requested-format valid-formats annotation-type]
  "checks that the requested format is a valid conversion format for the annotation type"
  (if (not (some #{requested-format} valid-formats))
    (throw (IllegalArgumentException.
             (str "Invalid conversion format requested for annotation type. Unable to convert annotation type: "
                  annotation-type " to format: " requested-format
                  ". Valid conversion formats include the following: " valid-formats)))))

(def concept-to-color-map {"CHEBI"     "bgColor:#32cd32"
                           "CL"        "bgColor:#ffa500"
                           "GO_BP"     "bgColor:#00ffff"
                           "GO_CC"     "bgColor:#ff0000"
                           "GO_MF"     "bgColor:#7fff00"
                           "MOP"       "bgColor:#deb887"
                           "NCBITAXON" "bgColor:#0f0f0f"
                           "PR"        "bgColor:#340034"
                           "SO"        "bgColor:#981198"
                           "UBERON"    "bgColor:#5f9ea0"})


(defn get-ontology-files [input]
  "get ontology files for a particular annotation-type"
  (let [[annotation-type annotation-directory] input]
    ;; gather the ontology file(s) relevant to the specified annotation-type
    (filter #(and (.isFile %) (.endsWith (.getName %) ".obo.zip"))
            (file-seq (.getParentFile annotation-directory)))))


(defn get-ontology-files-fileset [fileset]
  "get all relevant ontology files based on information stored in the fileset"
  (let [annotation-types (:annotation-types fileset)
        annotation-directories (:annotation-directories fileset)]
    (doall (map get-ontology-files (map vector annotation-types annotation-directories)))))


(defn get-annotation-files [input-directories]
  "returns a sequence of vectors containing the nth file from each input-directory"
  (apply map vector
         (map (fn [input-directory] (sort (filter #(.isFile %) (file-seq input-directory))))
              input-directories)))


(deftask convert
         "Do the actual file conversion from the native format to a specified output format."
         [b bionlp bool "output BioNLP format"
          r brat bool "output BRAT format"
          i conll-coref-ident bool "output identity chains in CoNLL Coreference 2011/12 format"
          a conll-coref-appos bool "output apposition chains in CoNLL Coreference 2011/12 format"
          u conll-u bool "output CoNLL-U format"
          k knowtator2 bool "output Knowtator2 format"
          p pubannotation bool "output PubAnnotation format"
          m uima bool "output UIMA format"
          s sentence bool "output one sentence per line"
          n use-pmcid bool "output file will use pmc id instead of the default pmid as part of the file name"
          o output-directory VAL str "the directory where files will be saved [OPTIONAL]. If not set, files will be placed in
                              an appropriately named directory on the same level as the native-format directory for
                              the annotations being processed."]
         (with-pre-wrap fileset
                        (ensure-single-format-specified bionlp brat conll-coref-ident conll-coref-appos conll-u knowtator2 pubannotation sentence uima)
                        (let [output-format (get-output-format-symbol bionlp brat conll-coref-ident conll-coref-appos conll-u knowtator2 pubannotation sentence uima)
                              output-file-props (output-format output-format-map)
                              output-directory (get-output-directory fileset output-directory (:dir-name output-file-props))]
                          (validate-requested-format output-format (:valid-formats fileset) (:annotation-types fileset))
                          (println (str "converting " (str (:annotation-types fileset)) " annotations to " output-format " ..."))
                          (println (str "output directory: " (.getAbsolutePath (file output-directory))))

                          (.mkdirs output-directory)
                          (doall (map (fn [input-files]
                                        (map (fn [f] (println (str "input directories: " f))) input-files)
                                        (let [firstfile (first input-files)
                                              pmid (.substring (.getName firstfile) 0 (.indexOf (.getName firstfile) "."))
                                              text-file (file (:text-file-directory craft-file-paths) (str pmid ".txt"))
                                              source-db "PMC"
                                              pmcid (get pmidToPmcidMap pmid)
                                              output-file (file output-directory
                                                                (str (if use-pmcid pmcid pmid) (:file-suffix output-file-props)))
                                              source-formats (:native-formats fileset)
                                              target-format (:format output-file-props)]

                                          (FileFormatConverter/convert source-formats
                                                                       target-format
                                                                       (.substring pmcid 3) ;; remove "PMC" prefix
                                                                       source-db
                                                                       input-files
                                                                       output-file
                                                                       text-file
                                                                       CharacterEncoding/UTF_8)))
                                      (get-annotation-files (:annotation-directories fileset))))

                          ;; if this is the BRAT format, the brat configuration files must be generated after the annotation files
                          (if brat (BratConfigFileWriter/createConfFiles output-directory (into () (flatten (get-ontology-files-fileset fileset)))
                                                                         CharacterEncoding/UTF_8 concept-to-color-map)))
                        fileset))



(deftask formats? []
         "List formats to which the active annotation type can be converted."
         (with-pre-wrap fileset
                        (println (str "Annotation type: " (clojure.string/join "," (:annotation-types fileset))
                                      " can be converted to the following formats: " (clojure.string/join "," (:valid-formats fileset))))
                        fileset))


(defn copy-and-unzip-file [destination-dir zipped-file]
  "copy a zipped file and place an unzipped copy in a specified destination directory"
  ;; remove .zip in the destination file name, unzip the file when it is copied
  (let [dest-file (file destination-dir (subs (.getName zipped-file) 0 (- (.length (.getName zipped-file)) 4)))
        stream (-> (io/input-stream zipped-file)
                   (java.util.zip.ZipInputStream.))]
    (.getNextEntry stream)
    (io/copy stream dest-file)))


(deftask knowtator-project-setup
         "Create the directory structure for a Knowtator2 project for the specified annotations"
         [o output-directory VAL str "the directory where the Knowtator2 project will be created"]
         (with-pre-wrap fileset
                        (let [article-dir (file output-directory "Articles")
                              annotation-dir (file output-directory "Annotations")
                              ontology-dir (file output-directory "Ontologies")
                              profile-dir (file output-directory "Profiles")]

                          ;; create directories
                          (.mkdirs article-dir)
                          (.mkdirs annotation-dir)
                          (.mkdirs ontology-dir)
                          (.mkdirs profile-dir)

                          ;; copy text files
                          (doall (map (fn [txt-file] (let [dest-file (file article-dir (.getName txt-file))]
                                                       (io/copy txt-file dest-file)))
                                      (filter #(and (.isFile %) (.endsWith (.getName %) ".txt"))
                                              (file-seq (:text-file-directory craft-file-paths)))))

                          ;; copy default profile
                          (let [profile-file-name "Default.xml"
                                default-profile-file (file (:knowtator-hidden-directory craft-file-paths) profile-file-name)]
                            (io/copy default-profile-file (file profile-dir profile-file-name)))

                          ;; create knowtator2.knowtator file (empty)
                          (.createNewFile (file output-directory "knowtator-2.knowtator"))

                          ;; copy and unzip ontology (if necessary)
                          (doall (map (fn [ont-file]
                                        (copy-and-unzip-file ontology-dir ont-file))
                                      (flatten (get-ontology-files-fileset fileset)))))
                        fileset))


(deftask knowtator-project
         "Create the directory structure for a Knowtator2 project and populated it with annotation files"
         [o output-directory VAL str "the directory where the Knowtator2 project will be created"]
         (comp (knowtator-project-setup :output-directory output-directory)
               (convert :knowtator2 true :output-directory (.getAbsolutePath (file output-directory "Annotations")))))



;;; Third-party code for transforming treebanks to dependency parses
(deftask treebank-to-dependency []
         "convert constituent parse files to dependency parse files"
         (with-pre-wrap fileset
                        (let [treebank-dir (file "structural-annotation" "treebank" "penn")
                              dependency-dir (file "structural-annotation" "dependency" "conllu")]
                          (.mkdirs dependency-dir)
                          (TreebankToDependencyConverter/convert treebank-dir dependency-dir HeadRule/STANFORD))
                        fileset))