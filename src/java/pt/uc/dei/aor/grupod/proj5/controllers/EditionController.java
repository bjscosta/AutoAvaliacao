package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
import pt.uc.dei.aor.grupod.proj5.exceptions.YearEditionException;
import pt.uc.dei.aor.grupod.proj5.facades.CriteriaFacade;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;
import pt.uc.dei.aor.grupod.proj5.utilities.MessagesForUser;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
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
    private Criteria criteria;
    private Long editionId;
    private UIForm editions;
    private UIForm newEdition;
    private UIForm createCriteria;
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

    /**
     * when this method is called, the method points the return value of
     * editionFacade.findEditionsByTheCurrentYear() to availableEditions
     *
     * @return availableEditions
     */
    public List<Edition> getAvailableEditions() {
        availableEditions = editionFacade.findEditionsByTheCurrentYear();
        return availableEditions;
    }

    /**
     *
     * @param availableEditions
     */
    public void setAvailableEditions(List<Edition> availableEditions) {
        this.availableEditions = availableEditions;
    }

    /**
     *
     * @return editionFacade
     */
    public EditionFacade getEditionFacade() {
        return editionFacade;
    }

    /**
     *
     * @param editionFacade
     */
    public void setEditionFacade(EditionFacade editionFacade) {
        this.editionFacade = editionFacade;
    }

    /**
     *
     * @return edition
     */
    public Edition getEdition() {

        return edition;
    }

    /**
     *
     * @param edition
     */
    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    /**
     *
     * @return criteria
     */
    public Criteria getCriteria() {
        return criteria;
    }

    /**
     *
     * @param criteria
     */
    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    /**
     *
     * @return editionId
     */
    public Long getEditionId() {
        return editionId;
    }

    /**
     *
     * @param editionId
     */
    public void setEditionId(Long editionId) {
        this.editionId = editionId;
    }

    /**
     *
     * @return editions
     */
    public UIForm getEditions() {
        return editions;
    }

    /**
     *
     * @param editions
     */
    public void setEditions(UIForm editions) {
        this.editions = editions;
    }

    /**
     *
     * @return newEdition
     */
    public UIForm getNewEdition() {
        return newEdition;
    }

    /**
     *
     * @param newEdition
     */
    public void setNewEdition(UIForm newEdition) {
        this.newEdition = newEdition;
    }

    /**
     *
     * @return createCriteria
     */
    public UIForm getCreateCriteria() {
        return createCriteria;
    }

    /**
     *
     * @param createCriteria
     */
    public void setCreateCriteria(UIForm createCriteria) {
        this.createCriteria = createCriteria;
    }

    /**
     *
     * @return criteriaList
     */
    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    /**
     *
     * @param criteriaList
     */
    public void setCriteriaList(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    /**
     *
     * @return criteriaFacade
     */
    public CriteriaFacade getCriteriaFacade() {
        return criteriaFacade;
    }

    /**
     *
     * @param criteriaFacade
     */
    public void setCriteriaFacade(CriteriaFacade criteriaFacade) {
        this.criteriaFacade = criteriaFacade;
    }

    /**
     *
     * @return loggedUserEJB
     */
    public LoggedUserEJB getLoggedUserEJB() {
        return loggedUserEJB;
    }

    /**
     *
     * @param loggedUserEJB
     */
    public void setLoggedUserEJB(LoggedUserEJB loggedUserEJB) {
        this.loggedUserEJB = loggedUserEJB;
    }

    /**
     *
     * @return formSaveEditionCriteriaHide
     */
    public UIForm getFormSaveEditionCriteriaHide() {
        return formSaveEditionCriteriaHide;
    }

    /**
     *
     * @param formSaveEditionCriteriaHide
     */
    public void setFormSaveEditionCriteriaHide(UIForm formSaveEditionCriteriaHide) {
        this.formSaveEditionCriteriaHide = formSaveEditionCriteriaHide;
    }

    /**
     *
     * @return
     */
    public UIForm getFormSaveEditionCriteriaShowing() {
        return formSaveEditionCriteriaShowing;
    }

    /**
     *
     * @param formSaveEditionCriteriaShowing
     */
    public void setFormSaveEditionCriteriaShowing(UIForm formSaveEditionCriteriaShowing) {
        this.formSaveEditionCriteriaShowing = formSaveEditionCriteriaShowing;
    }

    /**
     *
     * @return addCriteriaButton
     */
    public UIComponent getAddCriteriaButton() {
        return addCriteriaButton;
    }

    /**
     *
     * @param addCriteriaButton
     */
    public void setAddCriteriaButton(UIComponent addCriteriaButton) {
        this.addCriteriaButton = addCriteriaButton;
    }

    /**
     * this method when it's called, sets the activeEdition of loggedUserEJB to
     * selectedEdition
     *
     * @return selectedEdition
     */
    public Edition getSelectedEdition() {
        loggedUserEJB.setActiveEdition(selectedEdition);
        return selectedEdition;

    }

    /**
     *
     * @param selectedEdition
     */
    public void setSelectedEdition(Edition selectedEdition) {
        this.selectedEdition = selectedEdition;
        loggedUserEJB.setActiveEdition(selectedEdition);
    }

    /**
     *
     * @return createCriteriaArea
     */
    public UIComponent getCreateCriteriaArea() {
        return createCriteriaArea;
    }

    /**
     *
     * @param createCriteriaArea
     */
    public void setCreateCriteriaArea(UIComponent createCriteriaArea) {
        this.createCriteriaArea = createCriteriaArea;
    }

    /**
     *
     * @return vazio
     */
    public UIComponent getVazio() {
        return vazio;
    }

    /**
     *
     * @param vazio
     */
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
     * @throws pt.uc.dei.aor.grupod.proj5.exceptions.RatingScaleException
     */
    public void createEdition(Edition e) throws CreateEditionAbortedException, RatingScaleException, YearEditionException {

        try {

            loggedUserEJB.setActiveEdition(editionFacade.createsEdition(e));

        } catch (CreateEditionAbortedException ex) {
            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);

            throw new CreateEditionAbortedException();

        } catch (RatingScaleException ex) {
            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);

            throw new RatingScaleException();
        } catch (YearEditionException ex) {
            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);
            GregorianCalendar gc = new GregorianCalendar();
            int year = gc.get(Calendar.YEAR);
            throw new YearEditionException(year);
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

        } catch (CreateEditionAbortedException | RatingScaleException | YearEditionException ex) {

            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);
            MessagesForUser.addMessageError(ex.getMessage());

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
     * Opens the create criteria menu, set's the activeEdition of loggedUserEJB
     * to edition
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
            MessagesForUser.addMessageError(ex.getMessage());
        }

    }

    /**
     * this saves an edition to database, calls the method createEdition, if
     * creating fails, the methods catches the exceptions
     * CreateEditionAbortedException and RatingScaleException
     */
    public void saveEdition() {
        boolean save = true;
        if (loggedUserEJB.getActiveEdition() == null) {
            try {
                createEdition(edition);

            } catch (CreateEditionAbortedException | RatingScaleException | YearEditionException ex) {

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

    /**
     *
     * @return the edition String that leads to the page edition.xhtml
     */
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

    /**
     * method to edit the edition that is saved on the loggedUserEJB, this
     * method calls the method of the editionFacade editionEdition
     *
     * @return the edition String that leads to the page edition.xhtml or null
     * if the RatingException is catched
     */
    public String editEdition() {
        try {
            editionFacade.editEdition(loggedUserEJB.getActiveEdition());
            return "edition";
        } catch (RatingScaleException ex) {
            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, ex);
            MessagesForUser.addMessageError(ex.getMessage());
            return null;
        }
    }

    /**
     * goes to the editEdition view and sets the activeEdition to the edition
     * given in the parameters
     *
     * @param edition
     * @return the page editEdition
     */
    public String goToEdit(Edition edition) {
        loggedUserEJB.setActiveEdition(edition);

        return "editEdition";
    }

    /**
     * this methods renders the createCriteriaArea to true, to view the
     * createCriteria menu
     */
    public void openCriteriaMaker() {
        vazio.setRendered(false);
        createCriteriaArea.setRendered(true);
    }

    /**
     * cancels the createCriteria menu, renders to false
     */
    public void cancelMakeCriteria() {
        createCriteriaArea.setRendered(false);
        vazio.setRendered(true);
    }

    /**
     * this methods return the list of editions view
     */
    public void returnToEditions() {
        createCriteria.setRendered(false);
        editions.setRendered(true);
    }
}
