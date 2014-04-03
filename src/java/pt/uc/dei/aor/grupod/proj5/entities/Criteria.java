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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CRITERIA")
@NamedQueries({
    @NamedQuery(name = "Criteria.findAll", query = "SELECT c FROM Criteria c"),
    @NamedQuery(name = "Criteria.findByCriteria_Id", query = "SELECT c FROM Criteria c WHERE c.criteriaId = :criteriaId"),
    @NamedQuery(name = "Criteria.findByName", query = "SELECT c FROM Criteria c WHERE c.criteriaName = :criteriaName"),
    @NamedQuery(name = "Criteria.findByEdition", query = "SELECT c FROM Criteria c WHERE c.edition = :edition"),})
public class Criteria implements Serializable {

    @ManyToOne
    private ProjEvaluation projEvaluation;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long criteriaId;

    @NotNull
    @Column(name = "CRITERIA_NAME", nullable = false)
    private String criteriaName;

    @NotNull
    @Column(name = "CRITERIA_DISCRIPTION", nullable = false)
    private String criteriaDiscription;

    @ManyToOne
    private Edition edition;

    public Long getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(Long criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public String getCriteriaDiscription() {
        return criteriaDiscription;
    }

    public void setCriteriaDiscription(String criteriaDiscription) {
        this.criteriaDiscription = criteriaDiscription;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (criteriaId != null ? criteriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the criteriaId fields are not set
        if (!(object instanceof Criteria)) {
            return false;
        }
        Criteria other = (Criteria) object;
        if ((this.criteriaId == null && other.criteriaId != null) || (this.criteriaId != null && !this.criteriaId.equals(other.criteriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.dei.aor.grupod.proj5.entities.Criteria[ id=" + criteriaId + " ]";
    }

}
