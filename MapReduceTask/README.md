# Setup Hadoop Cluster
-- Server được sử dụng : 172.17.80.27/28/29/30/31
-- NameNode (Cài với HA) : Activate NameNode (27), Stanby NameNode (31), 5 JournalNode(27-31)
++ 5 DataNode (27-31)
- yarn.resourcemanager.hostname (27)
# MapReduce Task
-
## Word Count Task :
+ Em sử dụng regex để replace tất cả kí tự đặc biệt ở trong phase Mapper
+ 
 
