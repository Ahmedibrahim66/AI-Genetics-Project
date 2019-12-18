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
public class Examiners implements Comparable<Examiners>{

    String Name;
    ArrayList<String> Topics = new ArrayList<>();
    ArrayList<String> Moderator_for = new ArrayList<>();
    ArrayList<Projects> Examiner_for = new ArrayList<>();
    int numberOfDiscussedProjects;

    public int getNumberOfDiscussedProjects() {
        return numberOfDiscussedProjects;
    }

    public void setNumberOfDiscussedProjects(int numberOfDiscussedProjects) {
        this.numberOfDiscussedProjects = numberOfDiscussedProjects;
    }

    public Examiners(String Name, ArrayList<String> Topics) {
        this.Name = Name;
        this.Topics = Topics;

    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ArrayList<String> getTopics() {
        return Topics;
    }

    public void setTopics(ArrayList<String> Topics) {
        this.Topics = Topics;
    }

    public ArrayList<String> getModerator_for() {
        return Moderator_for;
    }

    public void setModerator_for(ArrayList<String> Moderator_for) {
        this.Moderator_for = Moderator_for;
    }

    public ArrayList<Projects> getExaminer_for() {
        return Examiner_for;
    }

    public void setExaminer_for(ArrayList<Projects> Examiner_for) {
        this.Examiner_for = Examiner_for;
    }

    @Override
    public String toString() {
        return "Examiners{" + "Name=" + Name + ", Topics=" + Topics + '}';
    }
     @Override
    public int compareTo(Examiners examinerscompare) {
        int compareTopicsSize =((Examiners)examinerscompare).getTopics().size();
        /* For Ascending order*/
        return this.Topics.size()-compareTopicsSize;
    }
    

}
