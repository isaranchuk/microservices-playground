#!/usr/bin/env bash
set -xe

for container_name in $(find . -name Dockerfile -print0 | xargs -0 -n1 dirname | sort --unique | cut -c 3-)
do
  echo "Building $container_name image"
  pushd $container_name

  if [ -f pom.xml ]; then
     echo "Building executable jar"
     mvn clean package
  fi

  docker build -t $container_name:latest .
  popd
done
