FROM amd64/openjdk:17-jdk
RUN microdnf install findutils
RUN mkdir /ccm
RUN cd /ccm
COPY ./* ./
RUN chmod +x gradlew
RUN gradle wrapper
RUN ./gradlew dependency
RUN ./gradlew bootrun
