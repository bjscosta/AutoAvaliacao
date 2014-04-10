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
import pt.uc.dei.aor.grupod.proj5.entities.Administrator;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.ProjEvaluation;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.entities.User;
import pt.uc.dei.aor.grupod.proj5.exceptions.CreateProjectAbortedException;
import pt.uc.dei.aor.grupod.proj5.exceptions.ProjEvaluationException;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;
import pt.uc.dei.aor.grupod.proj5.facades.ProjEvaluationFacade;
import pt.uc.dei.aor.grupod.proj5.facades.ProjectFacade;
import pt.uc.dei.aor.grupod.proj5.facades.StudentFacade;

@Named
@RequestScoped
public class ProjectController {

    @Inject
    private EditionFacade editionFacade;

    @Inject
    private ProjectFacade projectFacade;

    @Inject
    private LoggedUserEJB loggedUserEJB;

    @Inject
    private StudentFacade studentFacade;

    @Inject
    private ProjEvaluationFacade projEvaluationFacade;

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
    private Project project;
    private UIForm header;
    private UIComponent studentsEdition;
    private long projectID;
    private List<Student> studentsToDelete;
    private List<Student> studentsToAdd;
    private String searchText;
    private List<Project> studentProjectEvaluate;
    private UIForm projectsForevaluate;
    private List<Student> filterStudent;
    private List<ProjEvaluation> peListStudent;
    private List<Project> projectsAlreadyEvaluated;
    private UIForm projEvaluatedForm;
    private UIComponent columnSendEmail;

    @PostConstruct
    public void init() {

        projectCreated = new Project();
        User u = loggedUserEJB.getLoggedUser();

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

    public UIForm getProjEvaluatedForm() {
        return projEvaluatedForm;
    }

    public void setProjEvaluatedForm(UIForm projEvaluatedForm) {
        this.projEvaluatedForm = projEvaluatedForm;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;

    }

    public List<Project> getProjectsAlreadyEvaluated() {
        projectsAlreadyEvaluated = projectFacade.projectsEvaluated((Student) loggedUserEJB.getLoggedUser());
        return projectsAlreadyEvaluated;
    }

    public void setProjectsAlreadyEvaluated(List<Project> projectsAlreadyEvaluated) {
        this.projectsAlreadyEvaluated = projectsAlreadyEvaluated;
    }

    public UIForm getHeader() {
        return header;
    }

    public void setHeader(UIForm header) {
        this.header = header;
    }

    public UIComponent getStudentsEdition() {
        return studentsEdition;
    }

    public void setStudentsEdition(UIComponent studentsEdition) {
        this.studentsEdition = studentsEdition;
    }

    public long getProjectID() {
        return projectID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }

    public List<Student> getStudentsToDelete() {
        return studentsToDelete;
    }

    public void setStudentsToDelete(List<Student> StudentsToDelete) {
        this.studentsToDelete = StudentsToDelete;
    }

    public List<Student> getStudentsToAdd() {
        return studentsToAdd;
    }

    public void setStudentsToAdd(List<Student> StudentsToAdd) {
        this.studentsToAdd = StudentsToAdd;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<Project> getStudentProjectEvaluate() {
        actualizeStudentEvaluateProje();
        return studentProjectEvaluate;
    }

    public void setStudentProjectEvaluate(List<Project> studentProjectEvaluate) {
        this.studentProjectEvaluate = studentProjectEvaluate;
    }

    public UIForm getProjectsForevaluate() {
        return projectsForevaluate;
    }

    public void setProjectsForevaluate(UIForm projectsForevaluate) {
        this.projectsForevaluate = projectsForevaluate;
    }

    public List<ProjEvaluation> getPeListStudent() {

        peListStudent = projEvaluationFacade
                .evaluationsOfStudentAndProject((Student) loggedUserEJB.getLoggedUser(),
                        selectedOpenedProject);
        return peListStudent;
    }

    public void setPeListStudent(List<ProjEvaluation> peListStudent) {
        this.peListStudent = peListStudent;
    }

    public UIComponent getColumnSendEmail() {
        return columnSendEmail;
    }

    public void setColumnSendEmail(UIComponent columnSendEmail) {
        this.columnSendEmail = columnSendEmail;
    }

    public List<Student> getFilterStudent() {
        return filterStudent;
    }

    public void setFilterStudent(List<Student> filterStudent) {
        this.filterStudent = filterStudent;
    }

    public void actualizeStudentEvaluateProje() {
        User u = loggedUserEJB.getLoggedUser();
        if (u instanceof Student) {
            studentProjectEvaluate = projectFacade.
                    openProjectsToEvaluateStudent((Student) u);
        }
    }

    /**
     * method to get the opened projects from the database
     */
    public void actualizeOpenProjects() {
        if (loggedUserEJB.getLoggedUser() instanceof Student) {
            Student s = (Student) loggedUserEJB.getLoggedUser();
            openProjects = projectFacade.openProjectsToEvaluateStudent(s);

        } else if (loggedUserEJB.getLoggedUser() instanceof Administrator) {
            openProjects = projectFacade.findOpenProjects();

        }
    }

    public List<ProjEvaluation> listProjEvaluation(Student s) {
        return projEvaluationFacade.evaluationsOfStudentAndProject(s, loggedUserEJB.getActiveProject());
    }

    public void updateProjEv() {
        peListStudent = projEvaluationFacade
                .evaluationsOfStudentAndProject((Student) loggedUserEJB.getLoggedUser(),
                        loggedUserEJB.getActiveProject());
    }

    /**
     * method to get the closed projects from the database
     */
    public void actualizeClosedProjects() {
        if (loggedUserEJB.getLoggedUser() instanceof Student) {
            Student s = (Student) loggedUserEJB.getLoggedUser();

            closeProjects = projectFacade.studentClosedProjects(s);
        } else if (loggedUserEJB.getLoggedUser() instanceof Administrator) {

            closeProjects = projectFacade.findClosedProjects();
        }
    }

    /**
     * this method opens the createProjEvaluationect form
     */
    public void goToCreateProject() {
        openProjectsForm.setRendered(false);
        closedProjecsForm.setRendered(false);
        createProject.setRendered(true);
        header.setRendered(false);
    }

    /**
     * this methods call the method of the projectFacade createProjEvaluationect
     * for creating projects, catches the CreateProjectAbortedException if
     * unsuccessfull
     *
     */
    public void makeProject() {
        try {
            projectCreated.setStartingSelfEvaluationDate(beginningDate);
            projectCreated.setFinishingSelfEvaluationDate(endingDate);
            projectFacade.createProject(projectCreated, edition);
            closeCreateProjectForm();
            projectCreated = null;
            beginningDate = null;
            endingDate = null;

        } catch (CreateProjectAbortedException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);

            addMessage(ex.getMessage());

        }

    }

    public void closeCreateProjectForm() {
        if (createProject != null && openProjectsForm != null && closedProjecsForm != null) {
            createProject.setRendered(false);
            openProjectsForm.setRendered(true);
            closedProjecsForm.setRendered(true);
            header.setRendered(true);

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
        if (openProjectsForm != null) {
            openProjectsForm.setRendered(true);
        }
        if (projectsForevaluate != null) {
            projectsForevaluate.setRendered(true);
        }
        if (projEvaluatedForm != null) {
            projEvaluatedForm.setRendered(true);
        }
    }

    public void seeOpenProjects() {
        if (openProjectsForm != null) {
            openProjectsForm.setRendered(true);
        }
        closedProjecsForm.setRendered(false);
        if (projectsForevaluate != null) {
            projectsForevaluate.setRendered(false);
        }
        if (projEvaluatedForm != null) {
            projEvaluatedForm.setRendered(false);
        }
    }

    public void seeClosedProjects() {
        if (openProjectsForm != null) {
            openProjectsForm.setRendered(false);
        }
        closedProjecsForm.setRendered(true);
        if (projectsForevaluate != null) {
            projectsForevaluate.setRendered(false);
        }
        if (projEvaluatedForm != null) {
            projEvaluatedForm.setRendered(false);
        }
    }

    public void seeProjForEvaluate() {

        closedProjecsForm.setRendered(false);
        projectsForevaluate.setRendered(true);

        projEvaluatedForm.setRendered(false);

    }

    public void seeProjEvaluated() {

        closedProjecsForm.setRendered(false);
        projectsForevaluate.setRendered(false);

        projEvaluatedForm.setRendered(true);

    }

    public void goToAddStudents(Project project) {

        loggedUserEJB.setActiveProject(project);
        openProjectsForm.setRendered(false);
        closedProjecsForm.setRendered(false);
        addStudentForm.setRendered(true);
        header.setRendered(false);

        if (openProjects != null && openProjects.contains(project)) {
            columnSendEmail.setRendered(true);

        } else if (closeProjects != null && closeProjects.contains(project)) {
            columnSendEmail.setRendered(false);
        }
    }

    public List<Edition> getAllEditions() {
        return editionFacade.findAll();
    }

    public void deleteStudentsFromProject() {

        projectFacade.deleteStudents(loggedUserEJB.getActiveProject(), studentsToDelete);
    }

    public List<Student> listNotInProject() {

        return projectFacade.studentsNotInProject(loggedUserEJB.getActiveProject());
    }

    public void insertStudentsProject() {

        projectFacade.addStudentsProject(loggedUserEJB.getActiveProject(), studentsToAdd);
    }

    public void editProject() {
        projectFacade.edit(loggedUserEJB.getActiveProject());
        addStudentForm.setRendered(false);

        closedProjecsForm.setRendered(true);
        openProjectsForm.setRendered(true);
    }

    public List<Student> listStudentEdition() {

        return projectFacade.studentsInProject(loggedUserEJB.getActiveProject());

    }

    public String evaluate() {

        loggedUserEJB.setActiveProject(selectedOpenedProject);

        return "evaluation";

    }

    public String seeGradesinProjectStudent() {
        loggedUserEJB.setActiveProject(selectedClosedProject);

        return "seeProjectStudent";
    }

    public String confirm() {
        try {
            projEvaluationFacade.confirm(peListStudent);

        } catch (ProjEvaluationException ex) {

            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "openProjectStudent";

    }

}
