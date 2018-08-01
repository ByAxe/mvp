DROP SCHEMA IF EXISTS mvp CASCADE;
CREATE SCHEMA mvp;

SET search_path TO mvp, public;

DROP TABLE IF EXISTS mvp.customers;
CREATE TABLE mvp.customers(
  id BIGSERIAL UNIQUE,
  login VARCHAR,
  password VARCHAR,
  name VARCHAR ,
  phone VARCHAR ,
  email VARCHAR ,
  gender VARCHAR ,
  age SMALLINT,
  photo BYTEA,
  PRIMARY KEY (login, password)
);

DROP TABLE IF EXISTS mvp.masters;
CREATE TABLE mvp.masters(
  id BIGSERIAL UNIQUE ,
  login VARCHAR,
  password VARCHAR,
  name VARCHAR ,
  phone VARCHAR ,
  email VARCHAR ,
  gender VARCHAR ,
  age SMALLINT,
  photo BYTEA,
  active BOOLEAN,
  busy BOOLEAN,
  rank DECIMAL,
  PRIMARY KEY (login, password)
);

DROP TABLE IF EXISTS mvp.services;
CREATE TABLE mvp.services(
  id BIGSERIAL UNIQUE ,
  title VARCHAR,
  description TEXT,
  price DECIMAL,
  PRIMARY KEY (title)
);

DROP TABLE IF EXISTS mvp.orders;
CREATE TABLE mvp.orders(
  id BIGSERIAL UNIQUE,
  customer_id bigint REFERENCES mvp.customers (id),
  master_id bigint REFERENCES mvp.masters (id),
  location TEXT,
  photo_before BYTEA,
  photo_after BYTEA,
  date_time timestamptz,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mvp.masters_services;
CREATE TABLE mvp.masters_services(
  master_id bigint REFERENCES mvp.masters (id),
  service_id bigint REFERENCES mvp.services (id),
  PRIMARY KEY (master_id, service_id)
);

DROP TABLE IF EXISTS mvp.orders_services;
CREATE TABLE mvp.orders_services(
  order_id bigint REFERENCES mvp.orders (id),
  service_id bigint REFERENCES mvp.services (id),
  PRIMARY KEY (order_id, service_id)
);