
package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class RatingScale implements Serializable {

    private int inferiorLimit;

    private int superiorLimit;

    public RatingScale() {
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
