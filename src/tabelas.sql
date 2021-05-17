
CREATE TABLE Usuario(
    idUsuario               SERIAL          PRIMARY KEY,
    nomeUsuario             varchar(40)     NOT NULL,
    CPFUsuario              char(11)        NOT NULL UNIQUE,
    telefoneUsuario         char(13)        NOT NULL,
    sexoUsuario             varchar(12)     NOT NULL,
    enderecoUsuario         varchar(60)     NOT NULL,
    emailUsuario            varchar(256)    NOT NULL,
    senhaUsuario            varchar(128)
);

CREATE TABLE Cliente(
    idCliente               SERIAL          PRIMARY KEY,
    nomeCliente             varchar(40)     NOT NULL,
    CPFCliente              char(11)        NOT NULL UNIQUE,
    telefoneCliente         char(13)        NOT NULL,
    sexoCliente             varchar(12)     NOT NULL,
    enderecoCliente         varchar(60)     NOT NULL,
    emailCliente            varchar(256)    NOT NULL
);

CREATE TABLE Categoria(
    idCategoria             SERIAL          PRIMARY KEY,
    nomeCategoria           varchar(30)     NOT NULL UNIQUE,
    descricaoCategoria      varchar(255)    NOT NULL
);

CREATE TABLE Livro(
    idLivro                 SERIAL          PRIMARY KEY,
    nomeLivro               varchar(40)     NOT NULL,
    ISBNLivro               char(13)        NOT NULL,
    autorLivro              varchar(40)     NOT NULL DEFAULT 'Autor Desconhecido',
    editoraLivro            varchar(40)     NOT NULL,
    edicaoLivro             integer         NOT NULL,
    dataLancamentoLivro     date            NOT NULL,
    nomeLivroCategoria      varchar(30)     NOT NULL REFERENCES Categoria(nomeCategoria),
    estoqueLivro            integer         NOT NULL,
    locacaoLivro            integer         NOT NULL,
    paginasLivro            integer         NOT NULL,
    precoLivro              numeric         NOT NULL,
    sinopseLivro            varchar(8192)   NOT NULL
);

CREATE TABLE Exemplar(
    idExemplar              SERIAL          PRIMARY KEY,
    idExemplarLivro         integer         NOT NULL REFERENCES Livro(idLivro),
    estaAlocado             boolean         NOT NULL,
    dataObtencao            date            NOT NULL
);

CREATE TABLE Emprestimo(
    idEmprestimo            SERIAL          PRIMARY KEY,
    idEmprestimoCliente     integer         NOT NULL REFERENCES Cliente(idCliente),
    idEmprestimoExemplar    integer         NOT NULL REFERENCES Exemplar(idExemplar),
    idEmprestimoUsuario     integer         NOT NULL REFERENCES Usuario(idUsuario),
    dataEmprestimo          date            NOT NULL,
    dataDevolucao           date            NOT NULL
);

CREATE TABLE Multa (
    idMulta                 SERIAL          PRIMARY KEY,
    idMultaCliente          integer         NOT NULL REFERENCES Cliente(idCliente),
    idMultaEmprestimo       integer         NOT NULL REFERENCES Emprestimo(idEmprestimo),
    descricaoMulta          varchar(255)    DEFAULT 'Não efetuou a devolução no prazo',
    valorMulta              numeric         NOT NULL
);
