/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.facades;

import java.util.ArrayList;
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
                    ProjEvaluationException ex = new ProjEvaluationException();

                    throw new ProjEvaluationException();
                }

            }
        } catch (RollbackException exception) {
            throw new ProjEvaluationException();
        }
    }

    public List<ProjEvaluation> createProjEvaluation(Project p, Student s) {
        List<ProjEvaluation> projE = new ArrayList();
        for (Criteria c : s.getEdition().getCriteriaList()) {
            ProjEvaluation pe = new ProjEvaluation();
            pe.setCriteria(c);
            pe.setProject(p);
            pe.setStudent(s);
            projE.add(pe);
//

        }
        return projE;
    }

    public double averageEdition(Edition edition) throws NoResultQueryException {
        try {
            return (double) em.createNamedQuery("ProjEvaluation.avgOfAnEdition").setParameter("editionId", edition.getEditionId()).getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            throw new NoResultQueryException();
        }
    }

    public Edition averageCriteriaEdition(Edition edition) throws NoResultQueryException {

        for (Criteria c : edition.getCriteriaList()) {
            try {
                c.setAvgValue((Double) em.createNamedQuery("ProjEvaluation.avgOfACriteriaEdition").setParameter("criteriaId", c.getCriteriaId()).getSingleResult());
            } catch (NoResultException e) {
                throw new NoResultQueryException();
            }
        }
        return edition;

    }

    public double averageProject(Project p) throws NoResultQueryException {
        try {
            return (double) em.createNamedQuery("ProjEvaluation.avgProj").setParameter("projectId", p.getId()).getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            throw new NoResultQueryException();
        }
    }

    public Edition averageCriteriaProject(Edition edition, Project project) throws NoResultQueryException {

        for (Criteria c : edition.getCriteriaList()) {
            try {
                c.setAvgValue((Double) em.createNamedQuery("ProjEvaluation.avgOfACriteriaProject").setParameter("projectId", project.getId()).setParameter("criteriaId", c.getCriteriaId()).getSingleResult());
            } catch (NoResultException e) {
                throw new NoResultQueryException();
            }
        }
        return edition;

    }

    public double averageStudent(Student s) throws NoResultQueryException {
        try {
            return (double) em.createNamedQuery("ProjEvaluation.avgStudent").setParameter("studentId", s.getStudentID()).getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            throw new NoResultQueryException();
        }
    }

    public Edition averageStudentProject(Edition edition, Student student, Project project) throws NoResultQueryException {

        for (Criteria c : edition.getCriteriaList()) {
            try {
                c.setAvgValue((Double) em.createNamedQuery("ProjEvaluation.avgStudentProject").setParameter("studentId", student.getStudentID()).setParameter("projectId", project.getId()).setParameter("criteriaId", c.getCriteriaId()).getSingleResult());
            } catch (NoResultException e) {
                throw new NoResultQueryException();
            }
        }
        return edition;

    }

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
}
