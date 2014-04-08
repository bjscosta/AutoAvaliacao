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
import pt.uc.dei.aor.grupod.proj5.entities.ProjEvaluation;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;

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

}
