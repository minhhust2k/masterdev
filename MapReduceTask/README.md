# Setup Hadoop Cluster
+ Server được sử dụng : 172.17.80.27/28/29/30/31
+ NameNode (Cài với HA) : Activate NameNode (27), Stanby NameNode (31), 5 JournalNode(27-31)
+ 5 DataNode (27-31)
+ yarn.resourcemanager.hostname (27)
# MapReduce Task
+ Để thực hiện các job mapreduce, ssh vào hadoop@172.17.80.27 (password : 1) => cd minhpv64
## Word Count Task :
+ Em sử dụng regex để replace tất cả kí tự đặc biệt ở trong phase Mapper
+ (home/hadoop/minhpv64) : yarn jar MapReduceTask-minhpv64.jar main.java.WordCount.WordCount minhpv64/poem.txt minhpv64/output
## Distinct Count Task : 
+ Output : (0,99996)
+ Cách 1 : ở phase Reducer em để count + 1 với mỗi key được nhóm lại, sau đó em sẽ write count trong function cleanup (được gọi đến khi kết thúc task Reduce). Ưu điểm của cách này là đơn giản nhưng bắt buộc chỉ được sử dụng 1 task Reduce. 
(home/hadoop/minhpv64) yarn jar MapReduceTask-minhpv64.jar main.java.CountDistinct1ReducerTask.CountDistinct minhpv64/count_distinct.csv minhpv64/output
+ Cách 2 : Em sử dụng 2 job mapreduce liên tiếp, output của job1 sẽ là input của job2, với job1 thì là một 

+ yarn jar MapReduceTask-minhpv64.jar main.java.CountDistinct1ReducerTask.CountDistinct minhpv64/count_distinct.csv minhpv64/output
+ yarn jar MapReduceTask-minhpv64.jar main.java.CountDistinct2Jobs.CountDistinct minhpv64/count_distinct.csv minhpv64/outtemp minhpv64/output
+ yarn jar MapReduceTask-minhpv64.jar main.java.JoinReducePhaseText.JoinDriver minhpv64/salary.csv minhpv64/people.csv minhpv64/output
+ yarn jar MapReduceTask-minhpv64.jar main.java.JoinReducePhaseGenericWritable.JoinDriver minhpv64/salary.csv minhpv64/people.csv minhpv64/output
+ yarn jar MapReduceTask-minhpv64.jar main.java.JoinMapPhaseText.JoinDriver hdfs://ha-cluster/user/hadoop/minhpv64/salary.csv minhpv64/people.csv minhpv64/output
 
