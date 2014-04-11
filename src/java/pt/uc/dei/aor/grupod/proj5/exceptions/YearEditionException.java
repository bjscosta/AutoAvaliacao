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
public class YearEditionException extends Exception {

    public YearEditionException(int year) {
        super("O ano da edição não pode ser menor que " + year);
    }

}
