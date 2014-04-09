

package pt.uc.dei.aor.grupod.proj5.exceptions;


public class NoResultQueryException extends Exception {
    
    public NoResultQueryException() {
        super("Não existem avaliações");
    }
}
