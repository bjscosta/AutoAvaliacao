

package pt.uc.dei.aor.grupod.proj5.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIForm;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.Administrator;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.entities.User;

@Named
@RequestScoped
public class UserController {
    
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
    
    @PostConstruct
    public void init(){
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
    
    
    /**
     * Creates a new student
     * @return The string that leads to the xhtml page
     */
    public String newStudent(){
        
        return "";
    }
    
    /**
     * Verify the Student credentials
     * @return The string that leads to the xhtml page 
     */
    public String verifyStudent(){
        
        return "";
    }
    
    /**
     * Verify the Administrator credentials
     * @return The string that leads to the xhtml page 
     */
    public String verifyAdmin(){
        
        return "";
    }
    
    
    /**
     * Changes to the new Registration form
     */
    public void goToNewRegistration(){
        newRegistration.setRendered(true);
        login.setRendered(false);
        adminLogin.setRendered(false);
    }
    
    /**
     * Changes to the admin login form
     */
    public void goToAdminLogin(){
        newRegistration.setRendered(false);
        login.setRendered(false);
        adminLogin.setRendered(true);
    }
    
    /**
     * Changes to the login form
     */
    public void goToLogin(){
        newRegistration.setRendered(false);
        login.setRendered(true);
        adminLogin.setRendered(false);
    }
    
    
}
