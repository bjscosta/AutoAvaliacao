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
 * @author User
 */
public class MessagesForUser {

    public static void addMessageError(String m) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, m, "Erro");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public static void addMessageInfo(String m) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, m, "Aviso");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
