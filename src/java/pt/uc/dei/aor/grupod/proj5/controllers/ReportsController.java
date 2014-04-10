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
import pt.uc.dei.aor.grupod.proj5.exceptions.NoResultQueryException;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;
import pt.uc.dei.aor.grupod.proj5.facades.ProjEvaluationFacade;
import pt.uc.dei.aor.grupod.proj5.facades.ProjectFacade;
import pt.uc.dei.aor.grupod.proj5.utilities.MessagesForUser;


@Named
@ViewScoped
public class ReportsController {
    
    @Inject
    private UserController userController;

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
    private CartesianChartModel studentsProjectGraph;
    private CartesianChartModel studentsEditionGraph;
    private String nameGraphStrudent;
    private CartesianChartModel studentsEvolutionProjectGraph;
    private List<Project> selectedProjects;
    private UIComponent oneProjectStudent;
    private UIComponent variousProjectsStudent;
    private Student studentLogged;
    private CartesianChartModel studentProjGraphS;
    private CartesianChartModel studentProjectCriteriaGraph;
    private CartesianChartModel avgProjectStudent;
    private List<Project> newProjects;
    private CartesianChartModel bestCriteriaStudent;
    private CartesianChartModel bestCriteriaStudentAdmin;
    private CartesianChartModel bestCriteriaProjectAdmin;
    private CartesianChartModel bestCriteriaEditionAdmin;

    @PostConstruct
    public void init() {
        editionsList = editionFacade.findAllEditions();
        editionGraph = new CartesianChartModel();
        projectGraph = new CartesianChartModel();
        studentGraph = new CartesianChartModel();
        studentsProjectGraph = new CartesianChartModel();
        studentsEditionGraph = new CartesianChartModel();
        studentsEvolutionProjectGraph = new CartesianChartModel();
        studentLogged = userController.getStudent();
        studentProjGraphS = new CartesianChartModel();
        studentProjectCriteriaGraph = new CartesianChartModel();
        avgProjectStudent = new CartesianChartModel();
        bestCriteriaStudent = new CartesianChartModel();
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

    public CartesianChartModel getStudentsProjectGraph() {
        return studentsProjectGraph;
    }

    public void setStudentsProjectGraph(CartesianChartModel studentsProjectGraph) {
        this.studentsProjectGraph = studentsProjectGraph;
    }

    public CartesianChartModel getStudentsEditionGraph() {
        return studentsEditionGraph;
    }

    public void setStudentsEditionGraph(CartesianChartModel studentsEditionGraph) {
        this.studentsEditionGraph = studentsEditionGraph;
    }

    public String getNameGraphStrudent() {
        return nameGraphStrudent;
    }

    public void setNameGraphStrudent(String nameGraphStrudent) {
        this.nameGraphStrudent = nameGraphStrudent;
    }

    public CartesianChartModel getStudentsEvolutionProjectGraph() {
        return studentsEvolutionProjectGraph;
    }

    public void setStudentsEvolutionProjectGraph(CartesianChartModel studentsEvolutionProjectGraph) {
        this.studentsEvolutionProjectGraph = studentsEvolutionProjectGraph;
    }

    public List<Project> getSelectedProjects() {
        return selectedProjects;
    }

    public void setSelectedProjects(List<Project> selectedProjects) {
        this.selectedProjects = selectedProjects;
    }

    public UIComponent getOneProjectStudent() {
        return oneProjectStudent;
    }

    public void setOneProjectStudent(UIComponent oneProjectStudent) {
        this.oneProjectStudent = oneProjectStudent;
    }

    public UIComponent getVariousProjectsStudent() {
        return variousProjectsStudent;
    }

    public void setVariousProjectsStudent(UIComponent variousProjectsStudent) {
        this.variousProjectsStudent = variousProjectsStudent;
    }

    public Student getStudentLogged() {
        return studentLogged;
    }

    public void setStudentLogged(Student studentLogged) {
        this.studentLogged = studentLogged;
    }

    public CartesianChartModel getStudentProjGraphS() {
        return studentProjGraphS;
    }

    public void setStudentProjGraphS(CartesianChartModel studentProjGraphS) {
        this.studentProjGraphS = studentProjGraphS;
    }

    public CartesianChartModel getStudentProjectCriteriaGraph() {
        return studentProjectCriteriaGraph;
    }

    public void setStudentProjectCriteriaGraph(CartesianChartModel studentProjectCriteriaGraph) {
        this.studentProjectCriteriaGraph = studentProjectCriteriaGraph;
    }

    public CartesianChartModel getAvgProjectStudent() {
        return avgProjectStudent;
    }

    public void setAvgProjectStudent(CartesianChartModel avgProjectStudent) {
        this.avgProjectStudent = avgProjectStudent;
    }

    public List<Project> getNewProjects() {
        return newProjects;
    }

    public void setNewProjects(List<Project> newProjects) {
        this.newProjects = newProjects;
    }

    public CartesianChartModel getBestCriteriaStudent() {
        return bestCriteriaStudent;
    }

    public void setBestCriteriaStudent(CartesianChartModel bestCriteriaStudent) {
        this.bestCriteriaStudent = bestCriteriaStudent;
    }

    public CartesianChartModel getBestCriteriaStudentAdmin() {
        return bestCriteriaStudentAdmin;
    }

    public void setBestCriteriaStudentAdmin(CartesianChartModel bestCriteriaStudentAdmin) {
        this.bestCriteriaStudentAdmin = bestCriteriaStudentAdmin;
    }

    public CartesianChartModel getBestCriteriaProjectAdmin() {
        return bestCriteriaProjectAdmin;
    }

    public void setBestCriteriaProjectAdmin(CartesianChartModel bestCriteriaProjectAdmin) {
        this.bestCriteriaProjectAdmin = bestCriteriaProjectAdmin;
    }

    public CartesianChartModel getBestCriteriaEditionAdmin() {
        return bestCriteriaEditionAdmin;
    }

    public void setBestCriteriaEditionAdmin(CartesianChartModel bestCriteriaEditionAdmin) {
        this.bestCriteriaEditionAdmin = bestCriteriaEditionAdmin;
    }
    
    
    

    public void confirmEdition() {
        if (edition != null) {

            try {
                projectsList = edition.getProjectList();
                studentsList = projEvaluationFacade.studentsWithAvaliationsEdition(edition);
                editionAverage = projEvaluationFacade.averageEdition(edition);
                edition = projEvaluationFacade.averageCriteriaEdition(edition);
                createBestCriteriaEditionAdmin(edition);
                createEditionGraph(edition);
                createStudentsEditionChart(edition);
                editionReport.setRendered(true);
                projectReport.setRendered(false);
                studentReport.setRendered(false);
                project = null;
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                MessagesForUser.addMessage(ex.getMessage());
            }
        } else {
            MessagesForUser.addMessage("Selecione uma Edição");
        }

    }

    public void confirmProject() {

        if (project != null) {
            try {
                studentsList = projEvaluationFacade.studentsWithAvaliationsProject(project);
                projectAverage = projEvaluationFacade.averageProject(project);
                edition = projEvaluationFacade.averageCriteriaProject(edition, project);
                createBestCriteriaProjectAdmin(edition);
                createProjGraph(edition);
                createStudentsProjectChart(project, edition);
                studentReport.setRendered(false);
                editionReport.setRendered(false);
                projectReport.setRendered(true);
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                MessagesForUser.addMessage(ex.getMessage());
            }
        } else {
            MessagesForUser.addMessage("Selecione um Projecto");
        }
    }

    public void confirmStudents() {
        
        if(student != null){
        try {
            studentAverage = projEvaluationFacade.averageStudent(student);
            if (project == null) {
                edition = projEvaluationFacade.averageCriteriaStudent(edition, student);
                nameGraphStrudent = "Médias das Avaliações do Estudante";
                createStudentEvolutionProjects();
            } else {
                
                edition = projEvaluationFacade.averageStudentProject(edition, student, project);
                nameGraphStrudent = "Avaliações do Estudante";
            }
            createBestCriteriaStudentAdmin(edition);
            createStudentGraph(edition);
            projectReport.setRendered(false);
            studentReport.setRendered(true);
            editionReport.setRendered(false);
        } catch (NoResultQueryException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
            MessagesForUser.addMessage(ex.getMessage());
        }
        }
        else{
            MessagesForUser.addMessage("Selecione um Estudante");
        }
    }

    public void createEditionGraph(Edition edition) {
        editionGraph = new CartesianChartModel();
        ChartSeries criteria = new ChartSeries();
        criteria.setLabel("Media por Critério");

        for (Criteria c : edition.getCriteriaList()) {
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        editionGraph.addSeries(criteria);
    }

    public void createProjGraph(Edition edition) {

        projectGraph = new CartesianChartModel();
        ChartSeries criteria = new ChartSeries();
        criteria.setLabel("Media por Critério");

        for (Criteria c : edition.getCriteriaList()) {
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        projectGraph.addSeries(criteria);
    }

    public void createStudentGraph(Edition edition) {

        studentGraph = new CartesianChartModel();
        ChartSeries criteria = new ChartSeries();
        criteria.setLabel("Media por Critério");

        for (Criteria c : edition.getCriteriaList()) {
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        studentGraph.addSeries(criteria);
    }

    public void createStudentsProjectChart(Project p, Edition e) {
        studentsProjectGraph = new CartesianChartModel();

        for (Criteria c : e.getCriteriaList()) {

            ChartSeries a = new ChartSeries();
            for (Student s : projEvaluationFacade.studentsWithAvaliationsProject(p)) {
                try {
                    a.set(s.getName(), projEvaluationFacade.evaluationCriteriaStudentProject(project, c, s));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessage(ex.getMessage());
                }
                a.setLabel(c.getCriteriaName());
            }
            studentsProjectGraph.addSeries(a);
        }
    }

    public void createStudentsEditionChart(Edition e) {
        studentsEditionGraph = new CartesianChartModel();

        ChartSeries criteria = new ChartSeries();
        criteria.setLabel("Media por Aluno");

        for (Student s : projEvaluationFacade.studentsWithAvaliationsEdition(e)) {
            try {
                criteria.set(s.getName(), projEvaluationFacade.averageStudent(s));
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                MessagesForUser.addMessage(ex.getMessage());
            }
        }
        studentsEditionGraph.addSeries(criteria);

    }
    
    public void createStudentEvolutionProjects(){
        studentsEvolutionProjectGraph = new CartesianChartModel();
        ChartSeries evo = new ChartSeries();
        evo.setLabel("Média por Projeto");
        
        for(Project p : student.getProjects()){
            try {
                evo.set(p.getName(), projEvaluationFacade.evoStudentProject(p, student));
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                MessagesForUser.addMessage(ex.getMessage());
            }
        }
        studentsEvolutionProjectGraph.addSeries(evo);
    }
    
    public void confirmProjectStudent(){
        edition = userController.getStudent().getEdition();
        if (selectedProjects.isEmpty()){
            MessagesForUser.addMessage("Selecione projectos");
        }
        else if(selectedProjects.size() == 1){
            try {
                projectAverage = projEvaluationFacade.evoStudentProject(selectedProjects.get(0), studentLogged);
                edition = projEvaluationFacade.averageStudentProject(edition, studentLogged, selectedProjects.get(0));
                createStudentProjGraphS(edition);
                variousProjectsStudent.setRendered(false);
                oneProjectStudent.setRendered(true);
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                 MessagesForUser.addMessage(ex.getMessage());
            }
            
        }
        else{
            try {
                editionAverage = projEvaluationFacade.averageStudent(studentLogged);
                newProjects = projEvaluationFacade.insertAvgProject(selectedProjects, studentLogged);
                createBestCriteriaStudentGraph(edition);
                createAvgProjStudent(edition);
                createStudentProjectCriteriaGraph(edition);
                oneProjectStudent.setRendered(false);
                variousProjectsStudent.setRendered(true);
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                MessagesForUser.addMessage(ex.getMessage());
            }
        }
    }
    
    

    
    public  List<Project> listProjEvaStudent(){
        List <Project> lp = projEvaluationFacade.projWithEva(studentLogged);
        return lp;
    }
    
    public void createStudentProjGraphS(Edition e){
        studentProjGraphS = new CartesianChartModel();
        
        ChartSeries pc = new ChartSeries();
        pc.setLabel("Avaliações dos Critérios");
        
        for(Criteria c : e.getCriteriaList()){
            pc.set(c.getCriteriaName(), c.getAvgValue());
        }
        
        studentProjGraphS.addSeries(pc);
    }
    
    
    
    public void createStudentProjectCriteriaGraph(Edition e){
        studentProjectCriteriaGraph = new CartesianChartModel();
        
        for (Criteria c : e.getCriteriaList()) {

            ChartSeries cs = new ChartSeries();
            
            for (Project p : selectedProjects) {
                try {
                    cs.set(p.getName(), projEvaluationFacade.evaluationCriteriaStudentProject(p, c, studentLogged));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessage(ex.getMessage());
                }
                cs.setLabel(c.getCriteriaName());
            }
            studentProjectCriteriaGraph.addSeries(cs);
        }
        
        
        
    }
    
    public void createAvgProjStudent(Edition e){
        avgProjectStudent = new CartesianChartModel();
        
        ChartSeries cs = new ChartSeries();
        cs.setLabel("Média do Projeto");
        
        for(Project p : selectedProjects){
            try {
                cs.set(p.getName(), projEvaluationFacade.evoStudentProject(p, studentLogged));
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        avgProjectStudent.addSeries(cs);
        
    }
    
    public void createBestCriteriaStudentGraph(Edition e){
      bestCriteriaStudent = new CartesianChartModel();
      
      for (Project p : selectedProjects) {

            ChartSeries cs = new ChartSeries();
            
            for (Criteria c : e.getCriteriaList()) {
                try {
                    cs.set(c.getCriteriaName(), projEvaluationFacade.evaluationCriteriaStudentProject(p, c, studentLogged));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessage(ex.getMessage());
                }
                cs.setLabel(p.getName());
            }
            bestCriteriaStudent.addSeries(cs);
        }
    }
    
    
    public void createBestCriteriaEditionAdmin(Edition e){
        bestCriteriaEditionAdmin = new CartesianChartModel();
      
      for (Student s : studentsList) {

            ChartSeries cs = new ChartSeries();
            
            for (Criteria c : e.getCriteriaList()) {
                try {
                    cs.set(c.getCriteriaName(), projEvaluationFacade.evaEditionCriteria(c));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessage(ex.getMessage());
                }
                cs.setLabel(s.getName());
            }
            bestCriteriaEditionAdmin.addSeries(cs);
        }
    }
    
    public void createBestCriteriaProjectAdmin(Edition e){
        bestCriteriaProjectAdmin = new CartesianChartModel();
      
      for (Student s : studentsList) {

            ChartSeries cs = new ChartSeries();
            
            for (Criteria c : e.getCriteriaList()) {
                try {
                    cs.set(c.getCriteriaName(), projEvaluationFacade.evaProjectCriteria(project, c));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessage(ex.getMessage());
                }
                cs.setLabel(s.getName());
            }
            bestCriteriaProjectAdmin.addSeries(cs);
        }
    }
    
    public void createBestCriteriaStudentAdmin(Edition e){
        bestCriteriaStudentAdmin = new CartesianChartModel();
      
      for (Project p : projectsList) {

            ChartSeries cs = new ChartSeries();
            
            for (Criteria c : e.getCriteriaList()) {
                try {
                    cs.set(c.getCriteriaName(), projEvaluationFacade.evaluationCriteriaStudentProject(p, c, student));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessage(ex.getMessage());
                }
                cs.setLabel(p.getName());
            }
            bestCriteriaStudentAdmin.addSeries(cs);
        }
    }
    
}
