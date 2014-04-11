package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Entity
@Table(name = "STUDENT")
@NamedQueries({
    @NamedQuery(name = "Student.findAllStudents", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findStudentIDById", query = "SELECT s FROM Student s WHERE s.studentID = :studentID"),
    @NamedQuery(name = "Student.findStudentByEmail", query = "SELECT s FROM Student s WHERE s.email = :email"),
    @NamedQuery(name = "Student.findStudentByYearOfRegistration", query = "SELECT s FROM Student s WHERE s.yearOfRegistration = :yearOfRegistration"),
    @NamedQuery(name = "Student.findStudentByEdition", query = "SELECT s FROM Student s WHERE s.edition = :edition")})
public class Student extends User implements Serializable {

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "student", orphanRemoval = true)
    private List<ProjEvaluation> projEvaluations;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long studentID;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "students")
    private List<Project> projects;

    @Digits(integer = 4, fraction = 0, message = "Year not valid")
    @NotNull(message = "Year not valid")
    @Column(name = "YEAR_OF_REGISTRATION", nullable = false)
    private int yearOfRegistration;

    @ManyToOne
    private Edition edition;

    /**
     *
     * @return yearOfRegistration
     */
    public int getYearOfRegistration() {
        return yearOfRegistration;
    }

    /**
     *
     * @param yearOfRegistration
     */
    public void setYearOfRegistration(int yearOfRegistration) {
        this.yearOfRegistration = yearOfRegistration;
    }

    /**
     *
     * @return studentID
     */
    public Long getStudentID() {
        return studentID;
    }

    /**
     *
     * @param studentID
     */
    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    /**
     *
     * @return projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     *
     * @param projects
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     *
     * @return edition
     */
    public Edition getEdition() {
        return edition;
    }

    /**
     *
     * @param edition
     */
    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    /**
     *
     * @return projEvaluations
     */
    public List<ProjEvaluation> getProjEvaluations() {
        return projEvaluations;
    }

    /**
     *
     * @param projEvaluations
     */
    public void setProjEvaluations(List<ProjEvaluation> projEvaluations) {
        this.projEvaluations = projEvaluations;
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

    /**
     *
     * @return studentID + " " + super.getName()
     */
    @Override
    public String toString() {
        return super.getName();
    }

}
