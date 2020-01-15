# Multi Tenancy technical options

## Discussion and Questions

Need : We need to have a single database, cloud hosted, multi tenant aware with multiple client's data in it. 

Herman asked that we investigate a couple of options of connecting and accessing different databases and different database versions, from the same code base. 
We'll probably end up having our own canonical representation and map to each family and version (vantage, enterprise etc)
Our canonical entities will probably end up what we'll save in our own local multi tenant database. 

Things we need to look at:

- Does it support concurrency and is it thread safe? 
- What is the lifetime of a thread? Rest call? As long as its connected to the DB? 
- Does your solution support connection pooling? (This is a deal breaker.) 
- How does it behave under load? Will it scale? 
- How difficult will it be to maintain the code? 
- How much duplication of code is involved? 
- Is it worth the effort?
- What issues will we have down the line? 
- Is it really a good idea?

Options to investigate: 

1. The solution I started on, which was to connect to multiple client databases whilst still using JPA and Hibernate and Liquibase. 
   This solution will try and split the different database entities (eg EV5_1 ,EV5_2 , EV6, Vantage) in packages 
   There will be a top level shared package and the version specific entities will subclass the shared ones
   Use AbstractRoutingDatasource to connect to multiple databases automatically by using ClientId as lookup for connection details.
   
2. Use a factory to create repositories specific to each database (enterprise and vantage and their versions) by using the clientId to decide. 
   This solution (probably) won't use an ORM and will use normal jdbc to connect to client database. 
   We'll then have to map back to our canonical format of entities. 
   These repositories only know how to talk to the version that it was written for. 
   
3. See if we can have some other ORM or hybrid approach? 
   - Native Queries in JPA? 
   - Load the queries dynamically for each DB / Version? 
   - Can JPA Native queries be built dynamically?
   
4. Some other option we're not thinking of?  
