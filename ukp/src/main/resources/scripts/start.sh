#!/bin/bash
java ${launch.jvm.args} -jar ${artifact.artifactId}-${project.version}.jar $1
