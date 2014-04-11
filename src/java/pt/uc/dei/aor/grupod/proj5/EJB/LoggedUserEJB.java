package pt.uc.dei.aor.grupod.proj5.EJB;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.User;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Named
@Stateful
@SessionScoped
public class LoggedUserEJB {

    private User loggedUser;
    private Edition activeEdition;
    private Project activeProject;

    /**
     *
     * @return loggedUser
     */
    public User getLoggedUser() {

        return loggedUser;

    }

    /**
     *
     * @param loggedUser
     */
    public void setLoggedUser(User loggedUser) {

        this.loggedUser = loggedUser;

    }

    /**
     *
     * @return activeEdition
     */
    public Edition getActiveEdition() {
        return activeEdition;
    }

    /**
     *
     * @param activeEdition
     */
    public void setActiveEdition(Edition activeEdition) {
        this.activeEdition = activeEdition;
    }

    /**
     *
     * @return activeProject
     */
    public Project getActiveProject() {
        return activeProject;
    }

    /**
     *
     * @param activeProject
     */
    public void setActiveProject(Project activeProject) {
        this.activeProject = activeProject;
    }

}
