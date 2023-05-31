export DISPLAY=:0 && xhost local:root
su root <<!
crowpi
cd /home/pi/graue-energie
mvn clean javafx:run
!
