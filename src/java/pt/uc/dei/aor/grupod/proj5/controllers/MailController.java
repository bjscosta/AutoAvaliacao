/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.EJB.LoggedUserEJB;
import pt.uc.dei.aor.grupod.proj5.EJB.MailEJB;
import pt.uc.dei.aor.grupod.proj5.entities.ProjEvaluation;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.facades.ProjEvaluationFacade;
import pt.uc.dei.aor.grupod.proj5.utilities.MessagesForUser;

/**
 *
 * @author User
 */
@Named
@RequestScoped
public class MailController {

    @Inject
    private LoggedUserEJB loggedUserEJB;

    @Inject
    private ProjEvaluationFacade projEvaluationFacade;

    @Inject
    private MailEJB mailEJB;

    public void sendEmailToStudent(Student s) {
        Project p = loggedUserEJB.getActiveProject();
        List<ProjEvaluation> list = projEvaluationFacade.evaluationsOfStudentAndProject(s, p);
        if (list.isEmpty()) {
            mailEJB.sendEmailRemember(s.getEmail(), loggedUserEJB.getActiveProject());
        } else {
            MessagesForUser.addMessageError("Este estudante já avaliou o projecto");
        }
    }
}
