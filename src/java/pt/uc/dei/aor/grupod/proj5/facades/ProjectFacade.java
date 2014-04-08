/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.facades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.uc.dei.aor.grupod.proj5.entities.Criteria;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.ProjEvaluation;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.CreateProjectAbortedException;
import pt.uc.dei.aor.grupod.proj5.exceptions.ProjectListException;

/**
 *
 * @author
 */
@Stateless
public class ProjectFacade extends AbstractFacade<Project> {

    @PersistenceContext(unitName = "AutoAvaliacaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Inject
    private StudentFacade studentFacade;

    public ProjectFacade() {
        super(Project.class);
    }

    /**
     * method to create a Project
     *
     * @param p
     * @param e
     * @return
     * @throws CreateProjectAbortedException
     */
    public Project createProject(Project p, Edition e) throws CreateProjectAbortedException {
        try {
            p.setEdition(e);
            e.getProjectList().add(p);
            create(p);
            em.merge(e);
            return p;
        } catch (EJBException ex) {
            throw new CreateProjectAbortedException();
        }
    }

    /**
     * method that finds a project, using the named query
     * Project.findProjectById
     *
     * @param projectId
     * @return
     */
    public Project findProjectById(Long projectId) {
        try {
            Query q = em.createNamedQuery("Project.findProjectById");
            q.setParameter("id", projectId);
            return (Project) q.getSingleResult();

        } catch (NonUniqueResultException ex) {
            Logger.getLogger(ProjectFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new NonUniqueResultException();
        } catch (NoResultException ex) {
            Logger.getLogger(ProjectFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new NoResultException();
        }

    }

    /**
     * method that gets all projects by one edition
     *
     * @param edition
     * @return
     * @throws ProjectListException
     */
    public List<Project> getAllProjectsByEdition(Edition edition) throws ProjectListException {
        try {
            Query q = em.createNamedQuery("Project.findProjectByEdition");

            q.setParameter("edition", edition);
            return q.getResultList();
        } catch (Exception ex) {
            throw new ProjectListException();
        }
    }

    /**
     * method to find the all the projects that have the evaluation period
     * opened
     *
     * @return the result of the query
     */
    public List<Project> findOpenProjects() {

        List<Project> listProjects = em.createNamedQuery(Project.getFindAllProjects()).getResultList();
        List<Project> results = new ArrayList<>();
        Date today = new Date();
        for (Project p : listProjects) {
            if (!p.getStartingSelfEvaluationDate().after(today) && !p.getFinishingSelfEvaluationDate().before(today)) {
                results.add(p);
            }
        }
        return results;

    }

    /**
     * method to find the all the projects that the evaluation period closed
     *
     *
     * @return the result of the query
     */
    public List<Project> findClosedProjects() {

        List<Project> listProjects = em.createNamedQuery(Project.getFindAllProjects()).getResultList();
        Date today = new Date();
        List<Project> results = new ArrayList<>();
        for (Project p : listProjects) {
            if (today.before(p.getStartingSelfEvaluationDate()) || today.after(p.getFinishingSelfEvaluationDate())) {
                results.add(p);
            }

        }
        return results;

    }

    /**
     * method to get open projects of a student
     *
     * @param s
     * @return projects
     */
    public List<Project> studentOpenProjects(Student s) {
        Date today = new Date();
        List<ProjEvaluation> peList = getProjEvaluationByStudent(s);
        List<Project> projects = new ArrayList();
        for (ProjEvaluation pe : peList) {
            if (pe.getCriteriaValue() != -1) {
                Project p = pe.getProject();
                if (!p.getStartingSelfEvaluationDate().after(today) && !p.getFinishingSelfEvaluationDate().before(today)) {
                    projects.add(p);
                }
            }
        }
        return projects;
    }

    /**
     * method to get the closed projects of a student
     *
     * @param s
     * @return projects
     */
    public List<Project> studentClosedProjects(Student s) {
        Date today = new Date();
        List<ProjEvaluation> peList = getProjEvaluationByStudent(s);
        List<Project> projects = new ArrayList();
        for (ProjEvaluation pe : peList) {
            Project p = pe.getProject();
            if (today.before(p.getStartingSelfEvaluationDate()) || today.after(p.getFinishingSelfEvaluationDate())) {
                projects.add(p);
            }
        }
        return projects;
    }

    public List<ProjEvaluation> getProjEvaluationByStudent(Student s) {
        return em.createNamedQuery("ProjEvaluation.findByProject_Student")
                .setParameter("student", s).getResultList();
    }

    public void deleteStudents(Project project, List<Student> selectedStudents) {
        for (ProjEvaluation pe : project.getProjAvaliations()) {
            for (Student s : selectedStudents) {
                if (pe.getStudent().equals(s)) {
                    s.getProjEvaluations().remove(pe);
                    project.getProjAvaliations().remove(pe);
                    em.merge(s);
                    edit(project);
                }
            }
        }
    }

    public List<Student> studentsNotInProject(Project p) {
        List<Student> allStudentsEdition = studentFacade.findStudentsByEdition(p.getEdition());

        for (Student s : allStudentsEdition) {
            for (ProjEvaluation pe : p.getProjAvaliations()) {
                if (s.equals(pe.getStudent())) {
                    allStudentsEdition.remove(s);
                }
            }
        }
        return allStudentsEdition;
    }

    public void addStudentsProject(Project p, List<Student> sl) {
        for (Criteria c : p.getEdition().getCriteriaList()) {
            for (Student s : sl) {
                ProjEvaluation pe = new ProjEvaluation();
                pe.setCriteria(c);
                pe.setProject(p);
                pe.setStudent(s);
                pe.setCriteriaValue(-1);
                em.persist(pe);
            }
        }

    }
}
