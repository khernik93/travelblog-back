# Requirements

* redis-server >= 4.0.9
* java >= 8
* mysql >= 5.7

# Running the server

```
$ redis-server
$ ./gradlew build
$ ./gradlew bootRun
```

# Deployment

```
$ docker-compose build
$ MYSQL_ROOT_PASSWORD = "" docker-compose up -d
```
