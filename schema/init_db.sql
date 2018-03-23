-- Table: public.categorie

-- DROP TABLE public.categorie;

CREATE TABLE public.categorie
(
    id bigint NOT NULL,
    libelle character varying(20) COLLATE pg_catalog."default" NOT NULL,
    ordre integer NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.categorie OWNER to postgres;


INSERT INTO public.categorie(id, libelle, ordre) VALUES (1, 'Plats', 1);
INSERT INTO public.categorie(id, libelle, ordre) VALUES (2, 'Desserts', 2);


-- Table: public.recette

-- DROP TABLE public.recette;

CREATE TABLE public.recette
(
    id bigint NOT NULL,
    libelle character varying(100) COLLATE pg_catalog."default" NOT NULL,
    ordre integer NOT NULL,
    id_categorie bigint NOT NULL,
    CONSTRAINT recettes_pkey PRIMARY KEY (id),
    CONSTRAINT fk_recette_categorie FOREIGN KEY (id_categorie)
        REFERENCES public.categorie (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.recette OWNER to postgres;


-- Table: public.ingredient

-- DROP TABLE public.ingredient;

CREATE TABLE public.ingredient
(
    id bigint NOT NULL,
    libelle character varying(200) COLLATE pg_catalog."default" NOT NULL,
    ordre integer NOT NULL,
    id_recette bigint NOT NULL,
    CONSTRAINT ingredients_pkey PRIMARY KEY (id),
    CONSTRAINT fk_ingredient_recette FOREIGN KEY (id_recette)
        REFERENCES public.recette (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.ingredient OWNER to postgres;


-- Table: public.etape

-- DROP TABLE public.etape;

CREATE TABLE public.etape
(
    id bigint NOT NULL,
    libelle character varying(500) COLLATE pg_catalog."default" NOT NULL,
    ordre integer NOT NULL,
    id_recette bigint NOT NULL,
    CONSTRAINT etapes_pkey PRIMARY KEY (id),
    CONSTRAINT fk_etape_recette FOREIGN KEY (id_recette)
        REFERENCES public.recette (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.etape OWNER to postgres;

