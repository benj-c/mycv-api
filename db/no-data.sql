-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.28 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table mycvdb.cv: ~3 rows (approximately)
DELETE FROM `cv`;
/*!40000 ALTER TABLE `cv` DISABLE KEYS */;
INSERT INTO `cv` (`id`, `created_date`, `is_draft`, `summery`, `first_name`, `surname`, `country`, `city`, `email`, `contact_number`, `user_id`, `job_field_id`) VALUES
	(1, '2022-04-27 18:32:48', b'1', 'this my first cv', 'john', 'doe', 'sl', 'hikka', 'sdasd@asdasd.com', '0718987778', 1, 1),
	(3, '2022-04-27 20:50:35', b'1', 'this my first cv', 'john', 'doe 2', 'sl', 'hikka', 'sdasd@asdasd.com', '0718987778', 5, 1),
	(4, '2022-04-30 15:27:40', b'1', '{\r\n    "summery": "sc summery updated"\r\n}', 'john', 'boonie', 'sri lanka', 'colombo', 'john@asdasd.com', '0718987778', 14, 1);
/*!40000 ALTER TABLE `cv` ENABLE KEYS */;

-- Dumping data for table mycvdb.cv_job_field: ~2 rows (approximately)
DELETE FROM `cv_job_field`;
/*!40000 ALTER TABLE `cv_job_field` DISABLE KEYS */;
INSERT INTO `cv_job_field` (`id`, `field`) VALUES
	(1, 'IT'),
	(2, 'HR');
/*!40000 ALTER TABLE `cv_job_field` ENABLE KEYS */;

-- Dumping data for table mycvdb.degree_level: ~6 rows (approximately)
DELETE FROM `degree_level`;
/*!40000 ALTER TABLE `degree_level` DISABLE KEYS */;
INSERT INTO `degree_level` (`ID`, `level`) VALUES
	(1, 'Bachelor degree or equal'),
	(2, 'Doctoral degree'),
	(3, 'Master degree or equal'),
	(4, 'Secondary education'),
	(5, 'Technical/vocational diploma'),
	(6, 'Higher diploma or equal');
/*!40000 ALTER TABLE `degree_level` ENABLE KEYS */;

-- Dumping data for table mycvdb.education_history: ~1 rows (approximately)
DELETE FROM `education_history`;
/*!40000 ALTER TABLE `education_history` DISABLE KEYS */;
INSERT INTO `education_history` (`id`, `title`, `institution_name`, `location`, `end_date`, `start_date`, `degree_level_id`, `education_study_field_id`, `cv_id`) VALUES
	(4, 'Bcs', 'esoft', 'colombo', '2017-10-10', '2015-10-10', 5, 1, 4);
/*!40000 ALTER TABLE `education_history` ENABLE KEYS */;

-- Dumping data for table mycvdb.education_study_field: ~2 rows (approximately)
DELETE FROM `education_study_field`;
/*!40000 ALTER TABLE `education_study_field` DISABLE KEYS */;
INSERT INTO `education_study_field` (`id`, `title`) VALUES
	(1, 'IT'),
	(2, 'HR');
/*!40000 ALTER TABLE `education_study_field` ENABLE KEYS */;

-- Dumping data for table mycvdb.employment_type: ~6 rows (approximately)
DELETE FROM `employment_type`;
/*!40000 ALTER TABLE `employment_type` DISABLE KEYS */;
INSERT INTO `employment_type` (`id`, `type`) VALUES
	(1, 'full-time'),
	(2, 'contract basis'),
	(3, 'part time 50% or more'),
	(4, 'part time less than 50%'),
	(5, 'intern'),
	(6, 'temporary');
/*!40000 ALTER TABLE `employment_type` ENABLE KEYS */;

-- Dumping data for table mycvdb.professional_qualification: ~1 rows (approximately)
DELETE FROM `professional_qualification`;
/*!40000 ALTER TABLE `professional_qualification` DISABLE KEYS */;
INSERT INTO `professional_qualification` (`id`, `title`, `field`, `description`, `cv_id`) VALUES
	(1, 'degree', 'CS', 'degree in CS', 1);
/*!40000 ALTER TABLE `professional_qualification` ENABLE KEYS */;

-- Dumping data for table mycvdb.specific_skil: ~1 rows (approximately)
DELETE FROM `specific_skil`;
/*!40000 ALTER TABLE `specific_skil` DISABLE KEYS */;
INSERT INTO `specific_skil` (`id`, `title`, `description`, `field`, `cv_id`) VALUES
	(2, 'degree', 'degree in CS', 'CS', 4);
/*!40000 ALTER TABLE `specific_skil` ENABLE KEYS */;

-- Dumping data for table mycvdb.user: ~9 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `user_name`, `password`, `email`, `created_date`, `is_active`, `user_role_id`) VALUES
	(1, 'demo_user', '$2a$10$dm94pQB27BwMuBqqVwauw.1DK86lDVslAJHGmP1DJUGGiZMnXz6Mi', 'demo_user@test.com', '2022-04-27', b'1', 1),
	(2, 'demo_admin', '$2a$10$dm94pQB27BwMuBqqVwauw.1DK86lDVslAJHGmP1DJUGGiZMnXz6Mi', 'demo_admin@test.com', '2022-04-27', b'0', 3),
	(3, 'demo_agent', '$2a$10$dm94pQB27BwMuBqqVwauw.1DK86lDVslAJHGmP1DJUGGiZMnXz6Mi', 'agent@test.com', '2022-04-27', b'1', 2),
	(4, 'demo_agent_2', '$2a$10$dm94pQB27BwMuBqqVwauw.1DK86lDVslAJHGmP1DJUGGiZMnXz6Mi', 'agent2@test.com', '2022-04-27', b'0', 2),
	(5, 'demo_user_2', '$2a$10$dm94pQB27BwMuBqqVwauw.1DK86lDVslAJHGmP1DJUGGiZMnXz6Mi', 'demo_2@test.com', '2022-04-27', b'1', 1),
	(11, 'chathura', '$2a$10$LoJnPojDLTh.k4xYM7UwN.jv9P/4TB3PG1DSh7KSdVwGZQTN/kZpq', 'chathura', '2022-04-30', b'1', 1),
	(12, 'chathura_s', '$2a$10$qlKSyPDENfI2893VNwPgX.07XyzTDz.Odtu85k.04z/4mNDK0ylQG', 'chathura_s', '2022-04-30', b'1', 3),
	(13, 'chathura_ss', '$2a$10$nfzM1SXauS5Ns.BzdeRtp.5Bmvk45lkjUerDMP5QbKvlWFI4yJWw2', 'chathura_ss', '2022-04-30', b'1', 2),
	(14, 'john', '$2a$10$AVMAVwsZ0.rl/uUblXTLkOF.3jMtw82VLbBYo8hCxX.gGielHcDzu', 'john', '2022-04-30', b'1', 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping data for table mycvdb.user_role: ~3 rows (approximately)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `type`) VALUES
	(1, 'JOB_SEEKER'),
	(2, 'AGENT'),
	(3, 'ADMIN');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

-- Dumping data for table mycvdb.work_experience: ~1 rows (approximately)
DELETE FROM `work_experience`;
/*!40000 ALTER TABLE `work_experience` DISABLE KEYS */;
INSERT INTO `work_experience` (`id`, `job_title`, `country`, `employer`, `city`, `is_current_job`, `start_date`, `end_date`, `cv_id`, `employment_type_id`) VALUES
	(2, 'software dev', 'srilanka', 'mobitel', 'colombo', b'1', '2020-10-10', NULL, 4, 1);
/*!40000 ALTER TABLE `work_experience` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
