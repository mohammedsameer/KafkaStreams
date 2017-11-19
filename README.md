# KafkaStreams
Pre-Requisite:
Start zookeeper
```
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
```
Start kafka broker
```
./bin/kafka-server-start.sh ./config/server.properties
```

Examples:
1. Pipe
Create topics
```
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic input-test
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic output-test
```
Start Producer and Consumer
```
./bin/kafka-console-producer.sh --broker-list 10.0.0.243:9092 --topic input-test
./bin/kafka-console-consumer.sh --bootstrap-server 10.0.0.243:9092 --from-beginning --topic output-test
```
Samples
I/O:
hello

O/P:
hello

2. LinSplit
Create topics:
```
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic input-line
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic output-line
```
Start Producer and Consumer
```
./bin/kafka-console-producer.sh --broker-list 10.0.0.243:9092 --topic input-line
./bin/kafka-console-consumer.sh --bootstrap-server 10.0.0.243:9092 --from-beginning --topic output-line
```
Samples:
I/O:
my name is sameer
O/P:
my
name
is
sameer

3. WordCount
Create topics:
```
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic input-wordcount
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic output-wordcount
```
Start Producer and Consumer
```
./bin/kafka-console-producer.sh --broker-list 10.0.0.243:9092 --topic input-wordcount
./bin/kafka-console-consumer.sh --bootstrap-server 10.0.0.243:9092 --from-beginning --topic output-wordcount --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```
Samples:
I/O:
hello hello sameer
O/P:
hello   2
sameer  1