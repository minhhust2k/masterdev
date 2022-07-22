package SparkService;

import Utils.DataTrackingOut;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.text.SimpleDateFormat;


public class StreamingJob {
    public static void main(String[] args) throws StreamingQueryException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        SparkSession spark = SparkSession
                .builder()
                .appName("sparkstreaming-minhpv64")
                .getOrCreate();

        Dataset<Row> df = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "172.17.80.26:9092")
                .option("startingOffsets", "earliest")
                .option("subscribe", "protobuf-v3")
                .load();

        Dataset<String> df1 = df.select("value").as(Encoders.BINARY())
                .map((MapFunction<byte[], String>)
                        value -> String.format("%s,%s,%d,%s,%d,%d,%d,%d,%d", DataTrackingOut.DataTracking.parseFrom(value).getVersion(), DataTrackingOut.DataTracking.parseFrom(value).getName(), DataTrackingOut.DataTracking.parseFrom(value).getTimestamp(), DataTrackingOut.DataTracking.parseFrom(value).getPhoneId(), DataTrackingOut.DataTracking.parseFrom(value).getLon(), DataTrackingOut.DataTracking.parseFrom(value).getLat(), simpleDateFormat.parse(String.valueOf(DataTrackingOut.DataTracking.parseFrom(value).getTimestamp())).getYear() + 1900, simpleDateFormat.parse(String.valueOf(DataTrackingOut.DataTracking.parseFrom(value).getTimestamp())).getDate(), simpleDateFormat.parse(String.valueOf(DataTrackingOut.DataTracking.parseFrom(value).getTimestamp())).getHours())
                , Encoders.STRING());

        Column tempCol = functions.split(df1.col("value"), ",");

        Dataset<Row> df2 = df1.withColumn("version", tempCol.getItem(0))
                .withColumn("name", tempCol.getItem(1))
                .withColumn("timestamp", tempCol.getItem(2))
                .withColumn("phone_id", tempCol.getItem(3))
                .withColumn("lon", tempCol.getItem(4))
                .withColumn("lat", tempCol.getItem(5))
                .withColumn("year", tempCol.getItem(6))
                .withColumn("day", tempCol.getItem(7))
                .withColumn("hour", tempCol.getItem(8))
                .drop("value");


        StreamingQuery query = df2.writeStream()
                .outputMode("append")
                .format("parquet")
                .option("compression", "snappy")
                .option("checkpointLocation", "/user/minhpv64/checkpoint")
                .option("path", "/user/minhpv64/data_tracking")
                .partitionBy("year","day","hour")
                .start();
        query.awaitTermination();
        spark.streams().awaitAnyTermination(50000);
    }
}
