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
    private String insertEdition;
    private String password1;
    private String password2;
    private UIComponent loginEdition;

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

    public UIComponent getLoginEdition() {
        return loginEdition;
    }

    public void setLoginEdition(UIComponent loginEdition) {
        this.loginEdition = loginEdition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public UIForm getLogin() {
        return login;
    }

    public void setLogin(UIForm login) {
        this.login = login;
    }

    public UIForm getNewRegistration() {
        return newRegistration;
    }

    public void setNewRegistration(UIForm newRegistration) {
        this.newRegistration = newRegistration;
    }

    public UIForm getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(UIForm adminLogin) {
        this.adminLogin = adminLogin;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public StudentFacade getStudentFacade() {
        return studentFacade;
    }

    public void setStudentFacade(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    public String getDuplicateEmail() {
        return duplicateEmail;
    }

    public void setDuplicateEmail(String duplicateEmail) {
        this.duplicateEmail = duplicateEmail;
    }

    public String getPassDontMatch() {
        return passDontMatch;
    }

    public void setPassDontMatch(String passDontMatch) {
        this.passDontMatch = passDontMatch;
    }

    public AdministratorFacade getAdministratorFacade() {
        return administratorFacade;
    }

    public void setAdministratorFacade(AdministratorFacade administratorFacade) {
        this.administratorFacade = administratorFacade;
    }

    public String getPassNotCorrect() {
        return passNotCorrect;
    }

    public void setPassNotCorrect(String passNotCorrect) {
        this.passNotCorrect = passNotCorrect;
    }

    public String getUserNotFound() {
        return userNotFound;
    }

    public void setUserNotFound(String userNotFound) {
        this.userNotFound = userNotFound;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public String getInsertEdition() {
        return insertEdition;
    }

    public void setInsertEdition(String insertEdition) {
        this.insertEdition = insertEdition;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

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
                duplicateEmail = e.getMessage();
                return null;
            } catch (PassDontMatchException ex) {
                passDontMatch = ex.getMessage();
                return null;
            } catch (Exception e) {
                MessagesForUser.addMessage("Neste momento não é possivel registar-se tente mais tarde");
                return null;
            }
        } else {
            insertEdition = "Necessita de selecionar uma Edição";
            return null;
        }
        try {
            logFacade.createLog("Registration", student);
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
                        return "openProjectStudent";
                    } else {
                        loginEdition.setRendered(true);
                        MessagesForUser.addMessage("A sua Edição foi eliminada precisa de escolher"
                                + "selecione uma e volte a fazer login");
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
                        return "openProjectStudent";
                    } else {
                        loginEdition.setRendered(true);
                        MessagesForUser.addMessage("A sua Edição foi eliminada precisa de escolher"
                                + "selecione uma e volte a fazer login");
                        return null;
                    }
                } else {
                    return null;
                }
            }
        } catch (PasswordNotCorrectException e) {
            passNotCorrect = e.getMessage();
            return null;
        } catch (UserNotFoundException ex) {
            userNotFound = ex.getMessage();
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
            passNotCorrect = e.getMessage();
            return null;
        } catch (UserNotFoundException ex) {
            userNotFound = ex.getMessage();
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
                logFacade.createLog("FailToEditProfile", student);
            } catch (LogException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        try {
            logFacade.createLog("EditProfile", student);
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
            passDontMatch = ex.getMessage();

        } catch (DuplicateEmailException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            duplicateEmail = ex.getMessage();

        }
        return true;
    }

    public String deleteStudent() {
        studentFacade.removeStudent((Student) loggedUserEJB.getLoggedUser());
        return makeLogout();
    }

    /**
     * redirect for students pages
     */
    public void veryfyStudent() {
        loggedUserEJB.verifyStudents();
    }

    /**
     * redirect for administrator pages
     */
    public void veryfyAdministrator() {
        loggedUserEJB.verifyAdministrators();
    }

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
                nav.performNavigation("index");
            } else {

            }
        }

    }

    public void createLogForEnterPageStudent(String page) {
        try {
            logFacade.createLog("view " + page, student);
        } catch (LogException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
