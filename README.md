# FoodHub: Recipe page
Welcome to FoodHub recipe page!
You can find cooking recipes for different dishes in simple, short description. 
Users can perform CRUD Operations through the interactive buttons provided on the page as:
1) Add Recipe for creating and posting a new recipe 
2) Edit button for updating an already existing recipe 
3) Delete button for deleting a recipe 
4) Getting all the recipes on the home page which is the FoodHub page.

Technologies used:
Back end:
For back end, I have used Spring Boot with Hibernate JPA. 
Reasons:
Spring Boot provides smooth creation of standalone applications and Micro Services with auto configuration of Spring and Third Party libraries.
Sprng Boot has embedded Tomcat server so we can run applications without complex server infrastructure

H2 Database:
I've used embedded H2 database since it is fast and suports SQL and is "in-memory" database

Hibernate:
I've used Hibernate framework for Java Persistence API (JPA) which is a specification for object-relational mapping in Java.
Hibernate provides communication between Spring Boot application and database through objects

Front End technologies:
For front end I have used Thymeleaf for the view layer because its syntax is closer to HTML
It fully integrates with Spring Boot application

For Unit Testing:
Mockito framework has been used due to its good readability and neatness. 
It provides clean verification errors

I have written unit test cases for the Service class which check:
1) List of all recipes
2) Getting a recipe by Id
3) Creating that is Posting a new recipe
4) Update recipe of specific Id and 5) When list of recipes is empty
6) Delete recipe of specified Id

Screenshot:


<img width="939" alt="result 4" src="https://user-images.githubusercontent.com/37115375/90550613-cfcafd00-e1ad-11ea-9509-61607d0ab078.png">
