
# CRAFT Corpus Changes

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
