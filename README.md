# Countdown time using Spring native

```shell
docker run -p 8080:8080 countdown-timer-native:0.0.1
docker build . --tag countdown-timer-native:0.0.1 --platform=linux/amd64
```
```shell
docker tag countdown-timer-native:0.0.1 codehunter6323/countdown-timer-native:0.0.1
docker push docker push codehunter6323/countdown-timer-native:0.0.1
```
```shell
# show docker account list
less ~/.docker/config.json
```

https://hilla.dev/blog/ai-chatbot-in-java/deploying-a-spring-boot-app-as-a-graalvm-native-image-with-docker/