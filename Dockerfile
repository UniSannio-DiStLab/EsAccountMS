FROM maven:3.8.5-jdk-11 AS codeCompiler

#SHELL ["/bin/bash", "-c"]

RUN mkdir -p /source
WORKDIR /source
ADD . /source/

RUN mvn install

FROM jboss/wildfly:19.0.0.Final

ENV WILDFLY_APP AccountMS

USER root
RUN mkdir -p /source
WORKDIR /
COPY --from=codeCompiler /source /source


WORKDIR ${JBOSS_HOME}/modules/system/layers/base/com/mysql/main
ADD module.xml .
RUN curl -O https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.20/mysql-connector-java-8.0.20.jar


ADD standalone.xml ${JBOSS_HOME}/standalone/configuration/
RUN cp /source/target/${WILDFLY_APP}.war ${JBOSS_HOME}/standalone/deployments
RUN ${JBOSS_HOME}/bin/add-user.sh unisannio unisannio --silent


RUN chown -R jboss:0 ${JBOSS_HOME} && \
    chmod -R g+rw ${JBOSS_HOME}

EXPOSE 8080 8443 9990

USER jboss
ENTRYPOINT ${JBOSS_HOME}/bin/standalone.sh