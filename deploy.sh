#! /bin/bash

mvn clean
mvn package -P prod
scp -r ./target/yuanTodo-0.0.1-SNAPSHOT.jar root@43.140.252.199:/www/wwwroot/yuantodo_api

