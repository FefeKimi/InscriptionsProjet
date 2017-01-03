/*competition*/

DELIMITER |
	DROP PROCEDURE IF EXISTS GET_CANDIDATS_FROM_COMP;
	create procedure GET_CANDIDATS_FROM_COMP
	(Label varchar(25))
	begin
		SELECT NomCandidat FROM PARTICIPER, CANDIDAT WHERE PARTICIPER.NumCandidat=CANDIDAT.NumCandidat AND LabelComp = Label;
		
	end;
|

DELIMITER |
	DROP PROCEDURE IF EXISTS SET_NAME_COMP;
	create procedure SET_NAME_COMP (newName varchar(25), Labe varchar(25)) 
	BEGIN
		UPDATE COMPETITION
		SET NomComp = newName
		WHERE LabelComp = Label;

	END;	
|


DELIMITER |
	DROP procedure IF EXISTS date_cloture;
	create procedure date_cloture(Label varchar(25)) 
	begin
		SELECT DateCloture FROM COMPETITION WHERE LabelComp = Label;
	end;
|

DELIMITER |
	DROP function IF EXISTS inscription_ouverte;
	create function inscription_ouverte (Label varchar(25)) returns boolean
	begin
		DECLARE DATE_COMP date;
		DECLARE RES boolean;
		SELECT `DateCloture` INTO DATE_COMP FROM `COMPETITION` WHERE `LabelComp`=Label ;
		IF DATE_COMP < NOW() 
		THEN SET RES=0;
		ELSE 
			 SET RES=1;
		END IF;
		return RES;
	end;
|

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_COMP;
	create procedure ADD_COMP
	(Label varchar(25), NomC varchar(25), DateClot date, Equipe boolean)
	begin
		insert into COMPETITION(LabelComp, NomComp, DateCloture,EnEquipe) values (Label, NomC, DateClot,Equipe) ;
		
	end;
|


DELIMITER |
	DROP PROCEDURE IF EXISTS DEL_COMP;
	create procedure DEL_COMP (Label varchar(25)) 
	BEGIN
		DELETE FROM COMPETITION
		WHERE LabelComp = Label;

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
DELIMITER |
	DROP function IF EXISTS EN_EQUIPE_COMP;
	create function EN_EQUIPE_COMP (Label varchar(25)) returns boolean 
	BEGIN
		DECLARE bool boolean;
		SELECT EnEquipe into bool FROM COMPETITION WHERE LabelComp = Label ;
		return bool;
	END;	
|

DELIMITER |
	DROP PROCEDURE IF EXISTS ADD_CANDIDAT_COMP;
	create procedure ADD_CANDIDAT_COMP (NumCan int, Label varchar(25)) 
	BEGIN	
		insert into PARTICIPER(NumCandidat,LabelComp) values (NumCan,Label);
	END;	
|