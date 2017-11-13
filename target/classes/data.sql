DROP SCHEMA public CASCADE;
CREATE TABLE currency(
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(3) NOT NULL
);
CREATE TABLE synchronize(
    id INTEGER IDENTITY PRIMARY KEY,
    currency_id INTEGER NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    date DATE NOT NULL,
    CONSTRAINT currency_id_foreign_key FOREIGN KEY (currency_id) REFERENCES currency (id)
);
CREATE INDEX fki_currency_id_foreign_key ON synchronize (currency_id);

insert into currency (id, name) values(1 ,'PLN');
insert into currency (id, name) values(2, 'USD');
insert into currency (id, name) values(3, 'EUR');