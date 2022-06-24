# MongoDB Replica Set
+ Group 2 cài trên 3 node : 172.17.80.29:27018, 172.17.80.30:27018, 172.17.80.31:27018
+ Tên replicaset : repsetgr2
+ Database : group2, Collection : minhpv64
# CRUD Service
+ Insert 1 document : (POST) localhost:8080/api/insert-one
+ Insert nhiều document : (POST) localhost:8080/api/insert-many
+ Get all document theo phân trang và giảm dần theo thời gian xuất bản : (GET) localhost:8080/api/get-all-books/{page}/{size}
+ Tìm document bằng id : (GET) localhost:8080/api/find-by-id/{id}
+ Tìm document bằng tên sách : (GET) localhost:8080/api/find-by-name/{name}
+ Tìm document bằng tác giả : (GET) localhost:8080/api/find-by-author/{author}
+ Tìm document bằng mô tả sách : (GET) localhost:8080/api/find-by-desc/{desc}
+ Update document : (PUT) localhost:8080/api/update-book/{id}
+ Delete document : (DELETE) localhost:8080/api/delete-book/{id}
# Full Text Search By Book Name And Author
+ Trả về list document không sort : localhost:8080/api/full-text-search/{text}
+ Trả về list document sort theo score (set weight name = weight author = 1) : localhost:8080/api/full-text-search-sort-by-score/{text}
# Search By Date Service
+ Trả về list document có ngày xuất bản trong khoảng : localhost:8080/api/between-date/{date1}/{date2}
