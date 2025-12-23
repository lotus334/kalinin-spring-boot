CREATE TABLE t_user (
    id INT PRIMARY KEY,
    c_username VARCHAR NOT NULL UNIQUE
);

CREATE TABLE t_user_password (
    id SERIAL PRIMARY KEY,
    id_user INT NOT NULL UNIQUE REFERENCES t_user(id),
    c_password TEXT
);

CREATE TABLE t_user_authority (
    id SERIAL PRIMARY KEY, -- SERIAL/AUTO_INCREMENT @GeneratedValue(strategy = GenerationType.IDENTITY) плох в кластере,
                            --    но быстрее чем SEQUENCE
    id_user INT NOT NULL REFERENCES t_user(id),
    c_authority VARCHAR NOT NULL
);