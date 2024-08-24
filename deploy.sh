#!/bin/bash
mvn clean package
docker build -t demoproject .
docker run -p 8080:8080 demoproject
