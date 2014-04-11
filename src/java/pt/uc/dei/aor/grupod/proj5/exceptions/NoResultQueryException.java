package pt.uc.dei.aor.grupod.proj5.exceptions;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
public class NoResultQueryException extends Exception {

    public NoResultQueryException() {
        super("Não existem avaliações");
    }
}
