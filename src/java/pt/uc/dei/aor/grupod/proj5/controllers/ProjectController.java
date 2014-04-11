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
import javax.faces.application.ConfigurableNavigationHandler;
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
import pt.uc.dei.aor.grupod.proj5.utilities.MessagesForUser;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
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
    private List<Project> studentProjectEvaluate;
    private UIForm projectsForevaluate;
    private List<Student> filterStudent;
    private List<ProjEvaluation> peListStudent;
    private List<Project> projectsAlreadyEvaluated;
    private UIForm projEvaluatedForm;
    private UIComponent columnSendEmail;

    /**
     * this method initializes the variables projectCreated and
     */
    @PostConstruct
    public void init() {
        projectCreated = new Project();

    }

    /**
     *
     * @return
     */
    public List<Project> getOpenProjects() {
        actualizeOpenProjects();
        return openProjects;
    }

    /**
     *
     * @param openProjects
     */
    public void setOpenProjects(List<Project> openProjects) {
        this.openProjects = openProjects;
    }

    /**
     *
     * @return
     */
    public List<Project> getCloseProjects() {
        actualizeClosedProjects();
        return closeProjects;
    }

    /**
     *
     * @param closeProjects
     */
    public void setCloseProjects(List<Project> closeProjects) {
        this.closeProjects = closeProjects;
    }

    /**
     *
     * @return
     */
    public Project getSelectedOpenedProject() {
        return selectedOpenedProject;
    }

    /**
     *
     * @param selectedOpenedProject
     */
    public void setSelectedOpenedProject(Project selectedOpenedProject) {
        this.selectedOpenedProject = selectedOpenedProject;
    }

    /**
     *
     * @return
     */
    public Project getSelectedClosedProject() {
        return selectedClosedProject;
    }

    /**
     *
     * @param selectedClosedProject
     */
    public void setSelectedClosedProject(Project selectedClosedProject) {
        this.selectedClosedProject = selectedClosedProject;
    }

    /**
     *
     * @return
     */
    public UIForm getOpenProjectsForm() {
        return openProjectsForm;
    }

    /**
     *
     * @param openProjectsForm
     */
    public void setOpenProjectsForm(UIForm openProjectsForm) {
        this.openProjectsForm = openProjectsForm;
    }

    /**
     *
     * @return
     */
    public UIForm getClosedProjecsForm() {
        return closedProjecsForm;
    }

    /**
     *
     * @param closedProjecsForm
     */
    public void setClosedProjecsForm(UIForm closedProjecsForm) {
        this.closedProjecsForm = closedProjecsForm;
    }

    /**
     *
     * @return
     */
    public Project getProjectCreated() {
        return projectCreated;
    }

    /**
     *
     * @param projectCreated
     */
    public void setProjectCreated(Project projectCreated) {
        this.projectCreated = projectCreated;
    }

    /**
     *
     * @return
     */
    public Date getBeginningDate() {
        return beginningDate;
    }

    /**
     *
     * @param beginningDate
     */
    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    /**
     *
     * @return
     */
    public Date getEndingDate() {
        return endingDate;
    }

    /**
     *
     * @param endingDate
     */
    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    /**
     *
     * @return
     */
    public UIForm getCreateProject() {
        return createProject;
    }

    /**
     *
     * @param createProject
     */
    public void setCreateProject(UIForm createProject) {
        this.createProject = createProject;
    }

    /**
     *
     * @return
     */
    public UIForm getProjEvaluatedForm() {
        return projEvaluatedForm;
    }

    /**
     *
     * @param projEvaluatedForm
     */
    public void setProjEvaluatedForm(UIForm projEvaluatedForm) {
        this.projEvaluatedForm = projEvaluatedForm;
    }

    /**
     *
     * @return
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
     * @return
     */
    public List<Project> getProjectList() {
        return projectList;
    }

    /**
     *
     * @param projectList
     */
    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    /**
     *
     * @return
     */
    public UIComponent getEditEditionProject() {
        return editEditionProject;
    }

    /**
     *
     * @return
     */
    public UIForm getAddStudentForm() {
        return addStudentForm;
    }

    /**
     *
     * @param addStudentForm
     */
    public void setAddStudentForm(UIForm addStudentForm) {
        this.addStudentForm = addStudentForm;
    }

    /**
     *
     * @param editEditionProject
     */
    public void setEditEditionProject(UIComponent editEditionProject) {
        this.editEditionProject = editEditionProject;
    }

    /**
     *
     * @return
     */
    public ProjectFacade getProjectFacade() {
        return projectFacade;
    }

    /**
     *
     * @param projectFacade
     */
    public void setProjectFacade(ProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    /**
     *
     * @return
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
     * @return
     */
    public List<Student> getSelectedStudents() {
        return selectedStudents;
    }

    /**
     *
     * @param selectedStudents
     */
    public void setSelectedStudents(List<Student> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    /**
     *
     * @return
     */
    public Project getProject() {
        return project;
    }

    /**
     *
     * @param project
     */
    public void setProject(Project project) {
        this.project = project;

    }

    /**
     *
     * @return
     */
    public List<Project> getProjectsAlreadyEvaluated() {
        projectsAlreadyEvaluated = projectFacade.projectsEvaluated((Student) loggedUserEJB.getLoggedUser());
        return projectsAlreadyEvaluated;
    }

    /**
     *
     * @param projectsAlreadyEvaluated
     */
    public void setProjectsAlreadyEvaluated(List<Project> projectsAlreadyEvaluated) {
        this.projectsAlreadyEvaluated = projectsAlreadyEvaluated;
    }

    /**
     *
     * @return
     */
    public UIForm getHeader() {
        return header;
    }

    /**
     *
     * @param header
     */
    public void setHeader(UIForm header) {
        this.header = header;
    }

    /**
     *
     * @return
     */
    public UIComponent getStudentsEdition() {
        return studentsEdition;
    }

    /**
     *
     * @param studentsEdition
     */
    public void setStudentsEdition(UIComponent studentsEdition) {
        this.studentsEdition = studentsEdition;
    }

    /**
     *
     * @return
     */
    public long getProjectID() {
        return projectID;
    }

    /**
     *
     * @param projectID
     */
    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }

    /**
     *
     * @return
     */
    public List<Student> getStudentsToDelete() {
        return studentsToDelete;
    }

    /**
     *
     * @param StudentsToDelete
     */
    public void setStudentsToDelete(List<Student> StudentsToDelete) {
        this.studentsToDelete = StudentsToDelete;
    }

    /**
     *
     * @return
     */
    public List<Student> getStudentsToAdd() {
        return studentsToAdd;
    }

    /**
     *
     * @param StudentsToAdd
     */
    public void setStudentsToAdd(List<Student> StudentsToAdd) {
        this.studentsToAdd = StudentsToAdd;
    }

    /**
     *
     * @return
     */
    public List<Project> getStudentProjectEvaluate() {
        actualizeStudentEvaluateProje();
        return studentProjectEvaluate;
    }

    /**
     *
     * @param studentProjectEvaluate
     */
    public void setStudentProjectEvaluate(List<Project> studentProjectEvaluate) {
        this.studentProjectEvaluate = studentProjectEvaluate;
    }

    /**
     *
     * @return
     */
    public UIForm getProjectsForevaluate() {
        return projectsForevaluate;
    }

    /**
     *
     * @param projectsForevaluate
     */
    public void setProjectsForevaluate(UIForm projectsForevaluate) {
        this.projectsForevaluate = projectsForevaluate;
    }

    /**
     *
     * @return
     */
    public List<ProjEvaluation> getPeListStudent() {

        peListStudent = projEvaluationFacade
                .evaluationsOfStudentAndProject((Student) loggedUserEJB.getLoggedUser(),
                        selectedOpenedProject);
        return peListStudent;
    }

    /**
     *
     * @param peListStudent
     */
    public void setPeListStudent(List<ProjEvaluation> peListStudent) {
        this.peListStudent = peListStudent;
    }

    /**
     *
     * @return
     */
    public UIComponent getColumnSendEmail() {
        return columnSendEmail;
    }

    /**
     *
     * @param columnSendEmail
     */
    public void setColumnSendEmail(UIComponent columnSendEmail) {
        this.columnSendEmail = columnSendEmail;
    }

    /**
     *
     * @return
     */
    public List<Student> getFilterStudent() {
        return filterStudent;
    }

    /**
     *
     * @param filterStudent
     */
    public void setFilterStudent(List<Student> filterStudent) {
        this.filterStudent = filterStudent;
    }

    /**
     *
     */
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

    /**
     *
     * @param s
     * @return
     */
    public List<ProjEvaluation> listProjEvaluation(Student s) {
        return projEvaluationFacade.evaluationsOfStudentAndProject(s, loggedUserEJB.getActiveProject());
    }

    /**
     *
     */
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

    /**
     *
     */
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

    /**
     * this method deletes a project from an Edition
     */
    public void deleteProjectFromEdition() {
        projectFacade.removeProject(projectList);
    }

    /**
     * this method open the create project menu
     */
    public void openProjectMaker() {
        editEditionProject.setRendered(true);
    }

    /**
     *
     */
    public void editEditionMakeProject() {
        edition = loggedUserEJB.getActiveEdition();
        makeProject();
    }

    /**
     *
     */
    public void cancelMakeProject() {
        editEditionProject.setRendered(false);
    }

    /**
     *
     */
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

    /**
     *
     */
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

    /**
     *
     */
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

    /**
     *
     */
    public void seeProjForEvaluate() {

        closedProjecsForm.setRendered(false);
        projectsForevaluate.setRendered(true);

        projEvaluatedForm.setRendered(false);

    }

    /**
     *
     */
    public void seeProjEvaluated() {

        closedProjecsForm.setRendered(false);
        projectsForevaluate.setRendered(false);

        projEvaluatedForm.setRendered(true);

    }

    /**
     *
     * @param project
     */
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

    /**
     *
     * @return
     */
    public List<Edition> getAllEditions() {
        return editionFacade.findAll();
    }

    /**
     *
     */
    public void deleteStudentsFromProject() {

        projectFacade.deleteStudents(loggedUserEJB.getActiveProject(), studentsToDelete);
    }

    /**
     *
     * @return
     */
    public List<Student> listNotInProject() {

        return projectFacade.studentsNotInProject(loggedUserEJB.getActiveProject());
    }

    /**
     *
     */
    public void insertStudentsProject() {
        Project p = loggedUserEJB.getActiveProject();
        if (!p.getEdition().getCriteriaList().isEmpty()) {
            projectFacade.addStudentsProject(loggedUserEJB.getActiveProject(), studentsToAdd);
        } else {
            MessagesForUser.addMessageError("A edição deste projecto ainda não tem critérios,"
                    + " adicione primeiro critérios à edição.");
        }
    }

    /**
     *
     */
    public void editProject() {
        projectFacade.edit(loggedUserEJB.getActiveProject());
        addStudentForm.setRendered(false);

        closedProjecsForm.setRendered(true);
        openProjectsForm.setRendered(true);
    }

    /**
     *
     * @return
     */
    public List<Student> listStudentEdition() {

        return projectFacade.studentsInProject(loggedUserEJB.getActiveProject());

    }

    /**
     *
     * @return
     */
    public String evaluate() {

        loggedUserEJB.setActiveProject(selectedOpenedProject);

        return "evaluation";

    }

    /**
     *
     * @return
     */
    public String seeGradesinProjectStudent() {
        loggedUserEJB.setActiveProject(selectedClosedProject);

        return "seeProjectStudent";
    }

    /**
     *
     * @return
     */
    public String confirm() {
        try {
            projEvaluationFacade.confirm(peListStudent);

        } catch (ProjEvaluationException ex) {

            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "openProjectStudent";

    }

    /**
     *
     */
    public void listenerSelectedOpenedProjectEvaluationPage() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        if (selectedOpenedProject == null) {
            nav.performNavigation("openProjectStudent.xhtml?faces-redirect=true");
        }
    }

}
