-- Table: public.users

DROP TABLE IF EXISTS public.users CASCADE;

CREATE TABLE public.users
(
    user_id character varying(255) NOT NULL,
    user_created timestamp(4) without time zone,
    user_email character varying(255),
    user_isactive bigint,
    user_last_login timestamp(4) without time zone,
    user_modified timestamp(4) without time zone,
    user_name character varying(255),
    user_password character varying(255),
    user_token character varying(255),
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

-- Table: public.phones

DROP TABLE IF EXISTS public.phones;

CREATE TABLE public.phones
(
    phone_id character varying(255) NOT NULL,
    phone_city_code character varying(255),
    phone_country_code character varying(255),
    phone_number character varying(255),
    user_id character varying(255) NOT NULL,
    CONSTRAINT phones_pkey PRIMARY KEY (phone_id),
    CONSTRAINT fkmg6d77tgqfen7n1g763nvsqe3 FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);