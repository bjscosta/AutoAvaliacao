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
public class RatingScaleException extends Exception {

    public RatingScaleException() {
        super("O limite minimo não pode ser maior ou igual que o limite superior");
    }

}
