

package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Administrator extends User implements Serializable {
    @ManyToOne
    private Log log;
    
    
}
