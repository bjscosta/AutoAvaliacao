/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.aor.grupod.proj5.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.uc.dei.aor.grupod.proj5.entities.Project;

/**
 *
 * @author brunocosta
 */
@Stateless
public class ProjectFacade extends AbstractFacade<Project> {
    @PersistenceContext(unitName = "AutoAvaliacaoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectFacade() {
        super(Project.class);
    }
    
}
