#!/usr/bin/env bash
set -xe

echo "Building phones-db image"
pushd phones-db
docker build -t phones-db:latest .
popd

echo "Building phones image"
pushd phones
mvn clean package
docker build -t phones:latest .
popd
