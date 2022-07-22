package KafkaService;

import Utils.DataTrackingOut;
import com.github.javafaker.Faker;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;

public class ProtobufProducer {

    public static void main(String[] args) {
        ProtobufProducer protobufProducer = new ProtobufProducer();
        protobufProducer.writeMessages();
    }

    public void writeMessages() {
        Properties properties = new Properties();

        properties.put("bootstrap.servers", "172.17.80.26:9092");
        properties.put("schema.registry.url", "http://172.17.80.26:8081");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", ProtobufSerializer.class.getName());

        Producer<String, DataTrackingOut.DataTracking> producer = new KafkaProducer<>(properties);

        //prepare the message
        Faker faker = new Faker();
        int cnt = 45;
        while (cnt > 0) {
            String version = faker.crypto().md5();
            String name = "v8";

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Long timestamp = Long.valueOf(simpleDateFormat.format(new Timestamp(System.currentTimeMillis())));
//            Long timestamp = 20220323182222L;
            String phone = faker.phoneNumber().phoneNumber();
            Long lon = new Random().nextLong();
            Long lat = new Random().nextLong();
            DataTrackingOut.DataTracking simpleMessage =
                    DataTrackingOut.DataTracking.newBuilder()
                            .setVersion(version)
                            .setName(name)
                            .setTimestamp(timestamp)
                            .setPhoneId(phone)
                            .setLon(lon)
                            .setLat(lat)
                            .build();

            System.out.println(simpleMessage);


            //prepare the kafka record
            ProducerRecord<String, DataTrackingOut.DataTracking> record
                    = new ProducerRecord<>("protobuf-v3", "key", simpleMessage);

            producer.send(record);
                //ensures record is sent before closing the producer
            cnt--;
        }
        producer.flush();
        producer.close();
    }
}
