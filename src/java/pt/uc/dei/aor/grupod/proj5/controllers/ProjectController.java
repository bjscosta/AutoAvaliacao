/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.aor.grupod.proj5.entities.Project;
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

    @PostConstruct
    public void init() {
        openProjects = projectFacade.findOpenProjects();
        closeProjects = projectFacade.findClosedProjects();
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

}
