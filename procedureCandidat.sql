/*CANDIDAT*/

DELIMITER | ok
	DROP PROCEDURE IF EXISTS DEL_CANDIDAT;
	create procedure DEL_CANDIDAT (numCanDel int(25)) 
	BEGIN
	
		DELETE FROM ETRE_DANS
 		WHERE NumCandidat_Personne = numCanDel;
 		DELETE FROM ETRE_DANS
 		WHERE NumCandidat_Equipe = numCanDel;
		DELETE FROM PERSONNE
		WHERE NumCandidatPers =numCanDel;
		DELETE FROM PARTICIPER
		WHERE NumCandidat =numCanDel;
		DELETE FROM CANDIDAT
		WHERE NumCandidat =numCanDel;
		

	END;	
|
DELIMITER | ok
	
	CREATE TRIGGER before_del_cand BEFORE DELETE
	ON CANDIDAT FOR EACH ROW
	BEGIN
		
 		DELETE FROM ETRE_DANS
 		WHERE NumCandidatPers = Old.NumCandidat;
 		DELETE FROM PARTICIPER
 		WHERE NumCandidat = Old.NumCandidat;
	END;	
|
DELIMITER | ok
	DROP PROCEDURE IF EXISTS GET_CANDIDAT;
	create procedure GET_CANDIDAT()
	BEGIN

		SELECT * FROM CANDIDAT;

	END;

|

DELIMITER | ok
	DROP PROCEDURE IF EXISTS GET_NAME_CANDIDAT;
	create procedure GET_NAME_CANDIDAT(NumCandidat int)
	BEGIN

		SELECT * FROM CANDIDAT
		WHERE NumCandidat = NumCandidat;

	END;

|

DELIMITER | ok
	DROP PROCEDURE IF EXISTS SET_NAME_CANDIDAT;
	create procedure SET_NAME_CANDIDAT (newName varchar(25), NumCand int(25)) 
	BEGIN
		UPDATE CANDIDAT 
		SET NomCandidat = newName
		WHERE NumCandidat = NumCand;

	END;	
|


DELIMITER | ok
	DROP PROCEDURE IF EXISTS GET_COMP_CANDIDAT;
	create procedure GET_COMP_CANDIDAT
	(NumCandidat int)
	begin
		SELECT NomComp 
		FROM PARTICIPER, COMPETITION 
		WHERE PARTICIPER.LabelComp=COMPETITION.LabelComp 
		AND NumCandidat =NumCandidat ;
		
	end;
|