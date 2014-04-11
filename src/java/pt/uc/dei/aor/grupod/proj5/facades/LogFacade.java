package pt.uc.dei.aor.grupod.proj5.facades;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import pt.uc.dei.aor.grupod.proj5.entities.Log;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.LogException;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Stateless
public class LogFacade extends AbstractFacade<Log> {

    @PersistenceContext(unitName = "AutoAvaliacaoPU")
    private EntityManager em;

    /**
     *
     * @return em
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * consctructor of logFacade
     */
    public LogFacade() {
        super(Log.class);
    }

    /**
     * this method creates a log entry on a database
     *
     * @param operation
     * @param student
     * @throws LogException
     */
    public void createLog(String operation, Student student) throws LogException {
        try {
            Log log1 = new Log();
            log1.setOperation(operation);
            log1.setTimeStamp(new Date());
            log1.setStudentID(student.getStudentID());
            em.persist(log1);

        } catch (RollbackException e) {
            throw new LogException();
        }
    }

}
