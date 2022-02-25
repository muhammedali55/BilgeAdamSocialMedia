FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY googlekey.json .
COPY private.key .
COPY public.key .
ENTRYPOINT ["java","-jar","/app.jar"]