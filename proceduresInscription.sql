procedure.sql

/*PERSONNE*/

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_PERSONNE;
	create procedure ADD_PERSONNE(NomCandidat  Varchar(25), Prenom Varchar(25),Mail Varchar(25))
	BEGIN
		DECLARE NumCand int;
		insert into CANDIDAT(NomCandidat,equipe) values (NomCandidat,0) ;
		SELECT Max(NumCandidat) into NumCand FROM CANDIDAT;
		insert into PERSONNE(Prenom, Mail, NumCandidat)  values (Prenom, Mail, NumCand) ;
	END;
|

DELIMITER |
	DROP PROCEDURE IF EXISTS GET_EQUIPE_FROM_PERS;
	create procedure GET_EQUIPE_FROM_PERS(NumCand int(25))
	BEGIN
		SELECT nomcandidat FROM CANDIDAT c, ETRE_DANS e where c.numcandidat = e.NumCandidatEquipe AND e.NumCandidatPers = NumCand;
	END;
|



DELIMITER |
	DROP PROCEDURE IF EXISTS SET_MAIL;
	create procedure SET_PERSONNE(NumCand int(25), NomCand varchar(25), Prenom varchar(25), newMail varchar(25)) 
	BEGIN
		UPDATE PERSONNE
		SET Prenom = Prenom ,Mail = newMail
		WHERE NumCandidat = NumCand;
		UPDATE CANDIDAT
		SET NomCandidat = NomCand
		WHERE NumCandidat = NumCand;
	END;	
|

DELIMITER |
	DROP PROCEDURE IF EXISTS GET_PERSONNE;
	create procedure GET_PERSONNE (NumCand int(25)) 
	BEGIN
		SELECT * FROM PERSONNE WHERE NumCandidat = NumCand;
	END;	
|


/*EQUIPE*/

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_EQUIPE;
	create procedure ADD_EQUIPE(NomCandidat varchar(25))
	begin
		insert into CANDIDAT(NomCandidat,equipe) values (NomCandidat,1) ;
	end;
|

DELIMITER |
	DROP PROCEDURE IF EXISTS GET_EQUIPE;
	create procedure GET_EQUIPE()
	BEGIN
		SELECT * FROM CANDIDAT WHERE Equipe = 1;
	END;
|

DELIMITER |
	DROP PROCEDURE IF EXISTS GET_MEMBRE_EQUIPE;
	create procedure GET_MEMBRE_EQUIPE(NumCand int(25))
	BEGIN
		SELECT e.NumCandidatPers, c.NomCandidat Prenom, Mail   FROM PERSONNE p, ETRE_DANS e, CANDIDAT c 
		WHERE e.NumCandidatPers = p.NumCandidat 
		AND e.NumCandidatEquipe = c.NumCandidat 
		AND e.NumCandidatEquipe= NumCand;
	END;
|

/*CANDIDAT*/
DELIMITER |
	DROP PROCEDURE IF EXISTS GET_CANDIDATS;
	create procedure GET_CANDIDATS()
	BEGIN
		SELECT * FROM CANDIDAT;
	END;
|
DELIMITER |
	DROP PROCEDURE IF EXISTS SET_NAME_CANDIDAT;
	create procedure SET_NAME_CANDIDAT (NumCand int(25), newName varchar(25)) 
	BEGIN
		UPDATE CANDIDAT 
		SET NomCandidat = newName
		WHERE NumCandidat = NumCand;
	END;	
|

DELIMITER |
	DROP PROCEDURE IF EXISTS DEL_CANDIDAT;
	create procedure DEL_CANDIDAT (numCanDel int(25)) 
	BEGIN
		DELETE FROM ETRE_DANS
 		WHERE NumCandidatPers = numCanDel;
 		
 		DELETE FROM ETRE_DANS
 		WHERE NumCandidatEquipe = numCanDel;

		DELETE FROM PERSONNE
		WHERE NumCandidat =numCanDel;

		DELETE FROM PARTICIPER
		WHERE NumCandidat =numCanDel;

		DELETE FROM CANDIDAT
		WHERE NumCandidat =numCanDel;
	END;	
|

DELIMITER |
	CREATE TRIGGER before_del_cand BEFORE DELETE
	ON COMPETITION FOR EACH ROW
	BEGIN
 		DELETE FROM ETRE_DANS
 		WHERE NumCandidat_Personne = Old.NumCandidat;
 		DELETE FROM ETRE_DANS
 		WHERE NumCandidat_Equipe = Old.NumCandidat;
	END;	
|


DELIMITER |
	DROP PROCEDURE IF EXISTS GET_COMP_CANDIDAT;
	create procedure GET_COMP_CANDIDAT
	(NumCand int)
	begin
		SELECT c.NumComp, NomComp, DateCloture , EnEquipe 
		FROM PARTICIPER p, COMPETITION c 
		WHERE p.numcomp = c.numcomp 
		AND NumCandidat = NumCand;	
	end;
|

/*competition*/
DELIMITER |
	DROP PROCEDURE IF EXISTS GET_CANDIDATS_FROM_COMP;
	create procedure GET_CANDIDATS_FROM_COMP(NumCompet Int(25))
	BEGIN
		SELECT c.NumCandidat, NomCandidat, Equipe FROM PARTICIPER p, CANDIDAT c WHERE p.NumCandidat=c.NumCandidat AND NumComp = NumCompet;
	END;
|

DELIMITER |
	DROP PROCEDURE IF EXISTS GET_CANDIDATS_NOT_SIGN;
	create procedure GET_CANDIDATS_NOT_SIGN(NumCompet Int(25), enEquipe boolean )
	BEGIN
		SELECT NumCandidat, NomCandidat, Equipe FROM CANDIDAT c 
		WHERE Equipe = enEquipe 
		AND NumCandidat NOT IN(SELECT NumCandidat FROM PARTICIPER P WHERE NumComp = NumCompet) 
		AND NumCandidat NOT IN(SELECT NumCandidat FROM PARTICIPER);
	END;
|






DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_COMP;
	create procedure ADD_COMP
	(NomC varchar(25), DateClot date, Equipe boolean)
	begin
		insert into COMPETITION(NomComp, DateCloture,EnEquipe) values (NomC, DateClot,Equipe) ;
	end;
|


DELIMITER |
	DROP PROCEDURE IF EXISTS DEL_COMP;
	create procedure DEL_COMP (NumCompet Int(25)) 
	BEGIN
		DELETE FROM PARTICIPER 
		WHERE NumComp = NumCompet;
		DELETE FROM COMPETITION
		WHERE NumComp = NumCompet;
	END;	
|








