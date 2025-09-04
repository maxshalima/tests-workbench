FROM maven:3.9.11-amazoncorretto-21 AS builder
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD . $HOME
RUN mvn package

FROM amazoncorretto:21-alpine
#ADD target/testworks-0.0.1-SNAPSHOT.jar app.jar
COPY --from=builder /usr/app/target/testworks-0.0.1-SNAPSHOT.jar /app/testworks.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/testworks.jar"]
EXPOSE 8080
