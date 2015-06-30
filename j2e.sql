-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Dim 14 Juin 2015 à 19:36
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `j2e`
--
CREATE DATABASE IF NOT EXISTS `j2e` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `j2e`;

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note` int(11) NOT NULL,
  `date` varchar(50) NOT NULL,
  `texte` varchar(5000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `liason`
--

CREATE TABLE IF NOT EXISTS `liason` (
  `fk_recette` int(11) DEFAULT NULL,
  `fk_login` int(11) DEFAULT NULL,
  `fk_commentaire` int(11) DEFAULT NULL,
  KEY `fk_recette` (`fk_recette`),
  KEY `fk_login` (`fk_login`),
  KEY `fk_commentaire` (`fk_commentaire`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `recette`
--

CREATE TABLE IF NOT EXISTS `recette` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `duration` int(11) NOT NULL,
  `expertise` int(11) NOT NULL,
  `nbpeople` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  `recette` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `recette`
--

INSERT INTO `recette` (`id`, `title`, `duration`, `expertise`, `nbpeople`, `type`, `description`, `recette`) VALUES
(1, 'Salade de tomates', 5, 1, 1, 'salade', 'Salade tomate mozarella ', 'Il faut salade\r\ntomates\r\nmozarella\r\nbasilic'),
(2, 'Pates calabrese', 20, 2, 3, 'pates', 'Pates à la calabrese ', 'Il faut penne\r\ntomates\r\nmozarella\r\nbasilic'),
(3, 'Fraises', 5, 1, 2, 'dessert', 'Salade de fraises ', 'Il faut fraises\r\nsucre\r\njus de citron'),
(4, 'Barbecue', 35, 1, 5, 'Viande', 'Barbecue estival ', 'Il faut saucisses\r\nbrochettes\r\nribs');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `login` varchar(30) NOT NULL,
  `pwd` varchar(30) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastconnection` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `firstname` (`firstname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
