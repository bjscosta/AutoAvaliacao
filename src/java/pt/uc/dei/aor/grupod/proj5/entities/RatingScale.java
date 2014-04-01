/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.entities;

/**
 *
 * @author User
 */
public class RatingScale {

    private int inferiorLimit;

    private int superiorLimit;

    public RatingScale(int inferiorLimit, int superiorLimit) {
        this.inferiorLimit = inferiorLimit;
        this.superiorLimit = superiorLimit;
    }

    public int getInferiorLimit() {
        return inferiorLimit;
    }

    public void setInferiorLimit(int inferiorLimit) {
        this.inferiorLimit = inferiorLimit;
    }

    public int getSuperiorLimit() {
        return superiorLimit;
    }

    public void setSuperiorLimit(int superiorLimit) {
        this.superiorLimit = superiorLimit;
    }

}
