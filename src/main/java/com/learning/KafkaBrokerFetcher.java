package com.learning;

import java.util.List;

import org.apache.zookeeper.ZooKeeper;

/**
 * Created by msameer on 11/16/17.
 */
public class KafkaBrokerFetcher {

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 10000, null);
        List<String> ids = zk.getChildren("/brokers/ids", false);
        for (String id : ids) {
            String brokerInfo = new String(zk.getData("/brokers/ids/" + id, false, null));
            System.out.println(id + ": " + brokerInfo);
        }
    }
}
