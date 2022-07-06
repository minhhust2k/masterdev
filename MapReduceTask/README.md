# Setup Hadoop Cluster
+ Server được sử dụng : 172.17.80.27/28/29/30/31
+ NameNode (Cài với HA) : Activate NameNode (27), Stanby NameNode (31), 5 JournalNode(27-31)
+ 5 DataNode (27-31)
+ yarn.resourcemanager.hostname (27)
# MapReduce Task
+ Để thực hiện các job mapreduce, ssh vào hadoop@172.17.80.27 (password : 1) => cd minhpv64
+ Trong các folder có tên out... trong repo này là các output job của em
## Word Count Task :
+ Em sử dụng regex để replace tất cả kí tự đặc biệt ở trong phase Mapper
+ (home/hadoop/minhpv64) : yarn jar MapReduceTask-minhpv64.jar main.java.WordCount.WordCount minhpv64/poem.txt minhpv64/output
## Distinct Count Task : 
+ Output : (0,99996)
+ Cách 1 : ở phase Reducer em để count + 1 với mỗi key được nhóm lại, sau đó em sẽ write count trong function cleanup (được gọi đến khi kết thúc task Reduce). Ưu điểm của cách này là đơn giản nhưng bắt buộc chỉ được sử dụng 1 task Reduce
+ Cách 1 : (home/hadoop/minhpv64) yarn jar MapReduceTask-minhpv64.jar main.java.CountDistinct1ReducerTask.CountDistinct minhpv64/count_distinct.csv minhpv64/output
+ Cách 2 : Em sử dụng 2 job mapreduce liên tiếp, output của job1 sẽ là input của job2, với job1 thì là một wordcount job bình thường với output là (key : distinct number,value : số lần xuất hiện của number ấy) => mapper job2 => (key : distinct number , value : 1) => reducer job2 => (key : "0", value : count) 
+ Cách 2 : (home/hadoop/minhpv64) yarn jar MapReduceTask-minhpv64.jar main.java.CountDistinct2Jobs.CountDistinct minhpv64/count_distinct.csv minhpv64/outTemp minhpv64/output
## Join Task :
+ Ở task này em xử lý header bằng việc thêm try-catch khi đọc dữ liệu trong Mapper Phase, ghi ra header cho các Reducer task trong function setup (ghi một lần duy nhất tại đầu mỗi task) 
+ Cách 1 : Join tại Mapper phase, em cache lại và đọc từng dòng từ file nhỏ hơn (salary.csv) => tạo được 1 HashMap (key : job , value : salary) => sau đó filter trực tiếp với dữ liệu people.csv được đưa vào ở Mapper phase => (key : id-people , value : record-people(Text)) => Reducer => (key : NullWritable , value : Text)
+ Cách 1 : (home/hadoop/minhpv64) yarn jar MapReduceTask-minhpv64.jar main.java.JoinMapPhaseText.JoinDriver hdfs://ha-cluster/user/hadoop/minhpv64/salary.csv minhpv64/people.csv minhpv64/output
+ Cách 2 : Join tại Reducer phase, Mapper phase => (key : Object Key (job,recordType(0-salary, 1-people), value : recordSalary hoặc recordPeople (ở dạng Text)) => Reducer => (NullWritable, Text)
+ Cách 2 : (home/hadoop/minhpv64) yarn jar MapReduceTask-minhpv64.jar main.java.JoinReducePhaseText.JoinDriver minhpv64/salary.csv minhpv64/people.csv minhpv64/output
+ Cách 3 : Tương tự với cách 2 nhưng sẽ tổng quát hơn, thay vì truyền Text qua value của Mapper, ta sẽ truyền Object - ở đây là GenericWritable, loại object cỏ thể đại diện cho cả People và Salary
+ Cách 3 : (home/hadoop/minhpv64) yarn jar MapReduceTask-minhpv64.jar main.java.JoinReducePhaseGenericWritable.JoinDriver minhpv64/salary.csv minhpv64/people.csv minhpv64/output
 
