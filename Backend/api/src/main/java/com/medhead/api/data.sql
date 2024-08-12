CREATE TABLE HOSPITAL(
                         id int auto_increment,
                         name VARCHAR(250),
                         address VARCHAR(500),
                         gps VARCHAR(250),
                         number_bed INT,
                         number_free_bed INT,
                         PRIMARY KEY(id)
);

CREATE TABLE SPECIALIZATION(
                               id int auto_increment,
                               libelle VARCHAR(250),
                               libgroup VARCHAR(250),
                               PRIMARY KEY(id)
);

CREATE TABLE SPECIALIZATION_HOSPITAL(
   id_HOSPITAL INT,
   id_SPECIALIZATION INT,
   PRIMARY KEY(id_HOSPITAL, id_SPECIALIZATION),
   FOREIGN KEY(id_HOSPITAL) REFERENCES HOSPITAL(id),
   FOREIGN KEY(id_SPECIALIZATION) REFERENCES SPECIALIZATION(id)
);

INSERT INTO HOSPITAL(id, name, address, gps, number_bed, number_free_bed) VALUES
('1', 'St Thomas Hospital', 'Westminster Bridge Rd, Lambeth, London SE1 7EH', '51.4995,-0.1180', 300, 150),
('2', 'Royal London Hospital', 'Whitechapel Rd, Whitechapel, London E1 1BB', '51.5196,-0.0597', 500, 250),
('3', 'John Radcliffe Hospital', 'Headley Way, Headington, Oxford OX3 9DU', '51.7608,-1.2169', 400, 200),
('4', 'Queen Elizabeth Hospital Birmingham', 'Mindelsohn Way, Edgbaston, Birmingham B15 2GW', '52.4557,-1.9341', 600, 300),
('5', 'Manchester Royal Infirmary', 'Oxford Rd, Manchester M13 9WL', '53.4631,-2.2310', 350, 175),
('6', 'Royal Victoria Infirmary', 'Queen Victoria Rd, Newcastle upon Tyne NE1 4LP', '54.9784,-1.6183', 250, 125),
('7', 'Addenbrooke''s Hospital', 'Hills Rd, Cambridge CB2 0QQ', '52.1736,0.1352', 450, 225),
('8', 'Edinburgh Royal Infirmary', '51 Little France Crescent, Old Dalkeith Rd, Edinburgh EH16 4SA', '55.9266,-3.1532', 380, 190),
('9', 'Queen''s Medical Centre', 'Derby Rd, Nottingham NG7 2UH', '52.9396,-1.1830', 300, 150),
('10', 'Southampton General Hospital', 'Tremona Rd, Southampton SO16 6YD', '50.9355,-1.3952', 420, 210);
INSERT INTO SPECIALIZATION(id, libelle, libgroup) VALUES
('1', 'Anesthésie', 'Anesthésie'),
('2', 'Soins intensifs', 'Anesthésie'),
('3', 'Oncologie clinique', 'Oncologie clinique'),
('4', 'Spécialités dentaires supplémentaires', 'Groupe dentaire'),
('5', 'Radiologie dentaire et maxillo-faciale', 'Groupe dentaire'),
('6', 'Endodontie', 'Groupe dentaire'),
('7', 'Chirurgie buccale et maxillo-faciale', 'Groupe dentaire'),
('8', 'Pathologie buccale et maxillo-faciale', 'Groupe dentaire'),
('9', 'Médecine buccale', 'Groupe dentaire'),
('10', 'Chirurgie buccale', 'Groupe dentaire'),
('11', 'Orthodontie', 'Groupe dentaire'),
('12', 'Dentisterie pédiatrique', 'Groupe dentaire'),
('13', 'Parodontie', 'Groupe dentaire'),
('14', 'Prosthodontie', 'Groupe dentaire'),
('15', 'Dentisterie restauratrice', 'Groupe dentaire'),
('16', 'Dentisterie de soins spéciaux', 'Groupe dentaire'),
('17', 'Médecine d''urgence', 'Médecine d''urgence'),
('18', 'Médecine interne de soins aigus', 'Groupe de médecine générale'),
('19', 'Allergie', 'Groupe de médecine générale'),
('20', 'Médecine audiovestibulaire', 'Groupe de médecine générale'),
('21', 'Cardiologie', 'Groupe de médecine générale'),
('22', 'Génétique clinique', 'Groupe de médecine générale'),
('23', 'Neurophysiologie clinique', 'Groupe de médecine générale'),
('24', 'Pharmacologie clinique et thérapeutique', 'Groupe de médecine générale'),
('25', 'Dermatologie', 'Groupe de médecine générale'),
('26', 'Endocrinologie et diabète sucré', 'Groupe de médecine générale'),
('27', 'Gastroentérologie', 'Groupe de médecine générale'),
('28', 'Médecine générale (interne)', 'Groupe de médecine générale'),
('29', 'Médecine générale', 'Groupe de médecine générale'),
('30', 'Médecine générale (GP) 6 mois', 'Groupe de médecine générale'),
('31', 'Médecine génito-urinaire', 'Groupe de médecine générale'),
('32', 'Médecine gériatrique', 'Groupe de médecine générale'),
('33', 'Maladies infectieuses', 'Groupe de médecine générale'),
('34', 'Oncologie médicale', 'Groupe de médecine générale'),
('35', 'Ophtalmologie médicale', 'Groupe de médecine générale'),
('36', 'Neurologie', 'Groupe de médecine générale'),
('37', 'Médecine du travail', 'Groupe de médecine générale'),
('38', 'Autre', 'Groupe de médecine générale'),
('39', 'Médecine palliative', 'Groupe de médecine générale'),
('40', 'Médecine de réadaptation', 'Groupe de médecine générale'),
('41', 'Médecine rénale', 'Groupe de médecine générale'),
('42', 'Médecine respiratoire', 'Groupe de médecine générale'),
('43', 'Rhumatologie', 'Groupe de médecine générale'),
('44', 'Médecine du sport et de l''exercice', 'Groupe de médecine générale'),
('45', 'Cardiologie pédiatrique', 'Groupe pédiatrique'),
('46', 'Pédiatrie', 'Groupe pédiatrique'),
('47', 'Pathologie chimique', 'Groupe de pathologie'),
('48', 'Neuropathologie diagnostique', 'Groupe de pathologie'),
('49', 'Histopathologie médico-légale', 'Groupe de pathologie'),
('50', 'Pathologie générale', 'Groupe de pathologie'),
('51', 'Hématologie', 'Groupe de pathologie'),
('52', 'Histopathologie', 'Groupe de pathologie'),
('53', 'Immunologie', 'Groupe de pathologie'),
('54', 'Microbiologie médicale', 'Groupe de pathologie'),
('55', 'Pathologie pédiatrique et périnatale', 'Groupe de pathologie'),
('56', 'Virologie', 'Groupe de pathologie'),
('57', 'Service de santé communautaire dentaire', 'Groupe Pronostics et gestion de la santé/Santé communautaire'),
('58', 'Service de santé communautaire médicale', 'Groupe Pronostics et gestion de la santé/Santé communautaire'),
('59', 'Santé publique dentaire', 'Groupe Pronostics et gestion de la santé/Santé communautaire'),
('60', 'Pratique de l’art dentaire', 'Groupe Pronostics et gestion de la santé/Santé communautaire'),
('61', 'Psychiatrie infantile et adolescente', 'Groupe de psychiatrie'),
('62', 'Psychiatrie légale', 'Groupe de psychiatrie'),
('63', 'Psychiatrie générale', 'Groupe de psychiatrie'),
('64', 'Psychiatrie de la vieillesse', 'Groupe de psychiatrie'),
('65', 'Psychiatrie des troubles d''apprentissage', 'Groupe de psychiatrie'),
('66', 'Psychothérapie', 'Groupe de psychiatrie'),
('67', 'Radiologie clinique', 'Groupe de radiologie'),
('68', 'Médecine nucléaire', 'Groupe de radiologie'),
('69', 'Chirurgie cardiothoracique', 'Groupe chirurgical'),
('70', 'Chirurgie générale', 'Groupe chirurgical'),
('71', 'Neurochirurgie', 'Groupe chirurgical'),
('72', 'Ophtalmologie', 'Groupe chirurgical'),
('73', 'Otolaryngologie', 'Groupe chirurgical'),
('74', 'Chirurgie pédiatrique', 'Groupe chirurgical'),
('75', 'Chirurgie plastique', 'Groupe chirurgical'),
('76', 'Traumatologie et chirurgie orthopédique', 'Groupe chirurgical'),
('77', 'Urologie', 'Groupe chirurgical'),
('78', 'Chirurgie vasculaire', 'Groupe chirurgical');
INSERT INTO SPECIALIZATION_HOSPITAL(ID_HOSPITAL, ID_SPECIALIZATION) VALUES
('1', '1'),
('1', '2'),
('1', '3'),
('2', '1'),
('2', '2'),
('2', '3'),
('3', '1'),
('3', '2'),
('3', '3'),
('4', '1'),
('4', '2'),
('4', '3'),
('5', '1'),
('5', '2'),
('5', '3'),
('6', '1'),
('6', '2'),
('6', '3'),
('7', '1'),
('7', '2'),
('7', '3'),
('8', '1'),
('8', '2'),
('8', '3'),
('9', '1'),
('9', '2'),
('9', '3'),
('10', '1'),
('10', '2'),
('10', '3');