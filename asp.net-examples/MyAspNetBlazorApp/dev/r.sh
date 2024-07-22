#!/bin/bash

set -o errexit
set -o nounset
set -o pipefail

BASE_DIR=$(dirname $0)
BASE_DIR=$(cd $BASE_DIR;cd ..;pwd)

# Change Path and Password as required
CERT_PATH="${HOME}/.aspnet/https"
CERT_FILE="${CERT_PATH}/aspnetapp.pfx"
CERT_PASSWORD=$(openssl rand -base64 32)

if [ -f "$CERT_FILE" ]; then
    echo "Existing certificate found. Removing..."
    rm "$CERT_FILE"
fi

#Generate self-signed certificate if it doesn't exist
docker run --rm \
    -v "$BASE_DIR/out:/app" \
    -v "$CERT_PATH:/https" \
    mcr.microsoft.com/dotnet/sdk:8.0 \
    /bin/bash -c "\
    echo 'Cleaning up existing certificates...' && \
    dotnet dev-certs https --clean && \
    sleep 2 && \
    echo 'Generating new certificate...' && \
    dotnet dev-certs https -ep '/https/aspnetapp.pfx' -p $CERT_PASSWORD
    "

docker run -it --rm --security-opt seccomp=unconfined \
    -p 8080:8080 -p 8001:443 --name aspnet_hello \
    -e DOTNET_STARTUP_HOOKS=/app/Instana.Tracing.Core.dll \
    -e CORECLR_ENABLE_PROFILING=1 \
    -e CORECLR_PROFILER={cf0d821e-299b-5307-a3d8-b283c03916dd} \
    -e CORECLR_PROFILER_PATH=/app/instana_tracing/CoreProfiler.so \
    -e INSTANA_AGENT_HOST=host.docker.internal \
    -e INSTANA_AGENT_PORT=42699 \
    -e INSTANA_ALLOW_EXIT_AS_ROOT=1 \
    -e INSTANA_LOG_SPANS=1 \
    -v $BASE_DIR/out:/app \
    -v $CERT_PATH:/https/ \
    -e ASPNETCORE_URLS="https://+;http://+" \
    -e ASPNETCORE_HTTPS_PORTS=8001 \
    -e ASPNETCORE_Kestrel__Certificates__Default__Password=$CERT_PASSWORD \
    -e ASPNETCORE_Kestrel__Certificates__Default__Path=/https/aspnetapp.pfx \
    -w /app mcr.microsoft.com/dotnet/sdk:8.0 dotnet MyAspNetBlazorApp.dll


