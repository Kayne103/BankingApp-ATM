FROM openjdk:8
COPY ./out/artifacts/ATM_jar/ATM.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java -jar","ATM.jar"]