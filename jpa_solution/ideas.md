# Multi Tenancy technical options

## Discussion and Questions

Need : We need to have a single database, cloud hosted, multi tenant aware with multiple client's data in it. 

What is the simplest solution that will allow us to easily change in the future? 

I am assuming that when talking about multi-tenancy we're just going with one database, all table's data separated by a TenantId or ClientId. 

### Possible Solutions

#### Hosting a different microservice for every client version of the database which we connect to

Database structure in entities hardcoded to each schema version
We talk from our main application directly with this one via HTTP Rest. 

CON: Need to have a shared domain model that caries all detail of all the different entities, 
     or we need to convert our payload to the version of the service before we send it. 
     This is not a good idea. 

CON: Too many things to deploy and host? But deployments are cheap, and containers are cheap.

#### Shared Entities in same project, loading entities from package on the fly

Here we load the shared entities from the shared entities package with the EntityScan parameter of the PersistenceContext.
We then load the database specific entities from a folder that is specific to the version. 

    @EnableJpaRepositories(basePackages = "com.epiuse.models",
        entityManagerFactoryRef = "persistentDatabaseEntityManager", transactionManagerRef = "persistentDatabaseTransactionManager")

    final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setPackagesToScan("com.epiuse.models.enterprise.v5");

PRO: One code base / library / pom

We can have a Vantage Persistence layer and an Enterprise persistence layer? 

#### We don't connect to databases directly, but use a service bus / etl / kafka to talk with the client db's 

This is different to Oracle Golden Gate, where we connect to all the databases ourselves, and basically send DTOs around and update them. 
Need to think about routing, as to where an update need to go. We'll still have to have shared domain entities.

PRO:

CON: 

#### We use Oracle GoldenGate (or similar) to sync client data to our shared Multi-tenant database and back again and never connect to the client databases directly from our code

Where do we store the config to which client database a specific update needs to go? 
Does this mean that we need a specific oracle golden gate setup for each client? Each specific version?
Surely this means we'll have to have a set of ETL / transformations for each version of database that we connect to? 
Can we connect to multiple client databases from Golden Gate for the syncing? 

PRO: 
CON:
