/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.DuplicateEmailException;

/**
 *
 *
 */
@Stateless
public class StudentFacade extends AbstractFacade<Student> {

    @PersistenceContext(unitName = "AutoAvaliacaoPU")
    private EntityManager em;

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

    /**
     * this method finds all the students in the database, catches an Exception
     * if the transaction was unsuccessful uses the namedquery
     * Student.findAllStudents
     *
     * @return the result list of the
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
        } catch (NoResultException e) {
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
     * method returns null
     */
    public Student findStudentsByEmail(String email) {

        Query q = em.createNamedQuery("Student.findStudentIDById");

        q.setParameter("email", email);

        try {

            return (Student) q.getSingleResult();

        } catch (NoResultException e) {

            return null;

        }
    }

    /**
     * t
     *
     * @param yearOfRegistration
     * @return
     */
    public List<Student> findStudentsByYearOfRegistration(int yearOfRegistration) {
        Query q = em.createNamedQuery("Student.findStudentByYearOfRegistration");
        q.setParameter("yearOfRegistration", yearOfRegistration);
        return q.getResultList();
    }

    /**
     *
     * @param edition
     * @return
     */
    public List<Student> findStudentsByEdition(Edition edition) {
        Query q = em.createNamedQuery("Student.findStudentByEdition");
        q.setParameter("edition", edition);
        return q.getResultList();
    }

    public boolean checksEmail(String email) throws DuplicateEmailException {

        Student s = findStudentsByEmail(email);
        if (s != null) {
            throw new DuplicateEmailException();
        }
        return false;
    }
}
