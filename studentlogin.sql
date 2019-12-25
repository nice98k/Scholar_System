/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : studentlogin

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-12-25 16:36:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `final`
-- ----------------------------
DROP TABLE IF EXISTS `final`;
CREATE TABLE `final` (
`student_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`credit_sum`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`avg_score`  double(20,0) NULL DEFAULT NULL ,
`intelligent_add`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`moral_add`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`pe_add`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`final_score`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`avg_score_rank`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`final_rank`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`scholar`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsFail`  int(10) NULL DEFAULT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of final
-- ----------------------------
BEGIN;
INSERT INTO `final` (`student_number`, `student_name`, `credit_sum`, `avg_score`, `intelligent_add`, `moral_add`, `pe_add`, `final_score`, `avg_score_rank`, `final_rank`, `scholar`, `IsFail`) VALUES ('20175101018', 'Janice', '16.0', '88', '8.0', '0.0', '3.0', '58.1', '5', '6', 'null', null), ('20175101019', '毛俊哲', '16.0', '77', '22.0', '7.0', '3.0', '61.85', '6', '2', '挂科', '1'), ('20175101020', '佘贺威', '16.0', '95', '0.0', '3.0', '3.0', '58.45', '4', '5', '校三等奖学金', null), ('20175101064', '刘思维', '16.0', '100', '0.0', '3.0', '0.0', '60.900000000000006', '1', '3', '校一等奖学金', null), ('20175101085', '程思琪', '16.0', '100', '5.0', '0.0', '0.0', '62.75000000000001', '1', '1', '国家奖学金', null), ('20175120000', '孙冰花', '16.0', '100', '0.0', '3.0', '0.0', '60.900000000000006', '1', '3', '校二等奖学金', null);
COMMIT;

-- ----------------------------
-- Table structure for `student_admin`
-- ----------------------------
DROP TABLE IF EXISTS `student_admin`;
CREATE TABLE `student_admin` (
`admin_id`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`admin_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`admin_password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`admin_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci

;

-- ----------------------------
-- Records of student_admin
-- ----------------------------
BEGIN;
INSERT INTO `student_admin` (`admin_id`, `admin_name`, `admin_password`) VALUES ('213s', '123', '123');
COMMIT;

-- ----------------------------
-- Table structure for `student_class`
-- ----------------------------
DROP TABLE IF EXISTS `student_class`;
CREATE TABLE `student_class` (
`class_id`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`collage_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`major_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`class_name`  int(11) NOT NULL ,
PRIMARY KEY (`class_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci

;

-- ----------------------------
-- Records of student_class
-- ----------------------------
BEGIN;
INSERT INTO `student_class` (`class_id`, `collage_name`, `major_name`, `class_name`) VALUES ('01f54980-7417-4308-ac31-027b2a9ef9a3', '艺术设计学院', '装饰设计', '2'), ('063d0547-c265-4e3f-93a9-d3ea90deda74', '艺术设计学院', '服装设计', '2'), ('0d4bd1b1-96f0-4b0f-b804-40266a29c8ad', '艺术设计学院', '装饰设计', '3'), ('183d322e-f150-440b-8413-b5a02d3bf3be', '计算机学院', '软件工程', '1'), ('1977b66b-2b29-4830-ba3b-e60a0c1d97de', '政法学院', '法学', '2'), ('35f618fd-004b-4dce-8f26-3daa5bd427ff', '自动化学院', '物联网工程', '1'), ('39610dee-c306-4def-90c0-1533bdefdafc', '自动化学院', '机械手工程', '1'), ('3a8380d3-8ada-460e-921f-cfcd9d92456f', '政法学院', '法学', '1'), ('3cf05ad7-743b-4627-8921-50771a941160', '政法学院', '经济法', '1'), ('3e6132e7-d185-42d2-9605-dd9687b10e0b', '物理学院', '天文专业', '1'), ('44ee549d-2d28-4e15-8f49-9475989486ad', '自动化学院', '机械手工程', '2'), ('45fba4f5-056f-439b-a093-396a4176d412', '艺术设计学院', '服装设计', '1'), ('50b5eb3b-15ce-4fd2-a72e-d0071f6b2056', '物理学院', '天文专业', '2'), ('74dfd898-a746-4b52-b7a3-5b1a4c01599a', '计算机学院', '计算机科学与技术', '1'), ('893caf30-cabe-443c-8dd4-3e6bc45c2b54', '计算机学院', '网络工程', '2'), ('9b7a7b1d-16bc-4b07-a10e-2afb0a097c8a', '计算机学院', '网络工程', '1'), ('a28a0470-c82b-4319-a9b3-147bb21f7f09', '计算机学院', '软件工程', '2'), ('b14143db-6223-413e-b458-66562aea4171', '自动化学院', '物联网工程', '2'), ('ba008f72-901f-48e2-847a-b1c8d4731597', '政法学院', '经济法', '2'), ('caa1975b-f5a8-4606-b71b-af46e57b6856', '计算机学院', '计算机科学与技术', '2'), ('e05d55b1-0996-4c46-96aa-e2a4e4724332', '计算机学院', '计算机科学与技术', '3'), ('f4fff796-17db-46a1-9820-0d6cedd0a72d', '艺术设计学院', '装饰设计', '1');
COMMIT;

-- ----------------------------
-- Table structure for `student_collage`
-- ----------------------------
DROP TABLE IF EXISTS `student_collage`;
CREATE TABLE `student_collage` (
`collage_id`  int(11) NOT NULL AUTO_INCREMENT ,
`collage_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`collage_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of student_collage
-- ----------------------------
BEGIN;
INSERT INTO `student_collage` (`collage_id`, `collage_name`) VALUES ('1', '计算机学院'), ('2', '艺术设计学院'), ('3', '自动化学院'), ('4', '政法学院'), ('5', '物理学院');
COMMIT;

-- ----------------------------
-- Table structure for `student_course`
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
`course_id`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`major_name`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`course_name`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`course_score`  double(255,0) NULL DEFAULT NULL ,
PRIMARY KEY (`course_name`),
INDEX `course_score` (`course_score`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci

;

-- ----------------------------
-- Records of student_course
-- ----------------------------
BEGIN;
INSERT INTO `student_course` (`course_id`, `major_name`, `course_name`, `course_score`) VALUES ('d745731f-53c9-443a-bfe4-1fe87b44deae', '计算机科学与技术', 'C++程序语言设计', '4'), ('523f271f-c979-4b7f-b813-3a7f40e06281', '计算机科学与技术', 'C程序语言设计', '4'), ('3ccee050-5399-4cc8-b0ce-a6c79cb8af9b', '计算机科学与技术', 'Linux系统设计', '4'), ('5a7c730c-dccb-4fdf-a54e-dd7948738dec', '软件工程', 'python程序设计', '4'), ('abc57863-693a-4214-99bc-37ac7246b7ce', '计算机科学与技术', '信息安全', '4'), ('41d877cc-28a0-461c-a52f-b0b21c6c1489', '计算机科学与技术', '嵌入式系统设计', '4'), ('3b9edfb0-4074-4745-af10-84cb4c0529f5', '计算机科学与技术', '操作系统', '4'), ('b556e035-d4af-44a9-ad66-0c02dfe8179b', '计算机科学与技术', '数据结构', '4'), ('307a744e-670f-4a02-b6eb-12963a8afb2f', '计算机科学与技术', '编译原理', '4'), ('fe8e8108-6906-4ab0-a0f8-34d70a5068f1', '计算机科学与技术', '网页设计', '4'), ('47f49523-05ac-4361-aabf-96fde84fdda4', '服装设计', '艺术学', '4'), ('ec191453-9b54-47b6-ac35-066e376545e8', '计算机科学与技术', '计算机组成原理', '4'), ('372befeb-cb4e-49d4-8405-7f10762aef40', '计算机科学与技术', '计算机网络', '4');
COMMIT;

-- ----------------------------
-- Table structure for `student_intelligent`
-- ----------------------------
DROP TABLE IF EXISTS `student_intelligent`;
CREATE TABLE `student_intelligent` (
`student_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`intelligent_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`intelligent_score`  double(10,0) NULL DEFAULT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of student_intelligent
-- ----------------------------
BEGIN;
INSERT INTO `student_intelligent` (`student_number`, `student_name`, `intelligent_name`, `intelligent_score`) VALUES ('20175101019', '毛俊哲', '大学生英语竞赛', '7'), ('20175101019', '毛俊哲', '蓝桥杯', '7'), ('20175101019', '毛俊哲', '数学建模', '8'), ('20175101018', 'Janice', '大学生技能大赛', '8'), ('20175101085', '程思琪', '说课大赛', '5');
COMMIT;

-- ----------------------------
-- Table structure for `student_intelligent_score`
-- ----------------------------
DROP TABLE IF EXISTS `student_intelligent_score`;
CREATE TABLE `student_intelligent_score` (
`student_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`total_num`  int(10) NULL DEFAULT NULL ,
`sum`  double(10,0) NULL DEFAULT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of student_intelligent_score
-- ----------------------------
BEGIN;
INSERT INTO `student_intelligent_score` (`student_number`, `student_name`, `total_num`, `sum`) VALUES ('20175101019', '毛俊哲', '3', '22'), ('20175101018', 'Janice', '1', '8'), ('20175101085', '程思琪', '1', '5');
COMMIT;

-- ----------------------------
-- Table structure for `student_major`
-- ----------------------------
DROP TABLE IF EXISTS `student_major`;
CREATE TABLE `student_major` (
`major_id`  int(11) NOT NULL AUTO_INCREMENT ,
`collage_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`major_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`major_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci
AUTO_INCREMENT=11

;

-- ----------------------------
-- Records of student_major
-- ----------------------------
BEGIN;
INSERT INTO `student_major` (`major_id`, `collage_name`, `major_name`) VALUES ('1', '计算机学院', '计算机科学与技术'), ('2', '艺术设计学院', '服装设计'), ('3', '计算机学院', '软件工程'), ('4', '政法学院', '法学'), ('5', '自动化学院', '物联网工程'), ('6', '计算机学院', '网络工程'), ('7', '物理学院', '天文专业'), ('8', '艺术设计学院', '装饰设计'), ('9', '自动化学院', '机械手工程'), ('10', '政法学院', '经济法');
COMMIT;

-- ----------------------------
-- Table structure for `student_moral`
-- ----------------------------
DROP TABLE IF EXISTS `student_moral`;
CREATE TABLE `student_moral` (
`student_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`student_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`moral_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`moral_score`  double(10,0) NULL DEFAULT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of student_moral
-- ----------------------------
BEGIN;
INSERT INTO `student_moral` (`student_number`, `student_name`, `moral_name`, `moral_score`) VALUES ('20175101020', '佘贺威', '办公室副部', '3'), ('20175101064', '刘思维', '健美操', '3'), ('20175120000', '孙冰花', '健美操', '3'), ('20175101019', '毛俊哲', '省三好', '7');
COMMIT;

-- ----------------------------
-- Table structure for `student_moral_score`
-- ----------------------------
DROP TABLE IF EXISTS `student_moral_score`;
CREATE TABLE `student_moral_score` (
`student_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`total_num`  int(10) NULL DEFAULT NULL ,
`sum`  double(10,0) NULL DEFAULT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of student_moral_score
-- ----------------------------
BEGIN;
INSERT INTO `student_moral_score` (`student_number`, `student_name`, `total_num`, `sum`) VALUES ('20175101020', '佘贺威', '1', '3'), ('20175101064', '刘思维', '1', '3'), ('20175120000', '孙冰花', '1', '3'), ('20175101019', '毛俊哲', '1', '7');
COMMIT;

-- ----------------------------
-- Table structure for `student_pe`
-- ----------------------------
DROP TABLE IF EXISTS `student_pe`;
CREATE TABLE `student_pe` (
`student_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PE_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PE_score`  double(10,0) NULL DEFAULT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of student_pe
-- ----------------------------
BEGIN;
INSERT INTO `student_pe` (`student_number`, `student_name`, `PE_name`, `PE_score`) VALUES ('20175101019', '毛俊哲', '马拉松', '3'), ('20175101020', '佘贺威', '马拉松', '3'), ('20175101018', 'Janice', '马拉松', '3');
COMMIT;

-- ----------------------------
-- Table structure for `student_pe_score`
-- ----------------------------
DROP TABLE IF EXISTS `student_pe_score`;
CREATE TABLE `student_pe_score` (
`student_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`total_num`  int(10) NULL DEFAULT NULL ,
`sum`  double(10,0) NULL DEFAULT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of student_pe_score
-- ----------------------------
BEGIN;
INSERT INTO `student_pe_score` (`student_number`, `student_name`, `total_num`, `sum`) VALUES ('20175101019', '毛俊哲', '1', '3'), ('20175101020', '佘贺威', '1', '3'), ('20175101018', 'Janice', '1', '3');
COMMIT;

-- ----------------------------
-- Table structure for `student_score`
-- ----------------------------
DROP TABLE IF EXISTS `student_score`;
CREATE TABLE `student_score` (
`score_id`  int(11) NOT NULL AUTO_INCREMENT ,
`student_name`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`course_name`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`score`  double(10,0) NULL DEFAULT NULL ,
PRIMARY KEY (`score_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci
AUTO_INCREMENT=94

;

-- ----------------------------
-- Records of student_score
-- ----------------------------
BEGIN;
INSERT INTO `student_score` (`score_id`, `student_name`, `course_name`, `score`) VALUES ('66', 'Janice', 'C++程序语言设计', '88'), ('67', 'Janice', 'C程序语言设计', '88'), ('68', 'Janice', 'Linux系统设计', '88'), ('69', 'Janice', 'python程序设计', '88'), ('74', '佘贺威', 'C++程序语言设计', '95'), ('75', '佘贺威', 'C程序语言设计', '95'), ('76', '佘贺威', 'Linux系统设计', '95'), ('77', '佘贺威', 'python程序设计', '95'), ('78', '刘思维', 'python程序设计', '100'), ('79', '刘思维', 'C++程序语言设计', '100'), ('80', '刘思维', 'C程序语言设计', '100'), ('81', '刘思维', 'Linux系统设计', '100'), ('82', '程思琪', 'Linux系统设计', '100'), ('83', '程思琪', 'python程序设计', '100'), ('84', '程思琪', 'C程序语言设计', '100'), ('85', '程思琪', 'C++程序语言设计', '100'), ('86', '孙冰花', 'C++程序语言设计', '100'), ('87', '孙冰花', 'C程序语言设计', '100'), ('88', '孙冰花', 'Linux系统设计', '100'), ('89', '孙冰花', 'python程序设计', '100'), ('90', '毛俊哲', 'C++程序语言设计', '60'), ('91', '毛俊哲', 'C程序语言设计', '59'), ('92', '毛俊哲', 'Linux系统设计', '90'), ('93', '毛俊哲', 'python程序设计', '99');
COMMIT;

-- ----------------------------
-- Table structure for `student_stu`
-- ----------------------------
DROP TABLE IF EXISTS `student_stu`;
CREATE TABLE `student_stu` (
`student_id`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`student_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_email`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_collage`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_major`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_class`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`student_number`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci

;

-- ----------------------------
-- Records of student_stu
-- ----------------------------
BEGIN;
INSERT INTO `student_stu` (`student_id`, `student_number`, `student_name`, `student_email`, `student_collage`, `student_major`, `student_class`) VALUES ('94d13409-0cb2-4884-b31c-92f95e02436a', '20175101018', 'Janice', 'xxxxx@gmail.com', '计算机学院', '软件工程', '1'), ('866a47d9-5fb8-42f0-a711-564203ee01e9', '20175101019', '毛俊哲', '2532937079@qq.com', '计算机学院', '软件工程', '1'), ('8a0ba75b-3ee0-4d5d-8f94-f8603869f756', '20175101020', '佘贺威', 'sssss@dsds.com', '计算机学院', '软件工程', '1'), ('273cabb6-552b-445e-9878-a16682acbfaf', '20175101064', '刘思维', '11111@222.com', '计算机学院', '计算机科学与技术', '1'), ('d84194f6-6d39-4883-8cb6-bf45ac9eff07', '20175101085', '程思琪', '222222@121.com', '计算机学院', '计算机科学与技术', '1'), ('fba8c951-feb2-4cd2-b1a8-90069482a61c', '20175120000', '孙冰花', '22222@333.com', '计算机学院', '计算机科学与技术', '1');
COMMIT;

-- ----------------------------
-- Table structure for `student_study_score`
-- ----------------------------
DROP TABLE IF EXISTS `student_study_score`;
CREATE TABLE `student_study_score` (
`student_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`creditxscore`  double(10,0) NULL DEFAULT NULL ,
`credit`  int(20) NULL DEFAULT NULL ,
`course_sum`  int(20) NULL DEFAULT NULL ,
`avg_score`  double(10,0) NULL DEFAULT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of student_study_score
-- ----------------------------
BEGIN;
INSERT INTO `student_study_score` (`student_number`, `student_name`, `creditxscore`, `credit`, `course_sum`, `avg_score`) VALUES ('20175101018', 'Janice', '1408', '16', '4', null), ('20175101020', '佘贺威', '1520', '16', '4', null), ('20175101064', '刘思维', '1600', '16', '4', null), ('20175101085', '程思琪', '1600', '16', '4', null), ('20175120000', '孙冰花', '1600', '16', '4', null), ('20175101019', '毛俊哲', '1232', '16', '4', null);
COMMIT;

-- ----------------------------
-- Table structure for `student_user`
-- ----------------------------
DROP TABLE IF EXISTS `student_user`;
CREATE TABLE `student_user` (
`student_id`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`student_name`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`student_password`  varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`student_id`),
UNIQUE INDEX `student_name` (`student_name`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci

;

-- ----------------------------
-- Records of student_user
-- ----------------------------
BEGIN;
INSERT INTO `student_user` (`student_id`, `student_name`, `student_password`) VALUES ('1', '123', '123');
COMMIT;

-- ----------------------------
-- Auto increment value for `student_collage`
-- ----------------------------
ALTER TABLE `student_collage` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for `student_major`
-- ----------------------------
ALTER TABLE `student_major` AUTO_INCREMENT=11;

-- ----------------------------
-- Auto increment value for `student_score`
-- ----------------------------
ALTER TABLE `student_score` AUTO_INCREMENT=94;
