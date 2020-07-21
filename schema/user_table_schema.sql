-- user Table Create SQL
CREATE TABLE user
(
    `id`           INT             NOT NULL    COMMENT '아이디', 
    `user_id`      CHAR(20)        NOT NULL    COMMENT '유저 아이디', 
    `pw`           CHAR(20)        NOT NULL    COMMENT '비밀번호', 
    `name`         CHAR(20)        NOT NULL    COMMENT '이름', 
    `email`        CHAR(45)        NOT NULL    COMMENT '이메일', 
    `addr`	       VARCHAR(100)    NOT NULL    COMMENT '도로 주소', 
    `signup_date`  date        	   NOT NULL    COMMENT '가입 날짜', 
    `tel_num`      CHAR(45)        NOT NULL    COMMENT '전화 번호', 
    PRIMARY KEY (id, user_id)
);