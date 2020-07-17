FROM openjdk:14
 
COPY target/accountManager-0.0.1-SNAPSHOT.jar /app.jar
 
CMD ["java", "-jar", "/app.jar"]
