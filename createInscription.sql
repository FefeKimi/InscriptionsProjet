#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------
DROP DATABASE inscription2017;
CREATE DATABASE inscription2017;
USE inscription2017;
#------------------------------------------------------------
# Table: Candidat
#------------------------------------------------------------

CREATE TABLE Candidat(
        NumCandidat Int NOT NULL AUTO_INCREMENT,
        NomCandidat Varchar (25) ,
        Equipe Boolean,
        PRIMARY KEY (NumCandidat )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Personne
#------------------------------------------------------------

CREATE TABLE Personne(
        Prenom Varchar (25) ,
        Mail       Varchar (25) ,
        NumCandidat    Int NOT NULL ,
        PRIMARY KEY (NumCandidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Competition
#------------------------------------------------------------

CREATE TABLE Competition(
        NumComp   Int (25) NOT NULL AUTO_INCREMENT,
        NomComp     Varchar (25) ,
        DateCloture Date ,
        EnEquipe    Boolean ,
        PRIMARY KEY (NumComp)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Participer
#------------------------------------------------------------

CREATE TABLE Participer(
        NumCandidat Int NOT NULL ,
        NumComp   Int (25) NOT NULL  ,
        PRIMARY KEY (NumCandidat ,NumComp )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ETRE DANS
#------------------------------------------------------------

CREATE TABLE ETRE_DANS(
        NumCandidatEquipe   Int NOT NULL ,
        NumCandidatPers Int NOT NULL ,
        PRIMARY KEY (NumCandidatEquipe ,NumCandidatPers )
)ENGINE=InnoDB;

ALTER TABLE Personne ADD CONSTRAINT FK_Personne_NumCandidat_Pers FOREIGN KEY (NumCandidat) REFERENCES Candidat(NumCandidat);
ALTER TABLE Participer ADD CONSTRAINT FK_Participer_NumCandidat FOREIGN KEY (NumCandidat) REFERENCES Candidat(NumCandidat);
ALTER TABLE Participer ADD CONSTRAINT FK_Participer_NumComp FOREIGN KEY (NumComp) REFERENCES Competition(NumComp);
ALTER TABLE ETRE_DANS ADD CONSTRAINT FK_ETRE_DANS_NumCandidat_Equipe FOREIGN KEY (NumCandidatEquipe) REFERENCES Candidat(NumCandidat);
ALTER TABLE ETRE_DANS ADD CONSTRAINT FK_ETRE_DANS_NumCandidat_Pers FOREIGN KEY (NumCandidatPers) REFERENCES Personne(NumCandidat);
