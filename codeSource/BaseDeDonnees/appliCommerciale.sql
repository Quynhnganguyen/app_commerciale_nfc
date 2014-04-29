-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Mar 01 Avril 2014 à 23:11
-- Version du serveur: 5.5.35
-- Version de PHP: 5.3.10-1ubuntu3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `appliCommerciale`
--
CREATE DATABASE `appliCommerciale` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `appliCommerciale`;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `adresse_mail` varchar(40) NOT NULL,
  `login` varchar(40) NOT NULL,
  `mot_de_passe` varchar(40) NOT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

Create_table(:users) do |t|
    t.string :nom_client,     null: false
    t.string :prenom_client,  null: false
    t.string :adresse_mail_client, null: false
   t.timestamps
end
-- --------------------------------------------------------

--
-- Structure de la table `franchise`
--

CREATE TABLE IF NOT EXISTS `franchise` (
  `id_franchise` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `entreprise` varchar(40) NOT NULL,
  PRIMARY KEY (`id_franchise`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

Create_table(:franchises) do |t|
    t.string :nom_entreprise,     null: false
   t.timestamps
end
-- --------------------------------------------------------

--
-- Structure de la table `liste_courses_deja_faites`
--

CREATE TABLE IF NOT EXISTS `liste_courses_deja_faites` (
  `id_liste_courses_deja_faites` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_liste_courses_deja_faites`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Structure de la table `liste_favoris`
--

CREATE TABLE IF NOT EXISTS `liste_favoris` (
  `id_liste_favoris` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_liste_favoris`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `liste_noire`
--

CREATE TABLE IF NOT EXISTS `liste_noire` (
  `id_liste_noire` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_liste_noire`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `liste_produits_a_acheter`
--

CREATE TABLE IF NOT EXISTS `liste_produits_a_acheter` (
  `id_liste_produits_a_acheter` int(10) unsigned NOT NULL,
  `id_liste_courses_deja_faites` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_liste_produits_a_acheter`),
  KEY `id_liste_courses_deja_faites` (`id_liste_courses_deja_faites`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `magasin`
--

CREATE TABLE IF NOT EXISTS `magasin` (
  `id_magasin` int(11) unsigned NOT NULL,
  `nomMagasin` varchar(40) NOT NULL,
  `adresse` varchar(40) NOT NULL,
  `id_franchise` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_magasin`),
  KEY `id_franchise` (`id_franchise`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Create_table(:magasins) do |t|
    t.string :nom_magasin,     null: false
    t.string :adresse_magasin, null: false
    t.string :id_franchise, null: false
   t.timestamps
end
-- --------------------------------------------------------

--
-- Structure de la table `magasin_client`
--

CREATE TABLE IF NOT EXISTS `magasin_client` (
  `id_magasin` int(11) NOT NULL DEFAULT '0',
  `id_client` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_magasin`,`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `nfc_tag`
--

CREATE TABLE IF NOT EXISTS `nfc_tag` (
  `id_nfc_tag` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_nfc_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Create_table(:tags) do |t|
    t.string :id_nfc_tag,     null: false
   t.timestamps
end

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `id_produit` int(11) NOT NULL,
  `nom_produit` varchar(40) NOT NULL,
  `prix` float NOT NULL,
  `id_type_produit` int(10) unsigned NOT NULL,
  `id_source` int(10) unsigned NOT NULL,
  `id_magasin` int(10) unsigned NOT NULL,
  `id_nfc_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_produit`),
  KEY `id_type_produit` (`id_type_produit`),
  KEY `id_source` (`id_source`),
  KEY `id_magasin` (`id_magasin`),
  KEY `id_nfc_tag` (`id_nfc_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Create_table(:produits) do |t|
    t.string :nom_produit,     null: false
    t.float :prix,  null: false
    t.string :type_produit
    t.string :source
    t.string :magasin
    t.string :id_nfc_tag, null: false
   t.timestamps
end
-- --------------------------------------------------------

--
-- Structure de la table `produit_liste_favoris`
--

CREATE TABLE IF NOT EXISTS `produit_liste_favoris` (
  `id_produit` int(11) NOT NULL DEFAULT '0',
  `id_liste_favoris` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_produit`,`id_liste_favoris`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `produit_liste_noire`
--

CREATE TABLE IF NOT EXISTS `produit_liste_noire` (
  `id_produit` int(11) NOT NULL DEFAULT '0',
  `id_liste_noire` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_produit`,`id_liste_noire`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `produit_liste_produits_a_acheter`
--

CREATE TABLE IF NOT EXISTS `produit_liste_produits_a_acheter` (
  `id_produit` int(11) NOT NULL DEFAULT '0',
  `id_liste_produits_a_acheter` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_produit`,`id_liste_produits_a_acheter`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `source`
--

CREATE TABLE IF NOT EXISTS `source` (
  `id_source` int(11) unsigned NOT NULL,
  `pays` varchar(40) NOT NULL,
  PRIMARY KEY (`id_source`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
Create_table(:sources) do |t|
    t.string :pays,  null: false
   t.timestamps
end

-- --------------------------------------------------------

--
-- Structure de la table `type_de_produit`
--

CREATE TABLE IF NOT EXISTS `type_de_produit` (
  `id_type_produit` int(11) unsigned NOT NULL,
  `type_de_produit` varchar(40) NOT NULL,
  PRIMARY KEY (`id_type_produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Create_table(:type_de_produits) do |t|
    t.string :type_produit,     null: false
   t.timestamps
end
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `liste_produits_a_acheter`
--
ALTER TABLE `liste_produits_a_acheter`
  ADD CONSTRAINT `liste_produits_a_acheter_ibfk_1` FOREIGN KEY (`id_liste_courses_deja_faites`) REFERENCES `liste_courses_deja_faites` (`id_liste_courses_deja_faites`);

--
-- Contraintes pour la table `magasin`
--
ALTER TABLE `magasin`
  ADD CONSTRAINT `magasin_ibfk_1` FOREIGN KEY (`id_franchise`) REFERENCES `franchise` (`id_franchise`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_4` FOREIGN KEY (`id_nfc_tag`) REFERENCES `nfc_tag` (`id_nfc_tag`),
  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`id_type_produit`) REFERENCES `type_de_produit` (`id_type_produit`),
  ADD CONSTRAINT `produit_ibfk_2` FOREIGN KEY (`id_source`) REFERENCES `source` (`id_source`),
  ADD CONSTRAINT `produit_ibfk_3` FOREIGN KEY (`id_magasin`) REFERENCES `magasin` (`id_magasin`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
