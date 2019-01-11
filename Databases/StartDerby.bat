pushd %~dps0\Derby\lib

java -jar -Dderby.system.home=%~dps0 derbyrun.jar server start
