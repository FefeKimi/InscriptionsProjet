/*PERSONNE*/

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_PERSONNE;
	create procedure ADD_PERSONNE
	(NomCandidat  Varchar(25),MailCandidat Varchar(25), PrenomPersonne Varchar(25))
	BEGIN

		insert into CANDIDAT(NumCandidat, NomCandidat) values (null, NomCandidat) ;
		insert into PERSONNE(PrenomPersonne, NumCandidat, MailPers)  values (PrenomPersonne, NumCandidat, MailPers) ;
	END;

|
DELIMITER |
	DROP PROCEDURE IF EXISTS GET_PERSONNE;
	create procedure GET_PERSONNE()
	BEGIN

		SELECT PERSONNE.NumCandidat,NomCandidat,PrenomPersonne,MailCandidat FROM PERSONNE,CANDIDAT WHERE PERSONNE.NumCandidat = CANDIDAT.NumCandidat  ;

	END;

|
DELIMITER |
	DROP PROCEDURE IF EXISTS GET_PRENOM_PERSONNE;
	create procedure GET_PRENOM_PERSONNE (NumCand int(25)) 
	BEGIN
		SELECT PrenomPersonne
		FROM PERSONNE
		WHERE NumCandidat = NumCand;

	END;	
|

DELIMITER |
	DROP PROCEDURE IF EXISTS SET_PRENOM_PERSONNE;
	create procedure SET_PRENOM_PERSONNE (newFirstName varchar(25), NumCand int(25)) 
	BEGIN
		UPDATE PERSONNE 
		SET PrenomPersonne = newFirstName
		WHERE NumCandidat = NumCand;

	END;	
|
DELIMITER |
	DROP PROCEDURE IF EXISTS GET_MAIL;
	create procedure GET_MAIL (NumCand int(25)) 
	BEGIN
		SELECT MailPers
		FROM PERSONNE
		WHERE NumCandidat = NumCand;

	END;	
|
DELIMITER |
	DROP PROCEDURE IF EXISTS SET_MAIL_PERSONNE;
	create procedure SET_MAIL_PERSONNE (newFirstMail varchar(25), NumCand int(25)) 
	BEGIN
		UPDATE CANDIDAT 
		SET MailCandidat = newFirstMail
		WHERE NumCandidat = NumCand;

	END;	
|