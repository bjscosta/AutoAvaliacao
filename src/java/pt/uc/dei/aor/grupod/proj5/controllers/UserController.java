package pt.uc.dei.aor.grupod.proj5.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIForm;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.EJB.LoggedUserEJB;
import pt.uc.dei.aor.grupod.proj5.entities.Administrator;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.entities.User;
import pt.uc.dei.aor.grupod.proj5.exceptions.DuplicateEmailException;
import pt.uc.dei.aor.grupod.proj5.exceptions.PassDontMatchException;
import pt.uc.dei.aor.grupod.proj5.exceptions.PasswordNotCorrectException;
import pt.uc.dei.aor.grupod.proj5.exceptions.UserNotFoundException;
import pt.uc.dei.aor.grupod.proj5.facades.AdministratorFacade;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;
import pt.uc.dei.aor.grupod.proj5.facades.StudentFacade;

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

    @PostConstruct
    public void init() {
        student = new Student();
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

    /**
     * Creates a new student
     *
     * @return The string that leads to the xhtml page
     */
    public String newStudent() {

        if (edition != null) {

            try {
                student = studentFacade.newStudent(student, confirmPassword);
                loggedUserEJB.setLoggedUser(student);
                edition.getStudents().add(student);
                student.setEdition(edition);
                studentFacade.edit(student);
                editionFacade.edit(edition);
                return "openProjectStudent";
            } catch (DuplicateEmailException e) {
                duplicateEmail = e.getMessage();
                return null;
            } catch (PassDontMatchException ex) {
                passDontMatch = ex.getMessage();
                return null;
            }
        } else {
            insertEdition = "Necessita de selecionar uma Edição";
            return null;
        }

    }

    /**
     * Verify the Student credentials
     *
     * @return The string that leads to the xhtml page
     */
    public String verifyStudent() {
        try {
            loggedUserEJB.setLoggedUser(studentFacade.login(studentEmail, studentPassword));
            return "openProjectStudent";
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
            return "openProjectAdmin";
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

    public String makeLogout() {
        studentFacade.logout();
        return "index";
    }

}
