DROP TABLE IF EXISTS Image;

CREATE TABLE Image(
	id serial,
	name varchar,
	type varchar,
	data bytea
);