# TASK 1
- Tạo 5 table như hình dưới (mặc dù không chỉ định Foreign Key nhưng em vẫn vẽ thêm một số liên kết để thể hiện mối quan hệ giữa các table)
![image](https://user-images.githubusercontent.com/106506105/174639321-d870ebd1-af95-49c3-b29b-a2bf02dd3347.png)
- Với table Student em gen ra 30000 records, với thuộc tính tên được random từ nguồn tên nam/nữ người Việt Nam (source : https://github.com/duyet/vietnamese-namedb)
- Tương tự, table Teacher em cũng gen ra 1000 records
- Table Subject em lọc ra 200 môn và số tín chỉ tương ứng từ file excel đăng ký môn học của ĐHBK HN (source : https://ctt.hust.edu.vn/DisplayWeb/DisplayKeHoach?kehoach=25142)
- Table Class em gen dữ liệu theo ràng buộc mỗi môn sẽ có đúng 5 giảng viên giảng dạy, mỗi giảng viên sẽ dạy 5 lớp => mỗi môn học sẽ có 25 lớp => tổng cộng có 200x25 = 5000 records trong table Class
- Table Score em gen dư liệu theo ràng buộc, mỗi lớp sẽ có 30 sinh viên => Table Score sẽ có 30x5000 = 150000 records => mỗi sinh viên sẽ học 150000/30000 = 5 môn (lớp) học
- Run file GenSchoolData.py để tạo dữ liệu cho 5 table trên và run query trong file SQL_Task1.sql để hiển thị kết quả của bài tập

# Tas

