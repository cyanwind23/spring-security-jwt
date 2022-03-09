#!/bin/bash

if [[ $1 == "gen" || $1 == "g" ]]; then
	# generate
	if [[ $2 == "service" || $2 == "s" ]]; then
		# service
		if [[ -z $3 ]]; then
			echo "invalid name"
		else
			# create service file
			fileName="${3}Service"
			serviceDir="./src/main/java/xyz/thiennam/employeems/service/"
			echo -e "package xyz.thiennam.employeems.service;\n\npublic interface ${fileName} {\n\n}" >> "${serviceDir}${fileName}.java"
			
			# create bean file
			echo -e "package xyz.thiennam.employeems.service.bean;\n\nimport lombok.AllArgsConstructor;\nimport org.springframework.stereotype.Service;\nimport xyz.thiennam.employeems.service.${fileName};\n\n@Service\n@AllArgsConstructor\npublic class ${fileName}Bean implements ${fileName} {\n\n}" >> "${serviceDir}bean/${fileName}Bean.java"
		fi
	else
		echo "invalid target"
	fi
else
	echo "invalid cmd"
fi

