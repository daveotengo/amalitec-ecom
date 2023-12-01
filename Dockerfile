FROM openjdk:17
VOLUME /tmp
ADD target/amalitec-ecom-0.0.1-SNAPSHOT.jar amalitec-ecom-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/amalitec-ecom-0.0.1-SNAPSHOT.jar"]