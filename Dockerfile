FROM openjdk:17.0.2-jdk-slim-buster

EXPOSE 5050

ADD target/diplom-0.0.1-SNAPSHOT.jar diplom.jar

CMD ["java", "-jar", "diplom.jar"]