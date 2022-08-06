#!/bin/bash

mcvers=(1.17.1 1.18.1 1.18.2 1.19 1.19.1)
for v in ${mcvers[@]}; do  
  pushd mc$v
      ./gradlew clean >>../../build_log || exit
      ./gradlew --no-daemon --parallel build >>../../build_log|| exit
  popd
done