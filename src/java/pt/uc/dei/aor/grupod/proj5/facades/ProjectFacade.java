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
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.ProjEvaluation;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.CreateProjectAbortedException;
import pt.uc.dei.aor.grupod.proj5.exceptions.ProjectListException;
import pt.uc.dei.aor.grupod.proj5.utilities.MessagesForUser;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Stateless
public class ProjectFacade extends AbstractFacade<Project> {

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

    @Inject
    private StudentFacade studentFacade;

    /**
     *
     */
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
     * list od the projects evaluated
     *
     * @param s
     * @return projects
     */
    public List<Project> projectsEvaluated(Student s) {
        List<ProjEvaluation> list = em.createNamedQuery("ProjEvaluation.findByStudent")
                .setParameter("student", s).getResultList();
        List<Project> projects = new ArrayList();
        for (ProjEvaluation pe : list) {
            if (!projects.contains(pe.getProject())) {
                projects.add(pe.getProject());
            }
        }
        return projects;
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
        Edition e = s.getEdition();
        List<Project> projects = new ArrayList();
        List<Project> listOpen = openProjectsToEvaluateStudent(s);
        for (Project p1 : e.getProjectList()) {
            if (!listOpen.contains(p1)) {
                projects.add(p1);
            }
        }
        return projects;
    }

    /**
     * method to find all the projevaluations of a student
     *
     * @param s
     * @return the result of the named query
     * ProjEvaluation.findByProject_Student
     */
    public List<ProjEvaluation> getProjEvaluationByStudent(Student s) {
        return em.createNamedQuery("ProjEvaluation.findByProject_Student")
                .setParameter("student", s).getResultList();
    }

    /**
     * method to delete students from a project
     *
     * @param project
     * @param selectedStudents
     */
    public void deleteStudents(Project project, List<Student> selectedStudents) {
        Query q = em.createNamedQuery("ProjEvaluation.userEvaluation").setParameter("project", project);
        List<ProjEvaluation> pe;
        for (Student s : selectedStudents) {
            q.setParameter("student", s);
            pe = q.getResultList();
            if (pe.isEmpty()) {
                project.getStudents().remove(s);
                s.getProjects().remove(project);
                em.merge(s);
                em.merge(project);
            } else {
                MessagesForUser.addMessageError("Estudante " + s.getName() + " não pode ser eliminado");
            }

        }
    }

    /**
     * method to find the student not in the project
     *
     * @param p
     * @return students
     */
    public List<Student> studentsNotInProject(Project p) {

        List<Student> lS = em.createNamedQuery("Student.findStudentByEdition")
                .setParameter("edition", p.getEdition()).getResultList();
        List<Student> students = new ArrayList<>();

        for (Student s : lS) {

            if (!p.getStudents().contains(s)) {
                students.add(s);
            }

        }

        return students;
    }

    /**
     * method to add a list of student to a project
     *
     * @param p
     * @param sl
     */
    public void addStudentsProject(Project p, List<Student> sl) {

        for (Student s : sl) {
            p.getStudents().add(s);
            s.getProjects().add(p);
            em.merge(p);
            em.merge(s);
        }

    }

    /**
     * method to see the open Projects of a student
     *
     * @param s
     * @return openProjects
     */
    public List<Project> openProjectsToEvaluateStudent(Student s) {
        List<Project> openProjects = new ArrayList();

        Query query = em.createNamedQuery("ProjEvaluation.userEvaluation", ProjEvaluation.class
        );
        query.setParameter(
                "student", s);

        Date d = new Date();
        for (int i = 0;
                i < s.getProjects()
                .size(); i++) {
            Project a = s.getProjects().get(i);
            if (!a.getStartingSelfEvaluationDate().after(d) && !a.getFinishingSelfEvaluationDate().before(d)) {
                query.setParameter("project", a);

                List<ProjEvaluation> evaluations = query.getResultList();

                if (evaluations.isEmpty()) {
                    openProjects.add(a);
                }

            }
        }

        return openProjects;
    }

    /**
     * method to remove a list of projects
     *
     * @param pl
     */
    public void removeProjectList(List<Project> pl) {
        for (Project pr : pl) {
            if (pr.getProjAvaliations().isEmpty()) {
                pr.getEdition().getProjectList().remove(pr);
                for (Student s : pr.getStudents()) {
                    s.getProjects().remove(pr);
                }
                remove(pr);
            } else {
                MessagesForUser.addMessageInfo("Não pode apagar o projeto " + pr.getName() + ".");
            }
        }
    }

    /**
     * method to remove a project
     *
     * @param p
     */
    public void removeProject(Project p) {

        if (p.getProjAvaliations().isEmpty()) {
            p.getEdition().getProjectList().remove(p);
            for (Student s : p.getStudents()) {
                s.getProjects().remove(p);
            }
            Edition e = p.getEdition();
            e.getProjectList().remove(p);
            em.merge(e);
            remove(p);
        } else {
            MessagesForUser.addMessageInfo("Não pode apagar o projeto " + p.getName() + ".");
        }

    }

}
