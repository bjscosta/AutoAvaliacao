/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class Criteria implements Serializable {

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
