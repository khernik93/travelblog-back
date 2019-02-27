# Travelling blog backend

This is the backend of the travelblog infrastructure. It's written in Java Sprint Boot.

## Requirements

* redis-server >= 4.0.9
* java >= 8
* mysql >= 5.7

## Prerequisites

* mysql server running with proper database set
* redis server running

# Building and running

```
$ ./gradlew build
$ ./gradlew bootRun
```

# Deployment

```
$ docker-compose build
$ docker-compose up -d
```
