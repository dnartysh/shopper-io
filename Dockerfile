FROM openjdk
COPY shopper-0.1.jar /app.jar
ENTRYPOINT ["java","-jar","app.jar"]