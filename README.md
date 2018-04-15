**Prerequisite**

You should have a linux/Mac Osx machine with Docker installed and docker daemon up and running 

**To Build The Project**

Go to the project root directory and execute following commands

`$ mvn clean package`

`$ docker build -t sms-server --build-arg JAR_FILE=./target/smsserver-0.0.1-SNAPSHOT.jar .`


**To Run the Web Server, Redis Server and Postgresql DB Server**

`$ docker-compose up`

**To create DB schema**

``$ psql -h localhost -p 5432 -U postgres --password postgres -d smsdb -a -f sql/dbschema.sql ``


With this everything should be up and running.


