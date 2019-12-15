help tips: https://www.javainuse.com/devOps/docker/docker-compose-tutorial
preli:
dockercompose-producer produce listens on port 9090
dockercompose-consumer listens on port 8080
----------
producer testing on localhost
C:\Users\sdass>curl http://localhost:9090/hi
say hello
consumer testing on localhost
C:\Users\sdass>curl http://localhost:8080/getother
say hello
----------
docker file testing
C:\Users\sdass\STS\2019workspace\dockercompose-consumer>docker build -t dkr-compose-consumer .
>docker images
dkr-compose-consumer   latest              6d841ab0694b        About a minute ag

C:\Users\sdass\STS\2019workspace\dockercompose-producer>docker build -t dkr-compose-producer .
dkr-compose-producer   latest              09df0563bdfc        3 weeks ago

container testing-1
dockercompose-producer>docker run --name producer-c -p 9090:9090  dkr-compose-producer
C:\Users\sdass>docker ps
9d247552e9d2        dkr-compose-producer   "java -jar /tmp/dock"   About a minute ago   Up About a minute   0.0.0.0:9090->9090/tcp   producer-c

etc/hosts has dockerdev entry for docker-engine ip
C:\Users\sdass>curl http://dockerdev:9090/hi
say hello by compose
passed
----------
container testing-2

dockercompose-consumer>docker run --name cosumer-c -p 8080:8080  dkr-compose-consumer
C:\Users\sdass>docker ps
c74377e3b289        dkr-compose-consumer   "java -jar /tmp/dock"   30 seconds ago      Up 27 seconds       0.0.0.0:8080->8080/tcp   cosumer-c
C:\Users\sdass>curl http://dockerdev:8080/testing
testing passed
--------------------Basics above ---------------------
 Now modify dkr-compose-consumer's resttemplate localhost to put the service name as in docker-compose.yml

how to validate syntax of docker-compose.yml?
dockercompose-consumer>docker-compose -f docker-compose.yml config
ERROR: In file '.\docker-compose.yml', network must be a mapping, not a string.

docker-compose.yml snippet spits out if all good.

services:
  producer-c:
    image: dkr-compose-producer
    networks:
      - by-compose-producer-consumer
    ports:
      - "9090:9090"

  consumer-c:
    image: dkr-compose-consumer
    ports:
      - "8080:8080"
    networks:
      - by-compose-producer-consumer
      
networks:
  by-compose-producer-consumer:   

--------------
before creating dkr-compose-consumer image change localhost as servicename on .yml (below shown)
String dockerUrl = "http://producer-c:8080/hi";
-------
>docker-compose up

Creating network "dockercomposeconsumer_by-compose-producer-consumer" with the d
efault driver
Creating dockercomposeconsumer_consumer-c_1 ... done
Creating dockercomposeconsumer_producer-c_1 ... done
Attaching to dockercomposeconsumer_consumer-c_1, dockercomposeconsumer_producer-
c_1
.....
>docker network ls
shows
1dfc9cec445c        dockercomposeconsumer_by-compose-producer-consumer   bridge
along with default other 3 [1. host (name and driver type both host), 2. bridge (name and driver type both bridge) 3. none (name none, driver type=null).
------------------
>docker ps

734838da6e34        dkr-compose-producer   "java -jar /tmp/dock"   24 seconds ago      Up 18 seconds       0.0.0.0:9090->9090/tcp   dockercomposeconsumer_producer-c_1
1a0abe40627f        dkr-compose-consumer   "java -jar /tmp/dock"   24 seconds ago      Up 18 seconds       0.0.0.0:8080->8080/tcp   dockercomposeconsumer_consumer-c_1
---
docker logs -f 1a0abe40627f

test networked consumption with curl
C:\Users\sdass>curl http://dockerdev:8080/getother
say hello by compose

Also in the log window shows debug printing

2019-12-15 18:49:16.088  INFO 1 --- [nio-8080-exec-2] c.s.d.ConsumeService
               : dockerUrl :http://producer-c:9090/hi
2019-12-15 18:49:16.120  INFO 1 --- [nio-8080-exec-2] c.s.d.ConsumeService
               : received from http://producer-c:9090/hi result=say hello by com
pose
2019-12-15 18:49:16.121  INFO 1 --- [nio-8080-exec-2] c.s.d.ConsumeService
               : getFromOther(). . . .
---Passed --- successful---
