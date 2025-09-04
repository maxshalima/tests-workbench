FROM openjdk:21 as builder
WORKDIR /tmp
COPY . .
RUN ./mvnw install package

FROM openjdk:21
VOLUME /tmp
#ADD target/testworks-0.0.1-SNAPSHOT.jar app.jar
COPY --from=builder /tmp/target/testworks-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080
