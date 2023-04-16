-- Table: site_construction.site_master

-- DROP TABLE IF EXISTS site_construction.site_master;

CREATE TABLE IF NOT EXISTS site_construction.site_master
(
    site_master_id bigint NOT NULL,
    site_name character varying COLLATE pg_catalog."default" NOT NULL,
    site_address character varying COLLATE pg_catalog."default" NOT NULL,
    site_pin character varying COLLATE pg_catalog."default" NOT NULL,
    site_ward character varying COLLATE pg_catalog."default" NOT NULL,
    site_city character varying COLLATE pg_catalog."default" NOT NULL,
    site_photo oid,
    CONSTRAINT site_master_pkey PRIMARY KEY (site_master_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS site_construction.site_master
    OWNER to postgres;

------

