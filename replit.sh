#!/bin/bash

### Replit + Gradle start script for CompSci ###
# Downloads gradle, automatically pulls from configured git remote, and builds + runs.
# Replit, seems like native gradle support wouldn't be very difficult. Do so please!

out () {
  clear
  echo "$1"
  echo "-------------------------------------------------------------"
  echo ""
}

out "Pulling Latest Code"
git pull
chmod +x ./gradlew
out "Building... This will take a moment, ignore everything below."
./gradlew -Dorg.gradle.internal.launcher.welcomeMessageEnabled=false -q build
out "Done. Running."
./gradlew --console=plain -q run
