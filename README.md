
Spring Boot App to show bug with CRUDRepository findOne method when you have an 
entity with 2 collections that load eagerly.

To run the example create a mysql database test_collection 
```
CREATE DATABASE IF NOT EXISTS `test_collection` DEFAULT CHARACTER SET utf8;
```

Now run the test to see the failure
```
mvn test
```
