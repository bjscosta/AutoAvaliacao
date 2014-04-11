package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.EJB.LoggedUserEJB;
import pt.uc.dei.aor.grupod.proj5.entities.Administrator;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.entities.User;
import pt.uc.dei.aor.grupod.proj5.exceptions.DuplicateEmailException;
import pt.uc.dei.aor.grupod.proj5.exceptions.LogException;
import pt.uc.dei.aor.grupod.proj5.exceptions.PassDontMatchException;
import pt.uc.dei.aor.grupod.proj5.exceptions.PasswordNotCorrectException;
import pt.uc.dei.aor.grupod.proj5.exceptions.UserNotFoundException;
import pt.uc.dei.aor.grupod.proj5.facades.AdministratorFacade;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;
import pt.uc.dei.aor.grupod.proj5.facades.LogFacade;
import pt.uc.dei.aor.grupod.proj5.facades.StudentFacade;
import pt.uc.dei.aor.grupod.proj5.utilities.MessagesForUser;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Named
@RequestScoped
public class UserController {

    @Inject
    private StudentFacade studentFacade;

    @Inject
    private AdministratorFacade administratorFacade;

    @Inject
    private LoggedUserEJB loggedUserEJB;

    @Inject
    private EditionFacade editionFacade;

    @Inject
    private LogFacade logFacade;

    private User user;
    private Student student;
    private Administrator admin;
    private String studentEmail;
    private String studentPassword;
    private String adminEmail;
    private String adminPassword;
    private String confirmPassword;
    private UIForm login;
    private UIForm newRegistration;
    private UIForm adminLogin;
    private String duplicateEmail;
    private String passDontMatch;
    private String passNotCorrect;
    private String userNotFound;
    private Edition edition;
    private String password1;
    private String password2;
    private UIComponent loginEdition;

    /**
     *
     */
    @PostConstruct
    public void init() {
        if (loggedUserEJB.getLoggedUser() == null) {
            student = new Student();
        } else if (loggedUserEJB.getLoggedUser() instanceof Student) {
            student = (Student) loggedUserEJB.getLoggedUser();
        } else if (loggedUserEJB.getLoggedUser() instanceof Administrator) {
            admin = (Administrator) loggedUserEJB.getLoggedUser();
        }

    }

    /**
     *
     * @return
     */
    public UIComponent getLoginEdition() {
        return loginEdition;
    }

    /**
     *
     * @param loginEdition
     */
    public void setLoginEdition(UIComponent loginEdition) {
        this.loginEdition = loginEdition;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public Student getStudent() {
        return student;
    }

    /**
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     *
     * @return
     */
    public Administrator getAdmin() {
        return admin;
    }

    /**
     *
     * @param admin
     */
    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    /**
     *
     * @return
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     *
     * @param studentEmail
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /**
     *
     * @return
     */
    public String getStudentPassword() {
        return studentPassword;
    }

    /**
     *
     * @param studentPassword
     */
    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    /**
     *
     * @return
     */
    public String getAdminEmail() {
        return adminEmail;
    }

    /**
     *
     * @param adminEmail
     */
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    /**
     *
     * @return
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     *
     * @param adminPassword
     */
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    /**
     *
     * @return
     */
    public UIForm getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    public void setLogin(UIForm login) {
        this.login = login;
    }

    /**
     *
     * @return
     */
    public UIForm getNewRegistration() {
        return newRegistration;
    }

    /**
     *
     * @param newRegistration
     */
    public void setNewRegistration(UIForm newRegistration) {
        this.newRegistration = newRegistration;
    }

    /**
     *
     * @return
     */
    public UIForm getAdminLogin() {
        return adminLogin;
    }

    /**
     *
     * @param adminLogin
     */
    public void setAdminLogin(UIForm adminLogin) {
        this.adminLogin = adminLogin;
    }

    /**
     *
     * @return
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     *
     * @param confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     *
     * @return
     */
    public StudentFacade getStudentFacade() {
        return studentFacade;
    }

    /**
     *
     * @param studentFacade
     */
    public void setStudentFacade(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    /**
     *
     * @return
     */
    public String getDuplicateEmail() {
        return duplicateEmail;
    }

    /**
     *
     * @param duplicateEmail
     */
    public void setDuplicateEmail(String duplicateEmail) {
        this.duplicateEmail = duplicateEmail;
    }

    /**
     *
     * @return
     */
    public String getPassDontMatch() {
        return passDontMatch;
    }

    /**
     *
     * @param passDontMatch
     */
    public void setPassDontMatch(String passDontMatch) {
        this.passDontMatch = passDontMatch;
    }

    /**
     *
     * @return
     */
    public AdministratorFacade getAdministratorFacade() {
        return administratorFacade;
    }

    /**
     *
     * @param administratorFacade
     */
    public void setAdministratorFacade(AdministratorFacade administratorFacade) {
        this.administratorFacade = administratorFacade;
    }

    /**
     *
     * @return
     */
    public String getPassNotCorrect() {
        return passNotCorrect;
    }

    /**
     *
     * @param passNotCorrect
     */
    public void setPassNotCorrect(String passNotCorrect) {
        this.passNotCorrect = passNotCorrect;
    }

    /**
     *
     * @return
     */
    public String getUserNotFound() {
        return userNotFound;
    }

    /**
     *
     * @param userNotFound
     */
    public void setUserNotFound(String userNotFound) {
        this.userNotFound = userNotFound;
    }

    /**
     *
     * @return
     */
    public Edition getEdition() {
        return edition;
    }

    /**
     *
     * @param edition
     */
    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    /**
     *
     * @return
     */
    public String getPassword1() {
        return password1;
    }

    /**
     *
     * @param password1
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     *
     * @return
     */
    public String getPassword2() {
        return password2;
    }

    /**
     *
     * @param password2
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     * Creates a new student
     *
     * @return The string that leads to the xhtml page
     */
    public String newStudent() {

        if (edition != null) {

            try {
                student.setYearOfRegistration(new GregorianCalendar().get(Calendar.YEAR));
                loggedUserEJB.setLoggedUser(studentFacade.createStudent(student, confirmPassword, edition));

            } catch (DuplicateEmailException e) {

                MessagesForUser.addMessageError(e.getMessage());
                return null;
            } catch (PassDontMatchException ex) {

                MessagesForUser.addMessageError(ex.getMessage());
                return null;

            } catch (Exception e) {
                MessagesForUser.addMessageError("Neste momento não é possivel registar-se tente mais tarde");
                return null;
            }
        } else {
            MessagesForUser.addMessageError("Necessita de selecionar uma Edição");
            return null;
        }
        try {
            logFacade.createLog("Registration Successful", (Student) loggedUserEJB.getLoggedUser());
        } catch (LogException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "openProjectStudent";

    }

    /**
     * Verify the Student credentials
     *
     * @return The string that leads to the xhtml page
     */
    public String verifyStudent() {
        try {
            if (edition == null) {
                Student s = studentFacade.login(studentEmail, studentPassword);
                if (s != null) {
                    if (s.getEdition() != null) {
                        loggedUserEJB.setLoggedUser(s);
                        try {
                            logFacade.createLog("Login Successful ", s);
                        } catch (LogException exc) {
                            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, exc);
                        }
                        return "openProjectStudent";
                    } else {
                        loginEdition.setRendered(true);
                        MessagesForUser.addMessageError("A sua Edição foi eliminada"
                                + ", selecione a edição que pretende frequentar");
                        return null;
                    }
                } else {

                    return null;
                }
            } else {
                Student s = studentFacade.loginWithoutEdition(studentEmail, studentPassword, edition);
                if (s != null) {
                    if (s.getEdition() != null) {
                        loggedUserEJB.setLoggedUser(s);
                        try {
                            logFacade.createLog("Login Successful ", s);
                        } catch (LogException exc) {
                            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, exc);
                        }
                        return "openProjectStudent";
                    } else {
                        loginEdition.setRendered(true);
                        MessagesForUser.addMessageInfo("A sua Edição foi eliminada precisa de escolher"
                                + "selecione uma e volte a fazer login");
                        return null;
                    }
                } else {
                    return null;
                }
            }
        } catch (PasswordNotCorrectException e) {

            MessagesForUser.addMessageError(e.getMessage());
            return null;
        } catch (UserNotFoundException ex) {

            MessagesForUser.addMessageError(ex.getMessage());
            return null;
        }
    }

    /**
     * Verify the Administrator credentials
     *
     * @return The string that leads to the xhtml page
     */
    public String verifyAdmin() {
        try {
            loggedUserEJB.setLoggedUser(administratorFacade.login(adminEmail, adminPassword));
            return "projectAdmin";
        } catch (PasswordNotCorrectException e) {
            MessagesForUser.addMessageError(e.getMessage());
            return null;
        } catch (UserNotFoundException ex) {
            MessagesForUser.addMessageError(ex.getMessage());
            return null;
        }

    }

    /**
     * Changes to the new Registration form
     */
    public void goToNewRegistration() {
        newRegistration.setRendered(true);
        login.setRendered(false);
        adminLogin.setRendered(false);
    }

    /**
     * Changes to the admin login form
     */
    public void goToAdminLogin() {
        newRegistration.setRendered(false);
        login.setRendered(false);
        adminLogin.setRendered(true);
    }

    /**
     * Changes to the login form
     */
    public void goToLogin() {
        newRegistration.setRendered(false);
        login.setRendered(true);
        adminLogin.setRendered(false);
    }

    /**
     * calls the method for logout of the administrator and student on the
     * studentFacade
     *
     * @return
     */
    public String makeLogout() {

        if (loggedUserEJB.getLoggedUser() instanceof Student) {
            try {
                logFacade.createLog("Logout Successful ", student);
            } catch (LogException exc) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, exc);
            }
        }
        return studentFacade.logout();

    }

    /**
     * Calls the updateUser method and sets the logedUser, using the method
     * updatedUser of the studentFacade. catches PassDontMachException and
     * DuplicateEmailException
     *
     * @return The String that leads to a XHTML window
     */
    public String makeUpdateStudent() {
        boolean error = editProfile();

        if (error) {
            try {
                logFacade.createLog("EditProfile Failed", student);
            } catch (LogException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        try {
            logFacade.createLog("EditProfile Successful", student);
        } catch (LogException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "openProjectStudent";

    }

    private boolean editProfile() {

        try {
            loggedUserEJB.setLoggedUser(studentFacade.updateUser((Student) loggedUserEJB.getLoggedUser(),
                    student, password1, password2));
            return false;

        } catch (PassDontMatchException ex) {

            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            MessagesForUser.addMessageError(ex.getMessage());

        } catch (DuplicateEmailException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            MessagesForUser.addMessageError(ex.getMessage());

        }
        return true;
    }

    /**
     *
     * @return
     */
    public String deleteStudent() {
        try {
            logFacade.createLog("Delete Profile Successful", student);
        } catch (LogException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        studentFacade.removeStudent((Student) loggedUserEJB.getLoggedUser());
        return makeLogout();
    }

    /**
     *
     */
    public void verifyStudents() {

        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        if (loggedUserEJB.getLoggedUser() == null) {
            nav.performNavigation("index");
        } else {
            if (!(loggedUserEJB.getLoggedUser() instanceof Student)) {
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
        if (loggedUserEJB.getLoggedUser() == null) {
            nav.performNavigation("index");
        } else {
            if (!(loggedUserEJB.getLoggedUser() instanceof Administrator)) {
                try {
                    logFacade.createLog("Try to see an Administrator page", student);
                } catch (LogException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                nav.performNavigation("index");
            }

        }

    }

    /**
     *
     * @param page
     */
    public void createLogForEnterPageStudent(String page) {
        if (student != null) {
            try {
                logFacade.createLog("view " + page, student);
            } catch (LogException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
