FROM maven:3.6.3-jdk-11

EXPOSE 8081

MAINTAINER MAINTAINER Mezgin Alexander <mezgin@yandex.ru>

WORKDIR /opt/client

ADD wait-for-it.sh wait-for-it.sh

ADD docker-entrypoint.sh docker-entrypoint.sh

ADD target/hw-16-client-*.jar client.jar

RUN chmod +x ./wait-for-it.sh ./docker-entrypoint.sh

ENTRYPOINT ["./docker-entrypoint.sh"]

CMD java -jar client.jar