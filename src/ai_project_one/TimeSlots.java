package ai_project_one;

import java.util.ArrayList;

public class TimeSlots implements Comparable {
/*
    String projectName;
    

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public TimeSlots(String projectName) {
        this.projectName = projectName;
    }
    
    @Override
    public String toString() {
        return "TimeSlots{" + "projectName=" + projectName + '}';
    }
    */
    ArrayList<Projects> Days = new ArrayList<>();
    int fitnessScore ; 
    
    public TimeSlots(ArrayList<Projects> Days) {
        this.Days = Days;
    }

    public ArrayList<Projects> getDays() {
        return Days;
    }

    public void setDays(ArrayList<Projects> Days) {
        this.Days = Days;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(int fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    
    @Override
    public String toString() {
        return "TimeSlots{" + "Days=" + Days + '}' ;
    }
    
    
    

    @Override
    public int compareTo(Object o) {
        int compareage=((TimeSlots)o).getFitnessScore();
        /* For Ascending order*/
        return this.fitnessScore-compareage;
    }
     

    

    
}
