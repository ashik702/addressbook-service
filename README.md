# addressbook-service
This is service is similar to phone book from old times.
The Address Service help you find an entry against the search keyword you have passed.
How this works 
1. Just clone/download the repo.
2. Build the code. It runs on 9595 port. Swagger link http://localhost:9595/swagger-ui.html
3. We are using H2 database. 
4. For searching we are using JPA criteria query based on the input.
5. Once you build the code you need to add entries to it. There are two rest api save and saveAll. I have add a file with testdata.json. You could copy and paste the same into swagger/Postman.
6. Some features are still pending on like adding Docker file, Unit test cases etc. Will update the repo as and when done with rest of the changes.
