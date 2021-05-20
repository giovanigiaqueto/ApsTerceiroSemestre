
CREATE TABLE Usuario(
    id_usuario               SERIAL         PRIMARY KEY,
    nome_usuario            varchar(40)     NOT NULL,
    cpf_suario              char(11)        NOT NULL UNIQUE,
    telefone_usuario        char(13)        NOT NULL,
    sexo_usuario            varchar(12)     NOT NULL,
    endereco_usuario        varchar(60)     NOT NULL,
    email_usuario           varchar(256)    NOT NULL,
    senha_suario            varchar(128),
    ativo                   boolean         NOT NULL DEFAULT 'true'
);

CREATE TABLE Cliente(
    id_cliente               SERIAL          PRIMARY KEY,
    nome_cliente             varchar(40)     NOT NULL,
    cpf_cliente              char(11)        NOT NULL UNIQUE,
    telefone_cliente         char(13)        NOT NULL,
    sexo_cliente             varchar(12)     NOT NULL,
    endereco_cliente         varchar(60)     NOT NULL,
    email_cliente            varchar(256)    NOT NULL,
    ativo                    boolean         NOT NULL DEFAULT 'true'
);

CREATE TABLE Categoria(
    id_categoria             SERIAL          PRIMARY KEY,
    nome_categoria           varchar(30)     NOT NULL UNIQUE,
    descricao_categoria      varchar(255)    NOT NULL,
    ativo                    boolean         NOT NULL DEFAULT 'true'
);

CREATE TABLE Livro(
    id_livro                 SERIAL          PRIMARY KEY,
    nome_livro               varchar(40)     NOT NULL,
    isbn_livro               char(13)        NOT NULL,
    autor_livro              varchar(40)     NOT NULL DEFAULT 'Autor Desconhecido',
    editora_livro            varchar(40)     NOT NULL,
    edicao_livro             integer         NOT NULL,
    data_lancamento_livro    date            NOT NULL,
    nome_livro_categoria     varchar(30)     NOT NULL REFERENCES Categoria(nome_categoria),
    estoque_livro            integer         NOT NULL,
    locacao_livro            integer         NOT NULL,
    paginas_livro            integer         NOT NULL,
    preco_livro              numeric         NOT NULL,
    sinopse_livro            varchar(8192)   NOT NULL,
    ativo                    boolean         NOT NULL DEFAULT 'true'
);

CREATE TABLE Exemplar(
    id_exemplar              SERIAL          PRIMARY KEY,
    id_exemplarLivro         integer         NOT NULL REFERENCES Livro(id_livro),
    esta_alocado             boolean         NOT NULL,
    data_obtencao            date            NOT NULL,
    ativo                    boolean         NOT NULL DEFAULT 'true'
);

CREATE TABLE Emprestimo(
    id_emprestimo            SERIAL          PRIMARY KEY,
    id_emprestimo_cliente     integer        NOT NULL REFERENCES Cliente(id_cliente),
    id_emprestimo_exemplar    integer        NOT NULL REFERENCES Exemplar(id_exemplar),
    id_emprestimo_usuario     integer        NOT NULL REFERENCES Usuario(id_usuario),
    data_emprestimo           date           NOT NULL,
    data_devolucao            date           NOT NULL,
    ativo                     boolean        NOT NULL DEFAULT 'true'
);

CREATE TABLE Multa (
    id_multa                 SERIAL          PRIMARY KEY,
    id_multa_cliente        integer         NOT NULL REFERENCES Cliente(id_cliente),
    id_multa_emprestimo     integer         NOT NULL REFERENCES Emprestimo(id_emprestimo),
    descricao_multa         varchar(255)    DEFAULT 'Não efetuou a devolução no prazo',
    valor_multa             numeric         NOT NULL,
    pagamento_multa         boolean         NOT NULL,
    ativo                   boolean         NOT NULL DEFAULT 'true'
);

/*
TODO:
    criar triggers nas tabelas que impeçam de desativar registros
    caso existam registros ativos que dependem desses (evitar
    registros ativos referenciando registros inativos)
*/

CREATE OR REPLACE PROCEDURE desativar_usuario(ID integer)
    AS
    $$
    BEGIN
        UPDATE usuario
        SET ativo='false'
	WHERE usuario.id_usuario = ID;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE desativar_cliente(ID integer)
    AS
    $$
    BEGIN
        UPDATE cliente
        SET ativo='false'
	WHERE cliente.id_cliente = ID;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE desativar_categoria(ID integer)
    AS
    $$
    BEGIN
        UPDATE categoria
        SET ativo='false'
	WHERE categoria.id_categoria = ID;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE desativar_livro(ID integer)
    AS
    $$
    BEGIN
        UPDATE livro
        SET ativo='false'
	WHERE livro.id_livro = ID;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE desativar_exemplar(ID integer)
    AS
    $$
    BEGIN
        UPDATE exemplar
        SET ativo='false'
	WHERE exemplar.id_exemplar = ID;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE desativar_emprestimo(ID integer)
    AS
    $$
    BEGIN
        UPDATE emprestimo
        SET ativo='false'
	WHERE emprestimo.id_emprestimo = ID;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE desativar_multa(ID integer)
    AS
    $$
    BEGIN
        UPDATE multa
        SET ativo='false'
	WHERE multa.id_multa = ID;
    END;
    $$
LANGUAGE plpgsql;

-- Usuario
CREATE OR REPLACE FUNCTION fn_impede_desativa_usuario()
RETURNS trigger AS
$$
DECLARE
	qtd_emp_true INTEGER;
BEGIN
	IF(new.ativo = 'false') THEN
		qtd_emp_true := (SELECT COUNT(*) FROM (SELECT *
		FROM Emprestimo e
		JOIN Usuario u ON (u.id_usuario = e.id_emprestimo_usuario)
		WHERE u.id_usuario = new.id_usuario
		AND e.ativo = 'true') AS a);
		
		IF(qtd_emp_true > 0) THEN
			RETURN NULL;
		END IF;
	END IF;
	
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER tg_impede_desativa_usuario
BEFORE UPDATE ON Usuario
FOR EACH ROW EXECUTE PROCEDURE fn_impede_desativa_usuario();

-- Emprestimo
CREATE OR REPLACE FUNCTION fn_impede_desativa_emprestimo()
RETURNS trigger AS
$$
DECLARE
	qtd_multa_true INTEGER;
BEGIN
	IF(new.ativo = 'false') THEN
		qtd_multa_true := (SELECT COUNT(*) FROM (SELECT *
		FROM Emprestimo e
		JOIN Multa m ON (e.id_emprestimo = m.id_multa_emprestimo)
		WHERE e.id_emprestimo = new.id_emprestimo
		AND m.ativo = 'true') AS a);
		
		IF(qtd_multa_true > 0) THEN
			RETURN NULL;
		END IF;
	END IF;
	
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER tg_impede_desativa_emprestimo
BEFORE UPDATE ON Emprestimo
FOR EACH ROW EXECUTE PROCEDURE fn_impede_desativa_emprestimo();

-- Exemplar
CREATE OR REPLACE FUNCTION fn_impede_desativa_exemplar()
RETURNS trigger AS
$$
BEGIN
	IF(new.ativo = 'false') THEN
		IF(old.esta_alocado = 'true') THEN
			RETURN NULL;
		END IF;
	END IF;
	
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER tg_impede_desativa_exemplar
BEFORE UPDATE ON Exemplar
FOR EACH ROW EXECUTE PROCEDURE fn_impede_desativa_exemplar();

-- Categoria
CREATE OR REPLACE FUNCTION fn_impede_desativa_categoria()
RETURNS trigger AS
$$
DECLARE
	qtd_livros_categoria INTEGER;
BEGIN
	IF(new.ativo = 'false') THEN
		qtd_livros_categoria := (SELECT COUNT(*) FROM (SELECT *
		FROM Livro l
		JOIN Categoria c ON (l.nome_livro_categoria = c.nome_categoria)
		WHERE c.id_categoria = new.id_categoria
		AND l.nome_livro_categoria = new.nome_categoria) AS a);
		
		IF(qtd_livros_categoria > 0) THEN
			RETURN NULL;
		END IF;
	END IF;
	
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER tg_impede_desativa_categoria
BEFORE UPDATE ON Categoria
FOR EACH ROW EXECUTE PROCEDURE fn_impede_desativa_categoria();

-- Livro
CREATE OR REPLACE FUNCTION fn_impede_desativa_livro()
RETURNS trigger AS
$$
DECLARE
	qtd_exemplares INTEGER;
BEGIN
	IF(new.ativo = 'false') THEN
		qtd_exemplares := (SELECT COUNT(*) FROM (SELECT *
		FROM Exemplar exem
		JOIN livro l ON (l.id_livro = exem.id_exemplarlivro)
		WHERE l.id_livro = new.id_livro
		AND exem.id_exemplarlivro = new.id_livro) AS a);
		
		IF(qtd_exemplares > 0) THEN
			RETURN NULL;
		END IF;
	END IF;
	
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER tg_impede_desativa_livro
BEFORE UPDATE ON Livro
FOR EACH ROW EXECUTE PROCEDURE fn_impede_desativa_livro();

-- Cliente
CREATE OR REPLACE FUNCTION fn_impede_desativa_cliente()
RETURNS trigger AS
$$
DECLARE
	qtd_emp_true INTEGER;
	qtd_multas_nao_pagas INTEGER;
BEGIN
	IF(new.ativo = 'false') THEN
		qtd_emp_true := (SELECT COUNT(*) FROM (SELECT *
		FROM Emprestimo e
		JOIN Usuario u ON (u.id_usuario = e.id_emprestimo_usuario)
		WHERE u.id_usuario = new.id_usuario
		AND e.ativo = 'true') AS a);
		
		qtd_multas_nao_pagas := (SELECT COUNT(*) FROM (SELECT *
		FROM Multa m
		JOIN Cliente c ON (c.id_cliente = m.id_multa_cliente)
		WHERE c.id_cliente = new.id_cliente
		AND m.pagamento_multa = 'true') AS a);
		
		IF((qtd_emp_true > 0) OR (qtd_multas_nao_pagas > 0)) THEN
			RETURN NULL;
		END IF;
	END IF;
	
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER tg_impede_desativa_cliente
BEFORE UPDATE ON Cliente
FOR EACH ROW EXECUTE PROCEDURE fn_impede_desativa_cliente();

/*
// código salvo caso seja necessário de novo

CREATE OR REPLACE FUNCTION ativa_flag_usuario()
    RETURNS trigger AS
    $$
    BEGIN
        UPDATE Usuario
        SET ativo=false;
        RETURN NULL;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ativa_flag_cliente()
    RETURNS trigger AS
    $$
    BEGIN
        UPDATE Cliente
        SET ativo=false;
        RETURN NULL;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ativa_flag_categoria()
    RETURNS trigger AS
    $$
    BEGIN
        UPDATE Categoria
        SET ativo=false;
        RETURN NULL;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ativa_flag_livro()
    RETURNS trigger AS
    $$
    BEGIN
        UPDATE Livro
        SET ativo=false;
        RETURN NULL;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ativa_flag_exemplar()
    RETURNS trigger AS
    $$
    BEGIN
        UPDATE Exemplar
        SET ativo=false;
        RETURN NULL;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ativa_flag_emprestimo()
    RETURNS trigger AS
    $$
    BEGIN
        UPDATE Emprestimo
        SET ativo=false;
        RETURN NULL;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ativa_flag_multa()
    RETURNS trigger AS
    $$
    BEGIN
        UPDATE Multa
        SET ativo=false;
        RETURN NULL;
    END;
    $$
LANGUAGE plpgsql;

CREATE TRIGGER trigger_ativa_flag_usuario
AFTER INSERT ON Usuario
FOR EACH ROW EXECUTE PROCEDURE ativa_flag_usuario();

CREATE TRIGGER trigger_ativa_flag_cliente
AFTER INSERT ON Cliente
FOR EACH ROW EXECUTE PROCEDURE ativa_flag_cliente();

CREATE TRIGGER trigger_ativa_flag_categoria
AFTER INSERT ON Categoria
FOR EACH ROW EXECUTE PROCEDURE ativa_flag_categoria();

CREATE TRIGGER trigger_ativa_flag_livro
AFTER INSERT ON Livro
FOR EACH ROW EXECUTE PROCEDURE ativa_flag_livro();

CREATE TRIGGER trigger_ativa_flag_exemplar
AFTER INSERT ON Exemplar
FOR EACH ROW EXECUTE PROCEDURE ativa_flag_exemplar();

CREATE TRIGGER trigger_ativa_flag_emprestimo
AFTER INSERT ON Emprestimo
FOR EACH ROW EXECUTE PROCEDURE ativa_flag_emprestimo();

CREATE TRIGGER trigger_ativa_flag_multa
AFTER INSERT ON Multa
FOR EACH ROW EXECUTE PROCEDURE ativa_flag_multa();
*/
