/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : glmt

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2019-07-22 16:18:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `gl_attendance`
-- ----------------------------
DROP TABLE IF EXISTS `gl_attendance`;
CREATE TABLE `gl_attendance` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `store` tinyint(3) DEFAULT NULL,
  `jobid` int(11) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `attendanceTime` varchar(10) DEFAULT NULL,
  `attendanceDay` varchar(10) DEFAULT NULL,
  `operation` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_attendance
-- ----------------------------
INSERT INTO `gl_attendance` VALUES ('1', null, '谭可爱', '1', null, null, null, null, '10天', '0');
INSERT INTO `gl_attendance` VALUES ('2', null, '周胖妹', '1', null, null, null, null, '10天', '0');
INSERT INTO `gl_attendance` VALUES ('3', null, '陈波波', '1', null, null, null, null, '10天', '0');
INSERT INTO `gl_attendance` VALUES ('4', null, '梁妹头', '1', null, null, null, null, '10天', '0');
INSERT INTO `gl_attendance` VALUES ('5', null, '刘漂亮', '1', null, null, null, null, '10天', '0');
INSERT INTO `gl_attendance` VALUES ('6', null, '肖肖乐', '1', null, null, null, null, '10天', '0');

-- ----------------------------
-- Table structure for `gl_goods`
-- ----------------------------
DROP TABLE IF EXISTS `gl_goods`;
CREATE TABLE `gl_goods` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `classid` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_goods
-- ----------------------------
INSERT INTO `gl_goods` VALUES ('1', '狗粮奶茶', '/upload/20190722092931_8.png', '16', '0.1', '10', '很好喝的奶茶有珍珠咖啡和奶就是鸳鸯奶茶', '2019-07-20 11:19:50');
INSERT INTO `gl_goods` VALUES ('10', '狗粮意式', '/upload/20190722093154_7.png', '16', '28', '999', '就是意式咖啡啦，很好喝的咖啡咯还想怎么样', '2019-07-21 16:56:42');
INSERT INTO `gl_goods` VALUES ('11', '爱尔兰雪顶咖啡', '/upload/20190722092946_6.png', '16', '30', '999', '雪糕加咖啡的绝世搭配好喝到爆就是这样', '2019-07-21 16:57:44');
INSERT INTO `gl_goods` VALUES ('12', '奥利奥奶茶', '/upload/20190722093002_9.png', '15', '20', '999', '很好喝的奶茶加奥利奥哟哟哟切克闹', '2019-07-21 16:59:24');
INSERT INTO `gl_goods` VALUES ('13', '西柚杨枝甘露', '/upload/20190722093016_1.png', '17', '18', '999', '水果的茶，加了很多水果有西柚冰和水好好喝', '2019-07-21 17:00:48');
INSERT INTO `gl_goods` VALUES ('14', '吸个草莓', '/upload/20190722093029_11.png', '17', '26', '999', '就是很多很多很多草莓的果汁就是这样', '2019-07-21 17:01:38');
INSERT INTO `gl_goods` VALUES ('15', '绿茶婊的茶', '/upload/20190722093042_3.png', '15', '30', '999', '绿茶婊就是要多喝绿茶，点给你觉得最婊的人', '2019-07-21 17:02:45');
INSERT INTO `gl_goods` VALUES ('16', '田田田田', '/upload/20190722093054_10.png', '15', '25', '999', '就是很多很多阿华田就是热巧克力的感觉呗', '2019-07-21 17:03:37');
INSERT INTO `gl_goods` VALUES ('17', '芝士绿茶婊', '/upload/20190722093135_4.png', '15', '50', '999', '加了芝士的绿茶婊更厉害了，点给绿茶婊吧', '2019-07-21 17:04:34');

-- ----------------------------
-- Table structure for `gl_goods_cart`
-- ----------------------------
DROP TABLE IF EXISTS `gl_goods_cart`;
CREATE TABLE `gl_goods_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  `goodsname` varchar(255) DEFAULT NULL,
  `goodsprice` double DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `totalprice` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_goods_cart
-- ----------------------------
INSERT INTO `gl_goods_cart` VALUES ('78', '2', '1', '狗粮奶茶', '0.1', '3', '0.4', '2019-07-22 15:26:50');

-- ----------------------------
-- Table structure for `gl_goods_class`
-- ----------------------------
DROP TABLE IF EXISTS `gl_goods_class`;
CREATE TABLE `gl_goods_class` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_goods_class
-- ----------------------------
INSERT INTO `gl_goods_class` VALUES ('15', '奶茶', '0');
INSERT INTO `gl_goods_class` VALUES ('16', '咖啡', '0');
INSERT INTO `gl_goods_class` VALUES ('17', '果汁', '0');
INSERT INTO `gl_goods_class` VALUES ('18', '雪糕', '0');

-- ----------------------------
-- Table structure for `gl_jobs`
-- ----------------------------
DROP TABLE IF EXISTS `gl_jobs`;
CREATE TABLE `gl_jobs` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_jobs
-- ----------------------------
INSERT INTO `gl_jobs` VALUES ('1', '总经理');
INSERT INTO `gl_jobs` VALUES ('2', '副总经理');
INSERT INTO `gl_jobs` VALUES ('3', '销售总监');
INSERT INTO `gl_jobs` VALUES ('4', '试吃员');

-- ----------------------------
-- Table structure for `gl_notices`
-- ----------------------------
DROP TABLE IF EXISTS `gl_notices`;
CREATE TABLE `gl_notices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contacts` longtext,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_notices
-- ----------------------------
INSERT INTO `gl_notices` VALUES ('33', '♬  欢迎光临狗粮  ♬', '2019-07-22 14:44:03');
INSERT INTO `gl_notices` VALUES ('34', '♬ 欢迎光临DogEgg ♬', '2019-07-22 14:44:30');
INSERT INTO `gl_notices` VALUES ('36', '♬ 欢迎光临狗粮收银管理系统 ♬', '2019-07-22 14:45:21');
INSERT INTO `gl_notices` VALUES ('38', '♬ 如有任何疑问请与我们联系 ♬', '2019-07-22 14:46:27');

-- ----------------------------
-- Table structure for `gl_order`
-- ----------------------------
DROP TABLE IF EXISTS `gl_order`;
CREATE TABLE `gl_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `dingdanhao` varchar(255) DEFAULT NULL,
  `totalnumber` int(11) DEFAULT NULL,
  `totalprice` double DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_order
-- ----------------------------
INSERT INTO `gl_order` VALUES ('67', '支付宝', '20190722154451192', '1033', '103.30000000000001', '3', '805507465', '0', '2019-07-22 15:44:09');
INSERT INTO `gl_order` VALUES ('68', '支付宝', '20190722154408133', '1033', '103.30000000000001', '3', '805507465', '0', '2019-07-22 15:44:42');
INSERT INTO `gl_order` VALUES ('69', '支付宝', '20190722154536914', '1033', '103.30000000000001', '3', '805507465', '0', '2019-07-22 15:45:17');
INSERT INTO `gl_order` VALUES ('70', '支付宝', '20190722154524469', '1033', '103.30000000000001', '3', '805507465', '0', '2019-07-22 15:45:26');
INSERT INTO `gl_order` VALUES ('71', '支付宝', '20190722154734648', '1033', '103.30000000000001', '3', '805507465', '0', '2019-07-22 15:47:04');
INSERT INTO `gl_order` VALUES ('72', '支付宝', '20190722154742638', '1033', '103.30000000000001', '3', '805507465', '0', '2019-07-22 15:47:12');
INSERT INTO `gl_order` VALUES ('73', '支付宝', '20190722154727387', '1036', '179.3', '3', '805507465', '0', '2019-07-22 15:47:23');
INSERT INTO `gl_order` VALUES ('74', '支付宝', '20190722155402630', '1036', '179.3', '3', '805507465', '0', '2019-07-22 15:54:28');
INSERT INTO `gl_order` VALUES ('75', '支付宝', '20190722155469983', '1033', '103.30000000000001', '3', '805507465', '0', '2019-07-22 15:54:33');
INSERT INTO `gl_order` VALUES ('76', '支付宝', '20190722155479425', '3', '0.30000000000000004', '3', '805507465', '0', '2019-07-22 15:54:48');
INSERT INTO `gl_order` VALUES ('77', '员工', '20190722160942674', '11', '210.3', '3', '805507465', '1', '2019-07-22 16:09:32');

-- ----------------------------
-- Table structure for `gl_order_goods`
-- ----------------------------
DROP TABLE IF EXISTS `gl_order_goods`;
CREATE TABLE `gl_order_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dingdanhao` longtext,
  `goodsid` tinyint(4) DEFAULT NULL,
  `goodsname` varchar(255) DEFAULT NULL,
  `goodsprice` double DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `totalprice` double DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_order_goods
-- ----------------------------
INSERT INTO `gl_order_goods` VALUES ('119', '20190722154451192', '1', '狗粮奶茶', '0.1', '1033', '103.30000000000001', '3', '2019-07-22 15:44:09');
INSERT INTO `gl_order_goods` VALUES ('120', '20190722154408133', '1', '狗粮奶茶', '0.1', '1033', '103.30000000000001', '3', '2019-07-22 15:44:42');
INSERT INTO `gl_order_goods` VALUES ('121', '20190722154536914', '1', '狗粮奶茶', '0.1', '1033', '103.30000000000001', '3', '2019-07-22 15:45:17');
INSERT INTO `gl_order_goods` VALUES ('122', '20190722154524469', '1', '狗粮奶茶', '0.1', '1033', '103.30000000000001', '3', '2019-07-22 15:45:26');
INSERT INTO `gl_order_goods` VALUES ('123', '20190722154734648', '1', '狗粮奶茶', '0.1', '1033', '103.30000000000001', '3', '2019-07-22 15:47:04');
INSERT INTO `gl_order_goods` VALUES ('124', '20190722154742638', '1', '狗粮奶茶', '0.1', '1033', '103.30000000000001', '3', '2019-07-22 15:47:12');
INSERT INTO `gl_order_goods` VALUES ('125', '20190722154727387', '13', '西柚杨枝甘露', '18', '1', '18', '3', '2019-07-22 15:47:23');
INSERT INTO `gl_order_goods` VALUES ('126', '20190722154727387', '11', '爱尔兰雪顶咖啡', '30', '1', '30', '3', '2019-07-22 15:47:23');
INSERT INTO `gl_order_goods` VALUES ('127', '20190722154727387', '10', '狗粮意式', '28', '1', '28', '3', '2019-07-22 15:47:23');
INSERT INTO `gl_order_goods` VALUES ('128', '20190722154727387', '1', '狗粮奶茶', '0.1', '1033', '103.30000000000001', '3', '2019-07-22 15:47:23');
INSERT INTO `gl_order_goods` VALUES ('129', '20190722155402630', '13', '西柚杨枝甘露', '18', '1', '18', '3', '2019-07-22 15:54:28');
INSERT INTO `gl_order_goods` VALUES ('130', '20190722155402630', '11', '爱尔兰雪顶咖啡', '30', '1', '30', '3', '2019-07-22 15:54:28');
INSERT INTO `gl_order_goods` VALUES ('131', '20190722155402630', '10', '狗粮意式', '28', '1', '28', '3', '2019-07-22 15:54:28');
INSERT INTO `gl_order_goods` VALUES ('132', '20190722155402630', '1', '狗粮奶茶', '0.1', '1033', '103.30000000000001', '3', '2019-07-22 15:54:28');
INSERT INTO `gl_order_goods` VALUES ('133', '20190722155469983', '1', '狗粮奶茶', '0.1', '1033', '103.30000000000001', '3', '2019-07-22 15:54:33');
INSERT INTO `gl_order_goods` VALUES ('134', '20190722155479425', '1', '狗粮奶茶', '0.1', '3', '0.30000000000000004', '3', '2019-07-22 15:54:48');
INSERT INTO `gl_order_goods` VALUES ('135', '20190722160942674', '10', '狗粮意式', '28', '2', '56', '3', '2019-07-22 16:09:32');
INSERT INTO `gl_order_goods` VALUES ('136', '20190722160942674', '11', '爱尔兰雪顶咖啡', '30', '1', '30', '3', '2019-07-22 16:09:32');
INSERT INTO `gl_order_goods` VALUES ('137', '20190722160942674', '13', '西柚杨枝甘露', '18', '3', '54', '3', '2019-07-22 16:09:32');
INSERT INTO `gl_order_goods` VALUES ('138', '20190722160942674', '1', '狗粮奶茶', '0.1', '3', '0.30000000000000004', '3', '2019-07-22 16:09:32');
INSERT INTO `gl_order_goods` VALUES ('139', '20190722160942674', '17', '芝士绿茶婊', '50', '1', '50', '3', '2019-07-22 16:09:32');
INSERT INTO `gl_order_goods` VALUES ('140', '20190722160942674', '12', '奥利奥奶茶', '20', '1', '20', '3', '2019-07-22 16:09:32');

-- ----------------------------
-- Table structure for `gl_pay`
-- ----------------------------
DROP TABLE IF EXISTS `gl_pay`;
CREATE TABLE `gl_pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dingdanhao` varchar(255) DEFAULT NULL,
  `tradeno` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `paytime` datetime DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_pay
-- ----------------------------

-- ----------------------------
-- Table structure for `gl_store`
-- ----------------------------
DROP TABLE IF EXISTS `gl_store`;
CREATE TABLE `gl_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_store
-- ----------------------------
INSERT INTO `gl_store` VALUES ('1', '狗粮奶茶店', '广东省佛山市祖庙', '2019-07-22 14:21:31');

-- ----------------------------
-- Table structure for `gl_system`
-- ----------------------------
DROP TABLE IF EXISTS `gl_system`;
CREATE TABLE `gl_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sitename` varchar(255) DEFAULT NULL,
  `sitekey` varchar(255) DEFAULT NULL,
  `sitedesc` varchar(255) DEFAULT NULL,
  `sitenotice` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_system
-- ----------------------------
INSERT INTO `gl_system` VALUES ('1', null, null, null, null);

-- ----------------------------
-- Table structure for `gl_user`
-- ----------------------------
DROP TABLE IF EXISTS `gl_user`;
CREATE TABLE `gl_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `store` int(11) DEFAULT NULL,
  `jobid` int(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `operation` tinyint(4) DEFAULT NULL,
  `jurisdiction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_user
-- ----------------------------
INSERT INTO `gl_user` VALUES ('1', 'admin', '856ae2571dff545bd3514c31f698347d', '654802c9-669a-423b-bbd1-de30e4fc0ccb', '0', null, '1', 'admin@qq.com', '12345678911', '2019-07-01 00:00:00', '0', 'admin');
INSERT INTO `gl_user` VALUES ('2', '123456', '9c6be735756898ad6f239df5bcd04e38', 'd296c806-54da-4378-abf7-aca0c9be2509', '0', null, null, '123456@qq.com', '15555157997', '2019-07-21 20:27:09', '0', null);
INSERT INTO `gl_user` VALUES ('3', '805507465', '8c09a6db6f64f8961c1b592e5989d868', '5df7fb49-6ee7-473b-9979-30b2bf6bf65f', '0', null, null, '805507465@qq.com', '15555157997', '2019-07-22 12:35:20', '0', null);

-- ----------------------------
-- Table structure for `gl_userbak`
-- ----------------------------
DROP TABLE IF EXISTS `gl_userbak`;
CREATE TABLE `gl_userbak` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `store` tinyint(4) DEFAULT NULL,
  `jobid` int(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `operation` tinyint(4) DEFAULT NULL,
  `jurisdiction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gl_userbak
-- ----------------------------
INSERT INTO `gl_userbak` VALUES ('1', 'admin', '856ae2571dff545bd3514c31f698347d', '654802c9-669a-423b-bbd1-de30e4fc0ccb', '0', '1', null, '805507465@qq.com', '15555157997', '2019-07-15 18:49:51', '0', 'admin');
INSERT INTO `gl_userbak` VALUES ('2', '123456', '856ae2571dff545bd3514c31f698347d', '654802c9-669a-423b-bbd1-de30e4fc0ccb', '0', '1', null, '805507465@qq.com', '15555157997', '2019-07-15 18:49:51', '0', null);
INSERT INTO `gl_userbak` VALUES ('124', '805507465', 'f152b1cf7b322ef7934ad16193a812d4', '1430167e-9296-4921-a270-ac1204ad2365', '0', '1', null, '805507465@qq.com', '15555157997', '2019-07-20 11:44:20', '0', null);
INSERT INTO `gl_userbak` VALUES ('125', '88888888', '43b38736584f44b0443f5a1646dbe00f', '703692e7-6716-42b6-a93f-ee5b616a0e34', '0', '1', null, '88888888@qq.com', '15555157997', '2019-07-21 13:51:32', '0', null);
INSERT INTO `gl_userbak` VALUES ('126', '888888881', 'ba70896c60aeb8f6651df6d169a12145', 'c8786958-3155-4399-9e37-6f25e760880a', '0', null, null, '88888888@qq.com', '15555157997', '2019-07-21 13:53:53', '0', null);
INSERT INTO `gl_userbak` VALUES ('127', '124214', '31893b999494436947fdece518950dc1', '7559bd75-ada8-4a73-9bf4-a171e61eae9f', '0', '5', null, '88888888@qq.com', '15555157997', '2019-07-21 13:54:20', '0', null);

-- ----------------------------
-- Table structure for `punch_clock`
-- ----------------------------
DROP TABLE IF EXISTS `punch_clock`;
CREATE TABLE `punch_clock` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `userid` int(9) DEFAULT NULL,
  `punchinTime` datetime DEFAULT NULL,
  `punchoutTime` datetime DEFAULT NULL,
  `remark` char(255) DEFAULT NULL,
  `attendanceTime` date DEFAULT NULL,
  `userip` char(20) DEFAULT NULL,
  `loginaddress` char(50) DEFAULT NULL,
  `developername` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of punch_clock
-- ----------------------------
INSERT INTO `punch_clock` VALUES ('19', '1', '1970-01-01 20:18:34', '1970-01-01 21:21:11', null, '2019-07-21', '157.61.153.178', '\"广东省,佛山市\"', 'admin');
INSERT INTO `punch_clock` VALUES ('20', '2', '1970-01-01 20:27:56', null, null, '2019-07-21', '157.61.153.178', '\"广东省,佛山市\"', '123456');
INSERT INTO `punch_clock` VALUES ('21', '2', '1970-01-01 09:55:41', '1970-01-01 14:35:42', '安抚安抚asf', '2019-07-22', '121.9.252.242', '\"广东省,佛山市\"', '123456');
