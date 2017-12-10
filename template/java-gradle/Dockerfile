FROM anapsix/alpine-java:8_jdk as builder

USER root

RUN mkdir -p /root/src/

ADD function /root/src/
WORKDIR /root/src/
RUN ./gradlew

ADD Main.java /root/src/
WORKDIR /root/src
RUN javac -cp ".:function/build/libs/*"  Main.java

FROM anapsix/alpine-java:8_server-jre

RUN apk --no-cache add curl \
    && echo "Pulling watchdog binary from Github." \
    && curl -sSL https://github.com/openfaas-incubator/of-watchdog/releases/download/0.0.4/of-watchdog > /usr/bin/fwatchdog \
    && chmod +x /usr/bin/fwatchdog \
    && apk del curl --no-cache

RUN addgroup -S runner && adduser -S -g runner runner
WORKDIR /home/runner
USER runner

COPY --from=builder /root/src/function/build/libs /home/runner/libs
COPY --from=builder /root/src/Main.class /home/runner

ENV afterburn=true
ENV mode=afterburn
ENV fprocess="java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -cp '.:function/build/libs/*' Main"

HEALTHCHECK --interval=1s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
