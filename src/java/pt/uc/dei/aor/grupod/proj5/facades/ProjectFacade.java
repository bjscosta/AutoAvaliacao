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
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
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

}
