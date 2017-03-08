
/*ETRE DANS*/

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_MEMBRE;
	create procedure ADD_MEMBRE (Num_Equipe int,Num_Personne int) 
	BEGIN
		DECLARE etreUneEquipe boolean;
		SELECT Equipe INTO etreUneEquipe FROM CANDIDAT WHERE NumCandidat = Num_Equipe; 
		IF(etreUneEquipe) THEN
			insert into ETRE_DANS(NumCandidatEquipe,NumCandidatPers) VALUES (Num_Equipe,Num_Personne); 
		
		END IF;
	END;	
|

DELIMITER |
	DROP PROCEDURE IF EXISTS DEL_MEMBRE;
	create procedure DEL_MEMBRE (Num_Equipe int,Num_Personne int) 
	BEGIN
		DELETE FROM ETRE_DANS WHERE NumCandidatEquipe =Num_Equipe AND  NumCandidatPers= Num_Personne; 
	END;	
|

/* CREER UN TRIGGER POUR SAVOIR SI LE NUMEQUIPE EN PARAMETRE DANS GET_MEMBRE_EQUIPE CORRESPOND A UNE EQUIPE */

DELIMITER |
	DROP TRIGGER IF EXISTS AFTER_ADD_MEMBRE_EQUIPE;
	CREATE TRIGGER AFTER_ADD_MEMBRE_EQUIPE AFTER insert
	BEGIN

		SELECT PERSONNE.NumCandidat, PERSONNE.PrenomPersonne, CANDIDAT.NomCandidat 
		FROM PERSONNE, ETRE_DANS, CANDIDAT
		WHERE ETRE_DANS.NumCandidatPers = PERSONNE.NumCandidat 
		AND CANDIDAT.NumCandidat = PERSONNE.NumCandidat
		AND ETRE_DANS.NumCandidatEquipe=NumEQUIPE  ;

	END;

|
