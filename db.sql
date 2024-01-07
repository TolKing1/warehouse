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
-- Insert data into the dish table
INSERT INTO dish (name, price, quantity, material, set)
VALUES
    ('plate', 12.99, 10, 'ceramic', true),
    ('bowl', 9.99, 15, 'glass', false),
    ('cutlery_set', 14.50, 8, 'stainless_steel', true),
    ('plastic_tumblers', 7.25, 20, 'plastic', false),
    ('serving_platter', 18.75, 12, 'porcelain', true);

-- Insert data into the linen table
INSERT INTO linen (name, price, quantity, thickness, area)
VALUES
    ('luxury_tablecloth', 24.99, 5, 0.5, 10.0),
    ('soft_napkin', 19.50, 8, 0.7, 15.5),
    ('table_runner', 15.75, 12, 0.6, 12.0),
    ('placemats', 30.00, 6, 0.8, 18.0),
    ('tea_towels', 22.99, 10, 0.4, 8.5);

