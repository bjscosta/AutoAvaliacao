package pt.uc.dei.aor.grupod.proj5.exceptions;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
public class PassDontMatchException extends Exception {

    public PassDontMatchException() {
        super("As passwords não são iguais");
    }

}
