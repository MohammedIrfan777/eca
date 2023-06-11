# ECA SPRING CLOUD CONFIG SERVER 

This Application is created to load or keep configuration, environment props of aparments services in centralized location and read it on runtime.  

## Steps to Setup

**1. Clone the repository** 

```bash
git clone https://github.com/MohammedIrfan777/eca.git
```

**2. Run the app using maven**

```bash
cd eca
cd eca-configserver
mvn spring-boot:run
```

That's it! The application can be accessed at `http://localhost:6091`.

You may also package the application in the form of a jar and then run the jar file like so -

```bash
mvn clean package
java -jar target/eca-configserver*.jar
```

# OR

Simply run the docker image container using docker

```bash
docker build -t ecaconfigserver:latest .
docker run -d -p 6091:6091 ecaconfigserver:latest
```

