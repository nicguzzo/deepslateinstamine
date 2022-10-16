#!/bin/bash

mcvers=`ls |grep -P "mc1\..+"|tr "\n" " "|sed 's/mc//g'`
for v in ${mcvers[@]}; do  
  pushd mc$v
#      ./gradlew clean >>../../build_log || exit
      ./gradlew build >>../../build_log|| exit
  popd
done
