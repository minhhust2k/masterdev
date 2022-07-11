# Setup Enviroment :
+ Spark 2.4.0 
+ Scala 2.11.12
+ Maven 
# Hướng giải quyết các task :
+ Với 2 task em, sử dụng 2 cách : 1 - tạo temporary view của dataframe (dạng table) rồi sử dụng query SQL để xử lý, 2 - sử dụng các function Spark để xử lý trực tiếp trên dataframe
# Các bước chạy task :
+ Build file jar của project: sử dụng lệnh mvn package
+ scp file jar của project lên server (ví dụ : scp minhpv64-1.0-SNAPSHOT.jar hadoop@172.17.80.27:~/minhpv64/)
+ Chạy Task 1 : spark-submit --deploy-mode cluster --class Task1 minhpv64-1.0-SNAPSHOT.jar (Spark chạy với Yarn ở server 172.17.80.27 - password : 1)
+ Chạy Task 2 : spark-submit --deploy-mode cluster --class Task2 minhpv64-1.0-SNAPSHOT.jar
# Output chạy các Task được save trên HDFS :
+ /user/minhpv64/device_model_num_user/ (sử dụng query SQL)
+ /user/minhpv64/device_model_num_user_1/ (sử dụng function Spark)
+ /user/minhpv64/device_model_list_user/ (sử dụng query SQL)
+ /user/minhpv64/device_model_list_user_1/ (sử dụng function Spark)
+ /user/minhpv64/button_count_by_user_id_device_model/ (sử dụng query SQL)
+ /user/minhpv64/button_count_by_user_id_device_model_1/ (sử dụng function Spark)
