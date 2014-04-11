package pt.uc.dei.aor.grupod.proj5.exceptions;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
public class DuplicateEmailException extends Exception {

    public DuplicateEmailException() {
        super("Erro! Esse email já existe na base de dados");
    }

}
