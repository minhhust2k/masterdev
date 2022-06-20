-- Create users table
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL,
  `username` varchar(15) CHARACTER SET utf8mb4 NOT NULL,
  `fullname` varchar(30) CHARACTER SET utf8mb4 NOT NULL,
  `province` varchar(3) CHARACTER SET utf8mb4 NOT NULL,
  `age` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

-- Search user who has username is "ghtk" and compare speed searching btw INDEX and w/o INDEX 
-- Show all index
SHOW INDEX FROM users
-- Search user with INDEX
SELECT * FROM users u
WHERE u.username = "ghtk"
-- Drop index and search user
DROP INDEX pro_id ON users

SELECT * FROM users u
WHERE u.username = "ghtk"

--  Search top 10 users who are oldest in SG w/o INDEX

SELECT * FROM users u
WHERE u.province LIKE "H%"
ORDER BY u.age DESC
LIMIT 10

-- Search top 10 users who are oldest in SG with INDEX
CREATE INDEX pro_index ON users(province)
SELECT * FROM users u
WHERE u.province LIKE "H%"
ORDER BY u.age DESC
LIMIT 10

