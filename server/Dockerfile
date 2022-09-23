FROM gradle:7.4.0-jdk17 AS build_stage

COPY --chown=gradle:gradle . /project

WORKDIR /project

RUN gradle clean assemble

FROM openjdk:17

WORKDIR /project

COPY --from=build_stage /project/build/libs/*.jar ./build/app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "build/app.jar"]
