procedure.sql

/*PERSONNE*/
get MailCandidat ok
get PrenomPersonne ok
set MailCandidat ok
set PrenomPersonne ok
DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_PERSONNE;
	create procedure ADD_PERSONNE
	(NumCandidat  Int(25),NomCandidat  Varchar(25),MailCandidat Varchar(25), PrenomPersonne Varchar(25))
	BEGIN

		insert into CANDIDAT(NumCandidat, NomCandidat, MailCandidat) values (NumCandidat, NomCandidat, MailCandidat) ;
		insert into PERSONNE(PrenomPersonne, NumCandidat)  values (PrenomPersonne, NumCandidat) ;
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
	DROP PROCEDURE IF EXISTS GET_MAIL;
	create procedure GET_MAIL (NumCand int(25)) 
	BEGIN
		SELECT MailCandidat
		FROM CANDIDAT
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
	DROP PROCEDURE IF EXISTS SET_MAIL_PERSONNE;
	create procedure SET_MAIL_PERSONNE (newFirstMail varchar(25), NumCand int(25)) 
	BEGIN
		UPDATE CANDIDAT 
		SET MailCandidat = newFirstMail
		WHERE NumCandidat = NumCand;

	END;	
|
/*EQUIPE*/

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_EQUIPE;
	create procedure ADD_EQUIPE
	(NumCandidat int(25), NomCandidat varchar(25), MailCandidat varchar(25))
	begin
		insert into CANDIDAT(NumCandidat,NomCandidat,MailCandidat) values (NumCandidat,NomCandidat,MailCandidat) ;
		insert into EQUIPE(NumCandidat) values (NumCandidat) ;
	end;
|

DELIMITER |
	DROP PROCEDURE IF EXISTS GET_EQUIPE;
	create procedure GET_EQUIPE()
	BEGIN

		SELECT * FROM CANDIDAT,EQUIPE WHERE CANDIDAT.NumCandidat = EQUIPE.NumCandidat  ;

	END;

|

DELIMITER |
	DROP PROCEDURE IF EXISTS GET_MEMBRE_EQUIPE;
	create procedure GET_MEMBRE_EQUIPE(NumEQUIPE int )
	BEGIN

		SELECT PERSONNE.NumCandidat, PERSONNE.PrenomPersonne, CANDIDAT.NomCandidat, CANDIDAT.MailCandidat 
		FROM PERSONNE, ETRE_DANS, CANDIDAT
		WHERE ETRE_DANS.NumCandidat_Personne = PERSONNE.NumCandidat 
		AND CANDIDAT.NumCandidat = PERSONNE.NumCandidat
		AND ETRE_DANS.NumCandidat_Equipe=NumEQUIPE  ;

	END;

|
/*CANDIDAT*/
get nom candidat à faire
get comp candidat à faire
DELIMITER |
	DROP PROCEDURE IF EXISTS DEL_CANDIDAT;
	create procedure DEL_CANDIDAT (numCanDel int(25)) 
	BEGIN
		DELETE FROM PERSONNE
		WHERE NumCandidat =numCanDel;
		DELETE FROM EQUIPE
		WHERE NumCandidat =numCanDel;
		DELETE FROM PARTICIPATION
		WHERE NumCandidat =numCanDel;
		DELETE FROM CANDIDAT
		WHERE NumCandidat =numCanDel;
		

	END;	
|

DELIMITER |
	DROP PROCEDURE IF EXISTS GET_CANDIDAT;
	create procedure GET_CANDIDAT()
	BEGIN

		SELECT * FROM CANDIDAT;

	END;

|
DELIMITER |
	DROP PROCEDURE IF EXISTS MODIF_NOM_CANDIDAT;
	create procedure MODIF_NOM_CANDIDAT (newName varchar(25), NumCand int(25)) 
	BEGIN
		UPDATE CANDIDAT 
		SET NomCandidat = newName
		WHERE NumCandidat = NumCand;

	END;	
|
DELIMITER |
	DROP PROCEDURE IF EXISTS GET_NOM_CANDIDAT;
	create procedure GET_NOM_CANDIDAT (NumCand int(25)) 
	BEGIN
		SELECT NomCandidat 
		FROM CANDIDAT 
		WHERE NumCandidat = NumCand;

	END;	
|

DELIMITER |
	DROP PROCEDURE IF EXISTS GET_COMP_CANDIDAT;
	create procedure GET_COMP_CANDIDAT
	(NumCandidat int)
	begin
		SELECT NomComp FROM PARTICIPER, COMPETITION WHERE PARTICIPER.LabelComp=COMPETITION.LabelComp AND NumCandidat =NumCandidat ;
		
	end;
|
/*competition*/
add equipe comp
add pers comp
inscription ouverte
get date cloture
remove candidat compt dans participer

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_COMP;
	create procedure ADD_COMP
	(LabelComp varchar(25), NomComp varchar(25), DateCloture date, EnEquipe boolean)
	begin
		insert into COMPETITION(LabelComp, NomComp, DateCloture,EnEquipe) values (LabelComp, NomComp, DateCloture,EnEquipe) ;
		
	end;
|


DELIMITER |
	DROP PROCEDURE IF EXISTS DEL_CCOMP;
	create procedure DEL_COMP (LabelComp varchar(25)) 
	BEGIN
		DELETE FROM COMPETITION
		WHERE LabelComp = LabelComp;

	END;	
|
DELIMITER |
	DROP PROCEDURE IF EXISTS GET_COMP;
	create procedure GET_COMP () 
	BEGIN
		SELECT * 
		FROM COMPETITION;


	END;	
|
/*participer*/

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_PARTICIPATION;
	create procedure ADD_PARTICIPATION(NumCandidat int, LabelComp varchar(25)) 
	BEGIN
		INSERT INTO PARTICIPATION(NumCandidat, LabelComp) VALUES (NumCandidat, LabelComp);
	END;
|

DELIMITER |
	DROP PROCEDURE IF EXISTS DEL_PARTICIPATION;
	create procedure DEL_PARTICIPATION(NumCandidat int, LabelComp varchar(25)) 
	BEGIN
		DELETE FROM PARTICIPER  wHERE NumCandidat=NumCandidat and LabelComp=LabelComp;
	END;

|
DELIMITER |
	DROP PROCEDURE IF EXISTS GET_PARTICIPATION;
	create procedure GET_PARTICIPATION () 
	BEGIN
		SELECT * 
		FROM PARTICIPER;
	END;	
|
DELIMITER |
	
	CREATE TRIGGER before_del_comp BEFORE DELETE
	ON COMPETITION FOR EACH ROW
	BEGIN
		
 		DELETE FROM PARTICIPER
 		WHERE LabelComp = Old.LabelComp;
	END;	
|

/*ETRE DANS*/
add membre 
delete membre

