Material CRUD Spring Boot Application

This project is a full CRUD (Create, Read, Update, Delete) application for managing materials, built with Spring Boot.

It correctly models the "One-to-Many" relationship where one Uom, MaterialType, Manufacturer, or Vendor can be associated with many Materials.

Features

RESTful API: Full CRUD operations for materials.

Relational Data: Uses JPA/Hibernate to manage Many-to-One relationships.

DTOs: Uses Data Transfer Objects (MaterialDto) and ModelMapper for clean API contracts and separation from entity models.

The application will be available at http://localhost:8080.

API Documentation

Swagger UI

Once the application is running, you can access the interactive Swagger UI documentation here:

URL: http://localhost:8080/swagger-ui/index.html

Postman Collection

You can import the Postman collection to test the API.I have upload file is : Material.postman_collection.json

link: https://web.postman.co/workspace/My-Workspace~c87adcb2-5942-4538-8853-e0b05bfc2349/collection/44395005-331e19f2-9b90-417a-9503-0df2788fdab6?action=share&source=copy-link&creator=44395005

API Endpoints

create api

POST http://localhost:8080/api/materials

Creates a new material.

findAll

Post http://localhost:8080/api/materials

Returns a list of all materials.Also listing pagging.

findById

GET http://localhost:8080/api/materials/{id}

Returns a single material by its materialld.

Example: GET http://localhost:8080/api/materials/c3d4e5f6-a7b8-9012-cdef-123456789012

update api

PUT http://localhost:8080/api/materials/{id}

Updates an existing material. The request body is the same as the POST request.

Example: PUT http://localhost:8080/api/materials/c3d4e5f6-a7b8-9012-cdef-123456789012

delete api

DELETE http://localhost:8080/api/materials/{id}

Deletes a material by its materialld.

Example: DELETE http://localhost:8080/api/materials/c3d4e5f6-a7b8-9012-cdef-123456789012

Please update the Database url ,username and password ,and run sql query after run springboot Appliaction 


