# jpa_jdbc_testing

This is a simple testing environment to test various options for catering for a large amount of single-tenant databases in a single api instance

## Requirements

For this application, you require the following tools:

* Java SDK
* Docker
* Flyway

## Getting started

There is a docker-compose script that can be used to create 4 different database instances. <br>
In order to get the docker instances up, you can simply run:

```sh
docker-compose up -d
```

After the instances have been created, you can view their status by running:

```sh
docker ps -a
```

## Connecting to the instances

Below are the connection information per instance:

| Instance | Address | Port | Admin User | Admin Pass | Database | User | Pass | 
| -------- | ------- | ---- | ---------- | ---------- | -------- | ---- | ---- |
| db1_v5_1 | 127.0.0.1 | 3306 | root | pass123 | enterprise_v5_1 | client1 | pass123  |
| db2_v5_1 | 127.0.0.1 | 3307 | root | pass123 | enterprise_v5_1 | client2 | pass123  |
| db3_v5_2 | 127.0.0.1 | 3308 | root | pass123 | enterprise_v5_2 | client3 | pass123  |
| db4_v5_2 | 127.0.0.1 | 3309 | root | pass123 | enterprise_v5_2 | client4 | pass123  |


## Running the migrations

You can just run the following commands to run the migrations:

```sh
flyway -configFiles=./db/configs/db1_v5_1.conf -locations=filesystem:./db/migrations -target=5.1.0 migrate
flyway -configFiles=./db/configs/db2_v5_1.conf -locations=filesystem:./db/migrations -target=5.1.0 migrate
flyway -configFiles=./db/configs/db3_v5_2.conf -locations=filesystem:./db/migrations -target=5.2.0 migrate
flyway -configFiles=./db/configs/db4_v5_2.conf -locations=filesystem:./db/migrations -target=5.2.0 migrate
```

## Cleaning up

To tear down the containers you can run:

```sh
docker-compose down
```

and if you want to remove the containers AND remove the volume, you can run:

`WARNING: YOU WILL REMOVE THE DATA IN THESE DATABASES IF YOU CONTINUE`

```sh
docker-compose rm
```