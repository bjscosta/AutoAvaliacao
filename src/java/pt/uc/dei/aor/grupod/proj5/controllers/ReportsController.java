/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.aor.grupod.proj5.controllers;


import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.Edition;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
import pt.uc.dei.aor.grupod.proj5.entities.Student;
import pt.uc.dei.aor.grupod.proj5.facades.EditionFacade;


@Named
@RequestScoped
public class ReportsController {
    
    @Inject
    private EditionFacade editionFacade;
    
    private Edition edition;
    private Project project;
    private Student student;
    private List<Edition> editionsList;
    private List<Project> projectsList;
    private List<Student> studentsList;
    
    
    
    @PostConstruct
    public void init(){
        editionsList = editionFacade.findAllEditions();
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
    
    public void confirmEdition(){
        
        projectsList = edition.getProjectList();
    }
    
}
