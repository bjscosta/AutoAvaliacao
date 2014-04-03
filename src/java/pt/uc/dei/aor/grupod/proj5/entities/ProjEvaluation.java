package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROJ_EVALUATION")
@NamedQueries({
    @NamedQuery(name = "ProjEvaluation.findAll", query = "SELECT pe FROM ProjEvaluation pe"),
    @NamedQuery(name = "ProjEvaluation.findById", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.id = :id"),
    @NamedQuery(name = "ProjEvaluation.findByStudent", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.student = :student"),
    @NamedQuery(name = "ProjEvaluation.findByProject", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.project = :project"),
    @NamedQuery(name = "ProjEvaluation.findByEdition", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.project.edition = :edition"),
    @NamedQuery(name = "ProjEvaluation.findByProject_Student", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.project = :project AND pe.student = :student")
})
public class ProjEvaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CRITERIA_VALUE")
    private double criteriaValue;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "projEvaluation")
    private List<Criteria> criteriaList;

    public double getCriteriaValue() {
        return criteriaValue;
    }

    public void setCriteriaValue(double criteriaValue) {
        this.criteriaValue = criteriaValue;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjEvaluation)) {
            return false;
        }
        ProjEvaluation other = (ProjEvaluation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.dei.aor.grupod.proj5.entities.ProjAvaliation[ id=" + id + " ]";
    }

}
