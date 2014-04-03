package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;

@Named
@RequestScoped
public class EditionController {

    private List<Edition> availableEditions;

    private Edition edition;

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

    public void createEdition(Edition e) {

    }

}
