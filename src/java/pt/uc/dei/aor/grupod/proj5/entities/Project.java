

package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NotNull
    @Column(name = "PROJECT_NAME")
    private String name;
    
    @NotNull
    @Column(name = "PROJECT_EDITION")
    private Edition edition;
    
    @OneToMany
    @Column(name = "PROJECT_AVALIATIONS")
    private List<ProjEvaluation> projAvaliations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public List<ProjEvaluation> getProjAvaliations() {
        return projAvaliations;
    }

    public void setProjAvaliations(List<ProjEvaluation> projAvaliations) {
        this.projAvaliations = projAvaliations;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.dei.aor.grupod.proj5.entities.Project[ id=" + id + " ]";
    }
    
}
