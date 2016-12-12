
/*ETRE DANS*/

	DROP PROCEDURE IF EXISTS ADD_MEMBRE;
	create procedure ADD_MEMBRE (Num_Equipe int,Num_Personne int) 
	BEGIN
		insert into ETRE_DANS(NumCandidat_Equipe,NumCandidat_Personne) VALUES (Num_Equipe,Num_Personne); 
	END;	
|

DELIMITER |
	DROP PROCEDURE IF EXISTS DEL_MEMBRE;
	create procedure DEL_MEMBRE (Num_Equipe int,Num_Personne int) 
	BEGIN
		DELETE FROM ETRE_DANS wHERE NumCandidat_Equipe =Num_Equipe AND  NumCandidat_Personne= Num_Personne; 
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
