
-- user Table Create SQL
CREATE TABLE user
(
    `id`           INT             NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `user_id`      CHAR(20)        NOT NULL    COMMENT '유저 아이디',
    `pw`           CHAR(128)        NOT NULL    COMMENT '비밀번호',
    `name`         CHAR(20)        NOT NULL    COMMENT '이름',
    `email`        CHAR(45)        NOT NULL    COMMENT '이메일',
    `addr`         VARCHAR(100)    NOT NULL    COMMENT '주소',
    `created_at`   DATE        NOT NULL    COMMENT '가입 날짜',
    `phone`        VARCHAR(45)     NOT NULL    COMMENT '핸드폰',
    PRIMARY KEY (id),
    UNIQUE (user_id)
);

ALTER TABLE user COMMENT '회원 정보';

-- user Table Create SQL
CREATE TABLE video
(
    `id`               INT             NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `user_id`          CHAR(20)        NOT NULL    COMMENT '유저 아이디', 
    `title`            VARCHAR(100)    NOT NULL    COMMENT '제목', 
    `detail_contents`  VARCHAR(200)    NOT NULL    COMMENT '세부 내용', 
    `created_at`       DATE    		   NOT NULL    COMMENT '등록 날짜',
    `updated_at`       DATE       	   NOT NULL    COMMENT '업데이트 날짜',
    `like_count`       INT             NOT NULL    COMMENT '좋아요 개수', 
    `bad_count`        INT             NOT NULL    COMMENT '싫어요 개수', 
    `hits`             INT             NOT NULL    COMMENT '조회수', 
    `file_url`		   VARCHAR(45)	   NOT NULL	   COMMENT '파일 url',
    PRIMARY KEY (id)
);

ALTER TABLE video COMMENT '게시글';

-- user Table Create SQL
CREATE TABLE user_video
(
    `user_id`                 CHAR(20)       NOT NULL    COMMENT '유저 아이디',
    `like_list`               INT            NOT NULL    COMMENT '좋아요 목록을 위해서',
    `dibs_list`               INT            NOT NULL    COMMENT '게시글 찜 목록을 위해서',
    `post_count`              INT            NOT NULL    AUTO_INCREMENT COMMENT '게시 개수',
    `subscrip_list`           CHAR(20)    	 NOT NULL    COMMENT '구독자 아이디 등록',
    `subscrip_count`          INT            NOT NULL    COMMENT '구독 개수',
    `after_watch_video_list`  INT            NOT NULL    COMMENT '나중에 보다 동영상 목록',
    `new_play_list`           INT            NOT NULL    COMMENT '새 재생 목록',
    PRIMARY KEY (post_count)
);

ALTER TABLE user_video COMMENT '유저 게시글 관련 정보';

-- user Table Create SQL
CREATE TABLE video_file
(
    `id`         INT            NOT NULL    COMMENT '아이디', 
    `file_url`   VARCHAR(45)    NOT NULL    COMMENT '파일 url', 
    `file_size`  INT            NOT NULL    COMMENT '파일 크기', 
    PRIMARY KEY (id)
);

ALTER TABLE video_file COMMENT '동영상 파일';

        