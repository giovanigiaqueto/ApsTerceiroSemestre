
/*
limpa as tabelas Categoria, Livro, Exemplar,
Emprestimo e Multa

Limpa Categoria -> Limpa Livro -> Limpa Exemplar ->
Limpa Emprestimo -> Limpa Multa
*/
TRUNCATE TABLE Categoria CASCADE;

/*
limpa as tabelas Usuario, Emprestimo e Multa

Limpa Usuario -> Limpa Emprestimo -> Limpa Multa
*/
TRUNCATE TABLE Usuario CASCADE;

/*
limpa as tabelas Cliente, Emprestimo e Multa

Limpa Cliente -> Limpa Emprestimo -> Limpa Multa
*/
TRUNCATE TABLE Cliente CASCADE;
