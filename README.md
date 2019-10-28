
# Election Chain

## Requirements
- [Java SE Development Kit 8u231](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)


## Development
> note change "/" to "\\" when you run on Windows OS

1. Install dependencies and build `jar` file using this command
```
./mvnw clean package
```

2. Run development server by using this command
```
java -jar .\target\chains-0.0.1-SNAPSHOT.jar
# specific server port by add `--server.port=8081`
java -jar .\target\chains-0.0.1-SNAPSHOT.jar --server.port=8081
```