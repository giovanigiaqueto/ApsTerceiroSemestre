
INSERT INTO Usuario(
	idUsuario,
	NomeUsuario,
	CPFUsuario,
	telefoneUsuario,
	sexoUsuario,
	enderecoUsuario,
	emailUsuario,
	senhaUsuario
)
VALUES (
	1, 'Usuario 123', '12345678', 'f', 'Rua A', 'usuario.123@email.com', '123',
	2, 'Usuario ABC', '87654321', 'm', 'Rua B', 'usuario.abc@email.com', '321'
);

INSERT INTO Cliente(
	idCliente,
	NomeCliente,
	CPFCliente,
	telefoneCliente,
	sexoCliente,
	enderecoCliente,
	emailCliente
)
VALUES (
	1, 'João',  '00001111', 'm', 'Rua C', 'joao.123@email.com',
	2, 'Maria', '11110000', 'f', 'Rua D', 'maria.321@email.com'
);

INSERT INTO Categoria(idCategoria, nomeCategoria, descricaoCategoria)
VALUES(
	1, 'drama', 'livros de drama',
	2, 'comédia', 'livros de comédia',
	3, 'suspense', 'livros de suspense'
);

INSERT INTO Livro(
	idLivro,
	nomeLivro,
	ISBNLivro,
	autorLivro,
	editoraLivro,
	edicaoLivro,
	dataLancamentoLivro,
	nomeLivroCategoria,
	estoqueLivro,
	locacaoLivro,
	paginasLivro,
	precoLivro,
	sinopseLivro
)
VALUES(
	1, 'Livro A', '123456789ABCD', 'Autor A', 'Editora A', 1, '20200115 12:00:00', 'comédia', 5, 0, 42, 29.9, 'Esse é um bom livro de comédia',
	2, 'Livro B', 'DCBA987654321', 'Autor B', 'Editora B', 1, '20200115 12:00:00', 'drama',   4, 0, 32, 19.9, 'Esse é um bom livro de drama'
);

INSERT INTO Exemplar(
	idExemplar,
	idExemplarLivro,
	estaAlocado,
	dataObtencao
)
VALUES(
	1, 1, false, '20200120 12:00:00',
	2, 2, false, '20200120 12:00:00'
);
