FROM amd64/openjdk:17-jdk
COPY ./build/libs/ccm-0.0.1-SNAPSHOT.jar /ccm-0.0.1-SNAPSHOT.jar
COPY *.yml /
ENTRYPOINT ["java", "-jar", "ccm-0.0.1-SNAPSHOT.jar"]
