import random
import pandas as pd
import csv

# Tạo list tên họ
with open("last_name.txt", "r") as f:
    lines = f.readlines()
last_name = [i.split("\n")[0] for i in lines]

# Tạo list tên đệm và tên của nữ (phục vụ để sinh giới tính theo tên cho bài SQL1)
with open("first_name_girl.txt", "r") as f:
    lines = f.readlines()
first_name_girl = [i.split("\n")[0] for i in lines]

# Tạo list tên đệm và tên của nam (phục vụ để sinh giới tính theo tên cho bài SQL1)
with open("first_name_boy.txt", "r") as f:
    lines = f.readlines()
first_name_boy = [i.split("\n")[0] for i in lines]

# Sinh dữ liệu cho table Student
# Tạo header cho file .csv student
header = ['idStudent', 'fullName', 'gender', 'email', 'phone']

records = []

with open("student.csv", "w") as csvf:
    csv_writer = csv.writer(csvf)
    csv_writer.writerow(header)
    i = 0
    # Tạo 30k records cho sinh viên
    while (i < 30000):
        if (i < 10000):
            id = 180000 + i
            if (random.random() < 0.5):
                gender = 0
                name = random.choice(last_name) + " " + random.choice(first_name_girl)
                email = str(id) + "@sis.hust.edu.vn"
                phone = 989310000 + i
            else:
                gender = 1
                name = random.choice(last_name) + " " + random.choice(first_name_boy)
                email = str(id) + "@sis.hust.edu.vn"
                phone = 989310000 + i
        elif (i < 20000):

            id = 180000 + i
            if (random.random() < 0.5):
                gender = 0
                name = random.choice(last_name) + " " + random.choice(first_name_girl)
                email = str(id) + "@sis.hust.edu.vn"
                phone = 989310000 + i
            else:
                gender = 1
                name = random.choice(last_name) + " " + random.choice(first_name_boy)
                email = str(id) + "@sis.hust.edu.vn"
                phone = 989310000 + i
        else:

            id = 180000 + i
            if (random.random() < 0.5):
                gender = 0
                name = random.choice(last_name) + " " + random.choice(first_name_girl)
                email = str(id) + "@sis.hust.edu.vn"
                phone = 989310000 + i
            else:
                gender = 1
                name = random.choice(last_name) + " " + random.choice(first_name_boy)
                email = str(id) + "@sis.hust.edu.vn"
                phone = 989310000 + i
      
        record = [id, name, gender, email, "0" + str(phone)]
        records.append(record)
        i += 1
    csv_writer.writerows(records)

# Sinh dữ liệu cho table teacher
# Tạo header cho file .csv teacher
header = ['idTeacher', 'fullName', 'gender', 'email', 'phone']
records = []
with open("teacher.csv", "w") as csvf:
    csv_writer = csv.writer(csvf)
    csv_writer.writerow(header)
    id = 5000
    # Tạo 1000 record cho table teacher
    while (id < 6000):
        if (random.random() < 0.5):
            gender = 0
            name = random.choice(last_name) + " " + random.choice(first_name_girl)
            email = "teacher" + str(id) + "@sis.hust.edu.vn"
            phone = 949787218 + i
        else:
            gender = 1
            name = random.choice(last_name) + " " + random.choice(first_name_boy)
            email = "teacher" + str(id) + "@sis.hust.edu.vn"
            phone = 973877286 + i
        id += 1
        record = [id, name, gender, email, "0" + str(phone)]
        records.append(record)
    csv_writer.writerows(records)

# Sinh dữ liệu cho table Subject
# Tạo list teacher và subject, location để tạo record cho table class
list_teacher = pd.read_csv("teacher.csv")['idTeacher'].tolist()
list_subject = pd.read_csv("subject.csv")['idSubject'].tolist() 
list_location = ['B1', 'B7', 'B9', 'C1', 'C2', 'D3', 'D4', 'D5', 'D6', 'D9', 'TC']
# Tạo header cho file .csv subject
header = ['idClass', 'idSubject', 'idTeacher', 'location']
id = 12345
records = []
with open("class.csv", "w") as csvf:
    csv_writer = csv.writer(csvf)
    csv_writer.writerow(header)
    for i in range(len(list_subject)):
        # với mỗi subject sẽ có 5 teacher giảng dạy
        for j in range(5*i , 5*i + 5):
            # với mỗi teacher sẽ giảng dạy tại 5 lớp
            num_class = 5
            for k in range(num_class):
                record = [id, list_subject[i], list_teacher[j], random.choice(list_location)]
                records.append(record)
                id += 1
    # tổng cộng với mỗi môn học sẽ có 25 lớp học 
    csv_writer.writerows(records)

# Sinh dữ liệu cho table Class
# Tạo header cho file score.csv
list_class =  pd.read_csv("class.csv")['idClass'].tolist()
list_student = pd.read_csv("student.csv")['idStudent'].tolist()
header = ['idStudent', 'idClass', 'score']
records = []

with open("score.csv", "w") as csvf:
    csv_writer = csv.writer(csvf)
    csv_writer.writerow(header)
    for i in range(len(list_class)):
        # Mỗi lớp sẽ có 30 sinh viên
        num_std = 30
        for j in range(num_std):
            record = [list_student[id%30000], list_class[i], round(random.uniform(0, 10), 2)]
            records.append(record)
    records.writerows(records)

# Sinh dữ liệu cho table Score
# Tạo header cho file score.csv
list_class =  pd.read_csv("class.csv")['idClass'].tolist()
list_student = pd.read_csv("student.csv")['idStudent'].tolist()
header = ['idStudent', 'idClass', 'score']
records = []

with open("score.csv", "w") as csvf:
    csv_writer = csv.writer(csvf)
    csv_writer.writerow(header)
    for i in range(len(list_class)):
        # Mỗi lớp sẽ có 30 sinh viên
        num_std = 30
        for j in range(num_std):
            record = [list_student[id%30000], list_class[i], round(random.uniform(0, 10), 2)]
            records.append(record)
    records.writerows(records)