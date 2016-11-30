procedure.sql


DELIMITER |
	DROP PROCEDURE IF EXISTS DEL_CANDIDAT;
	create procedure DEL_CANDIDAT (numCanDel int(25)) 
	BEGIN
		DELETE FROM PERSONNE
		WHERE NumCandidat =numCanDel;
		DELETE FROM EQUIPE
		WHERE NumCandidat =numCanDel;
		DELETE FROM CANDIDAT
		WHERE NumCandidat =numCanDel;

	END;	
|


DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_PERSONNE;
	create procedure ADD_PERSONNE
	(NumCandidat  Int(25),NomCandidat  Varchar(25),MailCandidat Varchar(25), PrenomPersonne Varchar(25), NumEquipe varchar(25))
	BEGIN

		insert into CANDIDAT(NumCandidat, NomCandidat, MailCandidat) values (NumCandidat, NomCandidat, MailCandidat) ;
		insert into PERSONNE(PrenomPersonne, NumCandidat,NumCandidat_1)  values (PrenomPersonne, NumCandidat, NumEquipe) ;

	END;

|

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
	DROP PROCEDURE IF EXISTS ADD_COMP;
	create procedure ADD_COMP
	(LabelComp varchar(25), NomComp varchar(25), DateCloture date, EnEquipe boolean)
	begin
		insert into COMPETITION(LabelComp, NomComp, DateCloture,EnEquipe) values (LabelComp, NomComp, DateCloture,EnEquipe) ;
		
	end;
|

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_COMP;
	create procedure ADD_COMP
	(LabelComp varchar(25), NomComp varchar(25), DateCloture date, EnEquipe boolean)
	begin
		insert into COMPETITION(LabelComp, NomComp, DateCloture,EnEquipe) values (LabelComp, NomComp, DateCloture,EnEquipe) ;
		
	end;
|
DELIMITER |
	DROP PROCEDURE IF EXISTS DEL_CANDIDAT;
	create procedure before_del_comp (LabelComp varchar(25)) 
	BEGIN
		DELETE FROM COMPETITION
		WHERE LabelComp = LabelComp;
		DELETE FROM PARTICIPATION
		WHERE LabelComp = LabelComp;

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
	DROP PROCEDURE IF EXISTS ADD_PARTICIPATION;
	create procedure ADD_PARTICIPATION(NumCandidat int, LabelComp varchar(25)) 
	BEGIN
		INSERT INTO PARTICIPATION(NumCandidat, LabelComp) VALUES (NumCandidat, LabelComp);
	END;
|

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_PARTICIPATION;
	create procedure ADD_PARTICIPATION(NumCandidat int, LabelComp varchar(25)) 
	BEGIN
		INSERT INTO PARTICIPATION(NumCandidat, LabelComp) VALUES (NumCandidat, LabelComp);
	END;

|
DELIMITER |
	DROP TRIGGER IF EXISTS
	CREATE TRIGGER before_del_comp BEFORE DELETE
	ON  PARTICIPATION
	BEGIN
		IF Old.NumCandidat=NumCandidat 
 		DELETE FROM PERSONNE
	END;	
|