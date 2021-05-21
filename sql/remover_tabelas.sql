DROP TABLE Multa;
DROP TABLE Emprestimo;
DROP TABLE Exemplar;
DROP TABLE Livro;
DROP TABLE Categoria;
DROP TABLE Cliente;
DROP TABLE Usuario;

DROP FUNCTION fn_impede_desativa_usuario;
DROP FUNCTION fn_impede_desativa_emprestimo;
DROP FUNCTION fn_impede_desativa_exemplar;
DROP FUNCTION fn_impede_desativa_categoria;
DROP FUNCTION fn_impede_desativa_livro;
DROP FUNCTION fn_impede_desativa_cliente;

DROP PROCEDURE desativar_usuario;
DROP PROCEDURE desativar_cliente;
DROP PROCEDURE desativar_categoria;
DROP PROCEDURE desativar_livro;
DROP PROCEDURE desativar_exemplar;
DROP PROCEDURE desativar_emprestimo;
DROP PROCEDURE desativar_multa;
