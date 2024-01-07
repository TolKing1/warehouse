CREATE ROLE guest WITH LOGIN PASSWORD 'guest';
GRANT SELECT ON ALL TABLES IN SCHEMA public TO guest;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO guest;

create table if not exists dish
(
    id       serial
            primary key,
    name     varchar(200),
    price    numeric(10, 2),
    quantity integer,
    material varchar(100),
    set      boolean
);
create table if not exists linen
(
    id        serial
            primary key,
    name      varchar(200),
    price     numeric(10, 2),
    quantity  integer,
    thickness numeric(10, 2),
    area      numeric(10, 2)
);