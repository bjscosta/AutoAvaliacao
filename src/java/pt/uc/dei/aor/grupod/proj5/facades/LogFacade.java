
package pt.uc.dei.aor.grupod.proj5.facades;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import pt.uc.dei.aor.grupod.proj5.entities.Log;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.LogException;



@Stateless
public class LogFacade extends AbstractFacade<Log> {

    @PersistenceContext(unitName = "AutoAvaliacaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogFacade() {
        super(Log.class);
    }

    public void createLog(String operation, Student student) throws LogException {
        try {
            Log log1 = new Log();
            log1.setOperation(operation);
            log1.setTimeStamp(new Date());
            log1.setStudent(student);
            em.persist(log1);
            student.getLogEntries();
            student.getLogEntries().add(log1);
            student.getLogEntries();
            em.merge(student);
        } catch (RollbackException e) {
            throw new LogException();
        }
    }

}
