CREATE ROLE if not exists guest WITH LOGIN PASSWORD 'guest';
GRANT SELECT ON ALL TABLES IN SCHEMA public TO guest;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO guest;

create table if not exists dish
(
    id       integer default nextval('"Dish_id_seq"'::regclass) not null
        constraint "Dish_pkey"
            primary key,
    name     varchar(200),
    price    numeric(10, 2),
    quantity integer,
    material varchar(100),
    set      boolean
);
create table if not exists linen
(
    id        integer default nextval('"Linen_id_seq"'::regclass) not null
        constraint "Linen_pkey"
            primary key,
    name      varchar(200),
    price     numeric(10, 2),
    quantity  integer,
    thickness numeric(10, 2),
    area      numeric(10, 2)
);