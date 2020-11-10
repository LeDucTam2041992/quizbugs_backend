create database if not exists quiz_bugs;
use quiz_bugs;


create table  users
(
    id       bigint auto_increment
        primary key,
    password varchar(255) not null,
    username varchar(20)  not null,
    constraint UK_r43af9ap4edm43mmtq01oddj6
        unique (username)
)
    engine = MyISAM;

INSERT INTO quiz_bugs.users (id, password, username) VALUES (1, '123123', 'huy123');
INSERT INTO quiz_bugs.users (id, password, username) VALUES (2, '123123', 'admin');


create table roles
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
)
    engine = MyISAM;

INSERT INTO quiz_bugs.roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO quiz_bugs.roles (id, name) VALUES (2, 'ROLE_USER');


create table users_roles
(
    app_user_id bigint not null,
    roles_id    bigint not null,
    primary key (app_user_id, roles_id)
)
    engine = MyISAM;

create index FKa62j07k5mhgifpp955h37ponj
    on users_roles (roles_id);

INSERT INTO quiz_bugs.users_roles (app_user_id, roles_id) VALUES (1, 2);
INSERT INTO quiz_bugs.users_roles (app_user_id, roles_id) VALUES (2, 2);

create table categories
(
    id       bigint auto_increment
        primary key,
    category varchar(50) not null
)
    engine = MyISAM;

INSERT INTO quiz_bugs.categories (id, category) VALUES (1, 'PHP');
INSERT INTO quiz_bugs.categories (id, category) VALUES (2, 'JAVA');
INSERT INTO quiz_bugs.categories (id, category) VALUES (3, 'SQL');



create table questions
(
    id       bigint auto_increment
        primary key,
    enabled  tinyint(1) default 1 null,
    question text                 not null,
    type     int                  null
)
    engine = MyISAM;

INSERT INTO quiz_bugs.questions (id, enabled, question, type) VALUES (1, 1, 'Java là ngôn ngữ lập trình?', 0);
INSERT INTO quiz_bugs.questions (id, enabled, question, type) VALUES (2, 1, 'Java do Sun Microsystems sở hữu?', 2);
INSERT INTO quiz_bugs.questions (id, enabled, question, type) VALUES (3, 1, 'Đâu là phát biểu đúng về JVM', 0);
INSERT INTO quiz_bugs.questions (id, enabled, question, type) VALUES (4, 1, 'Các nền tảng Java', 1);
INSERT INTO quiz_bugs.questions (id, enabled, question, type) VALUES (5, 1, 'Java SE cung cấp các tính năng mở rộng của ngôn ngữ lập trình Java.', 2);
INSERT INTO quiz_bugs.questions (id, enabled, question, type) VALUES (6, 1, 'Các bước để xây dựng và thực thi một chương trình java', 0);
INSERT INTO quiz_bugs.questions (id, enabled, question, type) VALUES (7, 1, 'Các file java sau khi được biên dịch sẽ có kết quả là các file có phần mở rộng là?', 0);
INSERT INTO quiz_bugs.questions (id, enabled, question, type) VALUES (8, 1, 'Cho biết kết quả xuất ra màn hình sau khi thực hiện đoạn mã lệnh sau:

int a = 2 < 3 ? 2 : 3;
switch(a) {
    case 1:
       System.out.print("A");
       break;
    case 2:
       System.out.print("B");
    default:
       System.out.print("C");
}', 0);
INSERT INTO quiz_bugs.questions (id, enabled, question, type) VALUES (9, 1, 'Cho biết kết quả xuất ra màn hình sau khi thực hiện đoạn mã lệnh sau:

int a = 5 > 7 ? 2 : 1;
switch(a){
  case 1:
    System.out.print("A");
  case 2:
    System.out.print("B");
    break;
  default:
    System.out.print("C");
    break;
} ', 0);
INSERT INTO quiz_bugs.questions (id, enabled, question, type) VALUES (10, 1, 'Cho biết kết quả xuất ra màn hình sau khi thực hiện đoạn mã sau đây:

double x = 16 – 2;
if(x < 16) {
  System.out.println("A");
} else if(x < 15) {
  System.out.println("B");
} else {
  System.out.println("C");
}', 0);

create table answers
(
    id          bigint auto_increment
        primary key,
    answer      text   not null,
    status      bit    not null,
    question_id bigint null
)
    engine = MyISAM;

create index FK3erw1a3t0r78st8ty27x6v3g1
    on answers (question_id);

INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (1, 'Hướng đối tượng', true, 1);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (2, 'Hướng cấu trúc', false, 1);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (11, 'Dựa trên đối tượng', false, 1);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (12, 'Hướng hành vi', false, 1);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (13, 'True', false, 2);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (14, 'False', true, 2);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (15, 'Không câu nào đúng', false, 3);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (16, 'Viết tắt của từ Java Vituals Machine', false, 3);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (17, 'JVM chính là tên gọi dành cho trình thông dịch bytecode thực hiện nhiệm vụ giả lập', true, 3);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (18, 'Máy tính cần nhiều máy ảo Java để chạy các chương trình Java', false, 3);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (19, 'Java ME', true, 4);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (20, 'Java SE', true, 4);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (21, 'Java MOBILE', false, 4);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (22, ' Java EE', true, 4);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (23, 'True', false, 5);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (24, 'False', true, 5);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (25, 'Nạp, soạn thảo, biên dịch, chạy', false, 6);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (26, 'Soạn thảo, nạp và chạy', false, 6);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (27, 'Soạn thảo, gỡ lỗi, biên dịch, nạp và chạy', false, 6);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (28, 'Soạn thảo, biên dịch, nạp và chạy', true, 6);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (29, 'exe', false, 7);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (30, 'class', false, 7);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (31, 'dll', false, 7);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (32, 'java', false, 7);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (33, ' BC', true, 8);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (34, ' A', false, 8);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (35, 'AB', true, 8);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (36, 'C', false, 8);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (37, 'BC', false, 9);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (38, 'AB', true, 9);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (39, 'A', false, 9);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (40, 'C', false, 9);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (41, 'A', true, 10);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (42, 'AC', false, 10);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (43, 'BC', false, 10);
INSERT INTO quiz_bugs.answers (id, answer, status, question_id) VALUES (44, 'B', false, 10);



create table questions_categories
(
    question_id   bigint not null,
    categories_id bigint not null,
    primary key (question_id, categories_id)
)
    engine = MyISAM;

create index FKnc15vp2xx6jvpytgiyp2l7j44
    on questions_categories (categories_id);

INSERT INTO quiz_bugs.questions_categories (question_id, categories_id) VALUES (1, 2);
INSERT INTO quiz_bugs.questions_categories (question_id, categories_id) VALUES (2, 2);
INSERT INTO quiz_bugs.questions_categories (question_id, categories_id) VALUES (3, 2);
INSERT INTO quiz_bugs.questions_categories (question_id, categories_id) VALUES (4, 2);
INSERT INTO quiz_bugs.questions_categories (question_id, categories_id) VALUES (5, 2);
INSERT INTO quiz_bugs.questions_categories (question_id, categories_id) VALUES (6, 2);
INSERT INTO quiz_bugs.questions_categories (question_id, categories_id) VALUES (7, 2);
INSERT INTO quiz_bugs.questions_categories (question_id, categories_id) VALUES (8, 2);
INSERT INTO quiz_bugs.questions_categories (question_id, categories_id) VALUES (9, 2);
INSERT INTO quiz_bugs.questions_categories (question_id, categories_id) VALUES (10, 2);



create table if not exists exam
(
    id      bigint auto_increment
        primary key,
    enabled tinyint(1) default 1 null,
    name    text                 not null
)
    engine = MyISAM;

INSERT INTO quiz_bugs.exam (id, enabled, name) VALUES (1, 1, 'Java_Core_1');
INSERT INTO quiz_bugs.exam (id, enabled, name) VALUES (2, 1, 'SQL_Data');



create table if not exists exam_question_set
(
    exam_id         bigint not null,
    question_set_id bigint not null,
    primary key (exam_id, question_set_id)
)
    engine = MyISAM;

create index FKrfns00ngbxn1qkh9vkr7pe8ly
    on exam_question_set (question_set_id);

INSERT INTO quiz_bugs.exam_question_set (exam_id, question_set_id) VALUES (1, 1);
INSERT INTO quiz_bugs.exam_question_set (exam_id, question_set_id) VALUES (1, 2);
INSERT INTO quiz_bugs.exam_question_set (exam_id, question_set_id) VALUES (1, 3);
INSERT INTO quiz_bugs.exam_question_set (exam_id, question_set_id) VALUES (1, 4);
INSERT INTO quiz_bugs.exam_question_set (exam_id, question_set_id) VALUES (1, 5);
INSERT INTO quiz_bugs.exam_question_set (exam_id, question_set_id) VALUES (1, 6);
INSERT INTO quiz_bugs.exam_question_set (exam_id, question_set_id) VALUES (1, 7);
INSERT INTO quiz_bugs.exam_question_set (exam_id, question_set_id) VALUES (1, 8);
INSERT INTO quiz_bugs.exam_question_set (exam_id, question_set_id) VALUES (1, 9);
INSERT INTO quiz_bugs.exam_question_set (exam_id, question_set_id) VALUES (1, 10);