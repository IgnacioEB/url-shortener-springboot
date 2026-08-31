CREATE TABLE IF NOT EXISTS urls (
    codigo VARCHAR(10) PRIMARY KEY,
    url TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT now()
);
