CREATE TABLE Usuario(
                        idUsuario SERIAL PRIMARY KEY,
                        nomeUsuario varchar(40) NOT NULL,
                        CPFUsuario char(11) NOT NULL UNIQUE,
                        telefoneUsuario char(13) NOT NULL,
                        sexoUsuario varchar(12) NOT NULL,
                        enderecoUsuario varchar(60) NOT NULL,
                        emailUsuario varchar(256) NOT NULL,
                        senhaUsuario varchar(128)
);

CREATE TABLE Cliente(
                        idCliente SERIAL PRIMARY KEY,
                        nomeCliente varchar(40) NOT NULL,
                        CPFCliente char(11) NOT NULL UNIQUE,
                        telefoneCliente char(13) NOT NULL,
                        sexoCliente varchar(12) NOT NULL,
                        enderecoCliente varchar(60) NOT NULL,
                        emailCliente varchar(256) NOT NULL
);

CREATE TABLE Categoria(
                          idCategoria SERIAL PRIMARY KEY,
                          nomeCategoria varchar(30) not null,
                          descricaoCategoria varchar(255) not null
);

CREATE TABLE Livro(
                      idLivro SERIAL PRIMARY KEY,
                      nomeLivro varchar(40) not null,
                      ISBNLivro char(13) not null,
                      autorLivro varchar(40) not null default 'Autor Desconhecido',
                      edicaoLivro varchar(40) not null,
                      dataLancamentoLivro date,
                      idLivroCategoria integer not null REFERENCES Categoria(idCategoria),
                      estoqueLivro integer,
                      locacaoLivro integer,
                      precoLivro numeric,
                      sinopseLivro varchar(2048)
);

CREATE TABLE Emprestimo(
                           idEmprestimo SERIAL PRIMARY KEY,
                           idEmprestimoCliente integer not null REFERENCES Cliente(idCliente),
                           idEmprestimoLivro integer not null references Livro(idLivro),
                           idEmprestimoUsuario integer not null references Usuario(idUsuario),
                           dataEmprestimo date not null,
                           dataDevolucao date not null
);

CREATE TABLE Multa (
                       idMulta SERIAL PRIMARY KEY,
                       idMultaCliente INTEGER REFERENCES Cliente(idCliente),
                       idMultaEmprestimo INTEGER not null references Emprestimo(idEmprestimo),
                       descricaoMulta varchar(255),
                       valorMulta numeric
);
