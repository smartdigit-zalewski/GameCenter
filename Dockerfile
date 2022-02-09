FROM openjdk:11-jdk-oracle

VOLUME /tmp

ADD target/GameCenter-0.0.1-SNAPSHOT.jar myApp.jar

RUN sh -c 'touch myApp.jar'

ENTRYPOINT ["java","-jar","myApp.jar"]