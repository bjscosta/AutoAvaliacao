

package pt.uc.dei.aor.grupod.proj5.EJB;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.User;

@Named
@Stateful
@SessionScoped
public class LoggedUserEJB {

    private User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
    
}
