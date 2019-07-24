FROM hub.c.163.com/library/java:latest
VOLUME /tmp
ADD target/*.jar eureka-server.jar
#RUN bash -c 'touch /eureka-server.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-server.jar"]
EXPOSE 8761