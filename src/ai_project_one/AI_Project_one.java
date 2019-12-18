/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project_one;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author fcbar
 */
public class AI_Project_one extends Application {

    static ArrayList<Examiners> Examiners = new ArrayList<>();
    static ArrayList<Projects> Projects = new ArrayList<>();
    static ArrayList<TimeSlots> Chromosomes = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

        /*
        for (int i = 0; i < Chromosomes.get(0).Days.size(); i++) {
            System.out.println("Slots " + i + "  " + Chromosomes.get(0).Days.get(i).getName()
                    + " " + Chromosomes.get(0).Days.get(i).getExaminers() + " " + Chromosomes.get(0).Days.get(i).getExaminers());
        }

        int k = 0;
        for (int i = 0; i < Chromosomes.get(0).Days.size(); i++) {
            if (Chromosomes.get(0).Days.get(i).getName().equals("Empty")) {
                k++;
            }
        }

        System.out.println(k);

        System.out.println(checkifValid(Chromosomes.get(0)));
         */
       

    }

    public static void main(String[] args) {
        launch(args);
    }

}
