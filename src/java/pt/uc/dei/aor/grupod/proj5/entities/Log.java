package pt.uc.dei.aor.grupod.proj5.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Entity

public class Log implements Serializable {

    private Long studentID;
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "STUDENT_OPERATION", nullable = false)
    private String operation;

    @NotNull
    @Column(name = "TIME_STAMP", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timeStamp;

    /**
     *
     * @return operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     *
     * @param operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
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
     * @return new Date(timeStamp.getTime())
     */
    public Date getTimeStamp() {
        return new Date(timeStamp.getTime());
    }

    /**
     *
     * @param timeStamp
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = new Date(timeStamp.getTime());
    }

    /**
     *
     * @return studentID
     */
    public Long getStudentID() {
        return studentID;
    }

    /**
     *
     * @param studentID
     */
    public void setStudentID(Long studentID) {
        this.studentID = studentID;
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
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return id+" " + operation+ " "+studentID
     */
    @Override
    public String toString() {
        return id + " " + operation + " " + studentID;
    }

}
