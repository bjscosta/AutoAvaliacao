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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Entity
@Table(name = "CRITERIA")
@NamedQueries({
    @NamedQuery(name = "Criteria.findAll", query = "SELECT c FROM Criteria c"),
    @NamedQuery(name = "Criteria.findByCriteria_Id", query = "SELECT c FROM Criteria c WHERE c.criteriaId = :criteriaId"),
    @NamedQuery(name = "Criteria.findByName", query = "SELECT c FROM Criteria c WHERE c.criteriaName = :criteriaName"),
    @NamedQuery(name = "Criteria.findByEdition", query = "SELECT c FROM Criteria c WHERE c.edition = :edition"),})
public class Criteria implements Serializable {

    @OneToMany(mappedBy = "criteria")
    private List<ProjEvaluation> projEvaluations;

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

    @Transient
    private double avgValue;

    @ManyToOne
    private Edition edition;

    /**
     *
     * @return criteriaId
     */
    public Long getCriteriaId() {
        return criteriaId;
    }

    /**
     *
     * @param criteriaId
     */
    public void setCriteriaId(Long criteriaId) {
        this.criteriaId = criteriaId;
    }

    /**
     *
     * @return criteriaName
     */
    public String getCriteriaName() {
        return criteriaName;
    }

    /**
     *
     * @param criteriaName
     */
    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    /**
     *
     * @return criteriaDiscription
     */
    public String getCriteriaDiscription() {
        return criteriaDiscription;
    }

    /**
     *
     * @param criteriaDiscription
     */
    public void setCriteriaDiscription(String criteriaDiscription) {
        this.criteriaDiscription = criteriaDiscription;
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

    /**
     *
     * @return avgValue
     */
    public double getAvgValue() {
        return avgValue;
    }

    /**
     *
     * @param avgValue
     */
    public void setAvgValue(double avgValue) {
        this.avgValue = avgValue;
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

    /**
     *
     * @return criteriaId + " " + criteriaName
     */
    @Override
    public String toString() {
        return criteriaName;
    }

}
