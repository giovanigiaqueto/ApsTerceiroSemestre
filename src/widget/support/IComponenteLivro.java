package widget.support;

import model.Livro;

public interface IComponenteLivro {
    
    public Livro getLivro();
    public void setLivro(Livro livro);
    
    public void atualizarConteudo();
}
