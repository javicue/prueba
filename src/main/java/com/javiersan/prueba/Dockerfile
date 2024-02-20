FROM openjdk:8-jdk-alpine
COPY "./target/SBServicesRest-01.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]