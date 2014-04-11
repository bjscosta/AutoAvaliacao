package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Entity
@Table(name = "ADMINISTRATOR")
@NamedQueries({
    @NamedQuery(name = "Administrator.findAllAdministrators", query = "SELECT a FROM Administrator a"),
    @NamedQuery(name = "Administrator.findAdministratorById", query = "SELECT a FROM Administrator a WHERE a.administratorID = :administratorID"),
    @NamedQuery(name = "Administrator.findAdministratorByEmail", query = "SELECT a FROM Administrator a WHERE a.email = :email")})
public class Administrator extends User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long administratorID;

    /**
     *
     * @return administratorID
     */
    public Long getAdministratorID() {
        return administratorID;
    }

    /**
     *
     * @param administratorID
     */
    public void setAdministratorID(Long administratorID) {
        this.administratorID = administratorID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (administratorID != null ? administratorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) object;
        if ((this.administratorID == null && other.administratorID != null) || (this.administratorID != null
                && !this.administratorID.equals(other.administratorID))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return the name of the administrator
     */
    @Override
    public String toString() {
        return super.getName();
    }

}
