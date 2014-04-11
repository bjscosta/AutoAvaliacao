package pt.uc.dei.aor.grupod.proj5.exceptions;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
public class PasswordNotCorrectException extends Exception {

    public PasswordNotCorrectException() {
        super("Password incorrecta");
    }
}
