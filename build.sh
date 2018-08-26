#!/bin/bash

./gradlew clean bootRepackage

docker build --rm . --tag xueqingliao/zuul-service:${VER:?invalid version}
docker push xueqingliao/zuul-service:${VER:?invalid version}

#export VER
#docker stack deploy todo -c docker-compose.yml