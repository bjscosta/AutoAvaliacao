package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PROJ_EVALUATION")
@NamedQueries({
    @NamedQuery(name = "ProjEvaluation.findAll", query = "SELECT pe FROM ProjEvaluation pe"),
    @NamedQuery(name = "ProjEvaluation.findById", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.id = :id"),
    @NamedQuery(name = "ProjEvaluation.findByStudent", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.student = :student"),
    @NamedQuery(name = "ProjEvaluation.findByProject", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.project = :project"),
    @NamedQuery(name = "ProjEvaluation.findByEdition", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.project.edition = :edition"),
    @NamedQuery(name = "ProjEvaluation.findByProject_Student", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.student = :student"),
    @NamedQuery(name = "ProjEvaluation.userEvaluation", query = "SELECT pe FROM ProjEvaluation pe WHERE pe.project = :project AND pe.student = :student"),
    @NamedQuery(name = "ProjEvaluation.avgOfAnEdition", query = "SELECT avg(e.criteriaValue) FROM ProjEvaluation e GROUP BY e.project.edition.editionId HAVING e.project.edition.editionId = :editionId"),
    @NamedQuery(name = "ProjEvaluation.avgOfACriterion", query = "SELECT avg(e.criteriaValue), c FROM ProjEvaluation e, Criteria c GROUP BY e.criteria HAVING e.criteria.criteriaId = :criteria")
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

    @ManyToOne
    private Criteria criteria;

    private boolean evaluation;

    public boolean isEvaluation() {
        return evaluation;
    }

    public void setEvaluation(boolean evaluation) {
        this.evaluation = evaluation;
    }

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

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
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
