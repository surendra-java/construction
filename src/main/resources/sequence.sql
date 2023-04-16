-- SEQUENCE: site_construction.site_master_sequence

-- DROP SEQUENCE IF EXISTS site_construction.site_master_sequence;

CREATE SEQUENCE IF NOT EXISTS site_construction.site_master_sequence
    INCREMENT 1
    START 2
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE site_construction.site_master_sequence
    OWNER TO postgres;
------

-- SEQUENCE: site_construction.engineer_master_sequence

-- DROP SEQUENCE IF EXISTS site_construction.engineer_master_sequence;

CREATE SEQUENCE IF NOT EXISTS site_construction.engineer_master_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE site_construction.engineer_master_sequence
    OWNER TO postgres;
-------

-- SEQUENCE: site_construction.supervisor_master_sequence

-- DROP SEQUENCE IF EXISTS site_construction.supervisor_master_sequence;

CREATE SEQUENCE IF NOT EXISTS site_construction.supervisor_master_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE site_construction.supervisor_master_sequence
    OWNER TO postgres;