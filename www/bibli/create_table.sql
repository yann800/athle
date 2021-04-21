CREATE TABLE Document (
	id			int(11)		NOT NULL AUTO_INCREMENT,
	titre		VARCHAR(32)	NOT NULL,
	auteur		VARCHAR(32)	NOT NULL,	
	annee		INT(4)		,
	type		VARCHAR(16)	NOT NULL,
	theme		VARCHAR(32),
	bibli		VARCHAR(16),
	commentaire	VARCHAR(512),
	PRIMARY KEY (id)
);
