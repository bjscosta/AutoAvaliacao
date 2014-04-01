

package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Administrator extends User implements Serializable {
    
    @Basic(optional = false)
    @ManyToOne
    private List<Log> logEntries;

    public List<Log> getLogEntries() {
        return logEntries;
    }

    public void setLogEntries(List<Log> logEntries) {
        this.logEntries = logEntries;
    }
    
    
}
