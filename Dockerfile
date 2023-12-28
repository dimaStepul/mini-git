#pre-build
FROM gradle:jdk11 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle.kts settings.gradle.kts $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build --no-daemon || return 0
COPY . .
RUN gradle clean build
WORKDIR /app

#application build
FROM openjdk:11-slim

ENV ARTIFACT_NAME=fat.jar
ENV APP_HOME=/usr/app/


WORKDIR $APP_HOME

RUN groupadd -r appgroup && useradd -r -g appgroup appuser

COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

USER appuser

ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
