#!/usr/bin/env bash

CURRENT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

set -e

rm -rf src/main/resources/META-INF/resources/*

cd "$CURRENT_DIR/ui"

if [ ! -d node_modules ];
then
  npm install
fi

npm run build

cd "$CURRENT_DIR"
