/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.facades;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.DuplicateEmailException;
import pt.uc.dei.aor.grupod.proj5.exceptions.PassDontMatchException;
import pt.uc.dei.aor.grupod.proj5.exceptions.PasswordNotCorrectException;
import pt.uc.dei.aor.grupod.proj5.exceptions.UserNotFoundException;
import pt.uc.dei.aor.grupod.proj5.utilities.EncriptMD5;

/**
 *
 *
 */
@Stateless
public class StudentFacade extends AbstractFacade<Student> {

    @PersistenceContext(unitName = "AutoAvaliacaoPU")
    private EntityManager em;

    private String emailExists;

    private String passMissmatch;

    private String transactionError;

    private static final String errorTransaction = "Erro a remover o estudante";

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * creates an instance of this class
     */
    public StudentFacade() {
        super(Student.class);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String getEmailExists() {
        return emailExists;
    }

    public void setEmailExists(String emailExists) {
        this.emailExists = emailExists;
    }

    public String getPassMissmatch() {
        return passMissmatch;
    }

    public void setPassMissmatch(String passMissmatch) {
        this.passMissmatch = passMissmatch;
    }

    public String getTransactionError() {
        return transactionError;
    }

    public void setTransactionError(String transactionError) {
        this.transactionError = transactionError;
    }

    /**
     * this method finds all the students in the database, catches an Exception
     * if the transaction was unsuccessful uses the namedquery
     * Student.findAllStudents
     *
     * @return the result list
     */
    public List<Student> findAllStudents() {
        try {
            Query q = em.createNamedQuery("Student.findAllStudents");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * this method finds a student by the studentId parameter, uses the
     * namedquery Student.findStudentIDById, if there is no result, the method
     * catches NoResultException and returns null
     *
     * @param studentId
     * @return
     */
    public Student findStudentById(Long studentId) {

        try {
            Query q = em.createNamedQuery("Student.findStudentIDById");
            q.setParameter("studentID", studentId);

            return (Student) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    /**
     * this method finds a student by the email, using the namedquery
     * Student.findStudentIDById
     *
     * @param email
     *
     * @return the result of the query, if NoResultException is catched, the
     * method returns the student that was found by the query
     */
    public Student findStudentsByEmail(String email) {

        Query q = em.createNamedQuery("Student.findStudentByEmail");

        q.setParameter("email", email);

        try {

            return (Student) q.getSingleResult();

        } catch (NoResultException | NonUniqueResultException e) {

            return null;

        }
    }

    /**
     * this method finds a list of students by the yearOfRegistration, use the
     * named query Student.findStudentByYearOfRegistration
     *
     * @param yearOfRegistration
     * @return the result list if the query is successful and null if the query
     * can't find any student
     */
    public List<Student> findStudentsByYearOfRegistration(int yearOfRegistration) {
        try {
            Query q = em.createNamedQuery("Student.findStudentByYearOfRegistration");
            q.setParameter("yearOfRegistration", yearOfRegistration);
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(StudentFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * this method finds a list of students by the edition, uses the named query
     * Student.findStudentByEdition
     *
     * @param edition
     * @return
     */
    public List<Student> findStudentsByEdition(Edition edition) {
        try {
            Query q = em.createNamedQuery("Student.findStudentByEdition");
            q.setParameter("edition", edition);
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(StudentFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * checks if there is already an email registered by a student in a database
     *
     * @param student
     * @throws DuplicateEmailException if there is already an email in a
     * database
     */
    public void checksEmail(Student student) throws DuplicateEmailException {

        Student s = findStudentsByEmail(student.getEmail());
        if (s != null) {
            throw new DuplicateEmailException();
        }
    }

    /**
     * checks if there is already an email registered by a student in a database
     *
     * @param student
     * @throws DuplicateEmailException if there is already an email in a
     * database
     */
    public void checksEmailEdit(Student student) throws DuplicateEmailException {

        Student s = findStudentsByEmail(student.getEmail());
        if (s != null && !s.getEmail().equals(student.getEmail())) {
            throw new DuplicateEmailException();
        }
    }

    /**
     * Checks if the user exists and if the combination between Email and
     * Password is correct
     *
     * @param loginEmail
     * @param loginPassword
     * @return s if the login is successfull, null if unsuccessfull
     * @throws UserNotFoundException
     * @throws PasswordNotCorrectException
     */
    public Student login(String loginEmail, String loginPassword) throws UserNotFoundException, PasswordNotCorrectException {

        Student s = findStudentsByEmail(loginEmail);

        String pass = EncriptMD5.cryptWithMD5(loginPassword);

        if (s != null && s.getPassword().equals(pass)) {

            return s;
        } else {
            if (s == null) {
                throw new UserNotFoundException();
            } else if (!s.getPassword().equals(pass)) {
                throw new PasswordNotCorrectException();
            }
            return null;
        }
    }

    /**
     * Invalidate the session
     *
     * @return The String that leads to a XHTML window
     */
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "index";

    }

    /**
     * creates a new Student, checks if the second passord matches the first
     * password the student registered
     *
     * @param student
     * @param password2
     * @return
     * @throws DuplicateEmailException
     * @throws PassDontMatchException
     */
    public Student persistStudent(Student student, String password2) throws DuplicateEmailException, PassDontMatchException {

        if (student.getPassword().equals(password2)) {
            try {
                String pass = EncriptMD5.cryptWithMD5(password2);
                checksEmail(student);
                student.setPassword(pass);
                create(student);
                return student;
            } catch (DuplicateEmailException ex) {
                Logger.getLogger(StudentFacade.class.getName()).log(Level.SEVERE, null, ex);
                throw new DuplicateEmailException();
            }

        } else {
            throw new PassDontMatchException();
        }

    }

    /**
     * this is method to create student, uses the method persistStudent
     *
     * @param student
     * @param confirmPassword
     * @param edition
     * @return
     * @throws DuplicateEmailException
     * @throws PassDontMatchException
     */
    public Student createStudent(Student student, String confirmPassword, Edition edition)
            throws DuplicateEmailException, PassDontMatchException {
        try {
            student = persistStudent(student, confirmPassword);
            edition.getStudents().add(student);
            student.setEdition(edition);
            edit(student);
            em.merge(edition);
            return student;
        } catch (DuplicateEmailException e) {
            throw new DuplicateEmailException();
        } catch (PassDontMatchException ex) {
            throw new PassDontMatchException();
        }
    }

    /**
     * Make the changes to the user calling the method editStudent
     *
     * @param logedStudent
     * @param student
     * @param password1
     * @param password2
     * @return
     */
    public Student updateStudent(Student logedStudent, Student student,
            String password1, String password2) {

        if (!password1.isEmpty() && password1.equals(password2)) {

            String pass = EncriptMD5.cryptWithMD5(password1);
            student.setPassword(pass);

            return editStudent(student);

        } else if (password1.isEmpty() && password2.isEmpty()) {

            return editStudent(student);

        } else {
            passMissmatch = "The passwords doesn't match";
            return null;
        }
    }

    /**
     * edits the student
     *
     * @param student
     * @return
     */
    public Student editStudent(Student student) {
        try {
            checksEmail(student);
            edit(student);

            return student;
        } catch (DuplicateEmailException e) {

            emailExists = e.getMessage();
            return null;
        }
    }

    /**
     * removes the student from the aplication
     *
     * @param s
     */
    public void removeStudent(Student s) {
        try {

            remove(em.merge(s));

        } catch (RollbackException e) {

            Logger.getLogger(StudentFacade.class.getName()).log(Level.SEVERE, null, e);
            transactionError = errorTransaction;

        }

    }

    /**
     * Make the changes to the user
     *
     * @param logedStudent
     * @param student
     * @param password1
     * @param password2
     * @return The new user
     * @throws pt.uc.dei.aor.grupod.proj5.exceptions.PassDontMatchException
     * @throws pt.uc.dei.aor.grupod.proj5.exceptions.DuplicateEmailException
     */
    public Student updateUser(Student logedStudent, Student student,
            String password1, String password2) throws PassDontMatchException, DuplicateEmailException {

        if (!password1.isEmpty() && password1.equals(password2)) {

            String pass = EncriptMD5.cryptWithMD5(password1);
            student.setPassword(pass);

            try {
                checksEmailEdit(student);
                edit(student);

                return student;
            } catch (DuplicateEmailException e) {

                emailExists = e.getMessage();
                throw new DuplicateEmailException();
            }
        } else if (password1.isEmpty() && password2.isEmpty()) {

            try {
                checksEmailEdit(student);
                edit(student);
                return student;
            } catch (DuplicateEmailException e) {

                emailExists = e.getMessage();
                throw new DuplicateEmailException();
            }
        } else {
            passMissmatch = "The passwords doesn't match";
            throw new PassDontMatchException();
        }
    }

}
