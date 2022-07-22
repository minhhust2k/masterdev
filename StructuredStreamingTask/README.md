# Setup Enviroment:
+ Java 8
+ Maven
+ HDFS 3.2.3 (server : hadoop@172.17.80.27/28/29/30/31)
+ Spark 2.4.0 on yarn (server : hadoop@172.17.80.27)
+ Apache Hive 3.1.2 (server : hadoop@172.17.80.27)
+ Confluent-5.5.0 (kafka, schema registry) (server : minhpv64@172.17.80.26)

# Cấu trúc thư mục Project :
+ src/main/java/KafkaService:
  + ProtobufProducer : Generate dữ liệu và đẩy vào producer kafka
  + ProtobufSerializer : Override lại function serialize của KafkaProtobufSerializer nhằm mục đích serialize value message đẩy vào Kafka cùng tương thích với deserialize mà Spark Structured Streaming hỗ trợ (class org.apache.kafka.common.serialization.ByteArrayDeserializer)
+ src/main/java/SparkService:
  + Streamingjob : sử dụng Spark Structured Streaming để đọc dữ liệu đã được đẩy vào Kafka và ghi dữ liệu đó (dưới định dạng parquet) vào hdfs
+ src/main/java/Utils :
  + DataTrackingOut : File java được gen từ file schema Protobuf

# Các bước chạy :
+ Build file jar project: sử dụng lệnh mvn package
+ Chạy Streaming Job trên server chứa Spark và Yarn (27) : spark-submit --deploy-mode cluster --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.4.0,com.google.protobuf:protobuf-java:3.12.2 --class SparkService.StreamingJob StreamingTask-Minhpv64-1.0-SNAPSHOT.jar (Lưu ý ở đây có 2 dependencies cần import lúc chạy spark job bằng cách truyền tham số --packages)
+ Khi Streaming Job chạy, có thể tiếp tục dẩy message vào producer bằng cách chạy class ProtobufProducer để Streaming Job tiếp tục xử lý
+ Tạo table Hive từ folder dữ liệu được lưu tren hdfs ở trên : ví dụ CREATE TABLE IF NOT EXISTS minhpv64.data_tracking_2022_19_18 (`version` STRING, `name` STRING, `timestamp` STRING, `phone_id` STRING, `lon` STRING, `lat` STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS PARQUET LOCATION "/user/minhpv64/data_tracking/year=2022/day=19/hour=18";
+ Sau đó có thể vào server 27 => chạy hive rồi query ở trên table vừa tạo : ví dụ SELECT * FROM minhpv64.data_tracking_2022_19_18;
![image](https://user-images.githubusercontent.com/106506105/180393689-de2abe84-c484-4721-b072-a919bf793eb4.png)

# Output sau khi chạy job được save trên HDFS:
+ /user/minhpv64/checkpoint (checkpoint location)
+ /user/minhpv64/data_tracking/year=?/day=?/hour=? (dữ liệu được write dưới dạng parquet)
