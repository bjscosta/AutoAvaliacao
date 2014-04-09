/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import pt.uc.dei.aor.grupod.proj5.entities.Criteria;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.exceptions.NoResultQueryException;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;
import pt.uc.dei.aor.grupod.proj5.facades.ProjEvaluationFacade;
import pt.uc.dei.aor.grupod.proj5.facades.ProjectFacade;

@Named
@ViewScoped
public class ReportsController {

    @Inject
    private EditionFacade editionFacade;

    @Inject
    private ProjectFacade projectFacade;

    @Inject
    private ProjEvaluationFacade projEvaluationFacade;

    private Edition edition;
    private Project project;
    private Student student;
    private List<Student> studentsList;
    private List<Edition> editionsList;
    private List<Project> projectsList;
    private UIComponent projectTable;
    private UIComponent studentsTable;
    private UIComponent editionReport;
    private double editionAverage;
    private List<Double> criteriaAvgEdition;
    private CartesianChartModel editionGraph;
    private UIComponent projectReport;
    private CartesianChartModel projectGraph;
    private double projectAverage;
    private List<Double> criteriaAvgProject;
    private UIComponent studentReport;
    private CartesianChartModel studentGraph;
    private double studentAverage;
    private List<Double> criteriaAvgStudent;

    @PostConstruct
    public void init() {
        editionsList = editionFacade.findAllEditions();
        editionGraph = new CartesianChartModel();
        projectGraph = new CartesianChartModel();
        studentGraph = new CartesianChartModel();
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public CartesianChartModel getEditionGraph() {
        return editionGraph;
    }

    public void setEditionGraph(CartesianChartModel editionGraph) {
        this.editionGraph = editionGraph;
    }

    public List<Edition> getEditionsList() {
        return editionsList;
    }

    public void setEditionsList(List<Edition> editionsList) {
        this.editionsList = editionsList;
    }

    public List<Project> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Project> projectsList) {
        this.projectsList = projectsList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public UIComponent getProjectTable() {
        return projectTable;
    }

    public void setProjectTable(UIComponent projectTable) {
        this.projectTable = projectTable;
    }

    public UIComponent getStudentsTable() {
        return studentsTable;
    }

    public void setStudentsTable(UIComponent studentsTable) {
        this.studentsTable = studentsTable;
    }

    public UIComponent getEditionReport() {
        return editionReport;
    }

    public void setEditionReport(UIComponent editionReport) {
        this.editionReport = editionReport;
    }

    public double getEditionAverage() {
        return editionAverage;
    }

    public void setEditionAverage(double editionAverage) {
        this.editionAverage = editionAverage;
    }

    public List<Double> getCriteriaAvgEdition() {
        return criteriaAvgEdition;
    }

    public void setCriteriaAvgEdition(List<Double> criteriaAvgEdition) {
        this.criteriaAvgEdition = criteriaAvgEdition;
    }

    public UIComponent getProjectReport() {
        return projectReport;
    }

    public void setProjectReport(UIComponent projectReport) {
        this.projectReport = projectReport;
    }

    public CartesianChartModel getProjectGraph() {
        return projectGraph;
    }

    public void setProjectGraph(CartesianChartModel projectGraph) {
        this.projectGraph = projectGraph;
    }

    public double getProjectAverage() {
        return projectAverage;
    }

    public void setProjectAverage(double projectAverage) {
        this.projectAverage = projectAverage;
    }

    public List<Double> getCriteriaAvgProject() {
        return criteriaAvgProject;
    }

    public void setCriteriaAvgProject(List<Double> criteriaAvgProject) {
        this.criteriaAvgProject = criteriaAvgProject;
    }

    public UIComponent getStudentReport() {
        return studentReport;
    }

    public void setStudentReport(UIComponent studentReport) {
        this.studentReport = studentReport;
    }

    public CartesianChartModel getStudentGraph() {
        return studentGraph;
    }

    public void setStudentGraph(CartesianChartModel studentGraph) {
        this.studentGraph = studentGraph;
    }

    public double getStudentAverage() {
        return studentAverage;
    }

    public void setStudentAverage(double studentAverage) {
        this.studentAverage = studentAverage;
    }

    public List<Double> getCriteriaAvgStudent() {
        return criteriaAvgStudent;
    }

    public void setCriteriaAvgStudent(List<Double> criteriaAvgStudent) {
        this.criteriaAvgStudent = criteriaAvgStudent;
    }

    public void confirmEdition() {
        try {
            projectsList = edition.getProjectList();
            studentsList = edition.getStudents();
            editionAverage = projEvaluationFacade.averageEdition(edition);
            edition = projEvaluationFacade.averageCriteriaEdition(edition);
            createEditionGraph(edition);
            editionReport.setRendered(true);
            projectReport.setRendered(false);
            studentReport.setRendered(false);
            project = null;
        } catch (NoResultQueryException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(ex.getMessage());
        }

    }

    public void confirmProject() {
        try {
            studentsList = projectFacade.studentsInProject(project);
            projectAverage = projEvaluationFacade.averageProject(project);
            edition = projEvaluationFacade.averageCriteriaProject(edition, project);
            createProjGraph(edition);
            studentReport.setRendered(false);
            editionReport.setRendered(false);
            projectReport.setRendered(true);
        } catch (NoResultQueryException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(ex.getMessage());
        }
    }

    public void confirmStudents() {
        try {
            studentAverage = projEvaluationFacade.averageStudent(student);
            if (project == null) {
                edition = projEvaluationFacade.averageCriteriaStudent(edition, student);
            } else {
                edition = projEvaluationFacade.averageStudentProject(edition, student, project);
            }
            createStudentGraph(edition);
            projectReport.setRendered(false);
            studentReport.setRendered(true);
            editionReport.setRendered(false);
        } catch (NoResultQueryException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(ex.getMessage());
        }

    }

    public void createEditionGraph(Edition edition) {
        editionGraph = new CartesianChartModel();
        ChartSeries criteria = new ChartSeries();

        for (Criteria c : edition.getCriteriaList()) {
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        editionGraph.addSeries(criteria);
    }

    public void createProjGraph(Edition edition) {
        
        projectGraph = new CartesianChartModel();
        ChartSeries criteria = new ChartSeries();

        for (Criteria c : edition.getCriteriaList()) {
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        projectGraph.addSeries(criteria);
    }

    public void createStudentGraph(Edition edition) {
        
        studentGraph = new CartesianChartModel();
        ChartSeries criteria = new ChartSeries();

        for (Criteria c : edition.getCriteriaList()) {
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        studentGraph.addSeries(criteria);
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
