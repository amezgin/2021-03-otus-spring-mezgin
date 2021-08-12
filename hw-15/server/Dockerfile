FROM maven:3.6.3-jdk-11

EXPOSE 8888

MAINTAINER Mezgin Alexander <mezgin@yandex.ru>

WORKDIR /opt/server

ADD target/hw-15-server-*.jar server.jar

ENTRYPOINT java -jar server.jar