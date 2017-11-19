package com.learning;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;

public class WordCount {
    public static void main(String[] args) throws Exception {
        //Initialize the kafka stream properties
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "10.0.0.243:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KStreamBuilder streamBuilder = new KStreamBuilder();
        KStream<String, String> lineStream = streamBuilder.stream("input-wordcount");
        KTable<String, Long> wordCount =lineStream.flatMapValues(line -> Arrays.asList(line.toLowerCase(Locale.ENGLISH).split("\\W+")))
                .groupBy((key, word) -> word)
                .count("Counts");

        wordCount.to(Serdes.String(), Serdes.Long(), "output-wordcount");
        KafkaStreams kafkaStreams = new KafkaStreams(streamBuilder, props);
        kafkaStreams.start();

        System.out.println("Streams started!!");

        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }
}
