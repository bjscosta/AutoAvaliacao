

package pt.uc.dei.aor.grupod.proj5.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.uc.dei.aor.grupod.proj5.entities.Administrator;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.PasswordNotCorrectException;
import pt.uc.dei.aor.grupod.proj5.exceptions.UserNotFoundException;
import pt.uc.dei.aor.grupod.proj5.utilities.EncriptMD5;


@Stateless
public class AdministratorFacade extends AbstractFacade<Administrator> {
    @PersistenceContext(unitName = "AutoAvaliacaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorFacade() {
        super(Administrator.class);
    }
    
    /**
     * this method finds a administrator by the email, using the namedquery
     * Student.findStudentIDById
     *
     * @param email
     *
     * @return the result of the query, if NoResultException is catched, the
     * method returns the student that was found by the query
     */
    public Administrator findAdminByEmail(String email) {

        Query q = em.createNamedQuery("Administrator.findAdministratorByEmail");

        q.setParameter("email", email);

        try {

            return (Administrator) q.getSingleResult();

        } catch (NoResultException e) {
            
            return null;

        }
    }
    
    /**
     * Checks if the Administrator exists and if the combination between Email and
     * Password is correct
     *
     * @param adminLoginEmail
     * @param adminLoginPassword
     * @return one Administrator if the login is successfull, null if unsuccessfull
     * @throws UserNotFoundException
     * @throws PasswordNotCorrectException
     */
    public Administrator login(String adminLoginEmail, String adminLoginPassword) throws UserNotFoundException, PasswordNotCorrectException {

        Administrator admin = findAdminByEmail(adminLoginEmail);

        String pass = EncriptMD5.cryptWithMD5(adminLoginPassword);
        
        if (admin!= null && admin.getPassword().equals(pass)) {

            return admin;
        } else {
            if (admin == null) {
                throw new UserNotFoundException();
            } else if (!admin.getPassword().equals(pass)) {
                throw new PasswordNotCorrectException();
            }
            return null;
        }
    }
}
