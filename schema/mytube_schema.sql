
-- user Table Create SQL
CREATE TABLE user
(
    `id`           INT             NOT NULL    AUTO_INCREMENT,
    `user_id`      CHAR(20)        NOT NULL,
    `pw`           CHAR(128)       NOT NULL,
    `name`         CHAR(20)        NOT NULL,
    `email`        CHAR(45)        NOT NULL,
    `addr`         VARCHAR(100)    NOT NULL    COMMENT '주소',
    `created_at`   DATE        	   NOT NULL    COMMENT '가입 날짜',
    `phone`        VARCHAR(45)     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id)
);

ALTER TABLE user COMMENT '회원 정보';

-- user Table Create SQL
CREATE TABLE video
(
    `id`               INT             NOT NULL    AUTO_INCREMENT,
    `user_id`          CHAR(20)        NOT NULL, 
    `title`            VARCHAR(100)    NOT NULL, 
    `detail_contents`  VARCHAR(200)    NOT NULL    COMMENT '세부 내용', 
    `created_at`       DATE    		   NOT NULL	   COMMENT '등록 날짜',
    `updated_at`       DATE       	   NOT NULL    COMMENT '업데이트 날짜',
    `hits`             INT             NOT NULL    COMMENT '조회수', 
    `file_url`		   VARCHAR(45)	   NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE video COMMENT '동영상 게시글';

-- user Table Create SQL
CREATE TABLE user_video
(
    `user_id`                 CHAR(20)       NOT NULL,
    `like_list`               INT            NOT NULL    COMMENT '좋아요 목록',
    `dibs_list`               INT            NOT NULL    COMMENT '게시글 찜 목록',
    `post_count`              INT            NOT NULL    COMMENT '게시 개수',
    `subscrip_list`           CHAR(20)    	 NOT NULL    COMMENT '구독자 아이디 등록 목록',
    `subscrip_count`          INT            NOT NULL    COMMENT '구독 개수',
    `after_watch_video_list`  INT            NOT NULL    COMMENT '나중에 보다 동영상 목록',
    `new_play_list`           INT            NOT NULL    COMMENT '새 재생 목록',
    PRIMARY KEY (user_id)
);

ALTER TABLE user_video COMMENT '유저 동영상 게시글 관련 정보';

-- user Table Create SQL
CREATE TABLE video_file
(
    `id`         INT            NOT NULL	AUTO_INCREMENT, 
    `file_url`   VARCHAR(45)    NOT NULL, 
    `file_size`  INT            NOT NULL, 
    PRIMARY KEY (id)
);

ALTER TABLE video_file COMMENT '동영상 파일';

-- 좋아요 싫어요 삽입, 삭제로 취소 구현
-- 좋아요 테이블, 싫어요 테이블 생성
-- user Table Create SQL
CREATE TABLE user_like
(
    `video_id`  INT         NOT NULL, 
    `user_id`   CHAR(20)    NOT NULL, 
    `is_liked`	BOOLEAN 	NOT NULL COMMENT 'true -> like클릭, false -> dislike 클릭',
    PRIMARY KEY (video_id, user_id)
);

ALTER TABLE user_like COMMENT '유저 좋아요 정보';
        