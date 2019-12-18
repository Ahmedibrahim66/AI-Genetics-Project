/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project_one;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author AyhamHashesh
 */
public class ReadDataController implements Initializable {

    @FXML
    private Button projectsPathButton;
    @FXML
    private TextField projectsPath;
    @FXML
    private TextField ExaminersPath;
    @FXML
    private Button ExaminersPathButton;
    @FXML
    private Button logoutButton;
    @FXML
    private TableView<ExaminersTable> ExaminersTable;

    @FXML
    private TableColumn<ExaminersTable, String> ExaminerNameInTable;

    @FXML
    private TableColumn<ExaminersTable, String> preferredTopicsInTable;

    ObservableList<ExaminersTable> ExaminersObservableList = FXCollections.observableArrayList();

    @FXML
    private Button readExaminersButton;

    @FXML
    private TableView<ProjectsTable> ProjectsTable;

    @FXML
    private TableColumn<ProjectsTable, String> ProjectNameInTable;

    @FXML
    private TableColumn<ProjectsTable, String> StudentsInTable;

    @FXML
    private TableColumn<ProjectsTable, String> SupervisorInTable;

    @FXML
    private TableColumn<ProjectsTable, String> ProjectTopicsInTable;

    @FXML
    private TableColumn<ProjectsTable, String> PrefTimeInTable;

    @FXML
    private TableColumn<ProjectsTable, String> IntersetInTable;

    @FXML
    private TextArea textArea;
    @FXML
    private Button continueButton;
    
    Stage stage = new Stage();

    
    static int readProjectsStatus=0,readExaminersStatus=0,setExaminersStatus=0;

    ObservableList<ProjectsTable> ProjectsObservableList = FXCollections.observableArrayList();

    File file1 = null, file2 = null;
  Alert alert;

    @FXML
    void getExaminersPath(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        file1 = fileChooser.showOpenDialog(null);
        if (file1 != null) {
            ExaminersPath.setText(file1.getAbsolutePath());
        }
    }

    @FXML
    void ReadExaminers(ActionEvent event) {
        //Assign examiners Read from file
        if (file1 != null) {
            file1 = new File(ExaminersPath.getText());
            Scanner sc = null;
            try {
                sc = new Scanner(file1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AI_Project_one.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splited = line.split("/");
                String Name = splited[0].trim();

                String[] splited2 = splited[1].split("-");
                String topics = "";
                for (int j = 0; j < splited2.length; j++) {
                    topics = topics + splited2[j] + "- ";
                }

                ArrayList<String> Topics = new ArrayList<>();

                for (int i = 0; i < splited2.length; i++) {
                    Topics.add(splited2[i].trim());
                }

                //create examiners then add them to exmainers arraylist 
                Examiners examiners = new Examiners(Name, Topics);
                AI_Project_one.Examiners.add(examiners);

                ExaminersTable examinerTable = new ExaminersTable(Name, topics);
                ExaminersObservableList.add(examinerTable);
            }
            /*System.out.println(AI_Project_one.Examiners.size());
            for (int i = 0; i < AI_Project_one.Examiners.size(); i++) {
                System.out.println(AI_Project_one.Examiners.get(i).toString());
            }*/
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert!");
            alert.setContentText("Field is Empty!");
            alert.showAndWait();
        }
        ExaminersTable.setItems(ExaminersObservableList);
        readExaminersStatus=1;

    }

    @FXML
    void getProjectsPath(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        file2 = fileChooser.showOpenDialog(null);
        if (file2 != null) {
            projectsPath.setText(file2.getAbsolutePath());
        }
    }

    @FXML
    void ReadProjects(ActionEvent event) {

        if (file2 != null) {
            //Assign Project Read From File      
            file2 = new File(projectsPath.getText());
            Scanner sc1 = null;
            try {
                sc1 = new Scanner(file2);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AI_Project_one.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (sc1.hasNextLine()) {
                String line = sc1.nextLine();
                String[] splited = line.split("/");
                String Name = splited[0].trim();

                //add the studnets to array
                String Students = splited[1];
                String[] StudentsSlipted = Students.split("-");
                ArrayList<String> StudentsArray = new ArrayList<>();
                for (int i = 0; i < StudentsSlipted.length; i++) {
                    StudentsArray.add(StudentsSlipted[i]);
                }

                //add the instructor
                String Instructor = splited[2].trim();

                //add the topics to arrray
                String[] splitedTopics = splited[3].split("-");

                String topics = "";
                for (int j = 0; j < splitedTopics.length; j++) {
                    topics = topics + splitedTopics[j] + "- ";
                }

                ArrayList<String> Topics = new ArrayList<>();
                for (int i = 0; i < splitedTopics.length; i++) {
                    Topics.add(splitedTopics[i].trim());
                }

                //add the preferedtime to arrray
                String preferredTime = splited[4];

                //add the preferedtime to arrray
                String[] prefredgroup = splited[5].split("-");
                String prefGroup = "";
                for (int j = 0; j < prefredgroup.length; j++) {
                    prefGroup = prefGroup + prefredgroup[j] + "- ";
                }
                ArrayList<String> Prefredgroup = new ArrayList<>();
                for (int i = 0; i < prefredgroup.length; i++) {
                    Prefredgroup.add(prefredgroup[i].trim());
                }

                //System.out.println(Instructor);
                //create examiners then add them to exmainers arraylist 
                Projects projects = new Projects(Name, StudentsArray, Topics, Instructor, preferredTime);

                AI_Project_one.Projects.add(projects);

                ProjectsTable projectsTable = new ProjectsTable(Name, Students, Instructor, topics, preferredTime, prefGroup);
                ProjectsObservableList.add(projectsTable);

            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert!");
            alert.setContentText("Field is Empty!");
            alert.showAndWait();
        }
        ProjectsTable.setItems(ProjectsObservableList);
        readProjectsStatus=1;

    }

    @FXML
    void makeDistribution(ActionEvent event) {
        if (readProjectsStatus == 1 &&readExaminersStatus == 1){
        //Generating Array containing indices for each examiner
        Integer Array[] = new Integer[AI_Project_one.Examiners.size()];
        for (int i = 0; i < AI_Project_one.Examiners.size(); i++) {
            Array[i] = i;
        }
        //Adding Examiners to projects
        for (int i = 0; i < AI_Project_one.Projects.size(); i++) {
            arrayShuffling(Array);
            setExaminersForProjects(AI_Project_one.Projects.get(i), Array, 3);
        }
        for (int i = 0; i < AI_Project_one.Projects.size(); i++) {
            arrayShuffling(Array);
            setExaminersForProjects(AI_Project_one.Projects.get(i), Array, 6);
        }
        String text= "";
        text = text + "Projects:\n\n";
        for (int i = 0; i < AI_Project_one.Projects.size(); i++) {
            text = text + AI_Project_one.Projects.get(i).getName() + " " + AI_Project_one.Projects.get(i).getExaminers() +" \n";
        }
        text = text + ("\nExaminers:\n\n");
        for (int i = 0; i < AI_Project_one.Examiners.size(); i++) {
            text = text + AI_Project_one.Examiners.get(i).getName() + " takes " + AI_Project_one.Examiners.get(i).getExaminer_for().size() +" projects\n";
        }
        textArea.setText(text);
        }
        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert!");
            alert.setContentText("You have to read both Projects and Examiners!");
            alert.showAndWait();
            
        }
        setExaminersStatus=1;


    }

    public void setExaminersForProjects(Projects project, Integer Array[], int n) {
        ArrayList<String> ExaminersTopics;
        ArrayList<String> ProjectsTopics;
        String ExaminersName;
        String ProjectSupervisor;
        for (int j = 0; j < AI_Project_one.Examiners.size(); j++) {
            ExaminersTopics = AI_Project_one.Examiners.get(Array[j]).getTopics();
            ProjectsTopics = project.getTopics();
            ExaminersName = AI_Project_one.Examiners.get(Array[j]).getName().trim();
            ProjectSupervisor = project.Supervisor.trim();
            for (int k = 0; k < ExaminersTopics.size(); k++) {
                for (int m = 0; m < ProjectsTopics.size(); m++) {
                    if (ExaminersTopics.get(k).equals(ProjectsTopics.get(m) + "")) {
                        if (!ExaminersName.equals(ProjectSupervisor)) {
                            if (!project.Examiners.contains(AI_Project_one.Examiners.get(Array[j]))) {
                                if (AI_Project_one.Examiners.get(Array[j]).Examiner_for.size() < n) {
                                    if (project.Examiners.size() < 2) {
                                        project.Examiners.add(AI_Project_one.Examiners.get(Array[j]));
                                        AI_Project_one.Examiners.get(Array[j]).Examiner_for.add(project);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /*This method used to shuffling array, for example:
    1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 
    22 9 13 1 24 5 10 3 14 17 18 7 6 23 11 2 21 20 19 16 4 8 15 12 
     */
    public static void arrayShuffling(Integer Array[]) {
        List<Integer> intList = Arrays.asList(Array);

        Collections.shuffle(intList);

        intList.toArray(Array);

    }

    @FXML
    void makeLogout(ActionEvent event) {
        logoutButton.getScene().getWindow().hide();

    }
       @FXML
    void makeContinue(ActionEvent event) throws IOException {
        if (setExaminersStatus==1){
        continueButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("DistributionToTimeSlots.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert!");
            alert.setContentText("You have to set Examiners for Projects first!");
            alert.showAndWait();
            
        }
        
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ExaminerNameInTable.setCellValueFactory(new PropertyValueFactory<ExaminersTable, String>("ExaminerName"));
        preferredTopicsInTable.setCellValueFactory(new PropertyValueFactory<ExaminersTable, String>("ExaminerTopics"));

        ProjectNameInTable.setCellValueFactory(new PropertyValueFactory<ProjectsTable, String>("ProjectName"));
        StudentsInTable.setCellValueFactory(new PropertyValueFactory<ProjectsTable, String>("StudentsInProject"));
        SupervisorInTable.setCellValueFactory(new PropertyValueFactory<ProjectsTable, String>("ProjectSupervisor"));
        ProjectTopicsInTable.setCellValueFactory(new PropertyValueFactory<ProjectsTable, String>("ProjectTopics"));
        PrefTimeInTable.setCellValueFactory(new PropertyValueFactory<ProjectsTable, String>("prefTime"));
        IntersetInTable.setCellValueFactory(new PropertyValueFactory<ProjectsTable, String>("IntersetIn"));

        // TODO
    }

}
