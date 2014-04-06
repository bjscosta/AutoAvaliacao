package pt.uc.dei.aor.grupod.proj5.EJB;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.Administrator;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.entities.User;

@Named
@Stateful
@SessionScoped
public class LoggedUserEJB {

    private User loggedUser;
    private Edition activeEdition;

    public User getLoggedUser() {
        
        return loggedUser;
        
    }

    public void setLoggedUser(User loggedUser) {
        
        this.loggedUser = loggedUser;
        
    }

    public Edition getActiveEdition() {
        
        return activeEdition;
    }

    public void setActiveEdition(Edition activeEdition) {
        this.activeEdition = activeEdition;
        
    }

    /**
     * redirect to protect the student's views
     */
    public void verifyStudents() {

        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        if (loggedUser == null) {
            nav.performNavigation("index");
        } else {
            if (!(loggedUser instanceof Student)) {
                nav.performNavigation("index");
            }
        }

    }

    /**
     * redirect to protect the administrators's views
     */
    public void verifyAdministrators() {

        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        if (loggedUser == null) {
            nav.performNavigation("index");
        } else {
            if (!(loggedUser instanceof Administrator)) {
                nav.performNavigation("index");
            }
        }

    }

}
