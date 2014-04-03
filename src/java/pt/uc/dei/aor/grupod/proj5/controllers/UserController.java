

package pt.uc.dei.aor.grupod.proj5.controllers;

import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.Administrator;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.entities.User;

@Named
public class UserController {
    
    private User user;
    private Student student;
    private Administrator admin;
    private String email;
    private String password;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
   
    
    /**
     * Creates a new student
     * @return The string that leads to the xhtml page
     */
    public String newStudent(){
        
        
        
        return "";
    }
    
}
