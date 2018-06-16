/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50559
 Source Host           : localhost:3306
 Source Schema         : exam_system

 Target Server Type    : MySQL
 Target Server Version : 50559
 File Encoding         : 65001

 Date: 08/06/2018 16:26:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_access_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_access_log`;
CREATE TABLE `sys_access_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `os` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `assess_time` timestamp NULL DEFAULT NULL,
  `url` varchar(2500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 251 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_access_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_paper
-- ----------------------------
DROP TABLE IF EXISTS `sys_paper`;
CREATE TABLE `sys_paper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `role_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ks_time` timestamp NULL DEFAULT NULL,
  `js_time` timestamp NULL DEFAULT NULL,
  `dxt_count` int(20) NULL DEFAULT NULL,
  `dxt_score` decimal(18, 2) NULL DEFAULT NULL,
  `duoxt_count` int(20) NULL DEFAULT NULL,
  `duoxt_score` decimal(18, 2) NULL DEFAULT NULL,
  `pdt_count` int(20) NULL DEFAULT NULL,
  `pdt_score` decimal(15, 2) NULL DEFAULT NULL,
  `total_score` decimal(18, 3) NULL DEFAULT NULL,
  `create_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_names` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_paper
-- ----------------------------
INSERT INTO `sys_paper` VALUES (3, '数据结构', '上线', '2018-06-06 19:48:21', '2018-06-06 20:59:12', NULL, '16;22', '2018-06-06 00:00:00', '2018-06-20 00:00:00', 30, 2.00, 8, 5.00, NULL, NULL, 100.000, '王消失', '王消失;真的');
INSERT INTO `sys_paper` VALUES (4, '测试单选题', '下线', '2018-06-06 21:24:57', '2018-06-08 15:57:58', NULL, '16', '2018-06-06 18:04:05', '2018-06-27 20:04:04', 10, 2.00, 0, 0.00, 0, 0.00, 20.000, '王消失', '王消失');
INSERT INTO `sys_paper` VALUES (5, '幸福很幸福', '下线', '2018-06-08 15:14:04', '2018-06-08 15:14:18', NULL, '18;16;22', '2018-06-19 03:04:04', '2018-06-18 00:00:00', 10, 2.00, 10, 2.00, NULL, NULL, 40.000, '王消失', '长得;王消失;真的');

-- ----------------------------
-- Table structure for sys_paper_answer
-- ----------------------------
DROP TABLE IF EXISTS `sys_paper_answer`;
CREATE TABLE `sys_paper_answer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `is_true` tinyint(4) NULL DEFAULT NULL,
  `st_id` int(11) NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_paper_question
-- ----------------------------
DROP TABLE IF EXISTS `sys_paper_question`;
CREATE TABLE `sys_paper_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `key_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option1` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option2` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option3` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option4` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option5` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '',
  `option6` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `paper_id` int(255) NOT NULL,
  `score` double(10, 0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_question
-- ----------------------------
DROP TABLE IF EXISTS `sys_question`;
CREATE TABLE `sys_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `key_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option1` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option2` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option3` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option4` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option5` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `option6` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_question
-- ----------------------------
INSERT INTO `sys_question` VALUES (2, NULL, '2018-06-05 18:48:03', '2018-06-05 18:48:03', '单选题', '梵蒂冈地方官豆腐干', 'A,B', 'SDGDS ', 'SDGSDG ', '是的广告都是', '广泛大锅饭', '地方更好地发挥', '地方好地方', NULL, NULL);
INSERT INTO `sys_question` VALUES (3, NULL, '2018-06-05 18:55:41', '2018-06-05 18:55:41', '多选题', '法国梵蒂冈反对发的广泛大锅饭', 'A,B,C', 'FGFD ', 'SGSDSD', '电风扇广泛', '豆腐干广泛的', '发单号发给', '豆腐干大概地方', NULL, NULL);
INSERT INTO `sys_question` VALUES (4, NULL, '2018-06-05 18:58:32', '2018-06-05 19:01:12', '判断题', '电饭锅电饭锅', '对', '东方红东方红东方红东方红都很大方', '东方红东方红东方红东方红都很大方', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_question` VALUES (5, '王消失', '2018-06-06 20:09:49', '2018-06-06 20:09:49', '多选题', '收到公司的十多个是的', 'A,B,D', 'HDF ', 'fghfg很反感', '收到公司的', '十多个十多个', '烦得很发货', '东方红更多发挥', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '老师', '启用', '2018-06-05 22:41:48', '2018-06-05 23:22:41');
INSERT INTO `sys_role` VALUES (2, '学生', '启用', '2018-06-05 22:41:56', '2018-06-05 23:22:47');
INSERT INTO `sys_role` VALUES (3, '管理员', '启用', '2018-06-05 22:42:02', '2018-06-05 22:42:02');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_role_user_userid_fk`(`user_id`) USING BTREE,
  INDEX `sys_role_user_roleid_fk`(`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for sys_score
-- ----------------------------
DROP TABLE IF EXISTS `sys_score`;
CREATE TABLE `sys_score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `score` decimal(18, 2) NULL DEFAULT NULL,
  `cnname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `paper_id` int(11) NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `ks_time` timestamp NULL DEFAULT NULL,
  `_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `student_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_score
-- ----------------------------
INSERT INTO `sys_score` VALUES (1, '数据结构', 80.00, '张珊', NULL, NULL, '2018-06-06 19:38:24', '2018-06-06 19:45:28', '2018-08-08 02:03:02', '计应一班', '计信系', '1301212');
INSERT INTO `sys_score` VALUES (2, '网络技术', 66.00, '王灿', NULL, NULL, '2018-06-06 19:47:12', '2018-06-06 19:47:12', '2018-06-20 14:40:07', '计息', '计应', '201875');
INSERT INTO `sys_score` VALUES (3, '操作系统', 80.00, '似懂非懂是', NULL, NULL, '2018-06-08 14:47:06', '2018-06-08 14:47:11', '2018-06-19 00:00:00', '方法', '地方过分', '12323');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (16, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'ff@gmail.com', '13429989123', '2018-06-04 19:13:22', '2018-06-05 08:20:50', '启用', 3);
INSERT INTO `sys_user` VALUES (18, 'adminer', 'e10adc3949ba59abbe56e057f20f883e', 'dfdf@mail.com', '13424545435', '2018-06-04 19:54:13', '2018-06-05 05:46:45', '启用', NULL);
INSERT INTO `sys_user` VALUES (19, 'zhangshan', 'e10adc3949ba59abbe56e057f20f883e', 'ddfs@gmail.com', '13429989144', '2018-06-04 20:17:30', '2018-06-05 05:46:42', '启用', NULL);
INSERT INTO `sys_user` VALUES (20, 'lisi', 'e10adc3949ba59abbe56e057f20f883e', 'dsf@qq.com', '13223422343', '2018-06-04 20:21:30', '2018-06-05 05:46:40', '启用', NULL);
INSERT INTO `sys_user` VALUES (21, 'adminer1', 'e10adc3949ba59abbe56e057f20f883e', '123gh@fdg.com', '13212312342', '2018-06-05 20:46:31', '2018-06-05 23:08:48', '启用', 2);
INSERT INTO `sys_user` VALUES (22, 'zhangli', 'e10adc3949ba59abbe56e057f20f883e', 'sdf@gmial.com', '13223456456', '2018-06-05 21:30:28', '2018-06-05 23:08:43', '启用', 2);
INSERT INTO `sys_user` VALUES (23, 'zhsngd', 'e10adc3949ba59abbe56e057f20f883e', 'sdf@gmial.chg', '13429989234', '2018-06-07 09:07:40', '2018-06-07 09:07:40', '启用', NULL);

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info`  (
  `cnname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院系',
  `sys_user_id` int(11) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `student_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_user_id_fk`(`sys_user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES ('真的', 1, '男', 1, '发过的', '收到公司的', 22, '2018-06-05 22:27:33', '2018-06-05 22:40:16', '1232213', '2018-06-11 00:00:00');
INSERT INTO `sys_user_info` VALUES ('王消失', 2, '男', 23, '计应', '计信系', 16, '2018-06-05 22:38:18', '2018-06-05 22:38:18', '1234567', '2018-06-04 00:00:00');
INSERT INTO `sys_user_info` VALUES ('长得', 3, '男', 23, '第三方', '收到公司的', 18, '2018-06-05 22:39:26', '2018-06-05 22:39:26', '1234568', '2018-06-11 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
