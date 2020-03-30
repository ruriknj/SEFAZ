-- Scripts para criação do projeto -- banco: HSQLDB

CREATE TABLE pessoa (
   nome VARCHAR(100) NOT NULL,
   cpf VARCHAR(20) NOT NULL,
   idade INT NOT NULL,
   senha VARCHAR(20) NOT NULL,
   sexo VARCHAR(20) NOT NULL,
   PRIMARY KEY (cpf) 
);

CREATE TABLE endereco (
   id INTEGER IDENTITY PRIMARY KEY,
   numero INT NOT NULL,
   rua VARCHAR(100) NOT NULL,
   complemento VARCHAR(100) NOT NULL,
   cpf_pessoa VARCHAR(100) NOT NULL,
);

ALTER TABLE ENDERECO
ADD FOREIGN KEY (cpf_pessoa) 
REFERENCES pessoa(cpf);

