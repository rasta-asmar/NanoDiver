#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Set DIR to the directory containing this script
DIR="$(cd "$(dirname "$0")" && pwd)"

# Launch Gradle wrapper using Java
exec java -classpath "$DIR/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
