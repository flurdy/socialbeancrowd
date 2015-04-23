#FROM flurdy/oracle-java7:latest
FROM maven:3.2-jdk-7-onbuild

MAINTAINER flurdy

ENV DEBIAN_FRONTEND noninteractive

ENV APPDIR /opt/app

RUN mkdir -p /etc/opt/app 

ADD . /opt/app

WORKDIR /opt/app

RUN rm -rf /opt/app/target

RUN chmod +x /opt/app/socialbeancrowd

RUN mvn package 

ENTRYPOINT ["/opt/app/socialbeancrowd"]
