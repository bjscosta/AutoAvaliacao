/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import pt.uc.dei.aor.grupod.proj5.EJB.LoggedUserEJB;
import pt.uc.dei.aor.grupod.proj5.EJB.MailEJB;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.ProjEvaluation;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.LogException;
import pt.uc.dei.aor.grupod.proj5.exceptions.NoResultQueryException;
import pt.uc.dei.aor.grupod.proj5.exceptions.ProjEvaluationException;
import pt.uc.dei.aor.grupod.proj5.facades.LogFacade;
import pt.uc.dei.aor.grupod.proj5.facades.ProjEvaluationFacade;
import pt.uc.dei.aor.grupod.proj5.utilities.MessagesForUser;

/**
 *
 * @author User
 */
@ManagedBean
@ViewScoped
public class EvaluationController {

    @Inject
    private ProjEvaluationFacade projEvaluationFacade;

    @Inject
    private LoggedUserEJB loggedUserEJB;

    @Inject
    private MailEJB mailEJB;
    
    @Inject
    private LogFacade logFacade;

    private List<ProjEvaluation> list;

    private List<ProjEvaluation> evaluations;

    private Double avgStudentProject;

    @PostConstruct
    public void init() {
        list = projEvaluationFacade.createProjEvaluation(loggedUserEJB.getActiveProject(),
                (Student) loggedUserEJB.getLoggedUser());
        evaluations = projEvaluationFacade.getListProjEvaluation(
                (Student) loggedUserEJB.getLoggedUser(), loggedUserEJB.getActiveProject());
        try {
            Student s = (Student) loggedUserEJB.getLoggedUser();
            Project p = loggedUserEJB.getActiveProject();
            
            avgStudentProject = projEvaluationFacade.avgStudentProject(s.getStudentID(), p.getId());

        } catch (NoResultQueryException ex) {
            Logger.getLogger(EvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            MessagesForUser.addMessageError(ex.getMessage());

        }

    }

    public List<ProjEvaluation> getList() {
        return list;
    }

    public void setList(List<ProjEvaluation> list) {
        this.list = list;
    }

    public List<ProjEvaluation> getEvaluations() {
        updateEditionEvaluations();
        return evaluations;
    }

    public void setEvaluations(List<ProjEvaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Double getAvgStudentProject() {

        return avgStudentProject;

    }

    public void updateEditionEvaluations() {
        try {
            evaluations = projEvaluationFacade.getListProjEvaluation(
                    (Student) loggedUserEJB.getLoggedUser(), loggedUserEJB.getActiveProject());
            for (ProjEvaluation pe : evaluations) {

                Edition e = projEvaluationFacade.averageCriteriaEdition(pe.getCriteria().getEdition());
                pe.getCriteria().setEdition(e);

            }
        } catch (NoResultQueryException ex) {
            Logger.getLogger(EvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            MessagesForUser.addMessageError(ex.getMessage());
        }

    }

    public void updateAvgStudentProject() {
        try {
            Student s = (Student) loggedUserEJB.getLoggedUser();
            Project p = loggedUserEJB.getActiveProject();

            avgStudentProject = projEvaluationFacade.avgStudentProject(s.getStudentID(), p.getId());

        } catch (NoResultQueryException ex) {
            Logger.getLogger(EvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            MessagesForUser.addMessageError(ex.getMessage());

        }
    }

    public void setAvgStudentProject(Double avgStudentProject) {
        this.avgStudentProject = avgStudentProject;
    }
    
    
    
    public void updateProjEv() {
        list = projEvaluationFacade.createProjEvaluation(loggedUserEJB.getActiveProject(),
                (Student) loggedUserEJB.getLoggedUser());
    }

    /**
     * Submits the evaluation of a student
     * @return the string that leads to the xhtml page
     */

    public String confirmbutton() {
        
        try {
            projEvaluationFacade.confirm(list);
            MessagesForUser.addMessageInfo("Avaliação submetida com sucesso");

        } catch (ProjEvaluationException ex) {
            MessagesForUser.addMessageError(ex.getMessage());
            try {
            logFacade.createLog("Submit Evaluation Failed", (Student)loggedUserEJB.getLoggedUser());
            } catch (LogException e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            logFacade.createLog("Submit Evaluation Successful", (Student)loggedUserEJB.getLoggedUser());
        } catch (LogException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "openProjectStudent";
    }

}
