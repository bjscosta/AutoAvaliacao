package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Entity
@Table(name = "PROJECT")
@NamedQueries({
    @NamedQuery(name = "Project.findAllProjects", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findProjectById", query = "SELECT p FROM Project p WHERE p.id = :id"),
    @NamedQuery(name = "Project.findProjectByName", query = "SELECT p FROM Project p WHERE p.name = :name"),
    @NamedQuery(name = "Project.findProjectByEditionId", query = "SELECT p FROM Project p WHERE p.edition = :edition"),})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String findAllProjects = "Project.findAllProjects";

    private static final String findProjectById = "Project.findProjectById";

    private static final String findProjectByName = "Project.findProjectByName";

    private static final String findProjectByEditionId = "Project.findProjectByEditionId";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "PROJECT_NAME", nullable = false)
    private String name;

    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "STARTING_SELF_EVALUATION", nullable = false)
    private Date startingSelfEvaluationDate;

    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "FINISHING_SELF_EVALUATION", nullable = false)
    private Date finishingSelfEvaluationDate;

    @NotNull
    @ManyToOne
    private Edition edition;

    @OneToMany(mappedBy = "project")
    private List<ProjEvaluation> projAvaliations;

    @Transient
    private double avgProject;

    @ManyToMany
    private List<Student> students;

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @return projAvaliations
     */
    public List<ProjEvaluation> getProjAvaliations() {
        return projAvaliations;
    }

    /**
     *
     * @param projAvaliations
     */
    public void setProjAvaliations(List<ProjEvaluation> projAvaliations) {
        this.projAvaliations = projAvaliations;
    }

    /**
     *
     * @return students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     *
     * @param students
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     *
     * @return new Date(startingSelfEvaluationDate.getTime())
     */
    public Date getStartingSelfEvaluationDate() {
        return new Date(startingSelfEvaluationDate.getTime());
    }

    /**
     *
     * @param startingSelfEvaluationDate
     */
    public void setStartingSelfEvaluationDate(Date startingSelfEvaluationDate) {
        this.startingSelfEvaluationDate = new Date(startingSelfEvaluationDate.getTime());
    }

    /**
     *
     * @return new Date(finishingSelfEvaluationDate.getTime())
     */
    public Date getFinishingSelfEvaluationDate() {
        return new Date(finishingSelfEvaluationDate.getTime());
    }

    /**
     *
     * @param finishingSelfEvaluationDate
     */
    public void setFinishingSelfEvaluationDate(Date finishingSelfEvaluationDate) {
        this.finishingSelfEvaluationDate = new Date(finishingSelfEvaluationDate.getTime());
    }

    /**
     *
     * @return findAllProjects
     */
    public static String getFindAllProjects() {
        return findAllProjects;
    }

    /**
     *
     * @return findProjectById
     */
    public static String getFindProjectById() {
        return findProjectById;
    }

    /**
     *
     * @return findProjectByName
     */
    public static String getFindProjectByName() {
        return findProjectByName;
    }

    /**
     *
     * @return findProjectByEditionId
     */
    public static String getFindProjectByEditionId() {
        return findProjectByEditionId;
    }

    /**
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return avgProject
     */
    public double getAvgProject() {
        return avgProject;
    }

    /**
     *
     * @param avgProject
     */
    public void setAvgProject(double avgProject) {
        this.avgProject = avgProject;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return id + " " + name
     */
    @Override
    public String toString() {
        return name;
    }

}
