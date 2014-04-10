/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.EJB.LoggedUserEJB;
import pt.uc.dei.aor.grupod.proj5.EJB.MailEJB;
import pt.uc.dei.aor.grupod.proj5.entities.Student;

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
    private MailEJB mailEJB;

    public void sendEmailToStudent(Student s) {
        mailEJB.sendEmailRemember(s.getEmail(), loggedUserEJB.getActiveProject());
    }
}
