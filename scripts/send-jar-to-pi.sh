# TBD
# if you run this script you wont see prints in your console

mvn clean && mvn package
scp target/display-0.0.1-SNAPSHOT.jar pi@crowpi.local:display.jar
