#!/bin/bash

mcvers=`ls |grep -P "mc1\..+"|tr "\n" " "|sed 's/mc//g'`
for v in ${mcvers[@]}; do  
  pushd mc$v
      ./gradlew clean 
  popd
done