DROP DATABASE parking2;
CREATE DATABASE parking2;

use parking2; 

CREATE TABLE membre(
	id_membre  INT AUTO_INCREMENT ,
	nom VARCHAR (20) NOT NULL,
	prenom VARCHAR (20) NOT NULL,
	dateNaiss date NOT NULL,
	rue VARCHAR (50) NOT NULL,
	code_postal INT (5) NOT NULL,
	ville VARCHAR (20) NOT NULL,
	email VARCHAR (50) NOT NULL,
	pseudo VARCHAR (20) NOT NULL,
	mdp VARCHAR(10) NOT NULL,
	admin BOOLEAN ,
	valide BOOLEAN,
    bannis BOOLEAN,
	CONSTRAINT PRIMARY KEY (id_membre)
);

CREATE TABLE place(
	id_place INT AUTO_INCREMENT,
	libelle_place VARCHAR(3),
	CONSTRAINT PRIMARY KEY (id_place)
); 


CREATE TABLE reservation(
	id_res INT AUTO_INCREMENT,
	date_debut date,
	date_fin date,
	id_membre  INT,
	id_place INT,
	PRIMARY KEY (id_res)
);

 CREATE TABLE Liste_attente(
 	id_liste INT AUTO_INCREMENT,
 	rang INT NOT NULL,
 	id_membre INT,
 	PRIMARY KEY (id_liste)
 );

 CREATE TABLE membre_oubli_mdp(
 	id_liste_membre INT AUTO_INCREMENT,
 	id_membre INT,
 	PRIMARY KEY (id_liste_membre)
 );

INSERT INTO membre(nom, prenom, dateNaiss,rue, code_postal, ville, email, pseudo, mdp, admin, valide,bannis) 
VALUES ('Fervil', 'Darwin', '1995-01-10','15 rue arbres de la foret', '60008', 'Amiens', 'df@hotmail.fr', 'darwinoux', 'mdp', 0, 1,0);
INSERT INTO  membre(nom, prenom, dateNaiss,rue, code_postal, ville, email, pseudo, mdp, admin, valide,bannis) 
VALUES ('Bamba', 'Anais', '1995-01-10','15 rue arbres de la foret', '60008', 'Amiens', 'anais@hotmail.fr', 'anais', 'mdp', 0, 1,0);
INSERT INTO membre(nom, prenom, dateNaiss,rue, code_postal, ville, email, pseudo, mdp, admin, valide,bannis) 
VALUES ('DOUX', 'Jesus','1995-01-10', '1 rue du paradis', '77777', 'Inconnu', 'doux.jesus@paradis.cl', 'djesus', 'mdp', 0, 1,0);

INSERT into place(libelle_place) VALUES('01');
INSERT into place(libelle_place) VALUES('02');
INSERT into place(libelle_place) VALUES('03');
INSERT into place(libelle_place) VALUES('04');
INSERT into place(libelle_place) VALUES('05');
INSERT into Liste_attente(rang,id_membre) VALUES('1','3');