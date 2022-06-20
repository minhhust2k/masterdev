-- Create Subject Table
CREATE TABLE `Subject`
(
 `idSubject`   int unsigned NOT NULL ,
 `nameSubject` varchar(100) NOT NULL ,
 `numCredits`  tinyint unsigned NOT NULL ,

PRIMARY KEY (`idSubject`)
);

-- Create Teacher Table

CREATE TABLE `Teacher`
(
 `idTeacher` int unsigned NOT NULL ,
 `fullName`  varchar(50) NOT NULL ,
 `gender`    bit NOT NULL ,
 `email`     varchar(50) NOT NULL ,
 `phone`     varchar(12) NOT NULL ,

PRIMARY KEY (`idTeacher`)
);

-- Create Student Table

CREATE TABLE `Student`
(
 `idStudent` int unsigned NOT NULL ,
 `fullName`  varchar(50) NOT NULL ,
 `gender`    bit NOT NULL ,
 `email`     varchar(50) NOT NULL ,
 `phone`     varchar(12) NOT NULL ,

PRIMARY KEY (`idStudent`)
);

-- Create Class Table

CREATE TABLE `Class`
(
 `idClass`   int unsigned NOT NULL ,
 `idSubject` int unsigned NOT NULL ,
 `idTeacher` int unsigned NOT NULL ,
 `location`  varchar(2) NOT NULL ,

PRIMARY KEY (`idClass`)
);

-- Create Score Table

CREATE TABLE `Score`
(
 `idClass`   int unsigned NOT NULL ,
 `idStudent` int unsigned NOT NULL ,
 `score`     float NOT NULL ,

PRIMARY KEY (`idClass`, `idStudent`)
);

-- Q1 : Ði?m trung bình c?a toàn tru?ng?

SELECT AVG(sc.score) as score_avg FROM Score sc 

-- Q2 : T? l? tru?t, trung bình, khá, t?t và xu?t s?c

SELECT ((SELECT COUNT(*) FROM Score sc WHERE sc.score < 4) / (SELECT COUNT(*) FROM Score sc)) AS fail_ratio  
SELECT ((SELECT COUNT(*) FROM Score sc WHERE sc.score >= 4 AND sc.score < 6) / (SELECT COUNT(*) FROM Score sc)) AS medium_ratio
SELECT ((SELECT COUNT(*) FROM Score sc WHERE sc.score >= 6 AND sc.score < 8) / (SELECT COUNT(*) FROM Score sc)) AS fairly_ratio
SELECT ((SELECT COUNT(*) FROM Score sc WHERE sc.score >= 8 AND sc.score < 9) / (SELECT COUNT(*) FROM Score sc)) AS good_ratio
SELECT ((SELECT COUNT(*) FROM Score sc WHERE sc.score >= 9) / (SELECT COUNT(*) FROM Score sc)) AS excellent_ratio

-- Q3 : Môn nào có di?m trung bình cao nh?t?

SELECT sbj.nameSubject AS subject, AVG(sc.score) AS max_avg_score 
FROM Subject sbj, Score sc, Class class 
WHERE sbj.idSubject = class.idSubject 
AND class.idClass = sc.idClass 
GROUP BY sbj.idSubject 
ORDER BY AVG(sc.score) DESC
LIMIT 1

-- Q4 : L?p nào có di?m trung bình cao nh?t?

SELECT sc.idClass AS class_id, AVG(sc.score) AS max_avg_score
FROM Score sc
GROUP BY sc.idClass  
ORDER BY AVG(sc.score) DESC
LIMIT 1

-- Q5 : B?n nào có di?m trung bình cao nh?t?

SELECT st.fullName AS student_name, AVG(sc.score) AS max_avg_score
FROM Score sc, Student st
WHERE sc.idStudent = st.idStudent 
GROUP BY sc.idStudent
ORDER BY AVG(sc.score) DESC 
LIMIT 1

-- Q6 : Môn nào có t? l? tru?t cao nh?t?

SELECT tmp1.nameSubject AS subject_name, tmp2.cnt/tmp1.cnt AS fail_ration FROM (
(SELECT sbj.idSubject AS idSubject, sbj.nameSubject AS nameSubject, COUNT(sbj.idSubject) AS cnt  
FROM Score sc, Class class, Subject sbj
WHERE sc.idClass = class.idClass AND class.idSubject = sbj.idSubject
GROUP BY sbj.idSubject) tmp1 
INNER JOIN 
(SELECT sbj.idSubject AS idSubject, sbj.nameSubject AS nameSubject, COUNT(sbj.idSubject) AS cnt
FROM Score sc, Class class, Subject sbj
WHERE sc.idClass = class.idClass AND class.idSubject = sbj.idSubject AND sc.score < 4.0
GROUP BY sbj.idSubject) tmp2
ON tmp1.idSubject = tmp2.idSubject)
ORDER BY tmp2.cnt/tmp1.cnt DESC
LIMIT 1

-- Q7 : Danh sách các b?n không tru?t môn nào?
SELECT tmp1.fullName AS list_pass_all_student
FROM
(SELECT DISTINCT std.idStudent AS idStudent, std.fullName AS fullName 
FROM Student std, Score sc
WHERE std.idStudent = sc.idStudent) tmp1
LEFT JOIN
(SELECT DISTINCT std.idStudent AS idStudent, std.fullName AS fullName
FROM Student std, Score sc
WHERE std.idStudent = sc.idStudent AND sc.score < 4.0) tmp2
ON tmp1.idStudent = tmp2.idStudent
WHERE tmp2.idStudent IS NULL;

-- Q8 : Top 10 môn h?c khó nh?t

SELECT tmp1.nameSubject AS subject_name, tmp2.cnt/tmp1.cnt AS fail_ration FROM (
(SELECT sbj.idSubject AS idSubject, sbj.nameSubject AS nameSubject, COUNT(sbj.idSubject) AS cnt  
FROM Score sc, Class class, Subject sbj
WHERE sc.idClass = class.idClass AND class.idSubject = sbj.idSubject
GROUP BY sbj.idSubject) tmp1 
INNER JOIN 
(SELECT sbj.idSubject AS idSubject, sbj.nameSubject AS nameSubject, COUNT(sbj.idSubject) AS cnt
FROM Score sc, Class class, Subject sbj
WHERE sc.idClass = class.idClass AND class.idSubject = sbj.idSubject AND sc.score < 4.0
GROUP BY sbj.idSubject) tmp2
ON tmp1.idSubject = tmp2.idSubject)
ORDER BY tmp2.cnt/tmp1.cnt DESC
LIMIT 10

