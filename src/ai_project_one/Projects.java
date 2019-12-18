/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project_one;

import java.util.ArrayList;

/**
 *
 * @author fcbar
 */
public class Projects implements Comparable<Projects>{
    
    String Name;
    ArrayList<String> students = new ArrayList<String>();
    ArrayList<String> Topics = new ArrayList<String>();
    String Supervisor;
    String PreferedTime; 
    
    ArrayList<Examiners> Examiners = new ArrayList<Examiners>();
    
    ArrayList<Projects> Prefrences = new ArrayList<Projects>();

    public Projects(String Name , ArrayList<String> students ,ArrayList<String> Topics  , String Supervisor , String PreferedTime) {
        this.Name = Name;
        this.students = students;
        this.Topics = Topics;
        this.Supervisor= Supervisor; 
        this.PreferedTime = PreferedTime;
        
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<String> students) {
        this.students = students;
    }

    public ArrayList<String> getTopics() {
        return Topics;
    }

    public void setTopics(ArrayList<String> Topics) {
        this.Topics = Topics;
    }

    
    public String getSupervisor() {
        return Supervisor;
    }

    public void setSupervisor(String Supervisor) {
        this.Supervisor = Supervisor;
    }

    public ArrayList<Projects> getPrefrences() {
        return Prefrences;
    }

    public void setPrefrences(ArrayList<Projects> Prefrences) {
        this.Prefrences = Prefrences;
    }

    public ArrayList<Examiners> getExaminers() {
        return Examiners;
    }

    public void setExaminers(ArrayList<Examiners> Examiners) {
        this.Examiners = Examiners;
    }

    public String getPreferedTime() {
        return PreferedTime;
    }

    public void setPreferedTime(String PreferedTime) {
        this.PreferedTime = PreferedTime;
    }

    
    
  
    
    
    @Override
    public String toString() {
        return "Projects{" + "Name=" + Name + ", students=" + students + ", Topics=" + Topics + ", Supervisor=" + Supervisor + ", Prefrences=" + Prefrences + '}';
    }

    @Override
    public int compareTo(Projects projectcompare) {
        int compareTopicsSize =((Projects)projectcompare).getTopics().size();
        /* For Ascending order*/
        return this.Topics.size()-compareTopicsSize;
    }
    

    

    
    
    
    
    
    
}
