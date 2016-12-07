INSERT INTO "USER" VALUES('admin', 'admin','admin@helios.fr', 'doe', 'azerty', 'john');
INSERT INTO "USER" VALUES('thomas', 'student','thomas@helios.fr', 'bailly', 'azerty', 'thomas');
INSERT INTO "USER" VALUES('jazzy', 'student','jazy@helios.fr', 'ngosso', 'azerty', 'jason');
INSERT INTO "USER" VALUES('sheitan', 'student','sheitan@helios.fr', 'colas', 'azerty', 'korlan');
INSERT INTO "USER" VALUES('bertho', 'teacher','teacherBertho@helios.fr', 'berthome', 'azerty', 'pascal' );
INSERT INTO "USER" VALUES('clemou', 'teacher','teacherClemou@helios.fr', 'clemente', 'azerty', 'patrice' );
INSERT INTO "USER" VALUES('benji', 'teacher','inria@helios.fr', 'benjamin', 'azerty', 'nguyen' );
INSERT INTO "MODULE" VALUES(91, 'Theorie des Graphes', 'bertho');
INSERT INTO "MODULE" VALUES(92, 'Intelligence Artificielle', 'clemou');
INSERT INTO "MODULE" VALUES(93, 'Logique', 'clemou');
INSERT INTO "MODULE" VALUES(94, 'SGBDR', 'benji');
INSERT INTO "MODULE" VALUES(95, 'OOP', 'benji');
INSERT INTO "SUBSCRIPTION" VALUES(191, '1970-01-01', 1, 91, 'thomas');
INSERT INTO "SUBSCRIPTION" VALUES(192, '2001-01-01', 0, 92, 'thomas');
INSERT INTO "SUBSCRIPTION" VALUES(193, '2010-01-01', 0, 91, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(194, '1970-01-01', 0, 94, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(195, '1970-01-01', 0, 95, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(196, '1970-01-01', 0, 91, 'jazzy');
INSERT INTO "CHAPTER" VALUES(100, false, 'La théorie des graphes c est très très bien', 'Chapitre 1 : Introduction', 91);
INSERT INTO "CHAPTER" VALUES(101, true, 'Le lemme de l etoile', 'Chapitre 2 : Choses serieuses', 91);
INSERT INTO "QCM" VALUES(200, TRUE, '1970-01-01', '2020-01-01', 20, null, 20, 101);
INSERT INTO "QUESTION" VALUES (20, 10, 'Quelle est la taille du zizi?', 200) ; 
INSERT INTO "QUESTION" VALUES (30, 2, 'Quelle est la taille du fesse?', 200) ;
INSERT INTO "QUESTION" VALUES (40, 2, 'Quelle est la taille du amour?', 200) ; 
INSERT INTO "ANSWER" VALUES (100, '10cm', FALSE, 20) ; 
INSERT INTO "ANSWER" VALUES (101, '14cm', FALSE, 20) ; 
INSERT INTO "ANSWER" VALUES (102, '70cm', FALSE, 20) ; 
INSERT INTO "ANSWER" VALUES (103, 'normal', TRUE, 20) ; 
INSERT INTO "ANSWER" VALUES (104, 'moyen', FALSE, 30) ; 
INSERT INTO "ANSWER" VALUES (105, 'tres petit', TRUE, 30) ; 
INSERT INTO "ANSWER" VALUES (106, 'petit', TRUE, 30) ; 
INSERT INTO "ANSWER" VALUES (108, 'gros', FALSE, 30) ; 
INSERT INTO "ANSWER" VALUES (109, 'nul', FALSE, 40) ; 
INSERT INTO "ANSWER" VALUES (110, 'gigantesque', TRUE, 40) ; 
INSERT INTO "ANSWER" VALUES (111, 'petit chose', TRUE, 40) ; 
INSERT INTO "ANSWER" VALUES (112, 'réponse fausse', FALSE, 40) ; 
INSERT INTO "QCMINSTANCE" VALUES (520, '2015-05-05', true, 20, 5,  200, 'thomas', 191);
