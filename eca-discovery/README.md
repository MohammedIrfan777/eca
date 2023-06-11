# ECA SPRING CLOUD DISCOVERY SERVER 

Spring Cloud Discovery server to register all services  

## Steps to Setup

**1. Clone the repository** 

```bash
git clone https://github.com/MohammedIrfan777/eca.git
```

**2. Run the app using maven**

```bash
cd eca
cd eca-discovery
mvn spring-boot:run
```

That's it! The application can be accessed at `http://localhost:8761`.

You may also package the application in the form of a jar and then run the jar file like so -

```bash
mvn clean package
java -jar target/eca-discovery*.jar
```

# OR

Simply run the docker image container using docker

```bash
docker build -t ecadiscovery:latest .
docker run -d -p 8761:8761 ecadiscovery:latest
```

