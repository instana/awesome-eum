#!/bin/bash

set -o errexit
set -o nounset
set -o pipefail

BASE_DIR=$(dirname $0)
BASE_DIR=$(cd $BASE_DIR;cd ..;pwd)

docker run -it --rm --security-opt seccomp=unconfined -v $BASE_DIR:/app -w /app mcr.microsoft.com/dotnet/sdk:8.0 dotnet publish -c Release -o out
