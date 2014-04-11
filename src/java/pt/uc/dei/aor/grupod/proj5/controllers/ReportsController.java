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
import pt.uc.dei.aor.grupod.proj5.utilities.MessagesForUser;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@Named
@ViewScoped
public class ReportsController {

    @Inject
    private UserController userController;

    @Inject
    private EditionFacade editionFacade;


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

    /**
     * this method initializes some of the variables of the ReportsController
     */
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
        bestCriteriaEditionAdmin = new CartesianChartModel();
        bestCriteriaProjectAdmin = new CartesianChartModel();
        bestCriteriaStudentAdmin = new CartesianChartModel();
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
     * @return project
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
     * @return studentsList
     */
    public List<Student> getStudentsList() {
        return studentsList;
    }

    /**
     *
     * @param studentsList
     */
    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    /**
     *
     * @return editionGraph
     */
    public CartesianChartModel getEditionGraph() {
        return editionGraph;
    }

    /**
     *
     * @param editionGraph
     */
    public void setEditionGraph(CartesianChartModel editionGraph) {
        this.editionGraph = editionGraph;
    }

    /**
     *
     * @return editionsList
     */
    public List<Edition> getEditionsList() {
        return editionsList;
    }

    /**
     *
     * @param editionsList
     */
    public void setEditionsList(List<Edition> editionsList) {
        this.editionsList = editionsList;
    }

    /**
     *
     * @return projectsList
     */
    public List<Project> getProjectsList() {
        return projectsList;
    }

    /**
     *
     * @param projectsList
     */
    public void setProjectsList(List<Project> projectsList) {
        this.projectsList = projectsList;
    }

    /**
     *
     * @return student
     */
    public Student getStudent() {
        return student;
    }

    /**
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     *
     * @return projectTable
     */
    public UIComponent getProjectTable() {
        return projectTable;
    }

    /**
     *
     * @param projectTable
     */
    public void setProjectTable(UIComponent projectTable) {
        this.projectTable = projectTable;
    }

    /**
     *
     * @return studentsTable
     */
    public UIComponent getStudentsTable() {
        return studentsTable;
    }

    /**
     *
     * @param studentsTable
     */
    public void setStudentsTable(UIComponent studentsTable) {
        this.studentsTable = studentsTable;
    }

    /**
     *
     * @return editionReport
     */
    public UIComponent getEditionReport() {
        return editionReport;
    }

    /**
     *
     * @param editionReport
     */
    public void setEditionReport(UIComponent editionReport) {
        this.editionReport = editionReport;
    }

    /**
     *
     * @return editionAverage
     */
    public double getEditionAverage() {
        return editionAverage;
    }

    /**
     *
     * @param editionAverage
     */
    public void setEditionAverage(double editionAverage) {
        this.editionAverage = editionAverage;
    }

    /**
     *
     * @return criteriaAvgEdition
     */
    public List<Double> getCriteriaAvgEdition() {
        return criteriaAvgEdition;
    }

    /**
     *
     * @param criteriaAvgEdition
     */
    public void setCriteriaAvgEdition(List<Double> criteriaAvgEdition) {
        this.criteriaAvgEdition = criteriaAvgEdition;
    }

    /**
     *
     * @return projectReport
     */
    public UIComponent getProjectReport() {
        return projectReport;
    }

    /**
     *
     * @param projectReport
     */
    public void setProjectReport(UIComponent projectReport) {
        this.projectReport = projectReport;
    }

    /**
     *
     * @return projectGraph
     */
    public CartesianChartModel getProjectGraph() {
        return projectGraph;
    }

    /**
     *
     * @param projectGraph
     */
    public void setProjectGraph(CartesianChartModel projectGraph) {
        this.projectGraph = projectGraph;
    }

    /**
     *
     * @return projectAverage
     */
    public double getProjectAverage() {
        return projectAverage;
    }

    /**
     *
     * @param projectAverage
     */
    public void setProjectAverage(double projectAverage) {
        this.projectAverage = projectAverage;
    }

    /**
     *
     * @return criteriaAvgProject
     */
    public List<Double> getCriteriaAvgProject() {
        return criteriaAvgProject;
    }

    /**
     *
     * @param criteriaAvgProject
     */
    public void setCriteriaAvgProject(List<Double> criteriaAvgProject) {
        this.criteriaAvgProject = criteriaAvgProject;
    }

    /**
     *
     * @return studentReport
     */
    public UIComponent getStudentReport() {
        return studentReport;
    }

    /**
     *
     * @param studentReport
     */
    public void setStudentReport(UIComponent studentReport) {
        this.studentReport = studentReport;
    }

    /**
     *
     * @return studentGraph
     */
    public CartesianChartModel getStudentGraph() {
        return studentGraph;
    }

    /**
     *
     * @param studentGraph
     */
    public void setStudentGraph(CartesianChartModel studentGraph) {
        this.studentGraph = studentGraph;
    }

    /**
     *
     * @return studentAverage
     */
    public double getStudentAverage() {
        return studentAverage;
    }

    /**
     *
     * @param studentAverage
     */
    public void setStudentAverage(double studentAverage) {
        this.studentAverage = studentAverage;
    }

    /**
     *
     * @return criteriaAvgStudent
     */
    public List<Double> getCriteriaAvgStudent() {
        return criteriaAvgStudent;
    }

    /**
     *
     * @param criteriaAvgStudent
     */
    public void setCriteriaAvgStudent(List<Double> criteriaAvgStudent) {
        this.criteriaAvgStudent = criteriaAvgStudent;
    }

    /**
     *
     * @return studentsProjectGraph
     */
    public CartesianChartModel getStudentsProjectGraph() {
        return studentsProjectGraph;
    }

    /**
     *
     * @param studentsProjectGraph
     */
    public void setStudentsProjectGraph(CartesianChartModel studentsProjectGraph) {
        this.studentsProjectGraph = studentsProjectGraph;
    }

    /**
     *
     * @return studentsEditionGraph
     */
    public CartesianChartModel getStudentsEditionGraph() {
        return studentsEditionGraph;
    }

    /**
     *
     * @param studentsEditionGraph
     */
    public void setStudentsEditionGraph(CartesianChartModel studentsEditionGraph) {
        this.studentsEditionGraph = studentsEditionGraph;
    }

    /**
     *
     * @return nameGraphStrudent
     */
    public String getNameGraphStrudent() {
        return nameGraphStrudent;
    }

    /**
     *
     * @param nameGraphStrudent
     */
    public void setNameGraphStrudent(String nameGraphStrudent) {
        this.nameGraphStrudent = nameGraphStrudent;
    }

    /**
     *
     * @return studentsEvolutionProjectGraph
     */
    public CartesianChartModel getStudentsEvolutionProjectGraph() {
        return studentsEvolutionProjectGraph;
    }

    /**
     *
     * @param studentsEvolutionProjectGraph
     */
    public void setStudentsEvolutionProjectGraph(CartesianChartModel studentsEvolutionProjectGraph) {
        this.studentsEvolutionProjectGraph = studentsEvolutionProjectGraph;
    }

    /**
     *
     * @return selectedProjects
     */
    public List<Project> getSelectedProjects() {
        return selectedProjects;
    }

    /**
     *
     * @param selectedProjects
     */
    public void setSelectedProjects(List<Project> selectedProjects) {
        this.selectedProjects = selectedProjects;
    }

    /**
     *
     * @return oneProjectStudent
     */
    public UIComponent getOneProjectStudent() {
        return oneProjectStudent;
    }

    /**
     *
     * @param oneProjectStudent
     */
    public void setOneProjectStudent(UIComponent oneProjectStudent) {
        this.oneProjectStudent = oneProjectStudent;
    }

    /**
     *
     * @return variousProjectsStudent
     */
    public UIComponent getVariousProjectsStudent() {
        return variousProjectsStudent;
    }

    /**
     *
     * @param variousProjectsStudent
     */
    public void setVariousProjectsStudent(UIComponent variousProjectsStudent) {
        this.variousProjectsStudent = variousProjectsStudent;
    }

    /**
     *
     * @return studentLogged
     */
    public Student getStudentLogged() {
        return studentLogged;
    }

    /**
     *
     * @param studentLogged
     */
    public void setStudentLogged(Student studentLogged) {
        this.studentLogged = studentLogged;
    }

    /**
     *
     * @return studentProjGraphS
     */
    public CartesianChartModel getStudentProjGraphS() {
        return studentProjGraphS;
    }

    /**
     *
     * @param studentProjGraphS
     */
    public void setStudentProjGraphS(CartesianChartModel studentProjGraphS) {
        this.studentProjGraphS = studentProjGraphS;
    }

    /**
     *
     * @return studentProjectCriteriaGraph
     */
    public CartesianChartModel getStudentProjectCriteriaGraph() {
        return studentProjectCriteriaGraph;
    }

    /**
     *
     * @param studentProjectCriteriaGraph
     */
    public void setStudentProjectCriteriaGraph(CartesianChartModel studentProjectCriteriaGraph) {
        this.studentProjectCriteriaGraph = studentProjectCriteriaGraph;
    }

    /**
     *
     * @return avgProjectStudent
     */
    public CartesianChartModel getAvgProjectStudent() {
        return avgProjectStudent;
    }

    /**
     *
     * @param avgProjectStudent
     */
    public void setAvgProjectStudent(CartesianChartModel avgProjectStudent) {
        this.avgProjectStudent = avgProjectStudent;
    }

    /**
     *
     * @return newProjects
     */
    public List<Project> getNewProjects() {
        return newProjects;
    }

    /**
     *
     * @param newProjects
     */
    public void setNewProjects(List<Project> newProjects) {
        this.newProjects = newProjects;
    }

    /**
     *
     * @return bestCriteriaStudent
     */
    public CartesianChartModel getBestCriteriaStudent() {
        return bestCriteriaStudent;
    }

    /**
     *
     * @param bestCriteriaStudent
     */
    public void setBestCriteriaStudent(CartesianChartModel bestCriteriaStudent) {
        this.bestCriteriaStudent = bestCriteriaStudent;
    }

    /**
     *
     * @return bestCriteriaStudentAdmin
     */
    public CartesianChartModel getBestCriteriaStudentAdmin() {
        return bestCriteriaStudentAdmin;
    }

    /**
     *
     * @param bestCriteriaStudentAdmin
     */
    public void setBestCriteriaStudentAdmin(CartesianChartModel bestCriteriaStudentAdmin) {
        this.bestCriteriaStudentAdmin = bestCriteriaStudentAdmin;
    }

    /**
     *
     * @return bestCriteriaProjectAdmin
     */
    public CartesianChartModel getBestCriteriaProjectAdmin() {
        return bestCriteriaProjectAdmin;
    }

    /**
     *
     * @param bestCriteriaProjectAdmin
     */
    public void setBestCriteriaProjectAdmin(CartesianChartModel bestCriteriaProjectAdmin) {
        this.bestCriteriaProjectAdmin = bestCriteriaProjectAdmin;
    }

    /**
     *
     * @return bestCriteriaEditionAdmin
     */
    public CartesianChartModel getBestCriteriaEditionAdmin() {
        return bestCriteriaEditionAdmin;
    }

    /**
     *
     * @param bestCriteriaEditionAdmin
     */
    public void setBestCriteriaEditionAdmin(CartesianChartModel bestCriteriaEditionAdmin) {
        this.bestCriteriaEditionAdmin = bestCriteriaEditionAdmin;
    }

    /**
     * Create the report of a edition for the administrator
     */
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
                MessagesForUser.addMessageError(ex.getMessage());
            }
        } else {
            MessagesForUser.addMessageError("Selecione uma Edição");
        }

    }

    /**
     * Create the report of a project for the administrator
     */
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
                MessagesForUser.addMessageError(ex.getMessage());
            }
        } else {
            MessagesForUser.addMessageError("Selecione um Projecto");
        }
    }

    /**
     * Create the report of a student for the administrator
     */
    public void confirmStudents() {

        if (student != null) {
            try {
                studentAverage = projEvaluationFacade.averageStudent(student);
                if (project == null) {
                    edition = projEvaluationFacade.averageCriteriaStudent(edition, student);
                    nameGraphStrudent = "Médias das Avaliações do Estudante";
                    createStudentEvolutionProjects();
                    createBestCriteriaStudentAdmin(edition);
                } else {
                    edition = projEvaluationFacade.averageStudentProject(edition, student, project);
                    nameGraphStrudent = "Avaliações do Estudante";
                }

                createStudentGraph(edition);
                projectReport.setRendered(false);
                studentReport.setRendered(true);
                editionReport.setRendered(false);
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                MessagesForUser.addMessageError(ex.getMessage());
            }
        } else {
            MessagesForUser.addMessageError("Selecione um Estudante");
        }
    }

    /**
     * Create chart editionGraph
     * @param edition Edition
     */
    public void createEditionGraph(Edition edition) {
        editionGraph = new CartesianChartModel();
        ChartSeries criteria = new ChartSeries();
        criteria.setLabel("Media por Critério");

        for (Criteria c : edition.getCriteriaList()) {
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        editionGraph.addSeries(criteria);
    }

    /**
     * Create chart projectGraph
     * @param edition Edition
     */
    public void createProjGraph(Edition edition) {

        projectGraph = new CartesianChartModel();
        ChartSeries criteria = new ChartSeries();
        criteria.setLabel("Media por Critério");

        for (Criteria c : edition.getCriteriaList()) {
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        projectGraph.addSeries(criteria);
    }

    /**
     * Create chart studentGraph
     * @param edition Edition
     */
    public void createStudentGraph(Edition edition) {

        studentGraph = new CartesianChartModel();
        ChartSeries criteria = new ChartSeries();
        criteria.setLabel("Valor do Critério");

        for (Criteria c : edition.getCriteriaList()) {
            criteria.set(c.getCriteriaName(), c.getAvgValue());
        }
        studentGraph.addSeries(criteria);
    }

    /**
     * Create chart studentsProjectGraph
     * @param p Project
     * @param e Edition
     */
    public void createStudentsProjectChart(Project p, Edition e) {
        studentsProjectGraph = new CartesianChartModel();

        for (Criteria c : e.getCriteriaList()) {

            ChartSeries a = new ChartSeries();
            for (Student s : projEvaluationFacade.studentsWithAvaliationsProject(p)) {
                try {
                    a.set(s.getName(), projEvaluationFacade.evaluationCriteriaStudentProject(project, c, s));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessageError(ex.getMessage());
                }
                a.setLabel(c.getCriteriaName());
            }
            studentsProjectGraph.addSeries(a);
        }
    }

    /**
     * Create chart studentsEditionGraph
     * @param e Edition
     */
    public void createStudentsEditionChart(Edition e) {
        studentsEditionGraph = new CartesianChartModel();

        ChartSeries criteria = new ChartSeries();
        criteria.setLabel("Media por Aluno");

        for (Student s : projEvaluationFacade.studentsWithAvaliationsEdition(e)) {
            try {
                criteria.set(s.getName(), projEvaluationFacade.averageStudent(s));
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                MessagesForUser.addMessageError(ex.getMessage());
            }
        }
        studentsEditionGraph.addSeries(criteria);

    }

    /**
     * Create chart studentsEvolutionProjectGraph
     */
    public void createStudentEvolutionProjects() {
        studentsEvolutionProjectGraph = new CartesianChartModel();
        ChartSeries evo = new ChartSeries();
        evo.setLabel("Média por Projeto");

        for (Project p : student.getProjects()) {
            try {
                evo.set(p.getName(), projEvaluationFacade.evoStudentProject(p, student));
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                MessagesForUser.addMessageError(ex.getMessage());
            }
        }
        studentsEvolutionProjectGraph.addSeries(evo);
    }

    /**
     * Creates the report for one student
     */
    public void confirmProjectStudent() {
        edition = userController.getStudent().getEdition();
        if (selectedProjects.isEmpty()) {
            MessagesForUser.addMessageError("Selecione projectos");
        } else if (selectedProjects.size() == 1) {
            try {
                projectAverage = projEvaluationFacade.evoStudentProject(selectedProjects.get(0), studentLogged);
                edition = projEvaluationFacade.averageStudentProject(edition, studentLogged, selectedProjects.get(0));
                createStudentProjGraphS(edition);
                variousProjectsStudent.setRendered(false);
                oneProjectStudent.setRendered(true);
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                MessagesForUser.addMessageError(ex.getMessage());
            }

        } else {
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
                MessagesForUser.addMessageError(ex.getMessage());
            }
        }
    }

    /**
     * Gets the list of projects with evaluation
     * @return list of Projects
     */
    public List<Project> listProjEvaStudent() {
        List<Project> lp = projEvaluationFacade.projWithEva(studentLogged);
        return lp;
    }

    /**
     * Create chart studentProjGraphS
     * @param e Edition
     */
    public void createStudentProjGraphS(Edition e) {
        studentProjGraphS = new CartesianChartModel();

        ChartSeries pc = new ChartSeries();
        pc.setLabel("Avaliações dos Critérios");

        for (Criteria c : e.getCriteriaList()) {
            pc.set(c.getCriteriaName(), c.getAvgValue());
        }

        studentProjGraphS.addSeries(pc);
    }

    /**
     *  Create chart studentProjectCriteriaGraph
     * @param e Edition
     */
    public void createStudentProjectCriteriaGraph(Edition e) {
        studentProjectCriteriaGraph = new CartesianChartModel();

        for (Criteria c : e.getCriteriaList()) {

            ChartSeries cs = new ChartSeries();

            for (Project p : selectedProjects) {
                try {
                    cs.set(p.getName(), projEvaluationFacade.evaluationCriteriaStudentProject(p, c, studentLogged));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessageError(ex.getMessage());
                }
                cs.setLabel(c.getCriteriaName());
            }
            studentProjectCriteriaGraph.addSeries(cs);
        }

    }

    /**
     * Create chart avgProjectStudent
     * @param e Edition
     */
    public void createAvgProjStudent(Edition e) {
        avgProjectStudent = new CartesianChartModel();

        ChartSeries cs = new ChartSeries();
        cs.setLabel("Média do Projeto");

        for (Project p : selectedProjects) {
            try {
                cs.set(p.getName(), projEvaluationFacade.evoStudentProject(p, studentLogged));
            } catch (NoResultQueryException ex) {
                Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        avgProjectStudent.addSeries(cs);

    }

    /**
     * Create chart bestCriteriaStudent
     * @param e Edition
     */
    public void createBestCriteriaStudentGraph(Edition e) {
        bestCriteriaStudent = new CartesianChartModel();

        for (Project p : selectedProjects) {

            ChartSeries cs = new ChartSeries();

            for (Criteria c : e.getCriteriaList()) {
                try {
                    cs.set(c.getCriteriaName(), projEvaluationFacade.evaluationCriteriaStudentProject(p, c, studentLogged));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessageError(ex.getMessage());
                }
                cs.setLabel(p.getName());
            }
            bestCriteriaStudent.addSeries(cs);
        }
    }

    /**
     * Create chart bestCriteriaEditionAdmin
     * @param e Edition
     */
    public void createBestCriteriaEditionAdmin(Edition e) {
        bestCriteriaEditionAdmin = new CartesianChartModel();

        for (Student s : studentsList) {

            ChartSeries cs = new ChartSeries();

            for (Criteria c : e.getCriteriaList()) {
                try {
                    cs.set(c.getCriteriaName(), projEvaluationFacade.evaEditionCriteria(c, s));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessageError(ex.getMessage());
                }
                cs.setLabel(s.getName());
            }
            bestCriteriaEditionAdmin.addSeries(cs);
        }
    }

    /**
     *  Create chart bestCriteriaProjectAdmin
     * @param e Edition
     */
    public void createBestCriteriaProjectAdmin(Edition e) {
        bestCriteriaProjectAdmin = new CartesianChartModel();

        for (Student s : studentsList) {

            ChartSeries cs = new ChartSeries();

            for (Criteria c : e.getCriteriaList()) {
                try {
                    cs.set(c.getCriteriaName(), projEvaluationFacade.evaProjectCriteria(project, c, s));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessageError(ex.getMessage());
                }
                cs.setLabel(s.getName());
            }
            bestCriteriaProjectAdmin.addSeries(cs);
        }
    }

    /**
     * Create chart bestCriteriaStudentAdmin
     * @param e Edition
     */
    public void createBestCriteriaStudentAdmin(Edition e) {
        bestCriteriaStudentAdmin = new CartesianChartModel();

        for (Project p : projectsList) {

            ChartSeries cs = new ChartSeries();

            for (Criteria c : e.getCriteriaList()) {
                try {
                    cs.set(c.getCriteriaName(), projEvaluationFacade.evaluationCriteriaStudentProject(p, c, student));
                } catch (NoResultQueryException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                    MessagesForUser.addMessageError(ex.getMessage());
                }
                cs.setLabel(p.getName());
            }
            bestCriteriaStudentAdmin.addSeries(cs);
        }
    }

}
