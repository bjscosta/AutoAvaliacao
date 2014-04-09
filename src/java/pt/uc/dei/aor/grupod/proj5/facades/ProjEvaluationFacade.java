/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import pt.uc.dei.aor.grupod.proj5.entities.Criteria;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.ProjEvaluation;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.ProjEvaluationException;

/**
 *
 * @author brunocosta
 */
@Stateless
public class ProjEvaluationFacade extends AbstractFacade<ProjEvaluation> {

    @PersistenceContext(unitName = "AutoAvaliacaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjEvaluationFacade() {
        super(ProjEvaluation.class);
    }

    public List<ProjEvaluation> getListProjEvaluation(Student s, Project p) {
        return em.createNamedQuery("ProjEvaluation.findByProjectAndStudent")
                .setParameter("student", s).setParameter("project", p).getResultList();
    }

    public void setToZeroEvaluations(Student s, Project p) {
        List<ProjEvaluation> projEva = evaluationsOfStudentAndProject(s, p);
        for (ProjEvaluation pe : projEva) {
            if (pe.getProject().equals(p)) {
                pe.setCriteriaValue(0);
                em.merge(pe);
            }
        }
    }

    public List<ProjEvaluation> evaluationsOfStudentAndProject(Student s, Project p) {
        return em.createNamedQuery("ProjEvaluation.userEvaluation")
                .setParameter("student", s).setParameter("project", p).getResultList();
    }

    public void confirm(List<ProjEvaluation> pelist) throws ProjEvaluationException {
        try {
            for (ProjEvaluation pe : pelist) {
                
                em.merge(pe);
                em.merge(pe.getStudent());
            }
        } catch (RollbackException e) {
            throw new ProjEvaluationException();
        }
    }
    
    public double averageEdition(Edition edition){
        return (double) em.createNamedQuery("ProjEvaluation.avgOfAnEdition").setParameter("editionId", edition.getEditionId()).getSingleResult();
        
    }
    
    public Edition averageCriteriaEdition (Edition edition){
        
           
           for(Criteria c : edition.getCriteriaList()){
            c.setAvgValue((Double)em.createNamedQuery("ProjEvaluation.avgOfACriteriaEdition").setParameter("criteriaId", c.getCriteriaId()).getSingleResult());
       
           }
         return edition;
           
    }
    
    public double averageProject(Project p){
        return (double) em.createNamedQuery("ProjEvaluation.avgProj").setParameter("project.id", p.getId()).getSingleResult();
    }
    
    public Edition averageCriteriaProject (Edition edition, Project project){
        
           
           for(Criteria c : edition.getCriteriaList()){
            c.setAvgValue((Double)em.createNamedQuery("ProjEvaluation.avgOfACriteriaProject").setParameter("projectId", project.getId()).setParameter("criteriaId", c.getCriteriaId()).getSingleResult());
       
           }
         return edition;
           
    }

}
