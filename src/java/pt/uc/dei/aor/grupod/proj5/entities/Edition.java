package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Edition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long editionId;

    @NotNull
    @Column(name = "Name_Edition", nullable = false)
    private String nameEdition;

    @Min(value = 2014, message = "Year not valid")
    @Digits(integer = 4, fraction = 0, message = "Year not valid")
    @NotNull(message = "Year not valid")
    @Column(name = "YEAR_OF_REGISTRATION", nullable = false)
    private int yearEdition;

    @Embedded
    @NotNull
    private RatingScale ratingScale;

    private List<Project> projectList;

    @OneToMany(mappedBy = "edition")
    private List<Criteria> criteriaList;

    public String getNameEdition() {
        return nameEdition;
    }

    public void setNameEdition(String nameEdition) {
        this.nameEdition = nameEdition;
    }

    public int getYearEdition() {
        return yearEdition;
    }

    public void setYearEdition(int yearEdition) {
        this.yearEdition = yearEdition;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public RatingScale getRatingScale() {
        return ratingScale;
    }

    public void setRatingScale(RatingScale ratingScale) {
        this.ratingScale = ratingScale;
    }

    public Long getEditionId() {
        return editionId;
    }

    public void setEditionId(Long editionId) {
        this.editionId = editionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (editionId != null ? editionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the editionId fields are not set
        if (!(object instanceof Edition)) {
            return false;
        }
        Edition other = (Edition) object;
        if ((this.editionId == null && other.editionId != null) || (this.editionId != null && !this.editionId.equals(other.editionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.dei.aor.grupod.proj5.entities.Edition[ id=" + editionId + " ]";
    }

}
