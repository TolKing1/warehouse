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
    ('Premium Dinner Plate', 12.99, 10, 'Ceramic', true),
    ('Glass Salad Bowl', 9.99, 15, 'Glass', false),
    ('Stainless Steel Cutlery Set', 14.50, 8, 'Stainless Steel', true),
    ('Reusable Plastic Tumblers', 7.25, 20, 'Plastic', false),
    ('Elegant Porcelain Serving Platter', 18.75, 12, 'Porcelain', true);

-- Insert data into the linen table
INSERT INTO linen (name, price, quantity, thickness, area)
VALUES
    ('Luxury Tablecloth', 24.99, 5, 0.5, 10.0),
    ('Soft Napkin Set', 19.50, 8, 0.7, 15.5),
    ('Durable Table Runner', 15.75, 12, 0.6, 12.0),
    ('High-Quality Placemats', 30.00, 6, 0.8, 18.0),
    ('Embroidered Tea Towels', 22.99, 10, 0.4, 8.5);
