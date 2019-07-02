# CRAFT Corpus Changes

## Version 3.1.3
* Updated file-conversion dependeny to v0.2.2 to handle/prevent some improper discontinuous spans in the coreference annotations. For details, please see this issue: https://github.com/UCDenver-ccp/craft-shared-tasks/issues/1 and the changes made in the file-conversion project: https://github.com/UCDenver-ccp/file-conversion/blob/master/CHANGES.md

## Version 3.1.2
* Corrected erroneous extension class prefixes in the `concept-annotation/GO_MF/GO_MF+extensions/GO_MF_stub+GO_MF_extensions.obo` file

* Reverted Head rule used in dependency conversion back to STANFORD to match newly added CoNLL-U files.

* Added correctly formatted CoNLL-U files for the dependency parses. See `structural-annotation/dependency/conllu`. Many thanks to Manuel Ciosici and Sampo Pyysalo for their help in creating and vetting these files.

## Version 3.1.1
* Returned the dependency file format for the CRAFT dependency data back to the CoNLL-X format. The CoNLL-U files in v3.1 were improperly formatted (XPOS and UPOS columns were mistakenly swapped among other things) and there is no UPOS data to include. This change aligns the dependency files more closely with the original CRAFT dependency files (available in CRAFT v3.0 and earlier releases). Those original files were missing one POS column which is now included to fully comply with the CoNLL-X format. The non-compliant CoNLL-U files have been removed from the distribution. Also, a minor change was made to the HeadRule used in the conversion from treebank files to the dependency files. The CONLL HeadRule is now used instead of the STANFORD HeadRule.
  
* The file-conversion library dependency was updated to 0.2.1 to include changes to support the CoNLL-X file generation mentioned above as well as updates to allow the boot script to work with Java >= 9.0.
 
* The parse for final sentence of document 14611657 was added to the 14611657.tree file and the dependency parse was automatically derived and added to 14611657.conll
  
* For document 16098226, use of NCBITaxon:1910954 was swapped with NCBITaxon:10847 (partly because NCBITaxon:1910954 is not present in the NCBITaxonomy OBO file that is distributed with CRAFT)  

## Version 3.1
* The top-level directory has been reorganized into three main directories for annotations.
  * **concept_annotation/** stores all annotations of ontology concept mentions
  * **structural_annotation/** stores all syntactic annotations and annotations related to document structure
  * **coreference_annotation/** stores all coreference annotations

* A Clojure Boot script has been added to the distribution to facilitate dynamic generation of annotation files in different formats at a user's request. With this addition, annotation files in alternative formats (e.g. brat, uima, knowtator-2 etc.) have been removed leaving only the native file format for each annotation type. Doing so has reduced the overall size of the CRAFT project to under the 1GB threshold imposed by GitHub.

* Knowtator-2 project archives have been removed from the distribution. They can now be created dynamically using the new Clojure Boot script.

* Some treebank files have been adjusted based on errors reported by the CoNLL 2018 universal dependency shared task evaluation script (http://universaldependencies.org/conll18/evaluation.html) when run over dependency parses derived from the treebank files. Most errors took the form of multiple ROOT nodes present in the dependency parse and were related to nested CAPTION constructs in the treebank files. These were addressed by un-nesting the CAPTION constructs. There were also a few errors related to empty forms in the resulting dependency parses. These stemmed from lists in the treebank files that used empty forms, e.g. (:  ) or (SYM  ) and these were removed from the treebank files.

* New versions of the dependency files have been derived from the manually annotated treebank files using the ClearNLP library, specifically the C2DConvert.java application (https://github.com/clir/clearnlp/blob/master/src/main/java/edu/emory/clir/clearnlp/bin/C2DConvert.java). The file format for the dependency files has also been updated to use the CoNLL-U file format (https://universaldependencies.org/format.html). The original versions of the dependency files have been removed from the repository.

* Some erroneous relations were removed from a single knowtator-2 annotation file for the CL+extension concepts

* The coreference annotations have been revised to resolve instances of identity chains sharing mentions. The original knowtator files have been removed and replaced with knowtator-2 format files that contain the revised annotations. For details on the changes to the coreference annotations, please see this [README](https://github.com/UCDenver-ccp/CRAFT/blob/master/coreference-annotation/README.md).

* The distribution now includes XSD files for the knowtator and knowtator-2 XML file formats. See the **schema/** directory


## Version 3.0 
* The directory structure of the distribution has been substantially changed.  Along with the journal articles, the top level is organized by annotation type (coreference, dependency structures, ontology concepts, sentences/tokens/parts of speech, sections/typography, and full syntactic parses).  All available formats for a given annotation type are organized under it.

* The coreference annotations are provided in Knowtator, Knowtator 2, and UIMA XMI formats in addition to the previously available brat format.

* The concept annotations for all 8 of the ontologies used for v1-2 of the corpus have been updated using the classes of newer versions of these ontologies, resulting in a substantial increase in the number of annotations.  Additionally, extension classes of the ontologies have been created and extensively used for annotation.  The concept annotations for each ontology are packaged into sets created without any extension classes and sets augmented with extension class annotations.  Also included along with the concept annotations are original OBO ontology files and those augmented with extension classes, as well as various class mapping files.  This is discussed in ontology-concepts/README.md.

* Concept annotations using the Molecular Process Ontology (MOP) have been created for the articles of the corpus.

* The GO_BP and GO_MF concept annotations have been modularized into their own proper respective annotation sets.

* The structure of the NCBITaxon annotations has been been changed, now with each annotation directly specified with the appropriate NCBITaxon ID rather than as an attribute.  This new structure is consistent with the concept annotations created with the other ontologies.

* The Entrez Gene annotations have been removed from the distribution, as we believe they do not match the quality of the other concept annotations, and we recommend that they not be used.

* The concept annotation span guidelines have been slightly modified; this is discussed in ontology-concepts/README.md.

* The concept annotations are no longer distributed in the AO RDF or GENIA XML formats, nor as Protege-Frames projects.  However, they are now also provided in the Knowtator 2 format (which we believe is a more intuitive format than the original Knowtator format) as well as the brat format.  Additionally, we have removed the previously included XML files whose offsets are based on Unicode code points, as we have analyzed these to be identical to those based on Java code points and therefore not needed.


## Version 2.1 
* Concept annotations using the UBERON anatomical ontology have been created for the articles of the corpus.  These are distributed both using only proper UBERON classes and also with UBERON extension classes.


## Version 2.0 
* Coreference annotations have been added to the corpus and are include
  as Protege, XML, and BRAT file formats.


## Version 1.0 
* The Gene Ontology Biological Process and Molecular Function annotations are
  now complete, having undergone the quality assurance procedures.
   
* UIMA XMI output using the U-Compare type system has been added for the 
  concept annotations


## Version 0.9
* This represents the initial public release of the CRAFT corpus. 
* The Gene Ontology Biological Process and Molecular Function annotations are 
  still in the quality assurance stage, and thus will continue to be edited.
