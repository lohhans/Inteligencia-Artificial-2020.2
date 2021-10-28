from owlready2 import *
onto_path.append("1VA\Presença\9A")
onto = get_ontology("http://www.lesfleursdunormal.fr/static/_downloads/pizza_onto.owl")
onto.load()

with onto:
    class NonVegetarianPizza(onto.Pizza):
      equivalent_to = [
        onto.Pizza
      & ( onto.has_topping.some(onto.MeatTopping)
        | onto.has_topping.some(onto.FishTopping)
        ) ]
      def eat(self): print("Beurk! I'm vegetarian!")
      
onto.Pizza
test_pizza = onto.Pizza("test_pizza_owl_identifier")
test_pizza.has_topping = [ onto.CheeseTopping(), onto.TomatoTopping(), onto.MeatTopping  () ]

onto.save()
nvp = NonVegetarianPizza()

nvp.eat()
