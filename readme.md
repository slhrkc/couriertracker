# Getting Started

### Reference Documentation
To run the application run the commands below in the main directory of the project.

mvn clean install\
java -jar target/couriertracker-0.0.1-SNAPSHOT.jar

It will be loaded on http://localhost:8080/courier/

To test the API, you can use below url.
http://localhost:8080/courier//swagger-ui.html#/


Two Design Patterns are used.

- Facade Pattern on  CourierLocationServiceImpl class
- Singleton Pattern on GeoLocationUtil class
