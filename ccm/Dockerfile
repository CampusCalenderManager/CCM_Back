FROM amd64/openjdk:17-jdk
RUN microdnf install findutils
RUN mkdir /ccm
COPY ./ /ccm
WORKDIR /ccm
RUN chmod +x gradlew
RUN ./gradlew bootrun
