DROP SCHEMA IF EXISTS mvp CASCADE;
CREATE SCHEMA mvp;

SET search_path TO mvp, public;

DROP TABLE IF EXISTS mvp.customers;
CREATE TABLE mvp.customers(
  id BIGSERIAL,
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
  id BIGSERIAL,
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
  id BIGSERIAL,
  title VARCHAR,
  description TEXT,
  price DECIMAL,
  PRIMARY KEY (title)
);

DROP TABLE IF EXISTS mvp.orders;
CREATE TABLE mvp.orders(
  id BIGSERIAL,
  customer_id bigint,
  master_id bigint,
  location TEXT,
  photo_before BYTEA,
  photo_after BYTEA,
  date_time timestamptz,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mvp.masters_services;
CREATE TABLE mvp.masters_services(
  master_id bigint,
  service_id bigint,
  PRIMARY KEY (master_id, service_id)
);

DROP TABLE IF EXISTS mvp.orders_services;
CREATE TABLE mvp.orders_services(
  order_id bigint,
  service_id bigint,
  PRIMARY KEY (order_id, service_id)
);