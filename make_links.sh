#!/bin/bash

mcvers=`ls |grep -P "mc1\..+"|tr "\n" " "|sed 's/mc//g'`
for v in ${mcvers[@]}; do  
  pushd "mc$v/common"
    ln -s ../../common/src
    ln -s ../../common/build.gradle  
  popd
  pushd "mc$v/fabric"
    ln -s ../../fabric/src
    ln -s ../../fabric/build.gradle  
  popd
  pushd "mc$v/forge"
    ln -s ../../forge/src
    ln -s ../../forge/build.gradle  
    ln -s ../../forge/gradle.properties
  popd
done
