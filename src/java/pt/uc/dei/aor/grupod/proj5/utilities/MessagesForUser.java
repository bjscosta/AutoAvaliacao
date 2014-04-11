/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
public class MessagesForUser {

    /**
     * this method is to send messages of error to user
     *
     * @param m
     */
    public static void addMessageError(String m) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, m, "Erro");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * this method is to send messages of info to user
     *
     * @param m
     */
    public static void addMessageInfo(String m) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, m, "Aviso");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
