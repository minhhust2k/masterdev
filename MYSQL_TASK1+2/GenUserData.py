import unidecode
import random
import csv

# Tạo list tên họ 
with open("last_name.txt", "r") as f:
    lines = f.readlines()
last_name = [i.split("\n")[0] for i in lines]

# Tạo list tên đệm và tên của nữ 
with open("first_name_girl.txt", "r") as f:
    lines = f.readlines()
first_name_girl = [i.split("\n")[0] for i in lines]

# Tạo list tên đệm và tên của nam 
with open("first_name_boy.txt", "r") as f:
    lines = f.readlines()
first_name_boy = [i.split("\n")[0] for i in lines]

# Tạo list tên đệm và tên từ list tên nam và nữ ở trên (phục vụ bài SQL2 vì yêu cầu của bài không cần giới tính)
first_name = first_name_boy + first_name_girl

# Tạo list tên viết tắt của tỉnh thành phố (phục vụ bài SQL 2)
with open("TP.txt", "r") as f:
    lines = f.readlines()
province = [i.split("\n")[0] for i in lines]

id = 1000000
# tạo dictionary chứa key là tên username, value là count số lượng username đó đã tồn tại để nối thêm số thứ tự đó vào username
# ví dụ Phùng Văn Minh => minhpv64
map_usr = {}
header = ['id', 'username', 'fullname', 'province', 'age']
records = []
with open("user.csv", "w") as csvf:
    csv_writer = csv.writer(csvf)
    csv_writer.writerow(header)
    # Tạo 1000000 record cho table users
    while (id != 2000000) :
        lname = random.choice(last_name)
        fname = random.choice(first_name)
        name = lname + " " + fname
        # Convert từ tên có dấu về không dấu + lowercase => mục đích tạo username theo kiểu minhpv64,..
        lname = unidecode.unidecode(lname).lower()
        fname = unidecode.unidecode(fname).lower()
        user = fname.split()[-1] + lname[0]
        for i in list(reversed(fname.split()[:-1])):
            user += i[0]
        
        if (user in map_usr):
            # nếu username đã tồn tại trong dictionary thì tăng thêm 1 đơn vị cho value của nó
            map_usr[user] += 1
            user += str(map_usr[user])
        else:
            # nếu user chưa tồn tại trong dict thì tạo value cho nó là 0
            map_usr[user] = 0
        
        pro = random.choice(province)
        age = random.randint(0,102)
        record = [id, user, name, pro, age]
        records.append(record)
        id += 1
    csv_writer.writerows(records)