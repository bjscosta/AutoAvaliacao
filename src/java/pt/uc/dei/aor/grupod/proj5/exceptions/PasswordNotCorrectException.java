

package pt.uc.dei.aor.grupod.proj5.exceptions;


public class PasswordNotCorrectException extends Exception {
    
    
    public PasswordNotCorrectException(){
        super("The password is not correct");
    }
}
