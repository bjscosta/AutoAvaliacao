package pt.uc.dei.aor.grupod.proj5.exceptions;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("Utilizador não existe");
    }

}
