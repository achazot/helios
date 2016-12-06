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
INSERT INTO "SUBSCRIPTION" VALUES(91, NULL, 91, 'thomas');
INSERT INTO "SUBSCRIPTION" VALUES(92, NULL, 92, 'thomas');
INSERT INTO "SUBSCRIPTION" VALUES(93, NULL, 93, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(94, NULL, 94, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(95, NULL, 95, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(96, NULL, 92, 'jazzy');
INSERT INTO "CHAPTER" VALUES(100, false, 'La théorie des graphes c est très très bien', 'Chapitre 1 : Introduction', 91);
INSERT INTO "CHAPTER" VALUES(101, true, 'Le lemme de l etoile', 'Chapitre 2 : Choses serieuses', 91);
INSERT INTO "QCM" VALUES(200, TRUE, null, null, 20, null, 20, 101);
INSERT INTO "QUESTION" VALUES (20, 10, 'Quelle est la taille du zizi?', 200) ; 
INSERT INTO "QUESTION" VALUES (30, 2, 'Quelle est la taille du fesse?', 200) ;
INSERT INTO "QUESTION" VALUES (40, 2, 'Quelle est la taille du amour?', 200) ; 