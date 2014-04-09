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
import pt.uc.dei.aor.grupod.proj5.entities.ProjEvaluation;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.ProjEvaluationException;
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

    private boolean evaluated;

    private List<ProjEvaluation> list;

    @PostConstruct
    public void init() {
        list = projEvaluationFacade
                .evaluationsOfStudentAndProject((Student) loggedUserEJB.getLoggedUser(),
                        loggedUserEJB.getActiveProject());
        evaluated = false;

    }

    public List<ProjEvaluation> getList() {
        return list;
    }

    public void setList(List<ProjEvaluation> list) {
        this.list = list;
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    public void updateProjEv() {
        list = projEvaluationFacade
                .evaluationsOfStudentAndProject((Student) loggedUserEJB.getLoggedUser(),
                        loggedUserEJB.getActiveProject());
    }

    public String confirm() {
        try {
            projEvaluationFacade.confirm(list);

        } catch (ProjEvaluationException ex) {
            MessagesForUser.addMessage(ex.getMessage());
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "openProjectStudent";

    }
}
