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
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.EJB.LoggedUserEJB;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.CreateProjectAbortedException;
import pt.uc.dei.aor.grupod.proj5.facades.ProjectFacade;

@Named
@RequestScoped
public class ProjectController {

    @Inject
    private ProjectFacade projectFacade;

    @Inject
    private LoggedUserEJB loggedUserEJB;

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
    private List<Project> projectList;
    private UIComponent editEditionProject;
    private UIForm addStudentForm;
    private List<Student> selectedStudents;
    private List<Student> listStudentsEdition;

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

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public UIComponent getEditEditionProject() {
        return editEditionProject;
    }

    public UIForm getAddStudentForm() {
        return addStudentForm;
    }

    public void setAddStudentForm(UIForm addStudentForm) {
        this.addStudentForm = addStudentForm;
    }

    public void setEditEditionProject(UIComponent editEditionProject) {
        this.editEditionProject = editEditionProject;
    }

    public ProjectFacade getProjectFacade() {
        return projectFacade;
    }

    public void setProjectFacade(ProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    public LoggedUserEJB getLoggedUserEJB() {
        return loggedUserEJB;
    }

    public void setLoggedUserEJB(LoggedUserEJB loggedUserEJB) {
        this.loggedUserEJB = loggedUserEJB;
    }

    public List<Student> getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(List<Student> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public List<Student> getListStudentsEdition() {
        return selectedOpenedProject.getEdition().getStudents();
    }

    public void setListStudentsEdition(List<Student> listStudentsEdition) {
        this.listStudentsEdition = listStudentsEdition;
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
     */
    public void makeProject() {
        try {
            projectCreated.setStartingSelfEvaluationDate(beginningDate);
            projectCreated.setFinishingSelfEvaluationDate(endingDate);
            projectFacade.createProject(projectCreated, edition);
            pamps();
            projectCreated = null;
            beginningDate = null;
            endingDate = null;

        } catch (CreateProjectAbortedException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(ex.getMessage());

        }
    }

    public void pamps() {
        if (createProject != null && openProjectsForm != null && closedProjecsForm != null) {
            createProject.setRendered(false);
            openProjectsForm.setRendered(true);
            closedProjecsForm.setRendered(true);
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

    public void deleteProjectFromEdition() {
        for (Project p : projectList) {
            loggedUserEJB.getActiveEdition().getProjectList().remove(p);
            projectFacade.remove(p);
        }
    }

    public void openProjectMaker() {
        editEditionProject.setRendered(true);
    }

    public void editEditionMakeProject() {
        edition = loggedUserEJB.getActiveEdition();
        makeProject();
    }

    public void cancelMakeProject() {
        editEditionProject.setRendered(false);
    }

    public void seeAll() {
        closedProjecsForm.setRendered(true);
        openProjectsForm.setRendered(true);
    }

    public void seeOpenProjects() {
        openProjectsForm.setRendered(true);
        closedProjecsForm.setRendered(false);
    }

    public void seeClosedProjects() {
        openProjectsForm.setRendered(false);
        closedProjecsForm.setRendered(true);
    }

    public void goToAddStudents() {
        openProjectsForm.setRendered(false);
        closedProjecsForm.setRendered(false);
        addStudentForm.setRendered(true);
    }

}
