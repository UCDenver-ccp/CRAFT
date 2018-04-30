 ==============================================================================
 ====        The CRAFT (Colorado Richly Annotated Full-Text) Corpus        ====
 ==============================================================================

The contents of this downloaded tarball consist of the initial release of the 
CRAFT Corpus, v3.0. This release consists of 67 articles from the PubMed Central
Open Access subset. Each article has been annotated both syntactically and 
semantically. For the syntactic annotation of the corpus, all sentences have 
been marked up with regard to sentence segmentation, tokenization, and 
part-of-speech tagging, and manually curated syntactic parses for each sentence 
are available in Penn Treebank format.  Additionally, the articles have been 
coreferentially annotated.  The concept annotation identifies each 
mention of classes from ten biomedical ontologies:

Chemical Entities of Biological Interest (CHEBI): chemical entities, subatomic 
particles, and chemical roles
Cell Ontology (CL): cells
Gene Ontology Biological Process (GO_BP): biological processes
Gene Ontology Cellular Component (GO_CC): cellular and extracellular components and 
regions
Gene Ontology Molecular Function (GO_MF): molecular functionalities possessed by 
genes/gene products
Molecular Process Ontology (MOP): chemical/molecular reactions and other processes
NCBI Taxonomy (NCBITaxon): biological taxa and taxon levels
Protein Ontology (PR): proteins (which are also used to annotate corresponding genes 
and transcripts)
Sequence Ontology (SO): biomacromolecular entities, sequence features and attributes
Uberon (UBERON): anatomical entities and multicellular organisms defined in terms of 
developmental and sexual characteristics

For details of the concept annotations and citation:

  Bada, M., Eckert, M., Evans, D., Garcia, K., Shipley, K., Sitnikov, D., Baumgartner 
  Jr., W. A., Cohen, K. B., Verspoor, K., Blake, J. A., and Hunter, L. E. (2012) 
  Concept annotation in the CRAFT corpus. BMC Bioinformatics 12:161.

For syntactic tool performance over CRAFT and citation:

  Verspoor, K., Cohen, K.B., Lanfranchi, A., Warner, C., Johnson, H.L., Roeder, C., Choi,
  J.D., Funk, C., Malenkiy, Y., Eckert, M., Xue, N., Baumgartner Jr., W.A., Bada, M.,
  Palmer, M., Hunter L.E. (2012) A corpus of full-text journal articles is a robust 
  evaluation tool for revealing differences in performance of biomedical natural language 
  processing tools. BMC Bioinformatics 13:207.

For an overview of the concept annotation guidelines and citation:

  Bada, M., Eckert, M., Palmer, M., and Hunter, L.E. (2010) An overview of the CRAFT
  annotation guidelines.  Proceedings of the Fourth Linguistic Annotation Workshop,
  ACL 2010, pp. 207-211.

For evaluation of concept recognition tools on the concept annotations and citation:

  Funk, C., Baumgartner, W.A., Garcia, B., Roeder, C., Bada, M., Cohen, K.B., Hunter,
  L.E., and Verspoor, K. (2014) Large-scale biomedical concept recognition: an evaluation 
  of current automatic annotators and their parameters. BMC Bioinformatics 15:59.

For details of the coreference annotations and citation:

  Cohen, K.B., Lanfranchi, A., Choi, M.J., Bada, M., Baumgartner Jr., W.A., Panteleyeva,
  N., Verspoor, K., Palmer, M., and Hunter, L.E. (2017) Coreference annotation and 
  resolution in the Colorado Richly Annotated Full Text (CRAFT) corpus of biomedical 
  journal articles. BMC Bioinformatics 18:372.

For details of the Uberon anatomical annotations and citation:

  Bada, M., Vasilevsky, N., Baumgartner Jr., W.A., Haendel, M., and Hunter, L.E. (2017) 
  Gold-standard ontology-based anatomical annotation in the CRAFT Corpus. Database,
  Volume 2017, bax087.
                                                                                                                                                                   

 The CRAFT Corpus is available for download from:
    http://bionlp-corpora.sourceforge.net/CRAFT/index.shtml

 ==============================================================================
 ====                          Directory Structure                         ====
 ==============================================================================

  articles/
   ---------ids/
   ---------nxml/
   ---------txt/
  dependency/
  genia-xml/
   ---------pos/
   ---------term/
  knowtator-xml/
  ontologies/
  protege/
  rdf/
  treebank/
  xmi/
  xml/

  ::: articles/ids/ :::
    Contains a file listing the PubMed IDs contained in this distribution 
    (craft-pmids-release) and a file mapping from PubMed ID to PubMed 
    Central ID and original downloaded file name for all articles in this 
    distribution (craft-idmappings-release).

  ::: articles/nxml/ :::
	Contains the original XML for each article in this distribution as 
	downloaded as part of the PubMed Central Open Access collection.

  ::: articles/txt/ :::
	Contains a plain text version of each article that was derived from the 
	original XML files. NOTE: Annotation offsets included in this 
	distribution are relative to the plain-text versions of the articles. 
	The file name for any given article is its PubMed ID with a ".txt" 
	extension. All CRAFT articles use UTF-8 encoding. Also included for 
	each article are files containing the copyright information 
	([PUBMED_ID].copyright) and the article's references 
	([PUBMED_ID].references).

  ::: dependency/ :::
	Contains dependency parse trees for each sentence of every article that is 
	part of this distribution. 

  ::: genia-xml/pos :::
    Contains files showing sentence, token, and part-of-speech information in 
    the GENIA-style POS embedded XML format as defined in 
    http://www-tsujii.is.s.u-tokyo.ac.jp/~jdkim/publications/GENIA_Corpus_Manual.pdf.
     
  ::: genia-xml/term :::
	Contains files that represent the CRAFT concept annotations and in the 
	GENIA-style TERM embedded XML format as defined in 
	http://www-tsujii.is.s.u-tokyo.ac.jp/~jdkim/publications/GENIA_Corpus_Manual.pdf.
	NOTE: The GENIA-style embedded XML format is unable to handle multi-span
	annotations. FOR THIS REASON, THE REPRESENTATION OF THE CRAFT CONCEPT
	ANNOTATIONS IN THIS FORMAT IS INCOMPLETE. All split-span annotations have
	been excluded from this output format. Missing annotations are documented 
	in files ending with ".excluded_annotations". The annotation format used 
	is the following: class [tab] span(s) [tab] covered_text.

  ::: knowtator-xml/ :::
	Contains XML stand-off annotation in the Knowtator XML output format 
	for all concept annotations for every article in this distribution. 
	Before using this data outside of the Knowtator application, please 
	see the section on "Annotation Offsets and Java."

  ::: ontologies/ :::
	Contains the original ontology files used for concept annotation.
	All files in OBO format.

  ::: protege/ :::
    Contains Protege files for loading a project containing all concept 
    annotations using the Knowtator plugin.

  ::: rdf/ :::
	Contains an AO RDF (http://code.google.com/p/annotation-ontology/) 
	representation of the documents and annotations for	the articles that 
	are part of this distribution. 

  ::: treebank/ :::
	Contains the full syntactic parse trees in Penn Treebank style for each 
	sentence in every article that is part of this distribution .

  ::: xmi/ :::
  	Contains serialized UIMA (Unstructured Information Management 
  	Architecture; http://uima.apache.org/) CAS files using the UIMA XMI 
  	format. For the semantic concepts, the CCP type system (included) has been 
  	used. The ClearTK (http://code.google.com/p/cleartk/) type system has been 
  	used to represent the syntactic types. (Also, see note below on annotation 
  	offsets and Java)

  ::: xml/ :::
    Contains XML stand-off annotation in the Knowtator XML output format 
    for all concept annotations for every article in this distribution. 
    The offsets supplied in these files are based on Unicode code points, 
    and thus may differ from the offsets found in the knowtator-xml/ files. 
    Please see the section on "Annotation Offsets and Java" for clarification.

 ==============================================================================
 ====                          Character Encoding                          ====
 ==============================================================================

  UTF-8 encoding is used throughout the CRAFT project, so please default to 
  UTF-8 when using CRAFT resources. 


 ==============================================================================
 ====                    Annotation Offsets and Java                       ====
 ==============================================================================

  ******* NOTE: CHARACTER OFFSETS FOR SOME FORMATS ARE SPECIFIC TO JAVA *******
  A number of the offset-annotation output formats (UIMA XMI and Knowtator XML) 
  are specifically geared for use by applications coded in Java. These files 
  have been produced by Java applications and due to the way Java encodes 
  supplementary Unicode code points the character offsets defined in these files
  may be incorrect if used outside of a Java environment. The reason for this 
  potential discrepancy is that some Unicode code points are represented in Java 
  using more than one character primitive.
  For details, please see: 
     http://download.oracle.com/javase/6/docs/api/java/lang/Character.html
  
  The distribution includes stand-off annotations in the xml/ directory that 
  use Unicode code points as offsets. If using CRAFT in a non-Java environment
  use of the files in the xml/ directory is recommended.

 ==============================================================================
 ====                             Formatting                               ====
 ==============================================================================

  The CRAFT Corpus has been made available in a number of different formats. 
  Availability of the semantic and syntactic annotations in the various 
  formats is detailed in the table below.

         ---------------------------------------------------------
         | Format          | Concepts |        Syntactic         |
         ---------------------------------------------------------
         | AO RDF          |    X     |                          |
         | CoNLL Dep. Tree |          |  Dependency parse trees  |
         | GENIA XML       |    X*    |  Sentences, tokens, POS  |
         | Knowtator XML   |    X     |                          |
         | Penn TreeBank   |          |   Full syntactic trees   |
         | Protege         |    X     |                          |
         | UIMA XMI        |    X     |   Full syntactic trees   |
         | XML             |    X     |                          |
         ---------------------------------------------------------

  * Indicates incomplete representation of the CRAFT Corpus

  ::: AO RDF :::
  	CRAFT semantic annotations have been represented in RDF/XML, making 
  	extensive use of the Annotation Ontology 
  	(http://code.google.com/p/annotation-ontology/). The RDF format 
  	references versions of the documents on the PubMed Central Web site, 
  	e.g., http://www.ncbi.nlm.nih.gov/pmc/articles/PMC138691/?tool=pubmed. 
  	Annotations are defined using the Annotation Ontology 
  	PrefixPostfixTextSelector paradigm 
  	(see http://code.google.com/p/annotation-ontology/wiki/Selectors).

  ::: CoNLL Dependency Tree :::
  	Dependency parse trees were generated with the CLEAR Parser 
  	(http://code.google.com/p/clearparser/) using the CRAFT Treebank data 
  	as input. These parse trees have not been manually vetted.

  ::: GENIA XML :::
  	CRAFT concept annotations have been represented in the GENIA-style 
	Embedded XML format. All formats comply to the format definitions 
	described in http://www-tsujii.is.s.u-tokyo.ac.jp/~jdkim/publications/GENIA_Corpus_Manual.pdf. 
  	Available GENIA-style embedded XML formats include part-of-speech tags and 
   	concept annotation. NOTE: Overlapping split-span annotations cannot 
    	be represented in the GENIA embedded XML format; thus, a number of 
    	annotations are excluded from this format. Such cases are logged in the 
    	accompanying ".excluded_annotations" files.
        
  ::: Knowtator XML :::
  	The XML output format produced by the Knowtator application. 
  	(http://knowtator.sourceforge.net/) This format can be easily imported 
  	into Knowtator projects. Annotation offsets in this format are 
  	relative to the plain text versions of the articles that can be found 
  	in the articles/txt directory. Before making use of this format, please 
  	read the "Annotation Offsets and Java" section.
  
  ::: Penn TreeBank :::
  	Full syntactic parse trees in the Penn Treebank style
  
  ::: UIMA XMI :::
  	Versions of a UIMA CAS data structure representing each article and its 
  	annotations have been serialized using the UIMA XMI data format. 
  	Concept annotations are represented using the CCP Type System 
  	(CCPTypeSystem.xml; included in this distribution) while Treebank data 
  	is represented using ClearTK (http://code.google.com/p/cleartk/) type 
  	system.
  	
  ::: XML :::
    XML standoff annotation of the CRAFT concept annotations in the same 
    format as the Knowtator XML; however, the offsets in these files are to 
    Unicode code points (and not Java characters).
  
  	 

 ==============================================================================
 ====                           Browsing CRAFT                             ====
 ==============================================================================

  ::: Browse via BRAT :::
    The CRAFT Corpus is available for browsing via the brat rapid annotation 
    tool (http://brat.nlplab.org/) here: 
    http://compbio.ucdenver.edu/Craft/index.xhtml
    Hover over the annotations to see their class information. Detailed usage
    instructions are available here: http://brat.nlplab.org/manual.html
    Note that this BRAT installation is configured to be read-only (no editting 
    is permitted).
    Note that BRAT cannot display discontinuous annotations so all 
    discontinuous annotations are shown with their entire span. When hovering 
    over a discontinuous annotation the discontinuous text is displayed, 
    e.g. "region on the .. chromosome". For an example see the following 
    Sequence Ontology annotation: 
    http://compbio.ucdenver.edu/Craft/index.xhtml#/16700629?focus=T124.
     
  ::: Browse via DOMEO :::
    CRAFT will be available for browsing in the DOMEO annotation toolkit in the
    coming months. (http://annotationframework.org/)
  
  ::: Protege :::
  	
  	1) Download and install Protege v3.3.1 and Knowtator v1.7.4
  		- while there are newer versions of Knowtator, the annotation was done 
  		  using v1.7.4 so that is the recommended version for browsing the 
  		  CRAFT corpus.
  	    - follow instructions 1-6 here: 
  	                        http://knowtator.sourceforge.net/install.shtml 

    2) Increase the memory available to Protege to at least 2G
        - follow instructions under Knowtator/Protege runs out of memory here:
          http://knowtator.sourceforge.net/trouble_shooting.shtml
    
  	3) Start Protege
  	
  	4) From the File menu, select Open, then select the 
  	   protege/craft.pprj file that came with this distribution
  	
  	5) If the Knowtator tab is not present, Project --> Configure, then 
  	   check the box next to Knowtator can click OK
  	
  	6) Click on the "Knowtator" tab if it is not already selected and the 
  	   articles should appear with annotations overlayed
  	   
  ::: Use at U-Compare.org :::
    CRAFT will soon be available for use in U-Compare (http://u-compare.org/)
  	   
 ==============================================================================
 ====                               Feedback                               ====
 ==============================================================================

  Please direct comments, questions, and suggestions to either the CRAFT email   
  list on SourceForge: 
  https://sourceforge.net/mailarchive/forum.php?forum_name=bionlp-corpora-craft 
  or send email to ccpsupport@ucdenver.edu
