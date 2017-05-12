

ALTER TABLE reservation
ADD CONSTRAINT fk_id_membre FOREIGN KEY (id_membre) REFERENCES membre (id_membre),
ADD CONSTRAINT FK_id_place FOREIGN KEY (id_place) REFERENCES place (id_place);

ALTER TABLE liste_attente
ADD CONSTRAINT fk_id_membre2 FOREIGN KEY (id_membre) REFERENCES membre (id_membre);
 
ALTER TABLE membre_oubli_mdp
ADD CONSTRAINT fk_id_membre3 FOREIGN KEY (id_membre) REFERENCES membre (id_membre);