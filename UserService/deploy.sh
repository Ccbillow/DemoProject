#!/bin/bash

#use docker to deploy seperately
mvn clean package
docker build -t userservice .
docker run -p 8081:8081 userservice
