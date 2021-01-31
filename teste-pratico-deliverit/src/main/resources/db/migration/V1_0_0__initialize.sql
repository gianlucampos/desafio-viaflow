/**
 * Author:  Gianluca
 * Created: 28/01/2021
 */

DROP TABLE IF EXISTS linhatransporte,itinerario,pontotransporte;
DROP SEQUENCE IF EXISTS seqlinhatransporte,seqitinerario,seqpontotransporte;

CREATE TABLE linhatransporte (
	id int8 NOT NULL,
	codigo varchar(255) NULL,
	nome varchar(255) NULL,
	CONSTRAINT linhatransporte_pkey PRIMARY KEY (id)
);


CREATE TABLE itinerario (
	id int8 NOT NULL,
	linhaid int8 NULL,
	CONSTRAINT itinerario_pkey PRIMARY KEY (id),
	CONSTRAINT fk_itinerario_linhaid FOREIGN KEY (linhaid) REFERENCES linhatransporte(id)
);

CREATE TABLE pontotransporte (
	id int8 NOT NULL,
	latidude float8 NULL,
	longitude float8 NULL,
	itinerarioid int8 NULL,
	CONSTRAINT pontotransporte_pkey PRIMARY KEY (id),
	CONSTRAINT fk_pontotransporte_itinerarioid FOREIGN KEY (itinerarioid) REFERENCES itinerario(id)
);


CREATE SEQUENCE seqitinerario
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE seqlinhatransporte
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE seqpontotransporte
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;