FarmCollector Application

Technologies :
Java 17
Spring Boot
Maven
Lombok
H2 Database (for in-memory testing)
JUnit (for unit testing)


Running the Application :
Clone the Repository
git clone https://github.com/farhadkt/farm-collector

Build the Project:
mvn clean install
Run the Application

mvn spring-boot:run
The application will start on http://localhost:8080.

Initial Data
Sample data will be populated automatically upon application startup using the CommandLineRunner implementation.

Endpoints :

Farms :
Create a Farm -> POST /api/farms
Request Body:
{
    "name": "FarmA"
}
Get All Farms -> GET /api/farms
------------------------------------------
Plants :
Create a Plant -> POST /api/plants
Request Body:
{
    "farm": {
        "id": 1
    },
    "fieldName": "Field1",
    "season": "Winter",
    "plantingArea": 10.0,
    "cropType": "Corn",
    "expectedProductAmount": 100.0
}
Get All Plants -> GET /api/plants
---------------------------------------
Harvests:
Create a Harvest -> POST /api/harvests

Request Body:
{
    "plant": {
        "id": 1
    },
    "actualAmount": 80.0
}
Get All Harvests -> GET /api/harvests
--------------------------------------------
Reports :
Get Report by Season -> GET /api/reports/season?season=Winter

Get Report by Crop Type -> GET /api/reports/crop?cropType=Corn

Get Report for All Seasons -> GET /api/reports/all-seasons

Testing
Run unit tests using Maven:
mvn test

Notes
The application uses an in-memory H2 database for development and testing.