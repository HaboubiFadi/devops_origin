FROM openjdk:11
EXPOSE 8082
ADD target/app.jar DevOps_Project-2.1.jar
ENTRYPOINT ["java","-jar","/DevOps_Project-2.1.jar"]
