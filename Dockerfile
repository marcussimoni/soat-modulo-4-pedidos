FROM maven:3.8.3-openjdk-17 as MAVEN

COPY ./application-business ./application-business
COPY ./enterprise-business ./enterprise-business
COPY ./frameworks-drivers ./frameworks-drivers
COPY ./interface-adapters ./interface-adapters
COPY ./pom.xml ./pom.xml

RUN mvn clean package

FROM bellsoft/liberica-openjdk-alpine:17.0.7 as builder

COPY --from=MAVEN ./frameworks-drivers/target/*.jar /application.jar

RUN java -Djarmode=layertools -jar application.jar extract

FROM bellsoft/liberica-openjdk-alpine:17.0.7
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]