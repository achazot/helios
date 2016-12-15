INSERT INTO "USER" VALUES('admin', 'admin','admin@helios.fr', 'Rogue', 'azerty', 'Severus');


INSERT INTO "USER" VALUES('harry', 'student','harry.potter@helios.fr', 'Harry', 'azerty', 'Potter');
INSERT INTO "USER" VALUES('hermione', 'student','hermione.granger@helios.fr', 'Hermione', 'azerty', 'Granger');
INSERT INTO "USER" VALUES('ron', 'student','ron.weasley@helios.fr', 'Ron', 'azerty', 'Weasley');

INSERT INTO "USER" VALUES('albus', 'teacher','albus.dumbledore@helios.fr', 'Albus', 'azerty', 'Dumbledore' );
INSERT INTO "USER" VALUES('minerva', 'teacher','minerva.mcgonagall@helios.fr', 'Minerva', 'azerty', 'McGonagall' );

INSERT INTO "MODULE" VALUES(1, 'Theorie des Graphes', 'albus');
INSERT INTO "MODULE" VALUES(2, 'Intelligence Artificielle', 'minerva');
INSERT INTO "MODULE" VALUES(3, 'Logique', 'minerva');

				-- SUB ID , DATE , AVANCEMENT , LOGIN
INSERT INTO "SUBSCRIPTION" VALUES(1, '2016-10-23', 1, 1, 'harry');
INSERT INTO "SUBSCRIPTION" VALUES(2, '2016-09-12', 1, 2, 'harry');

INSERT INTO "SUBSCRIPTION" VALUES(3, '2016-09-10', 2, 1, 'hermione');
INSERT INTO "SUBSCRIPTION" VALUES(4, '2016-12-12', 1, 2, 'hermione');
INSERT INTO "SUBSCRIPTION" VALUES(5, '2016-01-01', 0, 3, 'hermione');

INSERT INTO "SUBSCRIPTION" VALUES(6, '2016-11-12', 0, 1, 'ron');

			-- CHAP ID , has QCM , TXT , TITLE , MOD ID
INSERT INTO "CHAPTER" VALUES(1, true, 'Les graphes tirent leur nom du fait qu’on peut les représenter par des dessins. À chaque sommet de G, on fait correspondre un point distinct du plan et on relie les points correspondant aux extrémités de chaque arête. Il existe donc une infinité de représentations d’un graphe. Les arêtes ne sont pas forcément rectilignes. Si on peut dessiner un graphe G dans le plan sans qu’aucune arête n’en coupe une autre (les arêtes ne sont pas forcément rectilignes), on dit que G est planaire.', 'Chapitre 1 : Définition', 1);

INSERT INTO "CHAPTER" VALUES(2, true, 'On appelle cycle eulérien d’un graphe G un cycle passant une et une seule fois par chacune des arêtes de G . Un graphe est dit eulérien s’il possède un cycle eulérien. On appelle chaîne eulérienne d’un graphe G une chaîne passant une et une seule fois par chacune des arêtes de G. Un graphe ne possédant que des chaînes eulériennes est semi-eulérien. Plus simplement, on peut dire qu’un graphe est eulérien (ou semi-eulérien) s’il est possible de dessiner le graphe sans lever le crayon et sans passer deux fois sur la même arête.', 'Chapitre 2 : Graphes Eurlériens', 1);

INSERT INTO "CHAPTER" VALUES(3, true, 'Le parcours en largeur est un algorithme de recherche très simple : nous examinons d’abord l’ ́etat initial, puis ses successeurs, puis les successeurs des successeurs, etc. Tous les noeuds d’une certaine profondeur sont examinés avant les noeuds de profondeur superieure. Pour implémenter cet algorithme, il suffit de placer les nouveaux noeuds systèmatiquement à la fin de la liste de noeuds à traiter', 'Chapitre 1 : Les algorithmes de recherche', 2);

INSERT INTO "CHAPTER" VALUES(4, false, 'On connaît de nombreux exemples explicites de fonctions incalculables. Le plus courant est celui du problème de l’arrêt : il n’existe pas de programme universel qui prenne n’importe quel programme en argument et qui, en temps fini, renvoie « oui » si l’exécution du programme reçu en argument finit par s’arrêter et « non » s’il ne finit pas. Un autre exemple d’une fonction non calculable, plus perturbante dans un certain sens, est celle dite du castor affairé. Il s’agit d’une fonction bien définie, ayant des valeurs pour chaque entier, mais dont on ne peut pas calculer la valeur. Gregory Chaitin a introduit un nombre Ω qui a, entre autres, la particularité d’être parfaitement défini, mais dont la suite des décimales ne peut pas être donnée par une fonction calculable.', 'Chapitre 1 : Calculabilité', 3);

INSERT INTO "QCM" VALUES(1, TRUE, '2016-10-01', '2017-10-10', 10,  20, 1);
INSERT INTO "QCM" VALUES(2, TRUE, '2016-09-23', '2017-10-10', 10,  20, 2);
INSERT INTO "QCM" VALUES(3, TRUE, '2016-10-17', '2016-10-10', 10,  20, 3);

INSERT INTO "QUESTION" VALUES (1, 10, 'Combien de représentations d’un graphe G existe-t-il ?', 1) ; 
INSERT INTO "QUESTION" VALUES (2, 5, 'Les arêtes d’un graphe sont-elles forcément rectilignes ?', 1) ;
INSERT INTO "QUESTION" VALUES (3, 5, 'On dit qu’un graphe G est planaire si...', 1) ; 

INSERT INTO "ANSWER" VALUES (1, 'Une seule', FALSE, 1) ; 
INSERT INTO "ANSWER" VALUES (2, 'Autant que le nombre de sommets qu’il contient', FALSE, 1) ; 
INSERT INTO "ANSWER" VALUES (3, 'Autant que le nombre d’arêtes qu’il contient', FALSE, 1) ; 
INSERT INTO "ANSWER" VALUES (4, 'Une infinité', TRUE, 1) ; 
INSERT INTO "ANSWER" VALUES (5, 'Oui', FALSE, 2) ; 
INSERT INTO "ANSWER" VALUES (6, 'Non', TRUE, 2) ;  
INSERT INTO "ANSWER" VALUES (7, 'Ses arêtes ne sont pas rectilignes', FALSE, 3) ; 
INSERT INTO "ANSWER" VALUES (8, 'La somme des degrés des ses sommets est égale à deux fois le nombre d’arêtes', FALSE, 3) ; 
INSERT INTO "ANSWER" VALUES (9, 'On peut le représenter sans que ses arêtes ne se croisent', TRUE, 3) ; 
INSERT INTO "ANSWER" VALUES (10, 'On peut le représenter sans lever le crayon', FALSE, 3) ; 

INSERT INTO "QUESTION" VALUES (4, 10, 'Un cycle d’un graphe G est dit eulérien quand... ', 2) ; 
INSERT INTO "QUESTION" VALUES (5, 5, 'Un graphe est eulérien si...', 2) ;
INSERT INTO "QUESTION" VALUES (6, 5, 'Comment appelle-t-on un graphe qui ne possède que des chaînes eulériennes ?', 2) ; 

INSERT INTO "ANSWER" VALUES (11, 'Il passe une et une seule fois par toutes les arêtes de G', TRUE, 4) ;
INSERT INTO "ANSWER" VALUES (12, 'Il passe au moins une fois par chacune des arêtes de G', FALSE, 4) ; 
INSERT INTO "ANSWER" VALUES (13, 'Il passe par plus de la moitié des arêtes de G', FALSE, 4) ; 
INSERT INTO "ANSWER" VALUES (14, 'Il possède un cycle eulérien', TRUE, 5) ;  
INSERT INTO "ANSWER" VALUES (15, 'Il est possible de le dessiner sans lever le crayon', FALSE, 5) ; 
INSERT INTO "ANSWER" VALUES (16, 'Un graphe semi-eulérien', TRUE, 6) ; 
INSERT INTO "ANSWER" VALUES (17, 'Un graphe eulérien total', FALSE, 6) ; 
INSERT INTO "ANSWER" VALUES (18, 'Un graphe eulérien', FALSE, 6) ; 
INSERT INTO "ANSWER" VALUES (19, 'Un graphe quelconque', FALSE, 6) ; 

INSERT INTO "QUESTION" VALUES (7, 8, 'Dans le parcours en largeur les nouveaux noeuds explorés sont placés...', 3) ; 
INSERT INTO "QUESTION" VALUES (8, 2, 'Lequel ou lesquels de ces algorithmes sont complets ?', 3) ;

INSERT INTO "ANSWER" VALUES (20, 'En tête de la liste de noeud à traîter', TRUE, 7) ;
INSERT INTO "ANSWER" VALUES (21, 'A la fin de la liste de noeud à traîter', FALSE, 7) ;
INSERT INTO "ANSWER" VALUES (22, 'En tête de la liste de noeud à traîter', FALSE, 7) ;

INSERT INTO "ANSWER" VALUES (23, 'Le parcours en profondeur', FALSE, 8) ;
INSERT INTO "ANSWER" VALUES (24, 'Le parcours en largeur', TRUE, 8) ;
INSERT INTO "ANSWER" VALUES (25, 'Aucun', FALSE, 8) ;
INSERT INTO "ANSWER" VALUES (26, 'Les deux', FALSE, 8) ;

INSERT INTO "QCMINSTANCE" VALUES(1, '2016-10-23', TRUE, 13, 2, 1, 'harry', 1) ; 
INSERT INTO "QCMINSTANCE" VALUES(2, '2016-09-28', TRUE, 19, 5, 2, 'harry', 2) ;

INSERT INTO "QCMINSTANCE" VALUES(3, '2016-10-20', TRUE, 20, 1, 1, 'hermione', 3) ; 
INSERT INTO "QCMINSTANCE" VALUES(4, '2016-10-20', TRUE, 20, 1, 2, 'hermione', 4) ;
INSERT INTO "QCMINSTANCE" VALUES(5, '2016-10-20', TRUE, 20, 1, 3, 'hermione', 5) ; 
INSERT INTO "QCMINSTANCE" VALUES(6, '2016-10-20', TRUE, 20, 1, 3, 'hermione', 3) ; 

INSERT INTO "QCMINSTANCE" VALUES(7, '2016-10-20', FALSE, 2, 8, 3, 'ron', 6) ; 
