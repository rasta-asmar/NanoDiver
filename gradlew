#!/usr/bin/env sh

# Determine the directory of this script
DIR="$(cd "$(dirname "$0")" && pwd)"

# Use Java to run the Gradle wrapper JAR
exec java -jar "$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"
