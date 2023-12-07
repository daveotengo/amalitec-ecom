FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /app

# Copy the Maven project file for dependency resolution
COPY ./pom.xml /app/

# Copy the source code
COPY ./src /app/src/

# Build the application
RUN ["mvn", "clean", "install", "-DskipTests=true"]

FROM openjdk:17

WORKDIR /app

# Copy the compiled JAR file from the builder stage
COPY --from=builder /app/target/amalitec-ecom-0.0.1-SNAPSHOT.jar /app/amalitec-ecom-0.0.1-SNAPSHOT.jar

# Expose the port on which the application runs (adjust as needed)
EXPOSE 11234

# Run the application
ENTRYPOINT ["java", "-jar", "amalitec-ecom-0.0.1-SNAPSHOT.jar"]

#FROM openjdk:17
#VOLUME /tmp
#ADD target/amalitec-ecom-0.0.1-SNAPSHOT.jar amalitec-ecom-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/amalitec-ecom-0.0.1-SNAPSHOT.jar"]