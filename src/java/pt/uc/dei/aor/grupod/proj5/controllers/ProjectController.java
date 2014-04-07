/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.exceptions.CreateProjectAbortedException;
import pt.uc.dei.aor.grupod.proj5.facades.ProjectFacade;

@Named
@RequestScoped
public class ProjectController {

    @Inject
    private ProjectFacade projectFacade;

    private List<Project> openProjects;
    private List<Project> closeProjects;
    private Project selectedOpenedProject;
    private Project selectedClosedProject;
    private UIForm openProjectsForm;
    private UIForm closedProjecsForm;
    private UIForm createProject;
    private Project projectCreated;
    private Date beginningDate;
    private Date endingDate;
    private Edition edition;

    @PostConstruct
    public void init() {
        openProjects = projectFacade.findOpenProjects();
        closeProjects = projectFacade.findClosedProjects();
        projectCreated = new Project();
    }

    public List<Project> getOpenProjects() {
        actualizeOpenProjects();
        return openProjects;
    }

    public void setOpenProjects(List<Project> openProjects) {
        this.openProjects = openProjects;
    }

    public List<Project> getCloseProjects() {
        actualizeClosedProjects();
        return closeProjects;
    }

    public void setCloseProjects(List<Project> closeProjects) {
        this.closeProjects = closeProjects;
    }

    public Project getSelectedOpenedProject() {
        return selectedOpenedProject;
    }

    public void setSelectedOpenedProject(Project selectedOpenedProject) {
        this.selectedOpenedProject = selectedOpenedProject;
    }

    public Project getSelectedClosedProject() {
        return selectedClosedProject;
    }

    public void setSelectedClosedProject(Project selectedClosedProject) {
        this.selectedClosedProject = selectedClosedProject;
    }

    public UIForm getOpenProjectsForm() {
        return openProjectsForm;
    }

    public void setOpenProjectsForm(UIForm openProjectsForm) {
        this.openProjectsForm = openProjectsForm;
    }

    public UIForm getClosedProjecsForm() {
        return closedProjecsForm;
    }

    public void setClosedProjecsForm(UIForm closedProjecsForm) {
        this.closedProjecsForm = closedProjecsForm;
    }

    public Project getProjectCreated() {
        return projectCreated;
    }

    public void setProjectCreated(Project projectCreated) {
        this.projectCreated = projectCreated;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public UIForm getCreateProject() {
        return createProject;
    }

    public void setCreateProject(UIForm createProject) {
        this.createProject = createProject;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    /**
     * method to get the opened projects from the database
     */
    public void actualizeOpenProjects() {
        openProjects = projectFacade.findOpenProjects();
    }

    /**
     * method to get the closed projects from the database
     */
    public void actualizeClosedProjects() {
        closeProjects = projectFacade.findClosedProjects();
    }

    /**
     * this method opens the createProject form
     */
    public void goToCreateProject() {
        openProjectsForm.setRendered(false);
        closedProjecsForm.setRendered(false);
        createProject.setRendered(true);
    }

    /**
     * this methods call the method of the projectFacade createProject for
     * creating projects, catches the CreateProjectAbortedException if
     * unsuccessfull
     *
     * @return projectAdmin if the exception is not catched, if the exception is
     * catched return null
     */
    public void makeProject() {
        try {
            projectCreated.setStartingSelfEvaluationDate(beginningDate);
            projectCreated.setFinishingSelfEvaluationDate(endingDate);
            projectFacade.createProject(projectCreated, edition);

        } catch (CreateProjectAbortedException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(ex.getMessage());

        }
    }

    /**
     * method to make a message severity appear to user
     *
     * @param summary
     */
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
