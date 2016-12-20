-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.6.17 - MySQL Community Server (GPL)
-- SE du serveur:                Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Export de la structure de la base pour conference
CREATE DATABASE IF NOT EXISTS `conference` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `conference`;


-- Export de la structure de table conference. article
CREATE TABLE IF NOT EXISTS `article` (
  `id_art` int(11) NOT NULL AUTO_INCREMENT,
  `id_sess` int(11) NOT NULL,
  `titre` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id_art`),
  KEY `FK_ARTICLE_SESSION` (`id_sess`),
  CONSTRAINT `FK_ARTICLE_SESSION` FOREIGN KEY (`id_sess`) REFERENCES `session` (`id_sess`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Export de données de la table conference.article : ~6 rows (environ)
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` (`id_art`, `id_sess`, `titre`) VALUES
	(2, 2, 'Cloud computing'),
	(3, 2, 'Business intelligence '),
	(4, 3, 'HADOOP Technologies'),
	(5, 4, 'Méthode semi empirique AM1'),
	(6, 4, 'Spectrophotoscopie'),
	(7, 4, 'Methode hartree fock');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;


-- Export de la structure de table conference. auteur
CREATE TABLE IF NOT EXISTS `auteur` (
  `id_aut` int(11) NOT NULL AUTO_INCREMENT,
  `nom` text COLLATE utf8_unicode_ci,
  `prenom` text COLLATE utf8_unicode_ci,
  `email` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id_aut`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Export de données de la table conference.auteur : ~7 rows (environ)
/*!40000 ALTER TABLE `auteur` DISABLE KEYS */;
INSERT INTO `auteur` (`id_aut`, `nom`, `prenom`, `email`) VALUES
	(2, 'Wassime', 'Bouimadaghene', 'wassime@gmail.com'),
	(3, 'Mohamed', 'Akouz', 'akouz@gmail.com'),
	(4, 'Nouhad', 'Haddad', 'nouhad@gmail.com'),
	(5, 'Hamza', 'Baqassou', 'baqassou@gmail.com'),
	(6, 'Atlassi', 'Mohamed', 'Atlassi@gmail.com'),
	(7, 'Hamza', 'Hajjaj', 'hamza@gmail.com'),
	(9, 'Auteur', 'Test', 'tets@gmail.com');
/*!40000 ALTER TABLE `auteur` ENABLE KEYS */;


-- Export de la structure de table conference. auteur_article
CREATE TABLE IF NOT EXISTS `auteur_article` (
  `id_aut` int(11) NOT NULL,
  `id_art` int(11) NOT NULL,
  PRIMARY KEY (`id_aut`,`id_art`),
  KEY `FK_AUTEUR_ARTICLE2` (`id_art`),
  CONSTRAINT `FK_AUTEUR_ARTICLE` FOREIGN KEY (`id_aut`) REFERENCES `auteur` (`id_aut`),
  CONSTRAINT `FK_AUTEUR_ARTICLE2` FOREIGN KEY (`id_art`) REFERENCES `article` (`id_art`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Export de données de la table conference.auteur_article : ~12 rows (environ)
/*!40000 ALTER TABLE `auteur_article` DISABLE KEYS */;
INSERT INTO `auteur_article` (`id_aut`, `id_art`) VALUES
	(3, 2),
	(6, 2),
	(2, 3),
	(4, 3),
	(7, 3),
	(2, 4),
	(5, 4),
	(2, 5),
	(4, 5),
	(3, 6),
	(7, 6),
	(5, 7);
/*!40000 ALTER TABLE `auteur_article` ENABLE KEYS */;


-- Export de la structure de table conference. president
CREATE TABLE IF NOT EXISTS `president` (
  `id_pres` int(11) NOT NULL AUTO_INCREMENT,
  `nom` text COLLATE utf8_unicode_ci,
  `prenom` text COLLATE utf8_unicode_ci,
  `email` text COLLATE utf8_unicode_ci,
  `departement` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id_pres`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Export de données de la table conference.president : ~2 rows (environ)
/*!40000 ALTER TABLE `president` DISABLE KEYS */;
INSERT INTO `president` (`id_pres`, `nom`, `prenom`, `email`, `departement`) VALUES
	(2, 'Samir', 'Rissouni', 'samir@gmail.com', 'Informatique'),
	(3, 'Karim', 'ADLI', 'karim@gmail.com', 'Chimie');
/*!40000 ALTER TABLE `president` ENABLE KEYS */;


-- Export de la structure de table conference. programme
CREATE TABLE IF NOT EXISTS `programme` (
  `id_prog` int(11) NOT NULL AUTO_INCREMENT,
  `intitule` text COLLATE utf8_unicode_ci,
  `date_deb` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_prog`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Export de données de la table conference.programme : ~2 rows (environ)
/*!40000 ALTER TABLE `programme` DISABLE KEYS */;
INSERT INTO `programme` (`id_prog`, `intitule`, `date_deb`, `date_fin`) VALUES
	(2, 'Big Data', '2016-05-24', '2016-05-28'),
	(3, 'Chimie analytique', '2016-05-02', '2016-05-04');
/*!40000 ALTER TABLE `programme` ENABLE KEYS */;


-- Export de la structure de table conference. session
CREATE TABLE IF NOT EXISTS `session` (
  `id_sess` int(11) NOT NULL AUTO_INCREMENT,
  `id_prog` int(11) NOT NULL,
  `id_pres` int(11) NOT NULL,
  `heure_deb` datetime DEFAULT NULL,
  `heure_fin` datetime DEFAULT NULL,
  `jour` date DEFAULT NULL,
  PRIMARY KEY (`id_sess`),
  KEY `FK_PRESIDENT_SESSION` (`id_pres`),
  KEY `FK_PROG_SESSION` (`id_prog`),
  CONSTRAINT `FK_PRESIDENT_SESSION` FOREIGN KEY (`id_pres`) REFERENCES `president` (`id_pres`),
  CONSTRAINT `FK_PROG_SESSION` FOREIGN KEY (`id_prog`) REFERENCES `programme` (`id_prog`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Export de données de la table conference.session : ~3 rows (environ)
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` (`id_sess`, `id_prog`, `id_pres`, `heure_deb`, `heure_fin`, `jour`) VALUES
	(2, 2, 2, '2016-05-30 08:30:00', '2016-05-30 12:00:00', '2016-05-24'),
	(3, 2, 2, '2016-05-30 14:00:00', '2016-05-30 17:00:00', '2016-05-24'),
	(4, 3, 3, '2016-05-30 08:00:00', '2016-05-30 11:00:00', '2016-05-02');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
