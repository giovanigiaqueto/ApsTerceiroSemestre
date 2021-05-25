/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internal;

public class SingletonInstantiationException extends Exception {
    
    /**
     * cria uma nova exceção com mensagem padrão "uma instancia da classe já existe"
     */
    public SingletonInstantiationException() {
        super("uma instancia da classe já existe");
    }
    
    /**
     * cria uma nova exceção dada mensagem e causa
     * 
     * @param mensagem a mensagem da exceção
     * @param causa a causa da exceção
     */
    public SingletonInstantiationException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
    
    /**
     * cria uma nova exceção dada mensagem, causa, supressão
     * habilitada ou desabilitada, e stack trace writable habilitado ou desabilitado
     * 
     * @param mensagem a mensagem da exceção
     * @param causa a causa da exceção
     * @param enableSuppression habilitar a supressão da exceção (?)
     * @param writableStackTrace habilitar stack trace writable (?)
     */
    public SingletonInstantiationException(String mensagem, Throwable causa,
        boolean enableSuppression, boolean writableStackTrace) {
        super(mensagem, causa, enableSuppression, writableStackTrace);
    }
    
    /**
     * cria uma nova exceção dada a causa
     * 
     * @param causa a causa da exceção
     */
    public SingletonInstantiationException(Throwable causa) {
        super(causa);
    }
}
