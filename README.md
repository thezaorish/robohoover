## Introduction

Solution for https://github.com/lampkicking/java-backend-test

## Setup

Running the application requires maven and mongodb

* install [maven](https://maven.apache.org/install.html)
* install [mongodb](https://docs.mongodb.com/manual/installation/)

## Execute

You can run the application by using the following command:

mvn clean spring-boot:run

## Testing

To test the web application, POST at http://localhost:8085/instructions

Example input:
```javascript
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```

Example (matching the input above):
```javascript
{
  "coords" : [1, 3],
  "patches" : 1
}
```
