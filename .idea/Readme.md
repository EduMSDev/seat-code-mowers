### About

Application to remotely control mowers in a plateau

### Running the Project Locally
- To run the project locally, follow these steps:
    * Prerequisites: Maven must be installed on your system.
    * From the project's root directory, execute the command mvn install.

### Testing Project Endpoints

* To test the project endpoints, you can import the provided Postman collection located in the root of the project.

    * Endpoint: /mowers/send (HTTP GET)

### Running Project Tests
- To run the project tests, execute the command mvn test from the root directory of the project

### Technical details
This project follows a Driver Domain Design with CQRS pattern and utilizes reactive programming.

### TODO
* Fix Test and Logic
* Add accepantance tests, integrations test and more unit test
* Show Cells cut and busy from Plateau as board (as tic tac toe)
* Implement API