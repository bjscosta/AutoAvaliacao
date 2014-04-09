/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.aor.grupod.proj5.controllers;


import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import pt.uc.dei.aor.grupod.proj5.entities.Criteria;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
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
    private List<Student> selectedStudents;
    private List<Edition> editionsList;
    private List<Project> projectsList;
    private List<Student> studentsList;
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
    
    
    @PostConstruct
    public void init(){
       editionsList = editionFacade.findAllEditions();
       editionGraph = new CartesianChartModel();
       projectGraph = new CartesianChartModel();
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

    public List<Student> getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(List<Student> selectedStudents) {
        this.selectedStudents = selectedStudents;
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

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
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
    
    
    
    public void confirmEdition(){
        projectsList = edition.getProjectList();
        editionAverage = projEvaluationFacade.averageEdition(edition);
        edition = projEvaluationFacade.averageCriteriaEdition(edition);
        createEditionGraph(edition);
        editionReport.setRendered(true);
        projectTable.setRendered(true);
        
    }
    
    public void confirmProject(){
        studentsList = projectFacade.studentsInProject(project);
        projectAverage = projEvaluationFacade.averageProject(project);
        edition = projEvaluationFacade.averageCriteriaProject(edition, project);
        createProjGraph(edition);
        studentsTable.setRendered(true);
        editionReport.setRendered(false);
        projectReport.setRendered(true);
    }
    
    public void confirmStudents(){
        
    }
    
    public void createEditionGraph(Edition edition){
        ChartSeries criteria = new ChartSeries();  
         
        for(Criteria c : edition.getCriteriaList()){
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        editionGraph.addSeries(criteria);
    }
    
    public void createProjGraph(Edition edition){
        ChartSeries criteria = new ChartSeries();  
         
        for(Criteria c : edition.getCriteriaList()){
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        projectGraph.addSeries(criteria);
    }
    
}
