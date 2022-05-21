FROM amd64/openjdk:17-jdk
RUN mkdir /ccm
RUN cd /ccm
COPY ./* ./
RUN chmod +x gradlew 
RUN ./gradlew dependency
RUN ./gradlew bootrun
