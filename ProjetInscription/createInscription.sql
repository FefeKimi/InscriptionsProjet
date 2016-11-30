-- Database: Inscription2017

DROP DATABASE Inscription2017;

CREATE DATABASE Inscription2017;
use Inscription2017;


#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Candidat
#------------------------------------------------------------

CREATE TABLE Candidat(
        NumCandidat  Int NOT NULL ,
        NomCandidat  Varchar (25) ,
        MailCandidat Varchar (25) ,
        PRIMARY KEY (NumCandidat )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Equipe
#------------------------------------------------------------

CREATE TABLE Equipe(
        NumCandidat Int NOT NULL ,
        PRIMARY KEY (NumCandidat )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Personne
#------------------------------------------------------------

CREATE TABLE Personne(
        PrenomPersonne Varchar (25) ,
        NumCandidat    Int NOT NULL ,
        NumCandidat_1  Int ,
        PRIMARY KEY (NumCandidat )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Competition
#------------------------------------------------------------

CREATE TABLE Competition(
        LabelComp   Varchar (25) NOT NULL ,
        NomComp     Varchar (25) ,
        DateCloture Date ,
        EnEquipe    Bool ,
        PRIMARY KEY (LabelComp )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Participer
#------------------------------------------------------------

CREATE TABLE Participer(
        NumCandidat Int NOT NULL ,
        LabelComp   Varchar (25) NOT NULL ,
        PRIMARY KEY (NumCandidat ,LabelComp )
)ENGINE=InnoDB;

CREATE TABLE Erreur (
    id TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    erreur VARCHAR(255) UNIQUE);
}



)ENGINE=InnoDB;
ALTER TABLE Equipe ADD CONSTRAINT FK_Equipe_NumCandidat FOREIGN KEY (NumCandidat) REFERENCES Candidat(NumCandidat);
ALTER TABLE Personne ADD CONSTRAINT FK_Personne_NumCandidat FOREIGN KEY (NumCandidat) REFERENCES Candidat(NumCandidat);
ALTER TABLE Personne ADD CONSTRAINT FK_Personne_NumCandidat_1 FOREIGN KEY (NumCandidat_1) REFERENCES Equipe(NumCandidat);
ALTER TABLE Participer ADD CONSTRAINT FK_Participer_NumCandidat FOREIGN KEY (NumCandidat) REFERENCES Candidat(NumCandidat);
ALTER TABLE Participer ADD CONSTRAINT FK_Participer_LabelComp FOREIGN KEY (LabelComp) REFERENCES Competition(LabelComp);