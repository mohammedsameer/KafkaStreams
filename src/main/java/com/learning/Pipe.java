package com.learning;


import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;

public class Pipe {
    public static void main(String[] args) throws Exception {
        //Initialize the kafka stream properties
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-pipe");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "10.0.0.243:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //Stream builder for reading/writing to topic
        KStreamBuilder streamBuilder = new KStreamBuilder();
        streamBuilder.stream("input-test").to("output-test");

        final KafkaStreams kafkaStreams = new KafkaStreams(streamBuilder, props);
        kafkaStreams.start();

        System.out.println("Streams started!!");

        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }
}
