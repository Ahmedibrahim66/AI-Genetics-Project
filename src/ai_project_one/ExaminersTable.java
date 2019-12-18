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
public class ExaminersTable {
    SimpleStringProperty ExaminerName;
    SimpleStringProperty ExaminerTopics;

    public ExaminersTable(String ExaminerName, String ExaminerTopics) {
        this.ExaminerName = new SimpleStringProperty(ExaminerName);
        this.ExaminerTopics = new SimpleStringProperty(ExaminerTopics);
    }
    
    

    public String getExaminerName() {
        return ExaminerName.get();
    }

    public void setExaminerName(String ExaminerName) {
        this.ExaminerName.set(ExaminerName);
    }

    public String getExaminerTopics() {
        return ExaminerTopics.get();
    }

    public void setExaminerTopics(String ExaminerTopics) {
        this.ExaminerTopics.set(ExaminerTopics);
    }
    
    
}
