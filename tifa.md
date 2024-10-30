ps -ef | grep business-0.0.1.jar
cd /tools/tifa
nohup java -jar business-0.0.1.jar > tifa.log 2>&1 &
