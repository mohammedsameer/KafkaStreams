# KafkaStreams
## Pre-Requisite
(i) [Download kafka](https://www.apache.org/dyn/closer.cgi?path=/kafka/1.0.0/kafka_2.11-1.0.0.tgz)
  > tar -xzf kafka_2.11-1.0.0.tgz
  
  > cd kafka_2.11-1.0.0
  
(ii) Start zookeeper
```
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
```
(iii) Start kafka broker
```
./bin/kafka-server-start.sh ./config/server.properties
```

## Examples
### 1. Pipe

a) Create topics
```
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic input-test
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic output-test
```
b) Start Producer and Consumer
```
./bin/kafka-console-producer.sh --broker-list 10.0.0.243:9092 --topic input-test
./bin/kafka-console-consumer.sh --bootstrap-server 10.0.0.243:9092 --from-beginning --topic output-test
```
Sample

Input:
hello
Output:
hello

### 2. LinSplit

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
Sample

Input:
my name is sameer
Output:
my
name
is
sameer

### 3. WordCount

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
Sample

Input:
hello hello sameer
Output:
hello   2
sameer  1
