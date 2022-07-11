import org.apache.spark.sql.functions.{col, collect_list, concat, lit}
import org.apache.spark.sql.{DataFrame, SparkSession}

object Task2 {

  val spark = SparkSession
    .builder()
    .appName("Spark-Task2-minhpv64")
    .getOrCreate()

  def UsingQuery(parquetFileDF : DataFrame): Unit = {
    parquetFileDF.createOrReplaceTempView("parquetTable")
    val filterDF = spark.sql("SELECT concat(parquetTable.user_id, '_', parquetTable.device_model) AS user_id_device_model, button_id FROM parquetTable WHERE device_model IS NOT NULL AND button_id IS NOT NULL")
    filterDF.createOrReplaceTempView("tempTable")
    val rsDF = spark.sql("SELECT user_id_device_model, button_id, COUNT(*) AS count FROM tempTable GROUP BY user_id_device_model, button_id" )
    rsDF.repartition(1).write.mode("overwrite").format("parquet").save("/user/minhpv64/button_count_by_user_id_device_model_1/")
  }

  def UsingFunction(parquetFileDF : DataFrame): Unit = {
    val concatDF = parquetFileDF.filter("device_model IS NOT NULL AND button_id IS NOT NULL").select(concat(col("user_id"), lit("_"), col("device_model")).as("user_id_device_model"), col("button_id"))
    val rsDF = concatDF.groupBy(col("user_id_device_model"), col("button_id")).count()
    rsDF.repartition(1).write.mode("overwrite").format("parquet").save("/user/minhpv64/button_count_by_user_id_device_model/")
  }

  def main (arg: Array[String]): Unit = {

    val parquetFileDF = spark.read.parquet("/user/minhpv64/Sample_data/")
    UsingQuery(parquetFileDF)
    UsingFunction(parquetFileDF)
  }
}