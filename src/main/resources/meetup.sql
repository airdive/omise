/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : meetup

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-02-02 15:37:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `edcon_charges`
-- ----------------------------
DROP TABLE IF EXISTS `edcon_charges`;
CREATE TABLE `edcon_charges` (
  `charges_id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(10) NOT NULL,
  `charges_rental` int(5) NOT NULL,
  `charges_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `charges_state` int(1) DEFAULT '0',
  `isSendmail` int(1) DEFAULT '0',
  `charges_number_Omise` varchar(100) DEFAULT NULL,
  `zxingCodePath` varchar(100) DEFAULT NULL,
  `sign` varchar(250) DEFAULT NULL,
  `isUsed` int(1) DEFAULT NULL,
  `cardNumber` varchar(30) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL,
  `isdelete` int(1) DEFAULT '0',
  `lang` varchar(5) DEFAULT NULL,
  `signTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isPayCoin` varchar(2) DEFAULT '0',
  `txhash` varchar(200) DEFAULT '',
  PRIMARY KEY (`charges_id`),
  KEY `fk_tickets_id` (`ticket_id`),
  KEY `fi_all_index` (`name`,`email`,`country`,`company`,`position`,`telephone`),
  FULLTEXT KEY `fi_all` (`name`,`country`,`company`,`position`,`email`,`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=8026 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of edcon_charges
-- ----------------------------
INSERT INTO `edcon_charges` VALUES ('8000', '1', '100', '2018-01-23 09:31:56', '0', '0', null, null, null, null, null, 'jack1', 'zz', 'lt', 'sh', '123@qq.com', '123', 'esfdsfsdf', '1', null, '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8006', '1', '100', '2018-01-23 06:37:10', '1', '1', 'chrg_5aq1hgga00uuu0epngs', 'd:/34cc9c7e0fdc49469ed08ce8cf54c59a.png', '8006,UwSvgu9CttHI7lZciPKEXSbt1cezz9nt4zXJT1DpKHkp9iHV3iEcAnjDuwGChA3pfXBaiOGYOrpZBH4448T3W6dVsc6VG6LLVVBvFNNbNMtHXUEQiG5knXnicAxCPvs8omSjTMi2AFcBSOI/RGOffkUVC2xUPdgVdTHivlMWLV0=', '0', '4367452053774461', '常潇潇', '中国', '分布视界', '经理', '571740367@qq.com', '18915522229', '', '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8007', '1', '100', '2018-01-23 06:58:03', '0', '1', 'chrg_5aq1ot5y58lb52jzyf2', 'images/tickets/7058b0b796a44250adbecf267097dbd1.png', 'iTxfS2pNxUyuHggp9GdI8RmxvR4KkIFG/icEzfZomNzI7QIDMn5qXzTlnKybVRzA0oPKDIDVEia/z2kJYUNd1aLusbA0QkKKdrcSSTfzM9GEIyxNqsvXLE8EGJKbA89f08tuc+OeIAxwHyXW+tmxc77XWtsvBj5DQr7d50dLCAU=', '0', '4392268335100146', 'GAOFENG', '中国', 'POW.ONE', 'Co-funder', 'golhoon@gmail.com', '18650178381', '期待这次会面，以太坊生态的共同建设者。', '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8008', '1', '100', '2018-01-23 07:02:48', '1', '1', 'chrg_5aq1qh359d8mk5s8tgf', 'images/tickets/5d2b542c88174061ae69f07a187b8f4c.png', 'G7QqTvvxlF2zn4tWIzpzRNJZ+ECN90iIUJPAUWugkepuskef4X8AoCT1bmwUkBaPVcifoGZX4xT0BDKaQRChib+r+GR65lWISY+mP8p3nKsuVL/0nOjYzOQIIqaaTyh7uoXigAZ9zkWIOH6sEb8Kk6INYcvBWW1jLdkiAuo7gYA=', '0', '4367455144688566', '陈斯娜', '中国', '木有', '木有', '729707809@qq.com', '13905080203', '', '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8009', '1', '100', '2018-01-23 07:03:18', '1', '1', 'chrg_5aq1qnlqnormno8knuh', 'images/tickets/db5d5bbfa03b41cab56077253c5cc5b7.png', '8009,N7iFFbT3Ao47Ijmmd/1NrrFD7EixzZpFk2L53xWKU0/5CMQ4wUHKR4EwPieSkqNdrvsAE1sTWduARuN61PIURSDCKP3b7n7n9eWa6396TkcbgmsokTHjkC771ExRGVrZ+CVNnmFt4H+q15SwpvLDPrMN03/97N/0yr9Kp/3aNDM=', '0', '4514617621258765', '吴炳霖', '中国', '', '', '948319914@qq.com', '13723710297', '', '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8010', '1', '100', '2018-01-23 07:14:25', '0', '1', 'chrg_5aq1ukiaq8233k1zsaf', 'images/tickets/2ce3df7c337f47d89201f40ccdc35bea.png', 'DDNZWyK65JH4dXOx9ZbK74HmsX89N20c90X179x0r5iBcrDgVjgvAHSvAGpNCWrmVCP2RAYTn0px/e8S0hAyygWFFsLjkqZjRoxh4TORkDLTGc2ZQgt4F5OGwd2GY5oiaVpdWC+ABGaF7vpFfr6y5TH6ejbYMgXeWuLou/FtJlk=', '0', '4135190006609571', '毛冠军', '中国', '海亮集团', '英语教师', 'mgjcharlie@gmail.com', '15968592107', '', '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8011', '1', '0', '2018-01-24 17:26:33', '0', '0', 'linkTime_91040895fef34a39a6d92f28c25f2a2b', null, null, '0', null, '从', '阿三', '123', '阿三', '571740367@qq.com', '111', null, '1', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8012', '1', '3500', '2018-01-24 19:31:04', '0', '1', 'linkTime_fe39faa1484e4eb38f7d71e59f15c2cd', 'images/tickets/fc2a8b8fcae94e34b309ff76b8622e7b.png', '8012,JttfIZ11eaSjkXNp+eMjmXgGj6Pwo0ENQhHK6huKuqHmMl60L3UNrI2sKMsBw2wUbY4RKbnWmxSksy9cxI9GL1ylZX08peUz8mEzZ9Ay5VUWwae4iA8FX0TsCBnOv1VliDgIGiBoPXSoD5pBQVBd6/P/4WMuEpsJo2ea0AgOQGk=', '0', null, '纪佳铭', '中国', '种子资本', '创始人', '64977668@qq.com', '13822203001', null, '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8013', '1', '3500', '2018-01-24 19:33:47', '0', '1', 'linkTime_e2938c05005c42c7a666cea74466a2be', 'images/tickets/8b21dd1122eb46e9a53cf5e45312df38.png', '8013,Gej7RNIPjt8y0IvD/d4Z+mAmkgY2V9BeXi+EzNcTePqoDpBdMsxp3p79RWvlyKMjROjXwcAABV8scwoFO1Z77PMN55nYPbWNwmomvmDaLnQwAwufCZx5vuE3L9IVcnvc/9yTvBgCuDgKB5OwRn6yV8ApAHmA2Blmaj9gyTR49Fc=', '0', null, '纪佳辉', '中国', '广州市众种子科技有限公司', '经理', 'kofisman@gmail.com', '18818808144', null, '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8014', '1', '3500', '2018-01-24 19:35:05', '0', '1', 'linkTime_87c0d10e2182427cb83f513f7cc77d3f', 'images/tickets/f7c375c2fab14454bec2b44c02c7f947.png', '8014,JqT8VuF+SnJV+9pPpuamqojJtxrF63Nr8HHErQ6atdLtytZRZneb0xIkQhdAYxNN5jMcj4Epbp6PhJlK/nl+Iee5IUn3duwt48HA1Ggs0Ati/pOJCHiDxl00o16Ca9rSLOO1f6HA5IQeSMkg6zXNDF06RNbiIUGNgTZJ7WemxMI=', '0', null, '王燕辉', '中国', '鼓浪屿海鲜餐厅', '', '727844943@qq.com', '+66 6-3168-2516‬‬', null, '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8015', '1', '3500', '2018-01-24 19:35:47', '0', '1', 'linkTime_16a4f691161344ee9f05fdd1e8d02706', 'images/tickets/646a0783ed384604a175cf336e1033e8.png', '8015,ffTXhD2U8IDVWX840Spr46xva0cG2YPP2lQ5+kfb1B8MOOeexJpl1Mt7eqbD+susNdp5prlA6lq45nYCVE71ZkwdytghGspwWZKkMV50K4osPUyyd4cU384ZJcSLLwfHecAEVeP8JQROfl4a2CajfQ3lO0LgI/tak+YltGkQK3Y=', '0', null, '张玉莹', '中国', '', '投资人', '185596625@qq.com', '13570829993', null, '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8016', '1', '3500', '2018-01-24 19:36:28', '0', '1', 'linkTime_a0ff55bf804e49b6b06ff1490e010d05', 'images/tickets/38a9c394031549e3aa33506713fa20b3.png', '8016,AVibXVBCzvA2+A/YX67qW/Yw8gXTy6WFM+wwv2HOQxZy0fauDDcKd2VHPr0unNoD7YGSrFSGTkBskeOjKs5kk4XDHiaPT3h/nKtaCAncpQqxswOUbiS/O5/U4GEccq+rN+nC/b0IDM6su19UFu4xwNDa9xvf6UN/AgmmADEIBe8=', '0', null, '王超', '中国', '自由创业者', '自由创业者', 'leon522886@gmail.com', '18201356921', null, '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8017', '1', '3500', '2018-01-24 19:37:25', '0', '1', 'linkTime_ca84d72aba484dfcbb09325cf8201f11', 'images/tickets/279f8ca4bd384c12b8e574b923e211ff.png', '8017,HJ/UHCYo5hpgJhekYKFsENNJe7u/aJKtsNSgOugk+EVHsHaiGLWYX4EmX30f/69RNHF/zO+knrUfdqrngKj7HI6/Y0WMHQIr9TydZSRF3h0cQCI9RxPZXloUR8a2erZJeRVVEBM21bv2YNKYPvjxPW4I22Zj2tev50s5hecd8gk=', '0', null, '向晶', '中国', 'Black Whale Capital', '', 'james.shang1979@gmail.com', '13968477765', null, '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8018', '1', '3500', '2018-01-24 19:38:01', '0', '1', 'linkTime_595707674efa41ecae68e6d819a21e80', 'images/tickets/a1a3e68a1cab49318ad6d2871f0f25a1.png', '8018,C+VMAB8q01wcgIbcth+2bknyk9L1j9oF7xgvk2S6daG3AXWyMAKh7aR+t+2/CJjrSE/8hRDS0RPh0TEq/n+y2vc6s1Y7k6pTYOqVM9outs8hvkSCor4HmzLSM97mTaSzcp1YqJaIJL3f8HFrKK+Z39pbxh0GZqCuQjt8ebAbHJs=', '0', null, '胡哲文', '中国', 'Black Whale Capital', '', 'fox19891989@hotmail.com', '15980670487', null, '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8019', '1', '3500', '2018-01-25 09:37:42', '0', '1', 'linkTime_fd86752dd1dd4647a24a218deea55400', 'images/tickets/99327328272f4d3caf27118c8a92944e.png', '8019,VH0Rv6ADUX/l8HHYk2ULYo4atMskCn+ToE1TWrwmJO4IVpNUGHMMldE1U7KJBCC0HX+2wgWzfftyk6n5Nr3cwvPFpyF06PBX9aEMIG/SrarV+lKqFxLc24M7Zt0hFQXP5UkfHzS7wOd911ZAbKz9UuGqGtiqs4/gfzzpY10utsg=', '0', null, '田雨', '中国', '山西合大有房地产经济有限公司', '总经理', 'f8xxzh@gmail.com', '18035168887', null, '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8020', '1', '3500', '2018-01-25 11:26:02', '0', '1', 'chrg_5aqrrzkrnc2wip5zv52', 'images/tickets/db70bb4d6395447b87c66ae14f8558bd.png', '8020,DIeonV7TPWUi0CW7TPc7XjQLRQE6DG0BbMfenArKn26D7DZekKcUGYGC0QpiLH5W3RWcl/ib3whLzzczTwvYIpM3zH5GCCRPr5DIFw7w98BW4NN4gz8Pr90YIjUcGS2KYs3FVTyWVmOjiJm7m60RXBELSMhEWYM2MsShZPOAlpw=', '0', '4147202291647970', '刘华清', '中国', 'GrowingIO', '软件架构师', 'lhq9220@gmail.com', '15117946626', '', '0', 'cn', '2018-01-25 14:40:55', '0', '');
INSERT INTO `edcon_charges` VALUES ('8021', '1', '3500', '2018-01-25 18:53:13', '0', '1', 'linkTime_e2e282de14e4410e8fd314da7bd51137', 'images/tickets/a480436ded13484b95538897aa4ddf88.png', '8021,dBjrsANbPTtqBeuS3khDt+Uw1lIOTerNQt7kS+FxlD02rNTyHal8p0YPmL3nS8wsVMB1bOrbzVioK5h3Gg4rNx3h++hyC7zP+eIaEIXlEq4LtNKETCmqEwcvUXeJnb7LRrGYEE5nle159FCC8kVXLCKhEXc+fVf3azzWWjW6BtY=', '0', null, '刘旭希', '中国', '', '', '492684737@qq.com', '13392887500', null, '0', 'cn', '2018-01-25 18:53:13', '0', '');
INSERT INTO `edcon_charges` VALUES ('8022', '1', '3500', '2018-01-25 18:53:39', '0', '1', 'linkTime_b5e6fdee360c4c1782c8724a514c3e79', 'images/tickets/e6dd4a8e2cd548f1ae1dd7c32e4abb29.png', '8022,atVvDQaty5t+eAvqaHo4E4LAvgKjuXzVzcB+LU0cJA93m3SyRfTxZcG9mI9ii/aQzVE8K2waQC3GF9cRDhhfRb0mT+cWw2jEKluLzYkSuXu8Ep5AmfyZovWJsYVQYJevqgNDFnyFajP5nIhiIJwWxvFqCMheIEGIKars7b+tpHk=', '0', null, '汪学渊', '中国', '', '', 'xue-cong2008@163.com', '18628115651', null, '0', 'cn', '2018-01-25 18:53:39', '0', '');
INSERT INTO `edcon_charges` VALUES ('8023', '1', '3500', '2018-01-25 18:54:09', '0', '1', 'linkTime_def1330883294846b5fd9b67ec3a4e96', 'images/tickets/e2ea59f969ad4c3aa0a492c79b2d25ba.png', '8023,LCzsBf3uyphvN/2jb/szGN5tSE+e+G5SYJAukWGtZ/AoucpqW6LrD2syqVhOKva1JJqbtY9dTR5BXrcurQ3SpitxepVXaT/U3FMvfdIfhTtDH4blwEy29htyMmOm4mXSTnE3GHZiReIAQM7qWDY3eQiX0iBKdgCF045t9NhGTvY=', '0', null, '蒋瑞花', '中国', '', '', 'qinya@emour.net', '17665372134', null, '0', 'cn', '2018-01-25 18:54:09', '0', '');
INSERT INTO `edcon_charges` VALUES ('8024', '1', '3500', '2018-01-25 19:06:38', '0', '1', 'chrg_5aqwa4i9g0myvem0rps', 'images/tickets/ffba4ab7cff84a759d5b9dedcc158e9a.png', '8024,F4Qslkp/z74LTiaLmznPZdrn91fH+301cVCCkF6THFIgLVHkTG8L9Yodq3vh9ylwWmaqTCbzr6XHmYxkqjxlMGtgrWJ2xghvmiVqEkA/IGtR4Fld6u20i9AiXrmasVnc7cCXhnG798jrxe8hLMEsDItNzVFkxBCaxo3FlTX6s38=', '0', '4392250048925068', '高昆', '中国', '深圳市英彭瑞尔科技有限公司', '经理', '546932327@qq.com', '18631106937', '', '0', 'cn', '2018-01-25 19:06:38', '0', '');
INSERT INTO `edcon_charges` VALUES ('8025', '1', '0', '2018-01-25 19:09:48', '0', '1', 'linkTime_b6a09df476e34a62b8d725d925ec7568', 'images/tickets/6154a05d91f649b8a3c4ba9304f95a1b.png', '8025,JBu6vkEGKqXRYWxQ0ndxQ0nnHtXjHorZ9nMupbto+CPTv7mr0V48mUmUtziXodpkD4hN2dfL1TNFVYgl6MxuJkS3kDY4ZyyHriU87Jfw6go2GM718/oiA4aypnmPnnAzPxJoaDJJxnPz29P+/WrnhOLzzY/9qBYaVnCg16hc4Ig=', '0', null, '王赛赛', '中国', '', '', '234891941@qq.com', '13586163355', null, '0', 'cn', '2018-01-25 19:09:48', '1', '');

-- ----------------------------
-- Table structure for `edcon_ticket`
-- ----------------------------
DROP TABLE IF EXISTS `edcon_ticket`;
CREATE TABLE `edcon_ticket` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT NULL,
  `number` int(5) NOT NULL DEFAULT '0',
  `location` varchar(50) DEFAULT NULL,
  `price` int(5) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of edcon_ticket
-- ----------------------------
INSERT INTO `edcon_ticket` VALUES ('1', '2018-03-01 17:50:20', '271', '深圳', '3500', 'c:/');

-- ----------------------------
-- Table structure for `failinfo`
-- ----------------------------
DROP TABLE IF EXISTS `failinfo`;
CREATE TABLE `failinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephine` varchar(50) DEFAULT NULL,
  `cardNumber4` varchar(50) DEFAULT NULL,
  `failMessage` varchar(200) DEFAULT NULL,
  `failtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `failure_code` varchar(200) DEFAULT NULL,
  `isread` varchar(2) DEFAULT '0',
  `lang` varchar(5) DEFAULT NULL,
  `ishandle` varchar(2) DEFAULT '0',
  `isPayCoin` varchar(2) DEFAULT '0',
  `txhash` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of failinfo
-- ----------------------------
INSERT INTO `failinfo` VALUES ('4', '王燕辉', '泰国', '鼓浪屿海鲜餐厅', '经理', '727844943@qq.com', '‭‭+66 6-3168-2516‬‬', '4461', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('5', '王燕辉', '泰国', '鼓浪屿海鲜餐厅', '经理', '727844943@qq.com', '‭‭+66 6-3168-2516‬‬', '4461', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('6', '王燕辉', '泰国', '鼓浪屿海鲜餐厅', '经理', '727844943@qq.com', '‭‭+66 631682516‬‬', '4461', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('7', '陈志渊', '中国', '深圳市聚合拓维信息技术有限公司', '开发', 'yoymin@foxmail.com', '‭137 9054 4032‬', '8765', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('8', '纪京言', '中国', '种子资本', '创始人', '64977668@qq.com ', '13822203001', '2392', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('9', '纪佳铭', '中国', '种子资本', '创始人', '64977668@qq.com ', '13822203001', '2102', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('10', '陈志渊', '中国', '深圳市聚合拓维信息技术有限公司', '产品经理', 'PHETHANLOK@GMAIL.COM', '13790544032‬', '8206', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('11', '纪伯翰', '中国', '广州市众种子科技有限公司', '合伙人', 'kofisman@gmail.com', '18818808144', '6519', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('12', '纪佳铭', '中国', '种子资本', '创始人', '64977668@qq.com', '13822203001', '6268', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('13', '王超', '中国', '创业者', '自由职业', 'leon522886@gmail.com', '18201356921', '2566', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('14', '陈志渊', '中国', '深圳市聚合拓维信息技术有限公司', '产品经理', 'PHETHANLOK@GMAIL.COM', '‭13790544032‬', '2551', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('15', '陈志渊', '中国', '深圳市聚合拓维信息技术有限公司', '产品经理', 'PHETHANLOK@GMAIL.COM', '‭13790544032‬', '3236', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('16', '王超', '中国', '创业', '自由', 'leon522886@gmail.com', '18201356921', '2566', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('17', '陈志渊', '中国', '深圳市聚合拓维信息技术有限公司', '产品经理', 'PHETHANLOK@GMAIL.COM', '‭13790544032‬', '2551', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('18', '林垂盛', 'china', '分布视界', '经理', 'chuishenglin@gmail.com', '0813410019', '3680', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('19', '林垂盛', '中国', '分布视界', '经理', 'chuishneglin@gmail.com', '0813410019', '3680', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('20', '林垂盛', '中国', '分布视界', '经理', 'chuishenglin@gmail.com', '0813410019', '3680', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('21', '林垂盛', '中国', '分布视界', '经理', 'chuishenglin@gmail.com', '0813410019', '3333', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('22', '林垂盛', '中国', '分布世界', '经理', 'chuishenglin@gmail.com', '0813410019', '1558', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('23', '林垂盛', '中国', '分布视界', '经理', 'chuishenglin@gmail.co m', '0813410019', '1558', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('24', '陈志渊', '中国', '深圳市聚合拓维信息技术有限公司', '产品经理', 'PHETHANLOK@GMAIL.COM', '‭13790544032‬', '2551', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('25', '毛天恩', '中国', '台湾金融小清新区块链媒体', '合伙人', '149765926@qq.com', '17621594611', '2979', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('26', '林垂盛', '中国', '分布视界', '经理', 'chuishenglin@gmail.com', '0813410019', '2171', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('27', '毛天恩', '中国', '金融小清新区块链媒体', '合伙人', '149765926@qq.com', '+86 17621594611', '9805', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('28', 'Chientze Huang', 'Taiwan', 'Taiwan Fintech FB Community', 'COO', 'chientze.huang@gmail.com', '+886989355212', '2309', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('29', '毛天恩', '中国', '金融小清新区块链媒体', '合伙人', '149765926@qq.com', '17621594611', '0163', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('30', 'Ferra Ting ', 'Malaysia ', 'Collinstar', 'Investment Manager ', 'tingsingyi1993@gmail.com', '+61420208000', '9041', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('31', 'Ferra Ting', 'Malaysia ', 'Collinstar ', 'Investment manager ', 'tingsingyi1993@gmail.com', '+61420208000', '9041', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('32', 'Ferra Ting ', 'Malaysia ', 'Collinstar ', 'Investment manager ', 'tingsingyi1993@gmail.com', '+61420208000', '4865', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('33', 'Chance Du', 'US', 'Coefficient Ventures ', 'Founding partner ', 'chance@coefficientventures.com', '6502503288', '9651', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('34', 'Chance Du', 'US', 'Coefficient Ventures ', 'Founding Partner ', 'chance@coefficientventures.com', '6502503288', '5098', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('35', 'Chance ', 'Du', 'Coefficient Ventures ', 'Founding Partner', 'chance@coefficientventures.com', '6502503288', '6596', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('36', 'Chance Du ', 'China ', 'Coefficient Ventures ', 'Partner ', 'chance@coefficientventures.com', '13518136612', '9660', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('37', 'Chance Du ', 'US', 'Coefficient Ventures ', 'Partner ', 'chance@coefficientventures.com', '6502503288', '9651', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('38', 'Chance Du', 'USA', 'Coefficient Ventures', 'General Partner', 'chance@coefficientventures.com', '6502503288', '9250', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('39', '林垂盛', '中国', '分布视界', '经理', 'chuishenglin@gmail.com', '0813410019', '2171', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('40', '林垂盛', '中国', '分布视界', '经理', 'chuishenglin@gmail.com', '0813410019', '1123', 'the security code is invalid', '2018-01-25 18:10:01', 'invalid_security_code', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('41', '林垂盛', '中国', '分布视界', '经理', 'chuishenglin@gmail.com', '0813410019', '1123', 'the security code is invalid', '2018-01-25 18:10:01', 'invalid_security_code', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('42', '林垂盛', '中国', '分布视界', '经理', 'chuishenglin@gmail.com', '0813410019', '2171', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('43', 'TIAN YU', 'CHI', 'shanxi hedayou real estate brokerage Co Ltd', 'CEO', 'f8xxzh@gmail.com', '18035168887', '9785', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('44', 'TIAN ', 'CHI', 'shanxi hedayou real estate brokerage Co.Ltd', 'ceo', 'f8xxzh@gmail.com', '18035168887', '6763', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('45', '向晶', '中国', 'Black Whale Capital Inc.', 'COO', 'james.shang1979@gmail.com', '+86-13968477765', '7294', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('46', 'ferra ting', 'malaysia', 'Collinstar', 'Investment manager', 'tingsingyi1993@gmail.com', '61420208000', '1871', 'the security code is invalid', '2018-01-25 18:10:01', 'invalid_security_code', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('47', 'Ferra Ting', 'Malaysia', 'Collinstar', 'Investment manager', 'tingsingyi1993@gmail.com', '61420208000', '4832', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('48', 'as', 'as', 'as', 'as', 'as', 'as', 'as', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('49', 'sa', 'sa', 'sa', 'sa', 'sa', 'sa', '4242', 'the security code is invalid', '2018-01-25 18:10:01', 'invalid_security_code', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('50', '朱玲玲', '中国', '河南联玲商贸有限公司', '法人', '229018727@qq.com', '13103717862', '4892', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('51', '朱玲玲', '中国', '河南联玲商贸有限公司', '法人', '229018727@qq.com', '13103717862', '4892', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('52', '林垂盛', '中国', '分布视界', '经理', 'chuishenglin@gmail.com', '0813410019', '5090', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('53', 'TIAN YU', 'CHI', 'shanxi hedayou real estate brokerage Co.Ltd', 'ceo', 'f8xxzh@gmail.com', '18035168887', '9785', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('54', 'TIAN YUNFEI', 'CHI', 'shanxi hedayou real estate brokerage Co.Ltd', 'ceo', 'f8xxzh@gmail.com', '18035168887', '9785', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('55', 'TIANYUNFEI', 'CHI', 'shanxi hedayou real estate brokerage Co.Ltd', 'ceo', 'f8xxzh@gmail.com', '18035168887', '9785', 'failed fraud check', '2018-01-25 18:10:01', 'failed_fraud_check', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('56', 'as', 'as', 'as', 'as', '571740367@qq.com', 'as', '4242', 'the security code is invalid', '2018-01-25 18:10:01', 'invalid_security_code', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('57', 'as', 'as', 'as', 'as', 'as', 'as', '2424', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('58', 'as', 'as', 'as', 'as', 'as', 'as', '4242', 'the security code is invalid', '2018-01-25 18:10:01', 'invalid_security_code', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('59', '马俊杰', '中国', '甲骨文研发中心', 'Application Developer', 'uiuiopop90@126.com', '15502111478', '5562', 'failed fraud check', '2018-02-02 13:37:45', 'failed_fraud_check', '1', null, '0', '1', 'asddadasd');
INSERT INTO `failinfo` VALUES ('60', '马俊杰', '中国', '甲骨文研发中心', 'Application Developer', 'uiuiopop90@126.com', '15502111478', '5562', 'failed fraud check', '2018-02-02 13:37:45', 'failed_fraud_check', '1', null, '0', '1', 'asddadasd');
INSERT INTO `failinfo` VALUES ('61', '1', '2', '3', '4', '6', '5', '1', '失效信息，名称无效，号码无效或不支持品牌', '2018-01-25 18:10:01', '失效信息，名称无效，号码无效或不支持品牌', '1', null, '0', '0', '');
INSERT INTO `failinfo` VALUES ('62', 'as', 'as', 'as', 'as', '571740367@qq.com', 'as', '4242', 'the security code is invalid', '2018-01-25 18:10:01', 'invalid_security_code', '1', 'cn', '0', '0', '');

-- ----------------------------
-- Table structure for `journey`
-- ----------------------------
DROP TABLE IF EXISTS `journey`;
CREATE TABLE `journey` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `content` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of journey
-- ----------------------------
INSERT INTO `journey` VALUES ('1', 'journey', '<table><tbody><tr class=\"firstRow\"><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td></tr><tr><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td></tr><tr><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td></tr><tr><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td></tr><tr><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td></tr><tr><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td><td width=\"119\" valign=\"top\"><br/></td></tr></tbody></table><p><br/></p>');
INSERT INTO `journey` VALUES ('2', 'journeyCN', '<table><tbody><tr class=\"firstRow\"><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">q</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">请</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">请</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">1</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">1</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">1</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">1</td></tr><tr><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\"><br/></td></tr><tr><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\"><br/></td></tr><tr><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\"><br/></td></tr><tr><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\"><br/></td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td><td width=\"79\" valign=\"top\" style=\"word-break: break-all;\">2</td></tr></tbody></table><p><br/></p>');

-- ----------------------------
-- Table structure for `mail`
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `name` varchar(50) DEFAULT NULL,
  `subject` varchar(500) DEFAULT NULL,
  `content` varchar(3500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail
-- ----------------------------
INSERT INTO `mail` VALUES ('pay', 'Payment Confirmation and E-ticket for Asia-Pacific Ethereum Community Meetup', '<p>Hi,&nbsp;</p><p><br/></p><p>Thanks for your support to Asia-Pacific Ethereum Community Meetup!&nbsp;</p><p><br/></p><p><br/></p><p><br/></p><p>We have received your payment, and attached is your personal E-ticket. Please note that the E-ticket is very important because with that you can get your personal name badge as the pass during the event. Each ticket can only be used once by the holder himself/herself, it shall not be sent, altered or transfered.\nWith any questions, please email at: meetup@linktimetech.com\nUpdates will be announced in the following channels, and Don&#39;t forget to join our communities! :)\n\nWebsite: https://baoming.in/\nTelegram: https://t.me/AsiaETHfans\n\nAgain, thank you for your participation. Have a nice day!\n\nBest regards\nLinkTime</p>');
INSERT INTO `mail` VALUES ('send', 'Free pass ticket to Asia-Pacific Ethereum Community Meetup', '<p>Hi,\n\nThanks for your support to Asia-Pacific Ethereum Community Meetup!&nbsp;</p><p><br/></p><p><br/></p><p>Attached is your personal E-ticket. Please note that the E-ticket is very important because with that you can get your personal name badge as the pass during the event.\nWith any questions, please email at: meetup@linktimetech.com\nUpdates will be announced in the following channels, and Don&#39;t forget to join our communities! :)\n\nWebsite: https://baoming.in/\nTelegram: https://t.me/AsiaETHfans\n\nAgain, thank you for your participation. Have a nice day!\n\nBest regards\nLinkTime</p>');
INSERT INTO `mail` VALUES ('payCN', '亚太以太坊社区Meet up的门票信息', '<p>您好！</p><p><br/></p><p>非常感谢您对亚太以太坊社区Meet up的支持！</p><p>我们已经收到您的付款，附件是您个人的电子门票，门票仅限本人使用。请注意保管好您的门票并于入场时出示该门票。</p><p>如果您有任何问题，请发邮件到meetup@linktimetech.com进行咨询。&nbsp;</p><p><br/></p><p>网站: <a href=\"https://baoming.in/\">https://baoming.in/</a></p><p>Telegram: <a href=\"https://t.me/AsiaETHfans\">https://t.me/AsiaETHfans</a></p><p>LinkTime</p>');
INSERT INTO `mail` VALUES ('sendCN', '亚太以太坊社区Meet up的门票信息', '<p>您好！&nbsp;</p><p><br/></p><p><br/></p><p><br/></p><p>非常感谢您对亚太以太坊社区Meet up的支持！\n我们已经收到您的付款，附件是您个人的电子门票，门票仅限本人使用。请注意保管好您的门票并于入场时出示该门票。\n如果您有任何问题，请发邮件到meetup@linktimetech.com进行咨询。\n\n网站: https://baoming.in/\nTelegram: https://t.me/AsiaETHfans\nLinkTime</p>');

-- ----------------------------
-- Table structure for `managers`
-- ----------------------------
DROP TABLE IF EXISTS `managers`;
CREATE TABLE `managers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(50) NOT NULL,
  `loginpsw` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of managers
-- ----------------------------
INSERT INTO `managers` VALUES ('1', 'qiangAdmin', 'Rester@Linktime.ok812');
INSERT INTO `managers` VALUES ('2', 'juneAdmin', '1');
INSERT INTO `managers` VALUES ('3', 'xueAdmin', 'Rester@Linktime.ok812');
INSERT INTO `managers` VALUES ('4', 'jiangAdmin', 'Rester@Linktime.ok812');
INSERT INTO `managers` VALUES ('5', 'okAdmin', 'Rester@Linktime.ok812');

-- ----------------------------
-- Table structure for `webcontent`
-- ----------------------------
DROP TABLE IF EXISTS `webcontent`;
CREATE TABLE `webcontent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `content` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of webcontent
-- ----------------------------
INSERT INTO `webcontent` VALUES ('1', 'info', '<p>梵蒂冈官方代购梵蒂冈的该奋斗的</p>');
INSERT INTO `webcontent` VALUES ('2', 'infoCN', '<p>法国的梵蒂冈梵蒂冈梵蒂冈</p>');
INSERT INTO `webcontent` VALUES ('3', 'time', '<p>肉肉热太热特</p>');
INSERT INTO `webcontent` VALUES ('4', 'timeCN', '<p>肉肉肉肉</p>');
