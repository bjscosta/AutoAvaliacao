

package pt.uc.dei.aor.grupod.proj5.exceptions;


public class PassDontMatchException extends Exception {
    
    public PassDontMatchException(){
        super("The passwords dont match");
    }
    
}
