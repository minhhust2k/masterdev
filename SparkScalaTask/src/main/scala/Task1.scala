import org.apache.spark.sql.functions.collect_list
import org.apache.spark.sql.{DataFrame, SparkSession}

object Task1 {

  val spark = SparkSession
    .builder()
    .appName("Spark-Task1-minhpv64")
    .getOrCreate()

  def UsingQuery(parquetFileDF : DataFrame): Unit = {
    parquetFileDF.createOrReplaceTempView("parquetTable")
    val resultDF1 = spark.sql("SELECT device_model, COUNT(DISTINCT user_id) AS count FROM parquetTable WHERE device_model IS NOT NULL GROUP BY device_model")
    resultDF1.repartition(1).write.mode("overwrite").format("parquet").save("/user/minhpv64/device_model_num_user/")
    parquetFileDF.createOrReplaceTempView("parquetTable")
    val resultDF2 = spark.sql("SELECT device_model, COLLECT_LIST(user_id) AS list_user_id FROM parquetTable WHERE device_model IS NOT NULL GROUP BY device_model")
    resultDF2.repartition(1).write.mode("overwrite").format("orc").save("/user/minhpv64/device_model_list_user/")
  }

  def UsingFunction(parquetFileDF : DataFrame): Unit = {
    val filterDeviceDF = parquetFileDF.filter("device_model IS NOT NULL")
    val distinctDF = filterDeviceDF.select("user_id", "device_model").distinct()
    val rsDF1 = distinctDF.groupBy("device_model").count()
    rsDF1.repartition(1).write.mode("overwrite").format("parquet").save("/user/minhpv64/device_model_num_user_1/")
    val rsDF2 = distinctDF.groupBy("device_model").agg(collect_list("user_id").as("list_user_id"))
    rsDF2.repartition(1).write.mode("overwrite").format("orc").save("/user/minhpv64/device_model_list_user_1/")
  }

  def main (arg: Array[String]): Unit = {

    val parquetFileDF = spark.read.parquet("/user/minhpv64/Sample_data/")
    UsingQuery(parquetFileDF)
    UsingFunction(parquetFileDF)
  }
}