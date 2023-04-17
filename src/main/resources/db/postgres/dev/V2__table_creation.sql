-- Table: site_construction.site_allocation

-- DROP TABLE IF EXISTS site_construction.site_allocation;

CREATE TABLE IF NOT EXISTS site_construction.site_allocation
(
    site_allocation_id bigint NOT NULL,
    client_id bigint NOT NULL,
    site_master_id bigint NOT NULL,
    CONSTRAINT site_allocation_id_pk PRIMARY KEY (site_allocation_id),
    CONSTRAINT client_id_fk FOREIGN KEY (client_id)
        REFERENCES site_construction.client_master (client_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT site_master_id_fk FOREIGN KEY (site_master_id)
        REFERENCES site_construction.site_master (site_master_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS site_construction.site_allocation
    OWNER to postgres;


-- Table: site_construction.supervisor_allocation

-- DROP TABLE IF EXISTS site_construction.supervisor_allocation;

CREATE TABLE IF NOT EXISTS site_construction.supervisor_allocation
(
    supervisor_allocation_id bigint NOT NULL,
    site_master_id bigint NOT NULL,
    CONSTRAINT supervisor_allocation_id_pk PRIMARY KEY (supervisor_allocation_id),
    CONSTRAINT site_master_id_fk FOREIGN KEY (site_master_id)
        REFERENCES site_construction.site_master (site_master_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS site_construction.supervisor_allocation
    OWNER to postgres;


-- Table: site_construction.engineer_allocation

-- DROP TABLE IF EXISTS site_construction.engineer_allocation;

CREATE TABLE IF NOT EXISTS site_construction.engineer_allocation
(
    engineer_allocation_id bigint NOT NULL,
    site_master_id bigint NOT NULL,
    CONSTRAINT engineer_allocation_id_pk PRIMARY KEY (engineer_allocation_id),
    CONSTRAINT site_master_id_fk FOREIGN KEY (site_master_id)
        REFERENCES site_construction.site_master (site_master_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS site_construction.engineer_allocation
    OWNER to postgres;