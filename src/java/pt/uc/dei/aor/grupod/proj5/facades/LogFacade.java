/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author brunocosta
 */
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
            Log log = new Log();
            log.setOperation(operation);
            log.setTimeStamp(new Date());
            log.setStudent(student);
            em.persist(log);
            student.getLogEntries().add(log);
            em.merge(student);
        } catch (RollbackException e) {
            throw new LogException();
        }
    }

}
