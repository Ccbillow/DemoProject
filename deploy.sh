#!/bin/bash

#use docker-compose to deploy

#first stop and delete containers
docker-compose down
docker rmi demoprojecteureka
docker rmi emailservice
docker rmi userservice

#build eureka server image
cd ./DemoProjectEureka
mvn clean package
docker build -t demoprojecteureka .

#build email server image
cd ../EmailService
mvn clean package
docker build -t emailservice .

#build user server image
cd ../UserService
mvn clean package
docker build -t userservice .

#start images
cd ..
docker-compose up -d
