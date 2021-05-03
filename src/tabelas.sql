CREATE TABLE Usuario(
                        idUsuario SERIAL PRIMARY KEY,
                        nomeUsuario text NOT NULL,
                        CPFUsuario text NOT NULL UNIQUE,
                        telefoneUsuario text NOT NULL,
                        sexoUsuario text NOT NULL,
                        enderecoUsuario text NOT NULL,
                        emailUsuario text NOT NULL,
                        senhaUsuario text
);

CREATE TABLE Cliente(
                        idCliente SERIAL PRIMARY KEY,
                        nomeCliente text NOT NULL,
                        CPFCliente text NOT NULL UNIQUE,
                        telefoneCliente text NOT NULL,
                        sexoCliente text NOT NULL,
                        enderecoCliente text NOT NULL,
                        emailCliente text NOT NULL
);

CREATE TABLE Categoria(
                          idCategoria SERIAL PRIMARY KEY,
                          nomeCategoria text not null,
                          descricaoCategoria text not null
);

CREATE TABLE Livro(
                      idLivro SERIAL PRIMARY KEY,
                      nomeLivro text not null,
                      ISBNLivro text not null,
                      autorLivro text not null default 'Autor Desconhecido',
                      edicaoLivro text not null,
                      dataLancamentoLivro date,
                      idLivroCategoria integer not null REFERENCES Categoria(idCategoria),
                      estoqueLivro integer
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
                       descricaoMulta text,
                       valorMulta numeric
);