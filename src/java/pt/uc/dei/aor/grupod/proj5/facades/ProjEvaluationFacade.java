package pt.uc.dei.aor.grupod.proj5.facades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import pt.uc.dei.aor.grupod.proj5.entities.Criteria;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.ProjEvaluation;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.NoResultQueryException;
import pt.uc.dei.aor.grupod.proj5.exceptions.ProjEvaluationException;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Stateless
public class ProjEvaluationFacade extends AbstractFacade<ProjEvaluation> {

    @PersistenceContext(unitName = "AutoAvaliacaoPU")
    private EntityManager em;

    /**
     *
     * @return em
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * constructor of ProjEvaluationFacade
     */
    public ProjEvaluationFacade() {
        super(ProjEvaluation.class);
    }

    /**
     * method to find the student with avaliations, uses the named query
     * "ProjEvaluation.userEvaluation"
     *
     * @param p
     * @return a list with students with avaliations in the project
     */
    public List<Student> studentsWithAvaliationsProject(Project p) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DAY_OF_MONTH, 1);
        Edition edition = p.getEdition();

        List<Student> withAvaliations = new ArrayList<>();
        for (Student s : p.getStudents()) {
            if (!em.createNamedQuery("ProjEvaluation.userEvaluation")
                    .setParameter("student", s).setParameter("project", p).getResultList().isEmpty()) {
                withAvaliations.add(s);
            } else {
                if (p.getFinishingSelfEvaluationDate().before(gc.getTime())) {
                    for (Criteria c : edition.getCriteriaList()) {
                        ProjEvaluation pe = new ProjEvaluation();
                        pe.setCriteria(c);
                        pe.setCriteriaValue(edition.getMinValueScale());
                        pe.setStudent(s);
                        pe.setProject(p);
                        em.persist(pe);
                        p.getProjAvaliations().add(pe);
                        s.getProjEvaluations().add(pe);
                        em.merge(s);
                        em.merge(p);
                    }
                    withAvaliations.add(s);
                }

            }
        }
        return withAvaliations;
    }

    /**
     * finds all the students of the edition with evaliations made
     *
     * @param e
     * @return withAvaliations
     */
    public List<Student> studentsWithAvaliationsEdition(Edition e) {
        List<Student> withAvaliations = new ArrayList<>();
        for (Project p : e.getProjectList()) {

            List<Student> ls = studentsWithAvaliationsProject(p);
            for (Student s : ls) {
                if (!withAvaliations.contains(s)) {
                    withAvaliations.add(s);
                }
            }
        }
        return withAvaliations;

    }

    /**
     * finds all projects of the student
     *
     * @param s
     * @return pwe
     */
    public List<Project> projWithEva(Student s) {

        List<Project> pwe = new ArrayList<>();
        for (Project p : s.getProjects()) {
            if (!em.createNamedQuery("ProjEvaluation.userEvaluation")
                    .setParameter("student", s).setParameter("project", p).getResultList().isEmpty()) {
                pwe.add(p);
            }
        }
        return pwe;
    }

    /**
     * finds all evaluations of the project of the student
     *
     * @param s
     * @param p
     * @return
     */
    public List<ProjEvaluation> getListProjEvaluation(Student s, Project p) {
        return em.createNamedQuery("ProjEvaluation.userEvaluation")
                .setParameter("student", s).setParameter("project", p).getResultList();
    }

    /**
     * sets to zero the evaluations of the student of the project
     *
     * @param s
     * @param p
     */
    public void setToZeroEvaluations(Student s, Project p) {
        List<ProjEvaluation> projEva = evaluationsOfStudentAndProject(s, p);
        for (ProjEvaluation pe : projEva) {
            if (pe.getProject().equals(p)) {
                pe.setCriteriaValue(0);
                em.merge(pe);
            }
        }
    }

    /**
     *
     * @param s
     * @param p
     * @return the result of the named query ProjEvaluation.userEvaluation
     */
    public List<ProjEvaluation> evaluationsOfStudentAndProject(Student s, Project p) {
        return em.createNamedQuery("ProjEvaluation.userEvaluation")
                .setParameter("student", s).setParameter("project", p).getResultList();
    }

    /**
     * this method it's to confirm the evaluations that were made my the student
     *
     * @param pelist
     * @throws ProjEvaluationException
     */
    public void confirm(List<ProjEvaluation> pelist) throws ProjEvaluationException {
        Edition e = pelist.get(0).getProject().getEdition();
        Project p = pelist.get(0).getProject();
        Student s = pelist.get(0).getStudent();
        try {
            for (ProjEvaluation pe : pelist) {
                if (pe.getCriteriaValue() >= e.getMinValueScale() && pe.getCriteriaValue() <= e.getMaxValueScale()) {
                    em.persist(pe);
                    p.getProjAvaliations().add(pe);
                    s.getProjEvaluations().add(pe);
                    em.merge(s);
                    em.merge(p);

                } else {
                    throw new ProjEvaluationException();
                }

            }
        } catch (RollbackException exception) {
            throw new ProjEvaluationException();
        }
    }

    /**
     * creates a list of ProjEvaluation for the student and project
     *
     * @param p
     * @param s
     * @return projE
     */
    public List<ProjEvaluation> createProjEvaluation(Project p, Student s) {
        List<ProjEvaluation> projE = new ArrayList();
        for (Criteria c : s.getEdition().getCriteriaList()) {
            ProjEvaluation pe = new ProjEvaluation();
            pe.setCriteria(c);
            pe.setProject(p);
            pe.setStudent(s);
            projE.add(pe);

        }
        return projE;
    }

    /**
     * method to see the average of the edition
     *
     * @param edition
     * @return the result of the named query ProjEvaluation.avgOfAnEdition
     * @throws NoResultQueryException
     */
    public double averageEdition(Edition edition) throws NoResultQueryException {
        try {
            return (double) em.createNamedQuery("ProjEvaluation.avgOfAnEdition").setParameter("editionId", edition.getEditionId()).getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            throw new NoResultQueryException();
        }
    }

    /**
     * method to set the transient value avgValue of each criteria of the
     * edition edition uses the named query ProjEvaluation.avgOfACriteriaEdition
     *
     * @param edition
     * @return edition
     * @throws NoResultQueryException
     */
    public Edition averageCriteriaEdition(Edition edition) throws NoResultQueryException {

        for (Criteria c : edition.getCriteriaList()) {
            try {
                c.setAvgValue((double) em.createNamedQuery("ProjEvaluation.avgOfACriteriaEdition").setParameter("criteriaId", c.getCriteriaId()).getSingleResult());
            } catch (NoResultException e) {
                throw new NoResultQueryException();
            }
        }
        return edition;

    }

    /**
     * method to see the average of the project
     *
     * @param p
     * @return the result of the named query ProjEvaluation.avgProj
     * @throws NoResultQueryException
     */
    public double averageProject(Project p) throws NoResultQueryException {
        try {
            return (double) em.createNamedQuery("ProjEvaluation.avgProj").setParameter("projectId", p.getId()).getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            throw new NoResultQueryException();
        }
    }

    /**
     * method to set the transient value avgValue of each criteria of the
     * edition edition uses the named query ProjEvaluation.avgOfACriteriaProject
     *
     * @param edition
     * @param project
     * @return edition
     * @throws NoResultQueryException
     */
    public Edition averageCriteriaProject(Edition edition, Project project) throws NoResultQueryException {

        for (Criteria c : edition.getCriteriaList()) {
            try {
                c.setAvgValue((double) em.createNamedQuery("ProjEvaluation.avgOfACriteriaProject").setParameter("projectId", project.getId()).setParameter("criteriaId", c.getCriteriaId()).getSingleResult());
            } catch (NoResultException e) {
                throw new NoResultQueryException();
            }
        }
        return edition;

    }

    /**
     * method to see the average of a student
     *
     * @param s
     * @return the result of the named query ProjEvaluation.avgStudent
     * @throws NoResultQueryException
     */
    public double averageStudent(Student s) throws NoResultQueryException {
        try {
            return (double) em.createNamedQuery("ProjEvaluation.avgStudent").setParameter("studentId", s.getStudentID()).getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            throw new NoResultQueryException();
        }
    }

    /**
     * method to set the transient value avgValue of each criteria of the
     * edition edition uses the named query
     * ProjEvaluation.avgStudentProjectCriteria
     *
     * @param edition
     * @param student
     * @param project
     * @return edition
     * @throws NoResultQueryException
     */
    public Edition averageStudentProject(Edition edition, Student student, Project project) throws NoResultQueryException {

        for (Criteria c : edition.getCriteriaList()) {
            try {
                c.setAvgValue((double) em.createNamedQuery("ProjEvaluation.avgStudentProjectCriteria").setParameter("studentId", student.getStudentID()).setParameter("projectId", project.getId()).setParameter("criteriaId", c.getCriteriaId()).getSingleResult());
            } catch (NoResultException e) {
                throw new NoResultQueryException();
            }
        }
        return edition;

    }

    /**
     * the average of the student in the project
     *
     * @param studentID
     * @param projectID
     * @return the result of the named query ProjEvaluation.avgProjStudent
     * @throws NoResultQueryException
     */
    public double avgStudentProject(Long studentID, Long projectID) throws NoResultQueryException {

        try {

            return (double) em.createNamedQuery("ProjEvaluation.avgProjStudent")
                    .setParameter("studentID", studentID).setParameter("projectId", projectID)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new NoResultQueryException();
        }

    }

    /**
     * method to set the transient value avgValue of each criteria of the
     * edition uses the named query ProjEvaluation.avgOfCriteriaStudent
     *
     * @param edition
     * @param student
     * @return edition
     * @throws NoResultQueryException
     */
    public Edition averageCriteriaStudent(Edition edition, Student student) throws NoResultQueryException {

        for (Criteria c : edition.getCriteriaList()) {
            try {
                c.setAvgValue((Double) em.createNamedQuery("ProjEvaluation.avgOfCriteriaStudent").setParameter("studentId", student.getStudentID()).setParameter("criteriaId", c.getCriteriaId()).getSingleResult());

            } catch (NoResultException e) {
                throw new NoResultQueryException();
            }
        }
        return edition;

    }

    /**
     * method see the evaluation of a student of criteria of the project
     *
     * @param p
     * @param c
     * @param s
     * @return the result of the named query
     * ProjEvaluation.evStudentProjectCriteria
     * @throws NoResultQueryException
     */
    public double evaluationCriteriaStudentProject(Project p, Criteria c, Student s) throws NoResultQueryException {

        try {
            return (double) em.createNamedQuery("ProjEvaluation.evStudentProjectCriteria").setParameter("studentId", s.getStudentID()).setParameter("projectId", p.getId()).setParameter("criteriaId", c.getCriteriaId()).getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultQueryException();
        }

    }

    /**
     *
     * @param p
     * @param s
     * @return the result of the named query ProjEvaluation.avgStudentProject
     * @throws NoResultQueryException
     */
    public double evoStudentProject(Project p, Student s) throws NoResultQueryException {

        try {
            return (double) em.createNamedQuery("ProjEvaluation.avgStudentProject").setParameter("studentId", s.getStudentID()).setParameter("projectId", p.getId()).getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultQueryException();
        }

    }

    /**
     *
     * @param pl
     * @param s
     * @return
     */
    public List<Project> insertAvgProject(List<Project> pl, Student s) {
        List<Project> p = new ArrayList<>();

        for (Project pt : pl) {
            pt.setAvgProject((double) em.createNamedQuery("ProjEvaluation.avgStudentProject").setParameter("studentId", s.getStudentID()).setParameter("projectId", pt.getId()).getSingleResult());
            p.add(pt);
        }

        return p;
    }

    /**
     *
     * @param p
     * @param c
     * @param s
     * @return
     * @throws NoResultQueryException
     */
    public double evaProjectCriteria(Project p, Criteria c, Student s) throws NoResultQueryException {

        try {
            return (double) em.createNamedQuery("ProjEvaluation.evaProjectCriteria").setParameter("criteriaId", c.getCriteriaId()).setParameter("projectId", p.getId()).setParameter("studentId", s.getStudentID()).getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultQueryException();
        }

    }

    /**
     *
     * @param c
     * @param s
     * @return
     * @throws NoResultQueryException
     */
    public double evaEditionCriteria(Criteria c, Student s) throws NoResultQueryException {

        try {
            return (double) em.createNamedQuery("ProjEvaluation.evaEditionCriteria").setParameter("criteriaId", c.getCriteriaId()).setParameter("studentId", s.getStudentID()).getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultQueryException();
        }

    }

}
