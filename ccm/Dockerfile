FROM amd64/openjdk:17-jdk
RUN microdnf install findutils wget  unzip
RUN wget https://services.gradle.org/distributions/gradle-7.4.2-bin.zip
RUN unzip -d /opt/gradle gradle-7.4.2-bin.zip
RUN export PATH=$PATH:/opt/gradle/gradle-7.4.2/bin
RUN mkdir /ccm
RUN cd /ccm
COPY ./* ./
RUN /opt/gradle/gradle-7.4.2/bin/gradle wrapper
RUN chmod +x gradlew
RUN ./gradlew bootrun
