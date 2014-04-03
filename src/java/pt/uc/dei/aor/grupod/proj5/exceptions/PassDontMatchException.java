package pt.uc.dei.aor.grupod.proj5.exceptions;

public class PassDontMatchException extends Exception {

    public PassDontMatchException() {
        super("As passwords não são iguais");
    }

}
