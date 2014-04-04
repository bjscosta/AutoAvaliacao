/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.exceptions;

/**
 *
 * @author User
 */
public class CriteriaNotFoundException extends Exception {

    public CriteriaNotFoundException() {
        super("Esse criterio não existe");
    }

}
