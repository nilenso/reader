#!/usr/bin/bash

lsof -t -i:8080 | xargs kill -9 #kill any process running on port 8080
java -jar reader-0.1.0-SNAPSHOT-standalone.jar &
