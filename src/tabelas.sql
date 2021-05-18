
CREATE TABLE Usuario(
    id_usuario               SERIAL          PRIMARY KEY,
    nome_usuario             varchar(40)     NOT NULL,
    cpf_suario              char(11)         NOT NULL UNIQUE,
    telefone_usuario         char(13)        NOT NULL,
    sexo_usuario             varchar(12)     NOT NULL,
    endereco_usuario         varchar(60)     NOT NULL,
    email_usuario            varchar(256)    NOT NULL,
    senha_suario            varchar(128)
);

CREATE TABLE Cliente(
    id_cliente               SERIAL          PRIMARY KEY,
    nome_cliente             varchar(40)     NOT NULL,
    cpf_cliente              char(11)        NOT NULL UNIQUE,
    telefone_cliente         char(13)        NOT NULL,
    sexo_cliente             varchar(12)     NOT NULL,
    endereco_cliente         varchar(60)     NOT NULL,
    email_cliente            varchar(256)    NOT NULL
);

CREATE TABLE Categoria(
    id_categoria             SERIAL          PRIMARY KEY,
    nome_categoria           varchar(30)     NOT NULL UNIQUE,
    descricao_categoria      varchar(255)    NOT NULL
);

CREATE TABLE Livro(
    id_livro                 SERIAL          PRIMARY KEY,
    nome_livro               varchar(40)     NOT NULL,
    isbn_livro               char(13)        NOT NULL,
    autor_livro              varchar(40)     NOT NULL DEFAULT 'Autor Desconhecido',
    editora_livro            varchar(40)     NOT NULL,
    edicao_livro             integer         NOT NULL,
    data_lancamento_livro     date           NOT NULL,
    nome_livro_categoria      varchar(30)    NOT NULL REFERENCES Categoria(nome_categoria),
    estoque_livro            integer         NOT NULL,
    locacao_livro            integer         NOT NULL,
    paginas_livro            integer         NOT NULL,
    preco_livro              numeric         NOT NULL,
    sinopse_livro            varchar(8192)   NOT NULL
);

CREATE TABLE Exemplar(
    id_exemplar              SERIAL          PRIMARY KEY,
    id_exemplarLivro         integer         NOT NULL REFERENCES Livro(id_livro),
    esta_alocado             boolean         NOT NULL,
    data_obtencao            date            NOT NULL
);

CREATE TABLE Emprestimo(
    id_emprestimo            SERIAL          PRIMARY KEY,
    id_emprestimo_cliente     integer        NOT NULL REFERENCES Cliente(id_cliente),
    id_emprestimo_exemplar    integer        NOT NULL REFERENCES Exemplar(id_exemplar),
    id_emprestimo_usuario     integer        NOT NULL REFERENCES Usuario(id_usuario),
    data_emprestimo          date            NOT NULL,
    data_devolucao           date            NOT NULL
);

CREATE TABLE Multa (
    id_multa                 SERIAL          PRIMARY KEY,
    id_multa_cliente          integer        NOT NULL REFERENCES Cliente(id_cliente),
    id_multa_emprestimo       integer        NOT NULL REFERENCES Emprestimo(id_emprestimo),
    descricao_multa          varchar(255)    DEFAULT 'Não efetuou a devolução no prazo',
    valor_multa              numeric         NOT NULL,
    pagamento_multa          boolean         NOT NULL
);
