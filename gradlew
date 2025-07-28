#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Set DIR to the directory containing this script
DIR="$(cd "$(dirname "$0")" && pwd)"
# Execute the actual Gradle wrapper
exec "$DIR/gradle/wrapper/gradle-wrapper" "$@"
