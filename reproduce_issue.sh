#!/bin/bash
mvn compile -q
mvn exec:java -Dexec.mainClass="com.antallis.scrimishterminal.ScrimishTerminal" < input.txt > output.txt 2>&1
cat output.txt
