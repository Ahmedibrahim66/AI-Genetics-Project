/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project_one;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author AyhamHashesh
 */
public class ProjectsTable {
    SimpleStringProperty ProjectName;
    SimpleStringProperty StudentsInProject;
    SimpleStringProperty ProjectSupervisor;
    SimpleStringProperty ProjectTopics;
    SimpleStringProperty prefTime;
    SimpleStringProperty IntersetIn;

    public ProjectsTable(String ProjectName, String StudentsInProject, String ProjectSupervisor, String ProjectTopics, String prefTime, String IntersetIn) {
        this.ProjectName = new SimpleStringProperty(ProjectName);
        this.StudentsInProject = new SimpleStringProperty(StudentsInProject);
        this.ProjectSupervisor = new SimpleStringProperty(ProjectSupervisor);
        this.ProjectTopics = new SimpleStringProperty(ProjectTopics);
        this.prefTime = new SimpleStringProperty(prefTime);
        this.IntersetIn = new SimpleStringProperty(IntersetIn);
    }

    public String getProjectName() {
        return ProjectName.get();
    }

    public String getStudentsInProject() {
        return StudentsInProject.get();
    }

    public String getProjectSupervisor() {
        return ProjectSupervisor.get();
    }

    public String getProjectTopics() {
        return ProjectTopics.get();
    }

    public String getPrefTime() {
        return prefTime.get();
    }

    public String getIntersetIn() {
        return IntersetIn.get();
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName.set(ProjectName);
    }

    public void setStudentsInProject(String StudentsInProject) {
        this.StudentsInProject.set(StudentsInProject);
    }

    public void setProjectSupervisor(String ProjectSupervisor) {
        this.ProjectSupervisor.set(ProjectSupervisor);
    }

    public void setProjectTopics(String ProjectTopics) {
        this.ProjectTopics.set(ProjectTopics);
    }

    public void setPrefTime(String prefTime) {
        this.prefTime.set(prefTime);
    }

    public void setIntersetIn(String IntersetIn) {
        this.IntersetIn.set(IntersetIn);
    }
    
    
    
    



    
}
