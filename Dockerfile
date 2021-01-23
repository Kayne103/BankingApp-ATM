FROM openjdk:8
COPY ./out/production/ATM/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","App"]