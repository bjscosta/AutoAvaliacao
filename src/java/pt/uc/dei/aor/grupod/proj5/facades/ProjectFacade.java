/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.facades;

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
 * @author brunocosta
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
     * mathod that finds a project, using the named query
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

    public List<Project> getAllProjectsByEdition(Edition edition) throws ProjectListException {
        try {
            Query q = em.createNamedQuery("Project.findProjectByEditionId");
            q.setParameter("edition", edition);
            return q.getResultList();
        } catch (Exception ex) {
            throw new ProjectListException();
        }
    }

}
