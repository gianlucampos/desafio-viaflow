/**
 * Author:  Gianluca
 * Created: 28/01/2021
 */

DROP TABLE IF EXISTS linhatransporte,pontotransporte;
DROP SEQUENCE IF EXISTS seqlinhatransporte,seqpontotransporte;

CREATE TABLE linhatransporte (
	id int8 NOT NULL,
	codigo varchar(255) NULL,
	nome varchar(255) NULL,
	CONSTRAINT linhatransporte_pkey PRIMARY KEY (id)
);

CREATE TABLE pontotransporte (
	id int8 NOT NULL,
	indice int8 NULL,
	latidude float8 NULL,
	longitude float8 NULL,
	linhatransporteid int8 NULL,
	CONSTRAINT pontotransporte_pkey PRIMARY KEY (id),
	CONSTRAINT fk_linhatransporte_id FOREIGN KEY (linhatransporteid) REFERENCES linhatransporte(id)
);

CREATE SEQUENCE seqpontotransporte
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;