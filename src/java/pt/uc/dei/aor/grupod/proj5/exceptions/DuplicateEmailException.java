package pt.uc.dei.aor.grupod.proj5.exceptions;

public class DuplicateEmailException extends Exception {

    public DuplicateEmailException() {
        super("Error! Esses email já existe na base de dados");
    }

}
