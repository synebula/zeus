FROM gradle:8.10.1-jdk21-alpine AS build
ENV GRADLE_USER_HOME=/.gradle
USER root
WORKDIR /src
COPY --chown=gradle:gradle . .
RUN --mount=type=cache,target=/.gradle gradle bootJar --no-daemon --build-cache

FROM openjdk:21-jdk-slim
EXPOSE 80
VOLUME /logs
WORKDIR /app
COPY --from=build /src/src/zeus.app/build/libs/*.jar ./spring-boot-application.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]