# products-ws [![Build Status](https://travis-ci.com/jaarques-uoc/products-ws.svg?branch=master)](https://travis-ci.com/jaarques-uoc/products-ws)

Command line tools:
* Mongodb:
    * Installation (https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/):
        * `brew tap mongodb/brew`
        * `brew install mongodb-community@5.0`
    * Start: `mongod --config /usr/local/etc/mongod.conf --fork`
* Spring boot:
    * build: `./gradlew build`
    * run: `./gradlew bootRun`
* Docker:
    * build: `docker build --tag=products-ws .`
    * run: `docker run -p 7002:7002 -t products-ws`
    * stop: `docker stop $(docker ps -q --filter ancestor=products-ws)`
    * stop all containers: `docker stop $(docker ps -a -q)`

Initialization endpoint:
* `curl localhost:700/init`: It initialises the application with 24 products.

Monitoring urls:
* Travis CI history: https://travis-ci.com/jaarques-uoc/products-ws/
* Docker image: https://cloud.docker.com/repository/docker/jaarquesuoc/products-ws
* Heroku app health-check: https://products-ws.herokuapp.com/actuator/health
