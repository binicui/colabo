DROP TABLE IF EXISTS BOARDS CASCADE;

CREATE TABLE IF NOT EXISTS BOARDS COMMENT '게시판' (
    ID                          INT                                 NOT NULL AUTO_INCREMENT COMMENT '게시판 ID',
    NAME                        VARCHAR(40)                         NOT NULL COMMENT '게시판 명',
    DESCRIPTION                 VARCHAR(55)                         NOT NULL COMMENT '게시판 설명',
    SORT                        INT                                 NOT NULL COMMENT '화면 정렬 순번',
    DELETED_YN                  CHAR(1)                             NOT NULL DEFAULT 'N' COMMENT '게시판 삭제 여부 (논리적 삭제)',
    CREATED_AT                  DATETIME                            NOT NULL DEFAULT NOW() COMMENT '게시판 등록 일시',
    UPDATED_AT                  DATETIME                            NOT NULL COMMENT '게시판 변경 일시',
    DELETED_AT                  DATETIME                            NULL COMMENT '게시판 삭제 일시',
    PRIMARY KEY (ID)
);



DROP TABLE IF EXISTS TASK_GROUPS CASCADE;

CREATE TABLE IF NOT EXISTS TASK_GROUPS COMMENT '업무 그룹' (
    ID                          INT                                 NOT NULL AUTO_INCREMENT COMMENT '업무 그룹 ID',
    NAME                        VARCHAR(40)                         NOT NULL COMMENT '업무 그룹 명',
    SORT                        INT                                 NOT NULL COMMENT '화면 정렬 순번',
    USED_YN                     CHAR(1)                             NOT NULL DEFAULT 'Y' COMMENT '업무 그룹의 사용 여부',
    CREATED_AT                  DATETIME                            NOT NULL DEFAULT NOW() COMMENT '업무 그룹 가입 일시',
    UPDATED_AT                  DATETIME                            NOT NULL COMMENT '업무 그룹 변경 일시',
    PRIMARY KEY (ID)
);



DROP TABLE IF EXISTS USERS CASCADE;

CREATE TABLE IF NOT EXISTS USERS COMMENT '회원' (
    ID                          INT                                 NOT NULL AUTO_INCREMENT COMMENT '회원 ID',
    EMAIL                       VARCHAR(45)                         UNIQUE NOT NULL COMMENT '가입 이메일',
    PASSWORD                    VARCHAR(255)                        NOT NULL COMMENT '비밀번호',
    NICKNAME                    VARCHAR(15)                         UNIQUE NOT NULL COMMENT '닉네임',
    NAME                        VARCHAR(15)                         NOT NULL COMMENT '이름',
    PHONE                       VARCHAR(11)                         NOT NULL COMMENT '휴대폰 번호',
    LOGIN_TYPE                  CHAR(5)                             NOT NULL COMMENT '로그인 유형',
    ENABLED                     TINYINT(1)                          NOT NULL COMMENT '계정 활성화 여부',
    DELETED_YN                  CHAR(1)                             NOT NULL DEFAULT 'N' COMMENT '계정 삭제 여부',
    CREATED_AT                  DATETIME                            NOT NULL DEFAULT NOW() COMMENT '회원 가입 일시',
    UPDATED_AT                  DATETIME                            NOT NULL COMMENT '회원 정보 변경 일시',
    DELETED_AT                  DATETIME                            NULL COMMENT '회원 계정 삭제 일시',
    PRIMARY KEY (ID)
);


DROP TABLE IF EXISTS USER_ROLES CASCADE;

CREATE TABLE IF NOT EXISTS USER_ROLES COMMENT '회원별 보유 권한 리스트' (
    USER_ID                     INT                                 NOT NULL COMMENT '회원 ID',
    ROLE_TYPE                   VARCHAR(20)                         NOT NULL COMMENT '권한 유형',
    CREATED_AT                  DATETIME                            NOT NULL DEFAULT NOW() COMMENT '권한 등록 일시',
    UPDATED_AT                  DATETIME                            NOT NULL COMMENT '권한 변경 일시',
    PRIMARY KEY (USER_ID, ROLE_TYPE),
    CONSTRAINT FK_USER_ROLES_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);


DROP TABLE IF EXISTS LOGIN_HISTORIES CASCADE;

CREATE TABLE IF NOT EXISTS LOGIN_HISTORIES COMMENT '로그인 이력' (
    ID                          INT                                 NOT NULL AUTO_INCREMENT COMMENT '이력 ID',
    USER_ID                     INT                                 NOT NULL COMMENT '회원 ID',
    IP_ADDRESS                  VARCHAR(15)                         NOT NULL COMMENT '아이피 주소 (IPv4)',
    DEVICE                      VARCHAR(10)                         NOT NULL COMMENT '기기 유형 (PC, MOBILE, TABLET)',
    OS                          VARCHAR(20)                         NOT NULL COMMENT '운영체제',
    BROWSER                     VARCHAR(15)                         NOT NULL COMMENT '브라우저',
    STATUS                      CHAR(1)                             NOT NULL COMMENT '로그인 상태 (I : 로그인, O : 로그아웃)',
    LOGIN_AT                    DATETIME                            NOT NULL COMMENT '로그인 일시',
    LOGOUT_AT                   DATETIME                            NULL COMMENT '로그아웃 일시',
    PRIMARY KEY (ID),
    CONSTRAINT FK_LOGIN_HISTORIES_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);


DROP TABLE IF EXISTS PROFILES CASCADE;

CREATE TABLE IF NOT EXISTS PROFILES COMMENT '프로필 정보' (
    ID                          INT                                 NOT NULL AUTO_INCREMENT COMMENT '프로필 ID',
    USER_ID                     INT                                 NOT NULL COMMENT '회원 ID',
    PROFILE_IMG_URL             VARCHAR(255)                        NULL COMMENT '프로필 이미지 저장 경로',
    BIO                         VARCHAR(255)                        NULL COMMENT '한 줄 소개',
    UNIV                        VARCHAR(50)                         NULL COMMENT '대학명',
    LOCATION                    VARCHAR(50)                         NULL COMMENT '활동 또는 거주지',
    URL                         VARCHAR(255)                        NULL COMMENT '개인 사이트 링크',
    CREATED_AT                  DATETIME                            NOT NULL DEFAULT NOW() COMMENT '프로필 등록 일시',
    UPDATED_AT                  DATETIME                            NOT NULL COMMENT '프로필 변경 일시',
    PRIMARY KEY (ID),
    CONSTRAINT FK_PROFILES_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);