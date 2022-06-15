import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.io.FileWriter;

public class Consumer {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "172.17.80.27:9092");
        properties.put("group.id", "customer-group1");
        properties.setProperty("key.deserializer", IntegerDeserializer.class.getName());
        properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        properties.setProperty("schema.registry.url", "http://172.17.80.27:8081");
        properties.setProperty("specific.avro.reader", "true");
        String topic = "HW4";

        KafkaConsumer<Integer, Customer> kafkaConsumer = new KafkaConsumer<Integer, Customer>(properties);
        kafkaConsumer.subscribe(Collections.singleton(topic));
        System.out.println("Waiting for data...");
        FileWriter writer = new FileWriter("src/main/java/output.csv");
        ArrayList<Integer> idList = new ArrayList<>();
        writer.append("id,num_order,Age,Tel");
        while (true) {
            System.out.println("Polling");
            ConsumerRecords<Integer, Customer> records = kafkaConsumer.poll(1000);

            for (ConsumerRecord<Integer, Customer> record : records) {
                System.out.println(record.value());
                if ((!idList.contains(record.key())) && record.value().getAge() < 20 && record.value().getNumOrder() > 100) {
                    idList.add(record.key());
                    writer.append(Integer.toString(record.value().getId()));
                    writer.append(",");
                    writer.append(Integer.toString(record.value().getNumOrder()));
                    writer.append(",");
                    writer.append(Integer.toString(record.value().getAge()));
                    writer.append(",");
                    writer.append(record.value().getTel());
                    writer.append("\n");
                }
                writer.flush();
            }

            kafkaConsumer.commitSync();
        }
    }
}
