package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.Criteria;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.exceptions.CreateEditionAbortedException;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;

@Named
@RequestScoped
public class EditionController {

    private List<Edition> availableEditions;

    private Edition edition;

    private String errorCreate;

    private Criteria criteria;

    private Long editionId;

    @Inject
    private EditionFacade editionFacade;

    /**
     * method that initializes atributes of EditionController
     */
    @PostConstruct
    public void init() {
        availableEditions = editionFacade.findEditionsByTheCurrentYear();
    }

    public List<Edition> getAvailableEditions() {
        availableEditions = editionFacade.findEditionsByTheCurrentYear();
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

    public void createsaCriteriaForEdition() {

        Edition e = editionFacade.findEditionById(editionId);
//        if(e!=null){
//
//        }

    }

}
