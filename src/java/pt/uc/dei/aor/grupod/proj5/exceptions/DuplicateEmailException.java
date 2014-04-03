

package pt.uc.dei.aor.grupod.proj5.exceptions;


public class DuplicateEmailException extends Exception {
    
    public DuplicateEmailException(){
        super("E-mail already taken");
    }
}
