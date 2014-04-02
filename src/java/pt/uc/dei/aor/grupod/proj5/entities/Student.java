package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
public class Student extends User implements Serializable {

    @Basic(optional = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long studentID;

    @ManyToMany(mappedBy = "studentsthatCantEvaluate")
    private List<Project> projects;

    @Basic(optional = false)
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

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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
    public int hashCode() {
        int hash = 0;
        hash += (studentID != null ? studentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentID == null && other.getStudentID() != null) || (this.studentID != null
                && !this.studentID.equals(other.studentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student: ";
    }

}
