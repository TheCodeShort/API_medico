CREATE TABLE medicos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    documentos VARCHAR(255) NOT NULL,
    especialidad VARCHAR(255) NOT NULL,
    calle VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    complemento VARCHAR(255),
    distrito VARCHAR(255) NOT NULL,
    ciudad VARCHAR(255) NOT NULL
);