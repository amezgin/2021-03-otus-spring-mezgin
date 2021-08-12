FROM maven:3.6.3-jdk-11

EXPOSE 8081

MAINTAINER MAINTAINER Mezgin Alexander <mezgin@yandex.ru>

WORKDIR /opt/client

ADD target/hw-15-client-*.jar client.jar

ENTRYPOINT java -jar client.jar