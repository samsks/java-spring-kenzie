INSERT INTO "users" ("name", "cpf", "email", "password", "type")
VALUES
    ('Samuel', '12345678900', 'sam@teste.com.br', 'aA@12345', 'COMMON' ),
    ('Vieira', '12345678901', 'vieira@teste.com.br', 'aA@12345', 'SELLER' );

INSERT INTO "transactions" ("payer_id", "payee_id", "value")
VALUES
    ('1', '2', 10.5);


