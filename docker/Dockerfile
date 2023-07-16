FROM amazoncorretto:17.0.7-alpine3.16
EXPOSE 8080
WORKDIR /opt/jar/
COPY core-services.jar .
ENTRYPOINT ["java", "-jar", "core-services.jar"]
