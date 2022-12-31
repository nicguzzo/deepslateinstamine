#!/bin/bash

pushd "versions"
  if [ "$1" == "" ]; then
    mcvers=`ls |grep -P "mc1\..+"|tr "\n" " "|sed 's/mc//g'`
  else
    mcvers=( $1 )
  fi
  for v in ${mcvers[@]}; do  
    pushd mc$v
        ./gradlew build || exit
    popd
  done
popd