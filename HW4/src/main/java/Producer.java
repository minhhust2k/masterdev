import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;

public class Producer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "172.17.80.27:9092");
        properties.setProperty("key.serializer", IntegerSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://172.17.80.27:8081");
        String topic = "HW4";

        KafkaProducer<Integer, Customer> producer = new KafkaProducer<Integer, Customer>(properties);
        try {
            FileReader filereader = new FileReader("src/main/java/Customer.csv");
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                Customer customer = new Customer(Integer.parseInt(row[0]),Integer.parseInt(row[1]),Integer.parseInt(row[2]),row[3]);
                int id = Integer.parseInt(row[0]);
                ProducerRecord<Integer, Customer> pr = new ProducerRecord<Integer, Customer>(topic, id, customer);
                RecordMetadata metadata = producer.send(pr).get();
                System.out.println(metadata);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.flush();
        producer.close();
    }
}
