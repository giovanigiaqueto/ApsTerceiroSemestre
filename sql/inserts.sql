INSERT INTO Usuario(
	nome_usuario,
	cpf_usuario,
	telefone_usuario,
	sexo_usuario,
	endereco_usuario,
	email_usuario,
	senha_usuario
)
VALUES
('Laura', '05983591096', '730-9419', 'f', 'Rua Tadeu Rangel Pestana, 506', 'eu.tellus@venenatisvel.edu', '3563c7e4f50fbd04b0cc3bbe6721b136f506cadb59f76aafe1f07ae01a253343'),
('Mário', '19076557004', '651-8231', 'm', 'Rua Francisco Olandim, 204', 'magna.Lorem@nasceturridiculus.com', '1d258ebe3bfe472e280319ab43f26afdafbff36b6b5abba4814bbaa2ad710e61'),
('Renata', '29715931006', '535-2202', 'f', 'Avenida José Antonio de Paula e Silva, 1028', 'ut.quam@aliquamarcu.com', '25557bde1da038db410e197232a11e3301c5d7997cace54739f3792ce95bad8b'),
('Paulo', '71871900000', '447-9066', 'm', 'Avenida Jorge Guilherme Senger, 2007', 'a.arcu.Sed@Quisque.com', 'dc29337435c7c2d881e964551550a7c10981a403f349c0f3a51c0d469bc18ca6'),
('Clarke', '24731104092', '202-4448', 'm', 'Rua Engenheiro Reynaldo Cajado, 760', 'metus.In@imperdiet.edu', 'd5c2af68b917deda25ede67c645cc4ff8b9166d4635ae01f66417caa7291e7c2');

INSERT INTO Cliente(
	nome_cliente,
	cpf_cliente,
	telefone_cliente,
	sexo_cliente,
	endereco_cliente,
	email_cliente
)
VALUES
('Pedro', '07643771081', '456-0877', 'm', 'Rua Deputado Cássio Ciampolini, 650', 'sagittis@augueeutempor.net'),
('Cassandra', '26059347053', '753-4834', 'f', 'Rua João Correia, 110', 'diam.luctus@augue.com'),
('Camila', '14420375043', '468-9597', 'f', 'Avenida dos Remédios, 940', 'Nullam@Nunc.edu'),
('Kevin', '46802447045', '664-2122', 'm', 'Rua Barão de Vallim, 886', 'Donec@ipsumCurabitur.ca'),
('Maria', '54510944039', '896-7647', 'f', 'Rua Maria Natália Teodoro, 251', 'eget@Mauris.ca');

INSERT INTO Categoria(nome_categoria, descricao_categoria)
VALUES
('drama', 'livros de drama'),
('comédia', 'livros de comédia'),
('suspense', 'livros de suspense'),
('romance', 'livros de romance'),
('aventura', 'livros de aventura');

INSERT INTO Livro(
	nome_livro,
	isbn_livro,
	autor_livro,
	editora_livro,
	edicao_livro,
	data_lancamento_livro,
	nome_livro_categoria,
	estoque_livro,
	locacao_livro,
	paginas_livro,
	preco_livro,
	sinopse_livro
)
VALUES
('A Seleção', '9788565765015', 'Kiera Cass', 'Seguinte', 1, '2012-09-17', 'romance', 2, 1, 368, 28.10, 'Muitas garotas sonham em ser princesas, mas este não é o caso de America Singer. Ela topa se inscrever na Seleção só para agradar a mãe, certa de que não será sorteada para participar da competição em que o príncipe escolherá sua futura esposa. Mas é claro que depois disso sua vida nunca mais será a mesma...'),
('Hamlet', '9788582850145', 'William Shakespeare', 'Penguin', 1, '2015-08-12', 'drama', 5, 2, 320, 28.90, 'Um jovem príncipe se reúne com o fantasma de seu pai, que alega que seu próprio irmão, agora casado com sua viúva, o assassinou. O príncipe cria um plano para testar a veracidade de tal acusação, forjando uma brutal loucura para traçar sua vingança. Mas sua aparente insanidade logo começa a causar estragos - para culpados e inocentes.'),
('Paraíso', '9786550970345', 'Dante Alighieri', 'Principis', 1, '2020-03-25', 'comédia', 3, 2, 240, 10.69, 'A Divina Comédia é um poema clássico da literatura italiana e mundial escrito por Dante Alighieri no século XIV e dividido em três partes: o Inferno, o Purgatório e o Paraíso. São cem cantos protagonizados pelo autor acompanhado do poeta romano Virgílio. O Paraíso é descrito em 33 cantos. Ao fim, Virgílio não pode entrar pois era pagão. Assim, o local do poeta é o inferno. No paraíso, Dante reencontra seu grande amor, Beatriz.'),
('A paciente silenciosa', '9788501116437', 'Alex Michaelides', 'Record', 10, '2019-05-20', 'suspense', 2, 0, 350, 31.29, 'Só ela sabe o que aconteceu.Só ele pode fazê-la falar. A paciente silenciosa é um daqueles livros que não saem da cabeça do leitor, quer ele queira, quer não. Alicia Berenson tinha uma vida perfeita. Ela era uma pintora famosa casada com um fotógrafo bem-sucedido e morava numa área nobre de Londres que dá para o parque de Hampstead Heath'),
('As aventuras de Tom Sawyer', '9788551302859', 'Mark Twain', 'Autêntica infantil e juvenil', 1, '2017-10-5', 'aventura', 1, 1, 240, 27.04, 'Órfão desde bebê, Tom Sawyer vive com sua tia Polly, seu irmão, Sid, e sua prima, Mary, num vilarejo às margens do rio Mississipi, nos Estados Unidos. Menino de bom coração, de bom caráter, Tom é também muito levado e esperto, e vive aprontando, sozinho ou com seu melhor amigo Huckleberry Finn, um garoto que mora nas ruas, dorme em barris vazios e come o que lhe dão.'),
('As Aventuras de Huckleberry Finn', '9788537818220', 'Mark Twain', 'Clássicos Zahar', 1, '2019-04-18', 'aventura', 2, 1, 408, 59.44, 'Huckleberry Finn – o parceiro de Tom Sawyer – escapa de casa para embarcar em uma série de aventuras junto com o escravo fugitivo Jim. A bordo de uma jangada, os dois sobem o Mississippi e vivem situações extraordinárias com personagens inesquecíveis – como o “Rei” e o “Duque”, uma das maiores duplas de vigaristas da história da literatura.'),
('O menino do pijama listrado', '9788535911121', 'John Boyne', 'Seguinte', 1, '2007-10-11', 'drama', 3, 0, 192, 32.89, 'Bruno tem nove anos e não sabe nada sobre o Holocausto e a Solução Final contra os judeus. Também não faz idéia que seu país está em guerra com boa parte da Europa, e muito menos que sua família está envolvida no conflito. Na verdade, Bruno sabe apenas que foi obrigado a abandonar a espaçosa casa em que vivia em Berlim e a mudar-se para uma região desolada, onde ele não tem ninguém para brincar nem nada para fazer.');

INSERT INTO Exemplar(
	id_exemplarLivro,
	esta_alocado,
	data_obtencao
)
VALUES
(1, 'false', '2019-08-17'),
(1, 'true', '2020-01-22'),
(2, 'true', '2019-10-1'),
(2, 'false', '2020-05-30'),
(2, 'false', '2020-07-14'),
(2, 'true', '2020-11-12'),
(2, 'false', '2020-11-12'),
(3, 'false', '2019-09-09'),
(3, 'true', '2019-09-10'),
(3, 'true', '2020-04-05'),
(4, 'false', '2019-11-28'),
(4, 'false', '2020-05-20'),
(5, 'true', '2020-06-04'),
(6, 'true', '2021-01-12'),
(6, 'false', '2021-02-21'),
(7, 'false', '2020-07-10'),
(7, 'false', '2020-09-16'),
(7, 'false', '2021-03-02');

INSERT INTO Emprestimo(
	id_emprestimo_cliente,
	id_emprestimo_exemplar,
	id_emprestimo_usuario,
	data_emprestimo,
	data_devolucao
)
VALUES
(1, 2, 1, '2020-02-25', '2020-03-25'),
(2, 3, 1, '2019-10-31', '2019-12-1'),
(2, 6, 2, '2021-02-13', '2021-03-13'),
(3, 9, 3, '2019-10-08', '2019-11-08'),
(3, 10, 3, '2020-04-06', '2020-05-06'),
(4, 13, 4, '2021-02-20', '2021-03-20'),
(4, 14, 5, '2021-03-12', '2021-04-12');

INSERT INTO Multa(
	id_multa_cliente,
	id_multa_emprestimo,
	descricao_multa,
	valor_multa,
	pagamento_multa
)
VALUES
(2, 3, 'Atrasou 12 dias a devolução do livro Hamlet de William Shakespeare.', 11.00, 'true'),
(3, 4, 'Atrasou 7 dias a devolução do livro Paraíso de Dante Alighieri.', 8.50, 'true'),
(4, 6, 'Atrasou 86 dias a devolução do livro As aventuras de Tom Sawyer de Mark Twain', 48.00, 'false'),
(4, 7, 'Atrasou 5 dias a devolução do livro As Aventuras de Huckleberry Finn de Mark Twain', 7.50, 'false');
