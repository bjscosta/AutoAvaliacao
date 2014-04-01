
package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Student extends User implements Serializable {
    
    @Basic(optional = false)
    @Min(value = 2014, message = "Year not valid")
    @Digits(integer = 4, fraction = 0, message = "Year not valid")
    @NotNull(message = "Year not valid")
    @Column(name = "YEAR_OF_REGISTRATION", nullable = false)
    private int yearOfRegistration;
    
    @Basic(optional = false)
    @ManyToOne
    private Edition edition;
    
    @Basic(optional = false)
    
    @OneToMany(mappedBy = "student")
    private List<Log> logEntries;

    public int getYearOfRegistration() {
        return yearOfRegistration;
    }

    public void setYearOfRegistration(int yearOfRegistration) {
        this.yearOfRegistration = yearOfRegistration;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public List<Log> getLogEntries() {
        return logEntries;
    }

    public void setLogEntries(List<Log> logEntries) {
        this.logEntries = logEntries;
    }
    
    
    
    @Override
    public String toString() {
        return "Student: ";
    }

}
