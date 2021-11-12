-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: kakajiu
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `teacher_invite_key`
--

DROP TABLE IF EXISTS `teacher_invite_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_invite_key` (
  `teacher_id` int NOT NULL,
  `invite_key` varchar(40) NOT NULL DEFAULT '' COMMENT '邀请码',
  PRIMARY KEY (`teacher_id`),
  UNIQUE KEY `teacher_invite_key_teacher_id_uindex` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教师对学生的邀请码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_invite_key`
--

LOCK TABLES `teacher_invite_key` WRITE;
/*!40000 ALTER TABLE `teacher_invite_key` DISABLE KEYS */;
INSERT INTO `teacher_invite_key` VALUES (2,'kamisato');
/*!40000 ALTER TABLE `teacher_invite_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_student`
--

DROP TABLE IF EXISTS `teacher_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_student` (
  `teacher_id` int NOT NULL,
  `student_id` int NOT NULL,
  KEY `teacher_student_user_user_id_fk` (`teacher_id`),
  KEY `teacher_student_user_user_id_fk_2` (`student_id`),
  CONSTRAINT `teacher_student_user_user_id_fk` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_student_user_user_id_fk_2` FOREIGN KEY (`student_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='老师学生关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_student`
--

LOCK TABLES `teacher_student` WRITE;
/*!40000 ALTER TABLE `teacher_student` DISABLE KEYS */;
INSERT INTO `teacher_student` VALUES (2,1),(2,7),(9,11);
/*!40000 ALTER TABLE `teacher_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `roles` varchar(10) DEFAULT 'student',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_user_id_uindex` (`user_id`),
  KEY `username_index` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'洛天依','123456','student'),(2,'神里绫华','123456','teacher'),(3,'admin','123456','admin'),(7,'student','123456','student'),(9,'teacher1','123456','teacher'),(11,'student3','123456','student');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_video_progress`
--

DROP TABLE IF EXISTS `user_video_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_video_progress` (
  `user_id` int NOT NULL,
  `video_id` int NOT NULL,
  `progress` int NOT NULL COMMENT '观看视频进度,百分比表示',
  KEY `user_video_progress_user_user_id_fk` (`user_id`),
  KEY `user_video_progress_video_video_id_fk` (`video_id`),
  CONSTRAINT `user_video_progress_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_video_progress_video_video_id_fk` FOREIGN KEY (`video_id`) REFERENCES `video` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户视频观看进度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_video_progress`
--

LOCK TABLES `user_video_progress` WRITE;
/*!40000 ALTER TABLE `user_video_progress` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_video_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `video_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `video_src` varchar(1000) NOT NULL,
  `tag_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`video_id`),
  UNIQUE KEY `video_video_id_uindex` (`video_id`),
  KEY `video_title__index` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,'主席讲话-央视','http://kakajiu.oss-cn-beijing.aliyuncs.com/videos%2F%E4%B8%BB%E5%B8%AD%E8%AE%B2%E8%AF%9D-%E5%A4%AE%E8%A7%86.mp4?Expires=1635521762&OSSAccessKeyId=STS.NUC3Bm6KFRKDapdHhddXEh4PB&Signature=VOwgQrYiemVfVPOpGD6JHBZVKOs%3D&security-token=CAIS8gF1q6Ft5B2yfSjIr5b2ePjZ25Rn5YmvY1bVrGgxaNdph%2FH7oDz2IH5Of3JuBuAYsvkymmxX6fsflqJcRoN7REDJavZKyszeLvUux82T1fau5Jko1beHewHKeTOZsebWZ%2BLmNqC%2FHt6md1HDkAJq3LL%2Bbk%2FMdle5MJqP%2B%2FUFB5ZtKWveVzddA8pMLQZPsdITMWCrVcygKRn3mGHdfiEK00he8Tojt%2FThmZDMt0KP3QSlmrYvyt6vcsT%2BXa5FJ4xiVtq55utye5fa3TRYgxowr%2Fks1%2FcapmeZ4ozAXQUOvEjbKYrO%2F9RjKwl9d7j3ogndMmZZ%2FxqAARfjcnya3CaY9kevOV%2BqCOO80DL6reJVwzGsSpuSBVltPeHqZQ6kfcsQ63YFuBTCD8zCE0GZ0IxgOcvpc3PQjYS4zcVXil5JUWq5%2Brt8lgfAaco2E5tGeXAeHdIBkcal8WNxU79h%2FvEOR6fpS%2BbTu3AuVovfzdd%2FOlZ7ASYbS6jS','央视'),(2,'上海外滩灯光秀','http://kakajiu.oss-cn-beijing.aliyuncs.com/videos%2F%E4%B8%8A%E6%B5%B7%E5%A4%96%E6%BB%A9%E7%81%AF%E5%85%89%E7%A7%80.mp4?Expires=1635522100&OSSAccessKeyId=STS.NUTSPrLpB263encWHnum39PhH&Signature=gQNXlmtfbAorU9NObZA38jv90kg%3D&security-token=CAIS8gF1q6Ft5B2yfSjIr5bhGOrGoa9jhfTYZ0jSs0g7eeIf1pXDqjz2IH5Of3JuBuAYsvkymmxX6fsflqJcRoN7REDJavZKyszjENgux82T1fau5Jko1beHewHKeTOZsebWZ%2BLmNqC%2FHt6md1HDkAJq3LL%2Bbk%2FMdle5MJqP%2B%2FUFB5ZtKWveVzddA8pMLQZPsdITMWCrVcygKRn3mGHdfiEK00he8Tojt%2FThmZDMt0KP3QSlmrYvyt6vcsT%2BXa5FJ4xiVtq55utye5fa3TRYgxowr%2Fks1%2FcapmeZ4ozAXQUOvEjbKYrO%2F9RjKwl9d7j3ogndMmZZ%2FxqAAYI50PqLLoR0AL9xuxDfdq9wNmvSOK9Ca0ADgipXUDm3vVbgVCQ3x0Vpj5CGz3afoY6CQATCpWPvYdHsnQvmf5bI8S80eYuN9OeLqxpUQAPT2L%2BN5%2BPYen%2BkTHGzlxbhkhaufqH7wuEUgZ3ceBSOTAUS6tYym246W2yBCnGANXwL','灯光秀'),(3,'全国灯光秀混剪','http://kakajiu.oss-cn-beijing.aliyuncs.com/videos%2F%E5%85%A8%E5%9B%BD%E7%81%AF%E5%85%89%E7%A7%80%E6%B7%B7%E5%89%AA.mp4?Expires=1635522138&OSSAccessKeyId=STS.NUR4u3sxHSZhXV143VhBpD7yM&Signature=5y7pnU%2Fur0AKg5p2GMsMna0uIUE%3D&security-token=CAIS8gF1q6Ft5B2yfSjIr5bnf8%2BHnqdp5JiDWnCA0DMDZM1cq%2FLSrzz2IH5Of3JuBuAYsvkymmxX6fsflqJcRoN7REDJavZKysykNt4ux82T1fau5Jko1beHewHKeTOZsebWZ%2BLmNqC%2FHt6md1HDkAJq3LL%2Bbk%2FMdle5MJqP%2B%2FUFB5ZtKWveVzddA8pMLQZPsdITMWCrVcygKRn3mGHdfiEK00he8Tojt%2FThmZDMt0KP3QSlmrYvyt6vcsT%2BXa5FJ4xiVtq55utye5fa3TRYgxowr%2Fks1%2FcapmeZ4ozAXQUOvEjbKYrO%2F9RjKwl9d7j3ogndMmZZ%2FxqAARWJxiMs4qMPVDYJNE2s6WzlLiYuiEJG8jhj3lP7i9lna5AvQiXaq6IzNVcNoie8jHETRHoyHNLyoDERzbGClg7kcihvv8UxBR7iL7SdP1P47zmnNrkJJW%2F46uQtPX31GBOu%2Ba%2Fj85wEZwTUMG6ek7IgiUoZufbOfS%2Fsz%2BwjuxF6','灯光秀'),(4,'致敬英雄-央视','http://kakajiu.oss-cn-beijing.aliyuncs.com/videos%2F%E8%87%B4%E6%95%AC%E8%8B%B1%E9%9B%84-%E5%A4%AE%E8%A7%86.mp4?Expires=1635817909&OSSAccessKeyId=STS.NTnm2724F9NWbFQTQsStTUwUH&Signature=OQ7%2FkF%2BTczWZzxacdI2%2BLQEJCU8%3D&security-token=CAIS8gF1q6Ft5B2yfSjIr5fbJoiD3%2Btnjoy8YGDgsFEmX%2Ft4urL%2Bqjz2IH5Of3JuBuAYsvkymmxX6fsflqJcRoN7REDJavZKysyQc9I9xs2T1fau5Jko1beHewHKeTOZsebWZ%2BLmNqC%2FHt6md1HDkAJq3LL%2Bbk%2FMdle5MJqP%2B%2FUFB5ZtKWveVzddA8pMLQZPsdITMWCrVcygKRn3mGHdfiEK00he8Tojt%2FThmZDMt0KP3QSlmrYvyt6vcsT%2BXa5FJ4xiVtq55utye5fa3TRYgxowr%2Fks1%2FcapmeZ4ozAXQUOvEjbKYrO%2F9RjKwl9d7j3ogndMmZZ%2FxqAAXJZltWsv0bh%2FVhfk1pRrY82eSTCXWrLJK6qzjkVZO4KzvaZYqprsUWp2mMmem1Cl5uOwx0XwHKhuuq%2BVqtw4FVEhFtYlbgdJsJmMAzCtd5fpg%2F29lElmIZe0Ru%2FeXm7yzdrP6b2kMdGcxrF%2BMfqT6MoRHuH3RnJ6f%2FJm8msSGG0','央视'),(5,'山东卫视-《我们正青春》','http://kakajiu.oss-cn-beijing.aliyuncs.com/videos%2F%E5%B1%B1%E4%B8%9C%E5%8D%AB%E8%A7%86-%E3%80%8A%E6%88%91%E4%BB%AC%E6%AD%A3%E9%9D%92%E6%98%A5%E3%80%8B.mp4?Expires=1635817955&OSSAccessKeyId=STS.NTHzCN4KHRRSVb9A7r3rzoRRo&Signature=D6mOnam9CpxXR4jJkXjZxVIFvj8%3D&security-token=CAIS8gF1q6Ft5B2yfSjIr5f9Mfn62ZRp5ZC4VESIpTcnP%2F1WgJf5jTz2IH5Of3JuBuAYsvkymmxX6fsflqJcRoN7REDJavZKyszHUtc9xs2T1fau5Jko1beHewHKeTOZsebWZ%2BLmNqC%2FHt6md1HDkAJq3LL%2Bbk%2FMdle5MJqP%2B%2FUFB5ZtKWveVzddA8pMLQZPsdITMWCrVcygKRn3mGHdfiEK00he8Tojt%2FThmZDMt0KP3QSlmrYvyt6vcsT%2BXa5FJ4xiVtq55utye5fa3TRYgxowr%2Fks1%2FcapmeZ4ozAXQUOvEjbKYrO%2F9RjKwl9d7j3ogndMmZZ%2FxqAAWAyVtIZ4Dy1V9N6mGaVU3%2FBidL0Gr2TQQWfEioOi8YQBZfEqy1qWT4X%2BCHRPxZ8WHt23TZFXOgcOWHU5L59mvS6Rf0AfzCmRjVbENNMS2aaV7mMfXp97S2vQWVvYPETnodCgdeGD75gyrecN6W6zo8Uj8KHwGr1moyr3YFvn%2FGf','地方卫视'),(6,'山西共青团','http://kakajiu.oss-cn-beijing.aliyuncs.com/videos%2F%E5%B1%B1%E8%A5%BF%E5%85%B1%E9%9D%92%E5%9B%A2.mp4?Expires=1635818005&OSSAccessKeyId=STS.NU7dMswveoQ6wLNAz6QEYaGBM&Signature=F1OjvDq77TWBjY8H7uWi4k5LN3g%3D&security-token=CAIS8gF1q6Ft5B2yfSjIr5aCL%2FfHmqlE2JPddWr%2FpXpjXcp1joLprzz2IH5Of3JuBuAYsvkymmxX6fsflqJcRoN7REDJavZKyszqTNQ9xs2T1fau5Jko1beHewHKeTOZsebWZ%2BLmNqC%2FHt6md1HDkAJq3LL%2Bbk%2FMdle5MJqP%2B%2FUFB5ZtKWveVzddA8pMLQZPsdITMWCrVcygKRn3mGHdfiEK00he8Tojt%2FThmZDMt0KP3QSlmrYvyt6vcsT%2BXa5FJ4xiVtq55utye5fa3TRYgxowr%2Fks1%2FcapmeZ4ozAXQUOvEjbKYrO%2F9RjKwl9d7j3ogndMmZZ%2FxqAAZ5a2aIYdCqmHAV8l6%2FySUFIQVgvKQ%2BhUAGtxh9n4HVZyx5RCdFv7qxPXIr%2F1tcCwggIRg3nFJFyyvUTzoJnitr3%2BMrEoRcQ1McU7wOeLHFEakrA9V%2FikI8anEGb%2F1f6Qc1rpIpFLvmlIroN4zhffKc49cI51AqH83nWEMJ7aqye','地方卫视'),(7,'湖南-星火','http://kakajiu.oss-cn-beijing.aliyuncs.com/videos%2F%E6%B9%96%E5%8D%97-%E6%98%9F%E7%81%AB.mp4?Expires=1635818019&OSSAccessKeyId=STS.NTMQm26K72S7RgQQvfhmwQzp3&Signature=CBFCP3LAtXB%2F75NFDll25BBiuZg%3D&security-token=CAIS8gF1q6Ft5B2yfSjIr5f4GteG25QWhZHcUEHgtXYzZOJbvr%2Fb0Tz2IH5Of3JuBuAYsvkymmxX6fsflqJcRoN7REDJavZKysyuWqs9xs2T1fau5Jko1beHewHKeTOZsebWZ%2BLmNqC%2FHt6md1HDkAJq3LL%2Bbk%2FMdle5MJqP%2B%2FUFB5ZtKWveVzddA8pMLQZPsdITMWCrVcygKRn3mGHdfiEK00he8Tojt%2FThmZDMt0KP3QSlmrYvyt6vcsT%2BXa5FJ4xiVtq55utye5fa3TRYgxowr%2Fks1%2FcapmeZ4ozAXQUOvEjbKYrO%2F9RjKwl9d7j3ogndMmZZ%2FxqAAXRHXwOiWOsd1xwbItq%2FkLvPTc8mpwf9tAivsWBXtDPWrRcTKXCwTYqo5D07bHbv8NUIEDhkifkDT7RQKu92CAxUyFIlziaKVYZp%2F8e1nUxM3U2EiKkQAqUJNK79FNcqyCh7ZJitilveB%2FeC51niUvzNo2U10si67D5JEr9fXoMP','地方卫视');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_tag`
--

DROP TABLE IF EXISTS `video_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_tag` (
  `tag_id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) NOT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `video_tag_tag_id_uindex` (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_tag`
--

LOCK TABLES `video_tag` WRITE;
/*!40000 ALTER TABLE `video_tag` DISABLE KEYS */;
INSERT INTO `video_tag` VALUES (1,'央视'),(2,'灯光秀'),(3,'地方卫视');
/*!40000 ALTER TABLE `video_tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-12 22:22:50
