FROM alpine:latest
RUN apk update
RUN apk add openjdk17-jre-headless wget
RUN apk add curl
RUN mkdir /usr/local/tomcat
RUN wget https://downloads.apache.org/tomcat/tomcat-10/v10.1.23/bin.apache-tomcat-10.1.23.tar.gz -0 /tmp/tomcat.tar.gz
RUN cd /tmp ; tar xvfz tomcat.tar.gz
RUN cp -Rv /tmp/apache-tomcat-10.1.23/* /usr/local/tomcat/
EXPOSE 8070
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk
LABEL email jessy.wendling@afpa-dev-pompey.fr
WORKDIR /usr/local/tomcat/webapps
RUN curl -0 -L https://tomcat.apache.org/tomcat-7.0-doc/appdev/sample/sample.war
CMD [ "/usr/local/tomcat/bin/catalina.sh", "run" ]