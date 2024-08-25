#!/bin/bash

#use docker to deploy seperately
mvn clean package
docker build -t demoprojecteureka .
docker run -p 8761:8761 demoprojecteureka
