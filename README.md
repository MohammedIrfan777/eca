<h1 align="center">A B2B/B2C Apartment Services Apps</h1>

<img alt="MVP1 model" src="eca-apartment-services.gif"> </img>

ECA APARTMENT SERVICES MVP1 Model
=============================

## Version 1.0.0 - Published on June 2023

## Contents
- [Software Requirements](#softwares-requirements)
- [Architectural Diagram](#architectural-diagram)
- [Code Mode 👨🏽‍💻](#code-mode)
- [Swagger UI](#swagger-setup)
- [Jenkins Setup](#jenkins-setup)
- [ELK Setup](#elkf-setup)
- [Grafana Setup](#grafana-setup)
- [Sonar and NFR](#sonar-nfr)
- [DB Setup](#db-setup)
- [Stage and prod urls](#stage-prod-urls)
- [Tools](#tools)

## Softwares Requirements
- java 11 
- Intellij IDEA or STS
- spring boot 2.7.11 and spring cloud 2021.0.7
- h2 inner memory DB
- kafka 7.3.0
- docker latest
- postgress 7.0
- redis latest
- zookeeper 7.3.0
- mongo DB latest
- kibana, elasticsearch, filebeats, logstash 7.17.0

## Architectural Diagram
<img alt="MVP1 model" src="eca-arch-diagram.jpg"> </img>

## Code Mode

Simply run the below docker compose cmds inside the eca folder.

```bash
docker-compose -f post-redis-pgadmin-docker-compose.yml up -d
docker-compose -f kafka-grafana-promo-eca-services-app-docker-compose up -d
```
After all services containers started access discovery server using your host and port(8761).
[eureka](https://localhost:8761/)

## Swagger Setup

After all services started access the swagger url using your host and port ex:
[swagger](https://localhost:6090/swagger-ui/index.html/)
## Jenkins Setup

Simply run the below docker cmds in your system

```bash
docker pull jenkins/jenkins:latest
docker run -p 8080:8080 jenkins/jenkins:latest
```
After jenkins container started access jenkins server using your host and port(8080).
[jenkins](https://localhost:8080/)

## ELKF Setup

Go inside dockerComposeEKLF folder update the elastic host, kibana host and port(9200,5601) in logstash and filebeat under config conf file.
After changes just run the below docker compose cmd inside dockerComposeEKLF folder

```bash
docker-compose -f docker-compose.yml up -d
```
After all ELK containers started access elastic and kibana server using your host and port(9200, 5601).
[elastic](https://localhost:9200/)
[kibana](https://localhost:5601/)

## Grafana Setup
```bash
docker-compose -f kafka-grafana-promo-eca-services-app-docker-compose up -d
```
Run the above docker compose file, all containers gets started along with grafana and prometheus.
Grafana and prometheus can be accessible using below urls.
[prometheus](https://localhost:9090/)
[grafana](https://localhost:3000/)

## Sonar NFR
Install sonarqube plugin in jenkins whenever jenkins build started and successfully executed it generates the
sonar NFR report shows scalability, reliability, vulnerabilities along with the code smells presents in the projects.
To display sonar NFR report we need to have sonar server running in the system to
to start sonar server in the system run the below docker cmd in the system.

```bash
docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube
```
after sonar server container started server can be accessible using below url.
[sonar](https://localhost:9000/)

## DB Setup
After containers started using services docker compose file. DB can be accessible in below url
[postgres](https://localhost:5050/)
[H2](https://localhost:6090/h2-console/)
login to the Database system and create the required DB to make run the apartment services up and running.
required DB(ECA_APARTMENT_DB, ECA_VISITOR_DB, ECA_USER_DB).

## Tools
- Jenkins
- Kibana
- elasticsearch
- postgres sql
- pgAdmin
- Intellij or STS







