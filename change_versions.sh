#!/bin/bash

VERSION=2.0
TYPE=release
mcvers=(1.17.1 1.18.1 1.18.2 1.19 1.19.1)
for v in ${mcvers[@]}; do  
  sed -i "s/mod_version=.*/mod_version=$VERSION/" mc$v/gradle.properties
  sed -i "s/release_type=.*/release_type=$TYPE/" mc$v/gradle.properties
done