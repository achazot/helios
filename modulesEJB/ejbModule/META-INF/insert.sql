INSERT INTO "USER" VALUES('admin', 'admin','admin@helios.fr', 'doe', 'azerty', 'john');
INSERT INTO "USER" VALUES('thomas', 'student','thomas@helios.fr', 'bailly', 'azerty', 'thomas');
INSERT INTO "USER" VALUES('jazzy', 'student','jazy@helios.fr', 'ngosso', 'azerty', 'jason');
INSERT INTO "USER" VALUES('sheitan', 'student','sheitan@helios.fr', 'colas', 'azerty', 'korlan');
INSERT INTO "USER" VALUES('bertho', 'teacher','teacherBertho@helios.fr', 'berthome', 'azerty', 'pascal' );
INSERT INTO "USER" VALUES('clemou', 'teacher','teacherClemou@helios.fr', 'clemente', 'azerty', 'patrice' );
INSERT INTO "USER" VALUES('benji', 'teacher','inria@helios.fr', 'benjamin', 'azerty', 'nguyen' );
INSERT INTO "MODULE" VALUES(91, 'theorie des graphes', 'bertho');
INSERT INTO "MODULE" VALUES(92, 'intelligence artificielle', 'clemou');
INSERT INTO "MODULE" VALUES(93, 'logique', 'clemou');
INSERT INTO "MODULE" VALUES(94, 'SGBDR', 'benji');
INSERT INTO "MODULE" VALUES(95, 'OOP', 'benji');
INSERT INTO "SUBSCRIPTION" VALUES(91, NULL, 91, 'thomas');
INSERT INTO "SUBSCRIPTION" VALUES(92, NULL, 92, 'thomas');
INSERT INTO "SUBSCRIPTION" VALUES(93, NULL, 93, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(94, NULL, 94, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(95, NULL, 95, 'sheitan');
INSERT INTO "SUBSCRIPTION" VALUES(96, NULL, 92, 'jazzy');

