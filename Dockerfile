FROM openjdk:17
VOLUME /tmp
ADD target/*.jar freshTree.jar
ENTRYPOINT ["java", "-jar", "freshTree.jar"]