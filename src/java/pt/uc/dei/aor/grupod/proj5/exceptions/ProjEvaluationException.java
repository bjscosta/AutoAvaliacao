/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.exceptions;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
public class ProjEvaluationException extends Exception {

    public ProjEvaluationException() {
        super("Não foi possivel gravar as suas avaliações");
    }
}
