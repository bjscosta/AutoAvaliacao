package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EDITION")
@NamedQueries({
    @NamedQuery(name = "Edition.findAll", query = "SELECT e FROM Edition e"),
    @NamedQuery(name = "Edition.findByEdition_Id", query = "SELECT e FROM Edition e WHERE e.editionId = :editionId"),
    @NamedQuery(name = "Edition.findByName", query = "SELECT e FROM Edition e WHERE e.editionName = :editionName"),
    @NamedQuery(name = "Edition.findByYearEdition", query = "SELECT e FROM Edition e WHERE e.yearEdition >= :yearEdition"),})
public class Edition implements Serializable {

    @OneToMany(mappedBy = "edition")
    private List<Student> students;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long editionId;

    @NotNull
    @Column(name = "EDITION_NAME", nullable = false)
    private String editionName;

    @Digits(integer = 4, fraction = 0, message = "Year not valid")
    @NotNull(message = "Year not valid")
    @Column(name = "YEAR_OF_EDITION", nullable = false)
    private int yearEdition;

    @NotNull
    @Column(name = "MIN_VALUE_SCALE", nullable = false)
    private int minValueScale;

    @NotNull
    @Column(name = "MAX_VALUE_SCALE", nullable = false)
    private int maxValueScale;

    @OneToMany(mappedBy = "edition")
    private List<Project> projectList;

    @OneToMany(mappedBy = "edition")
    private List<Criteria> criteriaList;

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
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

    public int getMinValueScale() {
        return minValueScale;
    }

    public void setMinValueScale(int minValueScale) {
        this.minValueScale = minValueScale;
    }

    public int getMaxValueScale() {
        return maxValueScale;
    }

    public void setMaxValueScale(int maxValueScale) {
        this.maxValueScale = maxValueScale;
    }

    public Long getEditionId() {
        return editionId;
    }

    public void setEditionId(Long editionId) {
        this.editionId = editionId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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
        return editionName;
    }

}
