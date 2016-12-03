INSERT INTO "USER" VALUES('admin', 'admin','admin@helios.fr', 'doe', 'azerty', 'john');
INSERT INTO "USER" VALUES('thomas', 'student','thomas@helios.fr', 'bailly', 'azerty', 'thomas');
INSERT INTO "USER" VALUES('jazzy', 'student','jazy@helios.fr', 'ngosso', 'azerty', 'jason');
INSERT INTO "USER" VALUES('sheitan', 'student','sheitan@helios.fr', 'colas', 'azerty', 'korlan');
INSERT INTO "USER" VALUES('bertho', 'teacher','teacherBertho@helios.fr', 'berthome', 'azerty', 'pascal' );
INSERT INTO "USER" VALUES('clemou', 'teacher','teacherClemou@helios.fr', 'clemente', 'azerty', 'patrice' );
INSERT INTO "USER" VALUES('benji', 'teacher','inria@helios.fr', 'benjamin', 'azerty', 'nguyen' );
INSERT INTO "MODULE" VALUES(1, 'theorie des graphes', 'bertho');
INSERT INTO "MODULE" VALUES(2, 'intelligence artificielle', 'clemou');
INSERT INTO "MODULE" VALUES(3, 'logique', 'clemou');
INSERT INTO "MODULE" VALUES(4, 'SGBDR', 'benji');
INSERT INTO "MODULE" VALUES(5, 'OOP', 'benji');
INSERT INTO "SUBSCRIPTION" VALUES(1, NULL, 1, 'thomas');
INSERT INTO "SUBSCRIPTION" VALUES(2, NULL, 2, 'thomas');
INSERT INTO "SUBSCRIPTION" VALUES(3, NULL, 3, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(4, NULL, 4, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(5, NULL, 5, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(6, NULL, 2, 'jazzy');

