-- 用于初始化数据库

CREATE TABLE IF NOT EXISTS items(
  id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  pid int,
  quantity int
);
