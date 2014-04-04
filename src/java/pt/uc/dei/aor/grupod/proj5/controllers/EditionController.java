package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIForm;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.Criteria;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.exceptions.CreateEditionAbortedException;
import pt.uc.dei.aor.grupod.proj5.facades.CriteriaFacade;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;

@Named
@RequestScoped
public class EditionController {
    
    @Inject
    private EditionFacade editionFacade;
    
    @Inject
    private CriteriaFacade criteriaFacade;
    
    private List<Edition> availableEditions;
    private Edition edition;
    private String errorCreate;
    private Criteria criteria;
    private Long editionId;
    private UIForm editions;
    private UIForm newEdition;
    private UIForm createCriteria;

    

    /**
     * method that initializes atributes of EditionController
     */
    @PostConstruct
    public void init() {
        availableEditions = editionFacade.findAllEditions();
        edition = new Edition();
        criteria = new Criteria();
    }

    public List<Edition> getAvailableEditions() {
        availableEditions = editionFacade.findAllEditions();
        return availableEditions;
    }

    public void setAvailableEditions(List<Edition> availableEditions) {
        this.availableEditions = availableEditions;
    }

    public EditionFacade getEditionFacade() {
        return editionFacade;
    }

    public void setEditionFacade(EditionFacade editionFacade) {
        this.editionFacade = editionFacade;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public String getErrorCreate() {
        return errorCreate;
    }

    public void setErrorCreate(String errorCreate) {
        this.errorCreate = errorCreate;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Long getEditionId() {
        return editionId;
    }

    public void setEditionId(Long editionId) {
        this.editionId = editionId;
    }

    public UIForm getEditions() {
        return editions;
    }

    public void setEditions(UIForm editions) {
        this.editions = editions;
    }

    public UIForm getNewEdition() {
        return newEdition;
    }

    public void setNewEdition(UIForm newEdition) {
        this.newEdition = newEdition;
    }

    public UIForm getCreateCriteria() {
        return createCriteria;
    }

    public void setCreateCriteria(UIForm createCriteria) {
        this.createCriteria = createCriteria;
    }
    

    

    /**
     * this method creates an edition to the database, uses the method
     * createsEdition of the editionFacade, if it can't create catches the
     * CreateEditionAbortedException
     *
     * @param e
     */
    public void createEdition(Edition e) {

        try {

            edition = editionFacade.createsEdition(e);

        } catch (CreateEditionAbortedException ex) {

            errorCreate = ex.getMessage();

            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     * selected edition and saves the editionId
     *
     * @param e
     */
    public void selectesEdition(Edition e) {

        editionId = e.getEditionId();

    }
    
    /**
     * Changes to the new Edition view
     */
    public void goToNewEdition(){
        editions.setRendered(false);
        newEdition.setRendered(true);
    }
    
    /**
     * Opens the create criteria area
     */
    public void goToCreateCriteria(){
        createCriteria.setRendered(true);
        createEdition(edition);
    }

    public void createsaCriteriaForEdition() {

        //Edition e = editionFacade.findEditionById(editionId);
//        if(e!=null){
//
//        }
        criteriaFacade.createsCriteria(criteria, edition);

    }
    
     

}
