#!/bin/bash

VERSION=2.1_release

modloader=(fabric forge)
mcvers=`ls |grep -P "mc1\..+"|tr "\n" " "|sed 's/mc//g'`


for v in ${mcvers[@]}; do
	for m in ${modloader[@]}; do
	  lm=`echo "$m" | tr '[:upper:]' '[:lower:]'`	  
	  echo " "
	  echo "================="
	  echo "$lm $v"

	  if [ -d ~/minecraft/testing_instances/test_${m}_${v}/.minecraft/mods/ ]; then
	  		  	
	  	if [ -f mc$v/$m/build/libs/DeepslateInstamine_mc$v-${VERSION}-$m.jar ]; then
	  		rm ~/minecraft/testing_instances/test_${m}_${v}/.minecraft/mods/DeepslateInstamine*
	  		cp mc$v/$m/build/libs/DeepslateInstamine_mc$v-${VERSION}-$m.jar ~/minecraft/testing_instances/test_${m}_${v}/.minecraft/mods/
	  	fi
	  	ls -1 ~/minecraft/testing_instances/test_${m}_${v}/.minecraft/mods/
	  fi
	  echo "================="
	done
done


