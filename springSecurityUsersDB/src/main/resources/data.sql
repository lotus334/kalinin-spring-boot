INSERT INTO t_user(id, c_username)
VALUES (1, 'dbuser');

INSERT INTO t_user_password(id_user, c_password)
VALUES (1, '{noop}password');

INSERT INTO t_user_authority(id_user, c_authority)
VALUES
    (1, 'ROLE_USER'),
    (1, 'ROLE_DB_USER');