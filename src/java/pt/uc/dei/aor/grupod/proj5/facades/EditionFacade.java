/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.facades;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
import pt.uc.dei.aor.grupod.proj5.entities.ProjEvaluation;
import pt.uc.dei.aor.grupod.proj5.exceptions.CreateEditionAbortedException;
import pt.uc.dei.aor.grupod.proj5.exceptions.RemoveEditionAborted;

/**
 *
 * @author brunocosta
 */
@Stateless
public class EditionFacade extends AbstractFacade<Edition> {

    private String error;

    private static final String errorFindingEditions = "Não existem resultados de edições nesses parametros";

    @PersistenceContext(unitName = "AutoAvaliacaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EditionFacade() {
        super(Edition.class);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static String getErrorFindingEditions() {
        return errorFindingEditions;
    }

    /**
     * this method finds all editions in the database, using the named query
     * Edition.findAll
     *
     * @return the result list of the query
     */
    public List<Edition> findAllEditions() {
        try {
            Query q = em.createNamedQuery("Edition.findAll");
            return q.getResultList();
        } catch (Exception e) {
            error = errorFindingEditions;
            Logger.getLogger(EditionFacade.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }

    /**
     * this method finds edition by the editionId, using the named query
     * Edition.findByEdition_Id
     *
     * @param editionId
     * @return the single result of the query, if the query has no results or
     * has more than, this method return null
     */
    public Edition findEditionById(Long editionId) {

        try {

            Query q = em.createNamedQuery("Edition.findByEdition_Id");

            return (Edition) q.getSingleResult();

        } catch (NoResultException | NonUniqueResultException e) {
            error = errorFindingEditions;
            Logger.getLogger(EditionFacade.class.getName()).log(Level.SEVERE, null, e);
            return null;

        }
    }

    /**
     * this method finds a list of the edition by their yearEdition, the method
     * gets the current year, and uses the named query Edition.findByYearEdition
     *
     * @return the result list of the query and null if the query is
     * unsuccessfull
     */
    public List<Edition> findEditionsByTheCurrentYear() {

        try {

            GregorianCalendar gc = new GregorianCalendar();
            Query q = em.createNamedQuery("Edition.findByYearEdition");
            q.setParameter("yearEdition", gc.get(Calendar.YEAR));
            return q.getResultList();

        } catch (NoResultException | NonUniqueResultException e) {
            error = errorFindingEditions;
            Logger.getLogger(EditionFacade.class.getName()).log(Level.SEVERE, null, e);
            return null;

        }
    }

    /**
     * this method finds a list of editions by their name, using the named query
     * Edition.findByName
     *
     * @param editionName
     * @return the result list of the query, if the query is successfull
     */
    public List<Edition> findEditionByName(String editionName) {
        try {

            Query q = em.createNamedQuery("Edition.findByName");
            q.setParameter("editionName", editionName);
            return q.getResultList();

        } catch (Exception e) {
            error = errorFindingEditions;
            Logger.getLogger(EditionFacade.class.getName()).log(Level.SEVERE, null, e);
            return null;

        }
    }

    /**
     * persists the edition into the database
     *
     * @param e
     * @return
     * @throws CreateEditionAbortedException
     */
    public Edition createsEdition(Edition e) throws CreateEditionAbortedException {

        try {

            create(e);
            return e;

        } catch (EJBException ex) {

            CreateEditionAbortedException exception = new CreateEditionAbortedException();
            error = exception.getMessage();
            throw exception;

        }
    }

    /**
     * this method checks if the edition to remove doesn't have ProjEvaluations,
     * and removes it
     *
     * @param e
     * @throws RemoveEditionAborted
     */
    public void removesEdition(Edition e) throws RemoveEditionAborted {

        try {
            Query q = em.createNamedQuery("ProjEvaluation.findByEdition");
            q.setParameter("edition", e);
            List<ProjEvaluation> listProjEvaluation = q.getResultList();
            if (listProjEvaluation != null) {
                throw new RemoveEditionAborted();
            }
            remove(e);
        } catch (Exception ex) {
            throw new RemoveEditionAborted();
        }

    }

}
