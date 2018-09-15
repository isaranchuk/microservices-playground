#!/usr/bin/env bash
set -xe

echo "Building phones-db image"

pushd phones-db

docker build -t phones-db:latest .

popd
