from owlready2 import *

onto_path.append("1VA\Presença\9A")
onto = get_ontology("1VA\Presença\9A\IA-owl-Armstrong.owl")
onto.load()

print('\n\n -- Tentativa com criação de OWL DL no Protégé -- \n\n')

print('\n\nConsulta da classe Animal na ontologia criada no Protégé:')
print(onto.Animal)

print('\n\nConsulta da classe Cachorro na ontologia criada no Protégé:')
print(onto.Cachorro)