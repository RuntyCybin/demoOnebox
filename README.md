# OneBox
## _Test exercise_

The project is a microservices application with the use of the cache instead of the database.
The main stack is Jdk 1.17 and Spring Boot 3.
The cache is implemented using a hashMap and the most common self-made methods.
The main cache interface is created using generics due to its implementation made by two different types of data
but with the mostly similar behavior, Cart and Product classes.
> I am aware of the existence of such plugins like ehCache or a Spring cache, but I guessed that the current way would
be more challenging.

Project prepared to use Yaml files instead of typical .properties.
Exception handling is performed for case of the Cart not found.

Endpoints collection ("OneBox collection.postman_collection.json") is attached to the exercise

## Entities :
- **Cart:**
  Integer id;
  String cartname;

- **Product:**
  Long productid;
  String description;
  int amount;
  String cart;

## Foreign keys:
- One to many : one cart contains many products -> same cart id is present in many products [Cart entitie]
- Many to one : many products in one cart -> many products with same cart id [Product entitie]

