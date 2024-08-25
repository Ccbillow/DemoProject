#!/bin/bash

#use docker to deploy seperately
mvn clean package
docker build -t emailservice .
docker run -p 8082:8082 emailservice
