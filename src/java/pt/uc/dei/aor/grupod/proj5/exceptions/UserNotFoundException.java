

package pt.uc.dei.aor.grupod.proj5.exceptions;


public class UserNotFoundException extends Exception{
    
    public UserNotFoundException(){
        super("Utilizador não existe");
    }
    
}
