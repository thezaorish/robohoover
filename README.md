You can run the application by using the following command:

mvn clean spring-boot:run


To test the web application, POST at http://localhost:8085/instructions

example input
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

expected output for the input
{
  "coords" : [1, 3],
  "patches" : 1
}