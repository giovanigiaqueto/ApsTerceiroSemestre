
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
