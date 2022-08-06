#!/bin/bash

VERSION=2.0_release

modloader=(fabric forge)
mcvers=(1.17.1 1.18.1 1.18.2 1.19 1.19.1)

for v in ${mcvers[@]}; do
	for m in ${modloader[@]}; do
	  lm=`echo "$m" | tr '[:upper:]' '[:lower:]'`	  
	  echo " "
	  echo "================="
	  echo "$lm $v"

	  if [ -d ~/minecraft/testing_instances/test_${m}_${v}/.minecraft/mods/ ]; then
	  		  	
	  	if [ -f mc$v/$m/build/libs/DeepslateInstamine_mc$v-${VERSION}-$m.jar ]; then
	  		cp mc$v/$m/build/libs/DeepslateInstamine_mc$v-${VERSION}-$m.jar ~/minecraft/testing_instances/test_${m}_${v}/.minecraft/mods/
	  	fi
	  	ls -1 ~/minecraft/testing_instances/test_${m}_${v}/.minecraft/mods/
	  fi
	  echo "================="
	done
done


