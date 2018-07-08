# The CRAFT (Colorado Richly Annotated Full-Text) Corpus

The contents of this repository consist of the v3.0 release of the CRAFT Corpus.  This release consists of 67 articles from the PubMed Central Open Access subset, each of which has been annotated along a number of different axes. For the syntactic annotation of the corpus, all sentences have been marked up with respect to sentence segmentation, tokenization, part-of-speech tags, grammatical dependency, and treebanking.  Document sections (e.g., Abstract, Results) and typography (e.g., italics, boldface, subscript, superscript) have also been specified.  Additionally, the corpus has been annotated with coreference relations, including identity and appositives, for all coreferring base noun phrases.  Finally, semantically, concepts mentioned in these articles have been mapped (“normalized”) to specific ontology classes, relying on ten Open Biomedical Ontologies.  (There is an additional README in the ontology-concepts directory with much more information about these concept annotations.)

For details of the concept annotations and citation:

Bada, M., Eckert, M., Evans, D., Garcia, K., Shipley, K., Sitnikov, D., Baumgartner Jr., W. A., Cohen, K. B., Verspoor, K., Blake, J. A., and Hunter, L. E. (2012) Concept annotation in the CRAFT corpus. BMC Bioinformatics 12:161.

For syntactic tool performance over CRAFT and citation:

Verspoor, K., Cohen, K.B., Lanfranchi, A., Warner, C., Johnson, H.L., Roeder, C., Choi, J.D., Funk, C., Malenkiy, Y., Eckert, M., Xue, N., Baumgartner Jr., W.A., Bada, M., Palmer, M., Hunter L.E. (2012) A corpus of full-text journal articles is a robust evaluation tool for revealing differences in performance of biomedical natural language processing tools. BMC Bioinformatics 13:207.

For an overview of the concept annotation guidelines and citation:

Bada, M., Eckert, M., Palmer, M., and Hunter, L.E. (2010) An overview of the CRAFT annotation guidelines.  Proceedings of the Fourth Linguistic Annotation Workshop, ACL 2010, pp. 207-211.

For evaluation of concept recognition tools on the concept annotations and citation:

Funk, C., Baumgartner, W.A., Garcia, B., Roeder, C., Bada, M., Cohen, K.B., Hunter, L.E., and Verspoor, K. (2014) Large-scale biomedical concept recognition: an evaluation   of current automatic annotators and their parameters. BMC Bioinformatics 15:59.

For details of the coreference annotations and citation:

Cohen, K.B., Lanfranchi, A., Choi, M.J., Bada, M., Baumgartner Jr., W.A., Panteleyeva, N., Verspoor, K., Palmer, M., and Hunter, L.E. (2017) Coreference annotation and  resolution in the Colorado Richly Annotated Full Text (CRAFT) corpus of biomedical  journal articles. BMC Bioinformatics 18:372.

For details of the Uberon anatomical annotations and citation:

Bada, M., Vasilevsky, N., Baumgartner Jr., W.A., Haendel, M., and Hunter, L.E. (2017) Gold-standard ontology-based anatomical annotation in the CRAFT Corpus. Database  Volume 2017, bax087.

The CRAFT Corpus is available for download in compressed form [here][craft release]

Or simply clone the contents of this repository using the following command:
  $ git clone https://github.com/UCDenver-ccp/CRAFT.git

The previous release of the CRAFT corpus (v2.0) is located [here][craft2.0]                                                                                                                                                               

## Directory Structure

### articles
#### ids
Contains a file listing the PubMed IDs contained in this distribution (craft-pmids-release) and a file mapping from PubMed ID to PubMed Central ID and original downloaded file name for all articles in this distribution (craft-idmappings-release).

#### nxml 
Contains the original XML for each article in this distribution as downloaded as part of the PubMed Central Open Access collection.

#### txt
Contains a plain text version of each article that was derived from the original XML files. NOTE: Annotation offsets included in this distribution are relative to the plain-text versions of the articles. The file name for any given article is its PubMed ID with a ".txt" extension. All CRAFT articles use UTF-8 encoding. Also included for each article are files containing the copyright information ([PUBMED_ID].copyright) and the article's references ([PUBMED_ID].references).

### coreference
Contains annotations of coreferential nouns/noun phrases, provided in brat, Knowtator XML, Knowtator 2 XML, and UIMA formats, for each article in this distribution. Details are specified in Cohen et al., 2017 (see citation above).

### dependency
Contains dependency parse trees for each sentence of every article that is part of this distribution. 

### ontology-concepts
Contains concept annotations mapped (“normalized”) to specific ontology classes for each article in this distribution.  Annotations are provided in brat, Knowtator XML, Knowtator 2 XML, and UIMA formats.  For each ontology, there are two subdirectories, one for annotations produced using only proper classes of the given ontology (e.g., ontology-concepts/CHEBI/CHEBI/), and the other for  annotations produced using these classes as well as extension classes created by  the CRAFT semantic annotators but defined in terms of existing ontology  classes  (e.g., ontology-concepts/CHEBI/CHEBI+extensions/).  Each subdirectory contains  the ontology file that should be used for the corresponding annotations (e.g.,  ontology-concepts/CHEBI/CHEBI.obo for the former, ontology-concepts/CHEBI/CHEBI+extensions/CHEBI+extensions.obo for the latter).  Most of these subdirectories also contain one or more mapping text files that may be useful, particularly when comparing automatically generated concept  annotations to the CRAFT concept annotations.  Details are specified in Bada et al., 2012 (see citation above), and users are also advised to read  ontology-concepts/README.md.

### sections-and-typography
Contains annotations for sections (e.g., Abstract, Results) and typography (e.g., italics, boldface, subscript, superscript) for each article in this distribution.  Annotations are provided in brat, Knowtator XML, Knowtator 2 XML, and UIMA formats.

### sentences-tokens-pos
Contains files showing sentence, token, and part-of-speech information in the GENIA-style POS-embedded XML format for each article in this distribution. Details are specified in Verspoor et al., 2012 (see citation above).

### treebank
Contains the full syntactic parse trees in Penn Treebank style for each sentence of each article in this distribution.  Details are specified in Verspoor et al., 2012 (see citation above).


## Character Encoding

UTF-8 encoding is used throughout the CRAFT project, so please default to UTF-8 when using CRAFT resources. 

## Formatting

The CRAFT Corpus has been made available in a number of different formats. Availability of the semantic and syntactic annotations in the various formats is detailed in the table below.

| Format          | Annotation Type(s)                                                                     |
|-----------------|----------------------------------------------------------------------------------------| 
| brat            | coreference, document sections and typography, ontology concepts                       |
| CoNLL dep. tree | dependency parse trees                                                                 |
| GENIA XML       | parts of speech, sentences, tokens                                                     |
| Knowtator XML   | coreference, document sections and typography, ontology concepts                       |
| Knowtator 2 XML | coreference, document sections and typography, ontology concepts                        |
| Penn TreeBank   | full syntactic trees                                                                   |
| UIMA XMI        | coreference, document sections and typography, full syntactic trees, ontology concepts |                        

### CoNLL Dependency Tree
Dependency parse trees were generated with the [CLEAR Parser][clear parser] using the CRAFT Treebank data as input. These parse trees have not been manually vetted.

### GENIA XML
Sentence, token, and part-of-speech annotations are represented in the GENIA-style POS-embedded XML format as defined in citeseerx.ist.psu.edu/viewdoc/citations;jsessionid...?doi=10.1.1.106.9947.
        
### Knowtator XML
The XML output format produced by the original [Knowtator][knowtator] for the frames-based Protege application (v3.3.1).  Annotation offsets in this format are relative to the plain text versions of the articles that can be found in the articles/txt directory.
  
### Knowtator 2 XML
The XML standoff output format produced by [Knowtator 2][knowtator2], implemented for Protege 5+.  (We believe this is a more intuitive format than that for the originalProtege-Frames-based Knowtator.)

### Penn TreeBank
Full syntactic parse trees in the Penn Treebank style.
  
### UIMA XMI
Versions of a UIMA CAS data structure representing each article and its annotations have been serialized using the UIMA XMI data format. Concept annotations are represented using the CCP Type System (CCPTypeSystem.xml; included in this distribution) while Treebank data is represented using [ClearTK][cleartk] type system.
     

## Browsing CRAFT

### brat
Each annotation set that has been provided in the brat format has been packaged such that users can directly open it in a local [brat installation][brat].

Additionally, users may view annotations in our locally hosted online [brat viewer][brat viewer].  Here, the annotations for any one article at a time are shown.

To view the details of a given annotation, simply hover over the class or category used to annotate the given text span(s).  Note that brat cannot properly highlight the text of discontinuous annotations, i.e., annotations  of one or more discontinuous text spans; instead, a single continuous text span is highlighted, but the correct discontinuous text spans can be seen  in the box that pops up upon hovering over the annotation class/category.

### Knowtator 2
Each annotation set that has been provided in the Knowtator 2 format has been packaged such that users can directly open it in a local [Knowtator 2][knowtator2].
   
       
## Feedback

Please direct comments, questions, and suggestions to the Issues section of the CRAFT GitHub page, or send e-mail to Mike Bada at mike.bada@ucdenver.edu.

[craft release]: https://github.com/UCDenver-ccp/CRAFT/releases
[craft2.0]: http://bionlp-corpora.sourceforge.net/CRAFT/
[clear parser]: https://code.google.com/archive/p/clearparser/
[knowtator]: http://knowtator.sourceforge.net/
[knowtator2]: https://github.com/UCDenver-ccp/Knowtator-2.0
[brat viewer]: http://compbio.ucdenver.edu/Craft/index.xhtml#/
[brat]: http://brat.nlplab.org
[cleartk]: http://code.google.com/p/cleartk/
