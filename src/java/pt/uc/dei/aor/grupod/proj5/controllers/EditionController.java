package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.EJB.LoggedUserEJB;
import pt.uc.dei.aor.grupod.proj5.entities.Criteria;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.exceptions.CreateEditionAbortedException;
import pt.uc.dei.aor.grupod.proj5.exceptions.OperationEditionAborted;
import pt.uc.dei.aor.grupod.proj5.exceptions.RatingScaleException;
import pt.uc.dei.aor.grupod.proj5.facades.CriteriaFacade;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;
import pt.uc.dei.aor.grupod.proj5.utilities.MessagesForUser;

@Named
@RequestScoped
public class EditionController {

    @Inject
    private EditionFacade editionFacade;

    @Inject
    private CriteriaFacade criteriaFacade;

    @Inject
    private LoggedUserEJB loggedUserEJB;

    private List<Edition> availableEditions;
    private Edition edition;
    private String errorCreate;
    private Criteria criteria;
    private Long editionId;
    private UIForm editions;
    private UIForm newEdition;
    private UIForm createCriteria;
    private String operationEditionError;
    private List<Criteria> criteriaList;
    private UIForm formSaveEditionCriteriaHide;
    private UIForm formSaveEditionCriteriaShowing;
    private UIComponent addCriteriaButton;
    private UIComponent createCriteriaArea;
    private Edition selectedEdition;
    private UIComponent vazio;

    /**
     * method that initializes atributes of EditionController
     */
    @PostConstruct
    public void init() {
        availableEditions = editionFacade.findEditionsByTheCurrentYear();
        if (loggedUserEJB.getActiveEdition() == null) {
            edition = new Edition();
        } else {
            edition = loggedUserEJB.getActiveEdition();
        }
        criteria = new Criteria();

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

    public String getOperationEditionError() {
        return operationEditionError;
    }

    public void setOperationEditionError(String operationEditionError) {
        this.operationEditionError = operationEditionError;
    }

    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public CriteriaFacade getCriteriaFacade() {
        return criteriaFacade;
    }

    public void setCriteriaFacade(CriteriaFacade criteriaFacade) {
        this.criteriaFacade = criteriaFacade;
    }

    public LoggedUserEJB getLoggedUserEJB() {
        return loggedUserEJB;
    }

    public void setLoggedUserEJB(LoggedUserEJB loggedUserEJB) {
        this.loggedUserEJB = loggedUserEJB;
    }

    public UIForm getFormSaveEditionCriteriaHide() {
        return formSaveEditionCriteriaHide;
    }

    public void setFormSaveEditionCriteriaHide(UIForm formSaveEditionCriteriaHide) {
        this.formSaveEditionCriteriaHide = formSaveEditionCriteriaHide;
    }

    public UIForm getFormSaveEditionCriteriaShowing() {
        return formSaveEditionCriteriaShowing;
    }

    public void setFormSaveEditionCriteriaShowing(UIForm formSaveEditionCriteriaShowing) {
        this.formSaveEditionCriteriaShowing = formSaveEditionCriteriaShowing;
    }

    public UIComponent getAddCriteriaButton() {
        return addCriteriaButton;
    }

    public void setAddCriteriaButton(UIComponent addCriteriaButton) {
        this.addCriteriaButton = addCriteriaButton;
    }

    public Edition getSelectedEdition() {
        loggedUserEJB.setActiveEdition(selectedEdition);
        return selectedEdition;

    }

    public void setSelectedEdition(Edition selectedEdition) {
        this.selectedEdition = selectedEdition;
        loggedUserEJB.setActiveEdition(selectedEdition);
    }

    public UIComponent getCreateCriteriaArea() {
        return createCriteriaArea;
    }

    public void setCreateCriteriaArea(UIComponent createCriteriaArea) {
        this.createCriteriaArea = createCriteriaArea;
    }

    public UIComponent getVazio() {
        return vazio;
    }

    public void setVazio(UIComponent vazio) {
        this.vazio = vazio;
    }

    /**
     * this method creates an edition to the database, uses the method
     * createsEdition of the editionFacade, if it can't create catches the
     * CreateEditionAbortedException
     *
     * @param e
     * @throws
     * pt.uc.dei.aor.grupod.proj5.exceptions.CreateEditionAbortedException
     */
    public void createEdition(Edition e) throws CreateEditionAbortedException, RatingScaleException {

        try {

            loggedUserEJB.setActiveEdition(editionFacade.createsEdition(e));

        } catch (CreateEditionAbortedException ex) {
            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);
            errorCreate = ex.getMessage();
            throw new CreateEditionAbortedException();

        } catch (RatingScaleException ex) {
            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);
            errorCreate = ex.getMessage();
            throw new RatingScaleException();
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
    public void goToNewEdition() {
        editions.setRendered(false);
        newEdition.setRendered(true);
        loggedUserEJB.setActiveEdition(null);
        edition = null;
        criteriaList = null;
    }

    /**
     * Opens the create criteria area when the edition is being created
     */
    public void opensCreateCriteriaWhenCreateEdition() {
        try {
            opensCreateCriteria();
            createEdition(edition);

        } catch (CreateEditionAbortedException | RatingScaleException ex) {
            errorCreate = ex.getMessage();
            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     * goes to the edition selected page
     *
     * @return "openEdition
     */
    public String openEdition() {
        loggedUserEJB.setActiveEdition(edition);
        return "openEdition";
    }

    /**
     * Opens the create criteria menu
     */
    public void opensCreateCriteria() {
        if (addCriteriaButton != null) {
            addCriteriaButton.setRendered(false);
            loggedUserEJB.setActiveEdition(edition);
        }
        createCriteria.setRendered(true);
    }

    /**
     * Creates a criteria for a edition
     */
    public void createsCriteriaForEdition() {
        try {
            editionFacade.createsCriteria(criteria, loggedUserEJB.getActiveEdition());
            criteria = null;
        } catch (OperationEditionAborted ex) {
            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);
            operationEditionError = ex.getMessage();
        }

    }

    /**
     *
     */
    public void saveEdition() {
        boolean save = true;
        if (loggedUserEJB.getActiveEdition() == null) {
            try {
                createEdition(edition);

            } catch (CreateEditionAbortedException | RatingScaleException ex) {
                errorCreate = ex.getMessage();
                Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);
                save = false;
                MessagesForUser.addMessageError(ex.getMessage());

            }
        }
        if (save) {
            editionFacade.edit(loggedUserEJB.getActiveEdition());
            createCriteria.setRendered(false);
            newEdition.setRendered(false);
            createCriteria.setRendered(true);
        }

    }

    /**
     * Deletes a list of criteria from one edition
     */
    public void deleteCriteriaListFromEdition() {
        for (Criteria c : criteriaList) {
            loggedUserEJB.getActiveEdition().getCriteriaList().remove(c);
            criteriaFacade.remove(c);
        }

    }

    public String deleteEdition() {
        try {
            if (selectedEdition == null) {
                selectedEdition = loggedUserEJB.getActiveEdition();
            }
            editionFacade.removesEdition(selectedEdition);
        } catch (OperationEditionAborted ex) {
            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);
            MessagesForUser.addMessageError(ex.getMessage());
        }
        return "edition";
    }

    public String editEdition() {
        editionFacade.edit(loggedUserEJB.getActiveEdition());
        return "edition";
    }

    public String goToEdit(Edition edition) {
        loggedUserEJB.setActiveEdition(edition);

        return "editEdition";
    }

    public void openCriteriaMaker() {
        vazio.setRendered(false);
        createCriteriaArea.setRendered(true);
    }

    public void cancelMakeCriteria() {
        createCriteriaArea.setRendered(false);
        vazio.setRendered(true);
    }

    public void returnToEditions() {
        createCriteria.setRendered(false);
        editions.setRendered(true);
    }
}
