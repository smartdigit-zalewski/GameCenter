FROM openjdk:11-jdk-oracle

VOLUME /tmp

ADD target/GameCenter-0.0.1-SNAPSHOT.jar myApp.jar

RUN sh -c 'touch /myapp.jar'

ENTRYPOINT ["java","-jar","/myapp.jar"]