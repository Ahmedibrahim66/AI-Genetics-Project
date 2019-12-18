/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_project_one;

import static ai_project_one.AI_Project_one.Chromosomes;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DistributionToTimeSlotsController implements Initializable {

    int slots = 60;
    Integer slotsArray[] = new Integer[slots];
    TimeSlots timslots;
    @FXML
    private Button generatePopulationsButton;

    @FXML
    private ChoiceBox<String> populationSizeBox;
    @FXML
    private TextArea chromosomeDataTextArea;

    @FXML
    private TextArea fitnessScoreTextArea;

    @FXML
    private Button CalculateFitnessScoreButton;
    @FXML
    private TextField numberOfGenerationsButton;
    @FXML
    private Label firstChromosomeLabel;
    Alert alert;
    int populationsGeneratorStatus = 0;
    static ArrayList<TimeSlots> newGeneration;

    @FXML
    private Button logoutButton;

    @FXML
    private ChoiceBox<String> daysBox;

    @FXML
    private ChoiceBox<String> timeBox;

    @FXML
    private Button FinalButton;

    @FXML
    private TextArea room1Area;

    @FXML
    private TextArea room2Area;

    @FXML
    private TextArea room3Area;

    @FXML
    private TextArea room5Area;

    @FXML
    private TextArea room4Area;

    @FXML
    void generatePopulations(ActionEvent event) {
        
        AI_Project_one.Chromosomes.clear();
        
        int populationSize = Integer.parseInt(populationSizeBox.getValue()); //number of populations

        for (int i = 0; i < populationSize; i++) {
            arrayShuffling(slotsArray);
            timslots = new TimeSlots(addProjectsToTimeSlots(AI_Project_one.Projects, slotsArray));
            AI_Project_one.Chromosomes.add(timslots);
        }
        /*
        for (int i = 0; i < AI_Project_one.Chromosomes.get(0).Days.size(); i++) {
            System.out.println("Slots " + i + "  " + AI_Project_one.Chromosomes.get(0).Days.get(i).getName()
                    + " " + AI_Project_one.Chromosomes.get(0).Days.get(i).getExaminers() + " " + AI_Project_one.Chromosomes.get(0).Days.get(i).getExaminers());
        }*/
        populationsGeneratorStatus = 1;
        firstChromosomeLabel.setVisible(true);
        String text = "";
        
        for(int i =0 ; i<  AI_Project_one.Chromosomes.get(0).Days.size() ; i++)
        {
            text = text + "\n" + " Slots " + i + " " + AI_Project_one.Chromosomes.get(0).Days.get(i).getName();
        }
        //text = AI_Project_one.Chromosomes.get(0).toString();
        chromosomeDataTextArea.setText(text);
        
        
        

    }

    @FXML
    void CalculateFitnessScore(ActionEvent event) {
        if (populationsGeneratorStatus == 1) {
            if (!numberOfGenerationsButton.equals("")) {
                String text = "";
                Collections.sort(AI_Project_one.Chromosomes);
                // Get the fitness score for all chromosome sorted
                text = text + "Fitness score for all chromosomes\n\n";
                for (int i = 0; i < AI_Project_one.Chromosomes.size(); i++) {
                    text = text + fitnessFunction(AI_Project_one.Chromosomes.get(i)) + "\n";

                }
                // Get the new generation fitness score for all chromosome
                text = text + "\nFitness score for all new generations of chromosomes Sorted!\n\n";
                newGeneration = getNewGeneration(AI_Project_one.Chromosomes);
                for (int i = 0; i < newGeneration.size(); i++) {
                    fitnessFunction(newGeneration.get(i));
                }
                Collections.sort(newGeneration);
                int w = Integer.parseInt(numberOfGenerationsButton.getText());//number of generations 

                for (int i = 0; i < w; i++) {
                    newGeneration = getNewGeneration(newGeneration);
                    for (int j = 0; j < newGeneration.size(); j++) {
                        fitnessFunction(newGeneration.get(j));
                    }
                    Collections.sort(newGeneration);
                }

                for (int i = 0; i < newGeneration.size(); i++) {
                    text = text + fitnessFunction(newGeneration.get(i)) + "\n";
                }
                fitnessScoreTextArea.setText(text);
                /*
                System.out.println("**************");
                System.out.println("**************");

                for (int i = 0; i < newGeneration.get(0).Days.size(); i++) {

                    System.out.println("Slot " + i + " " + Chromosomes.get(0).Days.get(i).getName());

                }
                 */
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Alert!");
                alert.setContentText("Field is Empty!");
                alert.showAndWait();

            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert!");
            alert.setContentText("Generate Populations First!");
            alert.showAndWait();

        }

    }

    public ArrayList<Projects> addProjectsToTimeSlots(ArrayList<Projects> project, Integer SlotsArray[]) {

        ArrayList<Projects> Days = new ArrayList<>();
        ArrayList<Integer> Numbers = new ArrayList<>();

        ArrayList<String> DummyData = new ArrayList<>();
        DummyData.add("NoName");
        DummyData.add("NoName");

        Examiners ex = new Examiners("NoName", DummyData);
        Examiners ex1 = new Examiners("NoName1", DummyData);

        ArrayList<Examiners> ex2 = new ArrayList<>();
        ex2.add(ex);
        ex2.add(ex1);

        Projects pro = new Projects("Empty", DummyData, DummyData, "NoName", "NoName");
        pro.setExaminers(ex2);

        for (int i = 0; i < project.size(); i++) {
            Days.add(pro);
        }

        ArrayList<Projects> Projectssss = new ArrayList<>();
        for (int i = 0; i < project.size(); i++) {
            Projectssss.add(project.get(i));
        }

        int size = 59;
        //System.out.println(Projectssss.size());
        int count = 0;
        for (int i = 0; i < project.size(); i++) {
            int number = getRandomNumberInRange(0, size);
            Numbers.add(number);
            Examiners Name1 = Projectssss.get(number).getExaminers().get(0);
            Examiners Name2 = Projectssss.get(number).getExaminers().get(1);
            int place = (i / 5);
            int Indextosearch = place * 5;
            if ((Days.get(Indextosearch).getExaminers().contains(Name1)
                    || Days.get(Indextosearch + 1).getExaminers().contains(Name1)
                    || Days.get(Indextosearch + 2).getExaminers().contains(Name1)
                    || Days.get(Indextosearch + 3).getExaminers().contains(Name1)
                    || Days.get(Indextosearch + 4).getExaminers().contains(Name1))) {

                //duplicate
            } else if ((Days.get(Indextosearch + 4).getExaminers().contains(Name2)
                    || Days.get(Indextosearch + 3).getExaminers().contains(Name2)
                    || Days.get(Indextosearch + 2).getExaminers().contains(Name2)
                    || Days.get(Indextosearch + 1).getExaminers().contains(Name2)
                    || Days.get(Indextosearch).getExaminers().contains(Name2))) {

                //duplicate 
            } else {
                //not duplicate 
                Days.set(i, Projectssss.get(number));
                Projectssss.remove(Projectssss.get(number));
                count++;
                size--;
            }

        }
        
        
        
        for (int i = 0; i < Days.size(); i++) {
            if (Days.get(i).getName().equals("Empty")) {
                int addhim = checkIfYouCanAddHim(Days, Projectssss, i);
                if (addhim != -1) {
                    Days.set(i, Projectssss.get(addhim));
                    Projectssss.remove(Projectssss.get(addhim));
                }

            }
        }

        return Days;
    }
    
    //this function check if chromosome is valid. all hard constraint is satisfied. 
    public int checkifValid(TimeSlots Projectssss) {

        for (int i = 0; i < Projectssss.Days.size(); i++) {

            
            Examiners Name1 = Projectssss.Days.get(i).getExaminers().get(0);
            Examiners Name2 = Projectssss.Days.get(i).getExaminers().get(1);

            int place = (i / 5);
            int Indextosearch = place * 5;
            int testing = 0;
            if (i <= 9) {
                if(i > 4)
                {
                    testing = 4 - (i-5);

                }else 
                testing = 4 - i;

            } else {
                
                if((i%10) > 4)
                {
                    testing = 4 - ((i % 10) - 5);
  
                }else 
                testing = 4 - ((i % 10));
                
                
            }

            if (testing == 4) { //1234

                
                if ((Projectssss.Days.get(Indextosearch + 1).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 2).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 3).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 4).getExaminers().contains(Name1))) {

                    //duplicate 
                    return -1;
                } else if ((Projectssss.Days.get(Indextosearch + 4).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 3).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 2).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 1).getExaminers().contains(Name2))) {

                    return -1;
                    //duplicate 
                } else {
                    //not duplicate 
                    
                }
            }

            if (testing == 3) { //1234

                if ((Projectssss.Days.get(Indextosearch).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 2).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 3).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 4).getExaminers().contains(Name1))) {

                    //duplicate 
                    return -1;
                } else if ((Projectssss.Days.get(Indextosearch + 4).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 3).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 2).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch).getExaminers().contains(Name2))) {

                    return -1;
                    //duplicate 
                } else {
                    //not duplicate 
                 
                }
            }

            if (testing == 2) { //1234

                if ((Projectssss.Days.get(Indextosearch).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 1).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 3).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 4).getExaminers().contains(Name1))) {

                    //duplicate 
                    return -1;
                } else if ((Projectssss.Days.get(Indextosearch + 4).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 3).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 1).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch).getExaminers().contains(Name2))) {

                    return -1;
                    //duplicate 
                } else {
                    //not duplicate 
                   
                }
            }

            if (testing == 1) { //1234

                if ((Projectssss.Days.get(Indextosearch).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 2).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 1).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 4).getExaminers().contains(Name1))) {

                    //duplicate 
                    return -1;
                } else if ((Projectssss.Days.get(Indextosearch + 4).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 1).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 2).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch).getExaminers().contains(Name2))) {

                    return -1;
                    //duplicate 
                } else {
                    //not duplicate 
                    
                }
            }

            if (testing == 0) { //1234

                if ((Projectssss.Days.get(Indextosearch).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 2).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 3).getExaminers().contains(Name1)
                        || Projectssss.Days.get(Indextosearch + 1).getExaminers().contains(Name1))) {

                    //duplicate 
                    return -1;
                } else if ((Projectssss.Days.get(Indextosearch + 1).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 3).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch + 2).getExaminers().contains(Name2)
                        || Projectssss.Days.get(Indextosearch).getExaminers().contains(Name2))) {

                    return -1;
                    //duplicate 
                } else {
                    //not duplicate 
                 
                }
            }

        }
        return 0;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    // check if you can add him , check if you can add any project in specific slot in the chromosome 
    private int checkIfYouCanAddHim(ArrayList<Projects> Days, ArrayList<Projects> Projectssss, int index) {

        for (int i = 0; i < Projectssss.size(); i++) {
            Examiners Name1 = Projectssss.get(i).getExaminers().get(0);
            Examiners Name2 = Projectssss.get(i).getExaminers().get(1);
            int place = (index / 5);
            int Indextosearch = place * 5;
            if ((Days.get(Indextosearch).getExaminers().contains(Name1)
                    || Days.get(Indextosearch + 1).getExaminers().contains(Name1)
                    || Days.get(Indextosearch + 2).getExaminers().contains(Name1)
                    || Days.get(Indextosearch + 3).getExaminers().contains(Name1)
                    || Days.get(Indextosearch + 4).getExaminers().contains(Name1))) {

                //duplicate
            } else if ((Days.get(Indextosearch + 4).getExaminers().contains(Name2)
                    || Days.get(Indextosearch + 3).getExaminers().contains(Name2)
                    || Days.get(Indextosearch + 2).getExaminers().contains(Name2)
                    || Days.get(Indextosearch + 1).getExaminers().contains(Name2)
                    || Days.get(Indextosearch).getExaminers().contains(Name2))) {

                //duplicate 
            } else {
                //not duplicate 
                // you can add 
                return i;

            }
        }
        return -1;

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

    public int fitnessFunction(TimeSlots Chromosomes) {

        int fitness_Score = 0;

        if (checkifValid(Chromosomes) == 0) {
            for (int i = 0; i < Chromosomes.Days.size() - 10; i++) {

                Examiners Name1 = Chromosomes.Days.get(i).getExaminers().get(0);
                Examiners Name2 = Chromosomes.Days.get(i).getExaminers().get(1);
                int FirstSlot = (int) (5 * (Math.floor(i / 5) + 1));
                int SecondSlot = (int) (5 * (Math.floor(i / 5) + 2));

                if ((Chromosomes.Days.get(FirstSlot).getExaminers().contains(Name1)
                        || Chromosomes.Days.get(FirstSlot + 1).getExaminers().contains(Name1)
                        || Chromosomes.Days.get(FirstSlot + 2).getExaminers().contains(Name1)
                        || Chromosomes.Days.get(FirstSlot + 3).getExaminers().contains(Name1)
                        || Chromosomes.Days.get(FirstSlot + 4).getExaminers().contains(Name1))
                        && (Chromosomes.Days.get(SecondSlot).getExaminers().contains(Name1)
                        || Chromosomes.Days.get(SecondSlot + 1).getExaminers().contains(Name1)
                        || Chromosomes.Days.get(SecondSlot + 2).getExaminers().contains(Name1)
                        || Chromosomes.Days.get(SecondSlot + 3).getExaminers().contains(Name1)
                        || Chromosomes.Days.get(SecondSlot + 4).getExaminers().contains(Name1))) {
                    //System.out.println(Name1 + " Has three consective at " + i);
                    fitness_Score++;
                }

                if ((Chromosomes.Days.get(FirstSlot).getExaminers().contains(Name2)
                        || Chromosomes.Days.get(FirstSlot + 1).getExaminers().contains(Name2)
                        || Chromosomes.Days.get(FirstSlot + 2).getExaminers().contains(Name2)
                        || Chromosomes.Days.get(FirstSlot + 3).getExaminers().contains(Name2)
                        || Chromosomes.Days.get(FirstSlot + 4).getExaminers().contains(Name2))
                        && (Chromosomes.Days.get(SecondSlot).getExaminers().contains(Name2)
                        || Chromosomes.Days.get(SecondSlot + 1).getExaminers().contains(Name2)
                        || Chromosomes.Days.get(SecondSlot + 2).getExaminers().contains(Name2)
                        || Chromosomes.Days.get(SecondSlot + 3).getExaminers().contains(Name2)
                        || Chromosomes.Days.get(SecondSlot + 4).getExaminers().contains(Name2))) {
                    //System.out.println(Name2 + " Has three consective at " + i);
                    fitness_Score++;

                }

            }

            for (int i = 0; i < Chromosomes.Days.size(); i++) {
                if (Chromosomes.Days.get(i).getName().equals("Empty")) {
                    fitness_Score++;
                }
            }

        } else {
            fitness_Score = 200;
        }

        Chromosomes.setFitnessScore(fitness_Score);
        return fitness_Score;
    }

    public ArrayList<TimeSlots> getNewGeneration(ArrayList<TimeSlots> Oldpopulations) {

        //40% from previous generation
        //50% cross over 
        //10% mutation
        ArrayList<TimeSlots> oldpopulations = new ArrayList<>();
        oldpopulations.addAll(Oldpopulations);

        ArrayList<TimeSlots> newGeneration = new ArrayList<>();

        int OldGenFromNewGen = (int) (Oldpopulations.size() * 0.4);

        int NewGenFromCrossOver = (int) (Oldpopulations.size() * 0.5);

        int NewGenFromMutation = (int) (Oldpopulations.size() * 0.1);

        for (int i = 0; i < OldGenFromNewGen; i++) {
            newGeneration.add(oldpopulations.get(i));
        }

        ///
        for (int i = 0; i < NewGenFromCrossOver; i++) {
            int Random1 = getRandomNumberInRange(0, NewGenFromCrossOver - 1);
            int Random2 = getRandomNumberInRange(0, NewGenFromCrossOver - 1);
            newGeneration.add(CrossOver(oldpopulations.get(Random1), oldpopulations.get(Random2)));

        }

        for (int i = 0; i < NewGenFromMutation; i++) {
            int Random1 = getRandomNumberInRange(0, NewGenFromCrossOver - 1);
            newGeneration.add(Mutation(oldpopulations.get(Random1)));

        }

        return newGeneration;
    }

    public TimeSlots CrossOver(TimeSlots First, TimeSlots second) {

        ArrayList<Projects> newCrossOverGene = new ArrayList<>();
        newCrossOverGene.addAll(First.Days);

        TimeSlots newCrossOverGene2 = new TimeSlots(second.Days);

        //cross over 50% of the chromosome 
        ArrayList<Integer> ProjectsToCross = getRandomPrjectNumber(30);
        //System.out.println(ProjectsToCross.toString());

        for (int i = 0; i < ProjectsToCross.size(); i++) {
            // i is the project number 
            //get two index for the project number 
            int Index1 = newCrossOverGene.indexOf(AI_Project_one.Projects.get(ProjectsToCross.get(i)));
            int Index2 = newCrossOverGene2.Days.indexOf(AI_Project_one.Projects.get(ProjectsToCross.get(i)));
            if (Index1 == -1 || Index2 == -1) {

            } else {
                Collections.swap(newCrossOverGene, Index1, Index2);
            }

        }

        return (new TimeSlots(newCrossOverGene));
    }

    public TimeSlots Mutation(TimeSlots chromosome) {
        ArrayList<Projects> newMutationGene = new ArrayList<>();
        newMutationGene.addAll(chromosome.Days);
        ArrayList<Integer> ProjectsToCross = getRandomPrjectNumber(10);
        ArrayList<Integer> ProjectsToCross2 = getRandomPrjectNumber(10);
        for (int i = 0; i < ProjectsToCross.size(); i++) {
            // i is the project number 
            //get two index for the project number 
            int Index1 = newMutationGene.indexOf(AI_Project_one.Projects.get(ProjectsToCross.get(i)));
            int Index2 = ProjectsToCross.get(i);

            if (Index1 == -1 || Index2 == -1) {

            } else {
                Collections.swap(newMutationGene, Index1, Index2);
            }

        }

        return (new TimeSlots(newMutationGene));

    }

    public ArrayList<Integer> getRandomPrjectNumber(int n) {
        ArrayList<Integer> Numbers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Numbers.add(getRandomNumberInRange(0, 59));
        }

        return Numbers;

    }

    @FXML
    void makeLogout(ActionEvent event) {
        logoutButton.getScene().getWindow().hide();
    }

    @FXML
    void doFinal(ActionEvent event) {
        
        Collections.sort(newGeneration);
        //System.out.println(newGeneration.size());
        
        //System.out.println(checkifValid(newGeneration.get(0)));
        
        
              for (int i = 0; i < newGeneration.get(0).Days.size(); i++) {

            System.out.println("Slot " + i + " " + newGeneration.get(0).Days.get(i).getName());
            System.out.println("Slot " + i + " " + newGeneration.get(0).Days.get(i).getExaminers().get(0) + " " + newGeneration.get(0).Days.get(i).getExaminers().get(1));


            
        }

        
        String day = daysBox.getValue();
        String time = timeBox.getValue();
        String text1 = "", text2 = "", text3 = "", text4 = "", text5 = "";
        
        //Day1
        if (day.equals("Day1")) {
            if (time.equals("9")) {
                text1 = newGeneration.get(0).Days.get(0).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(0).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(0).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(0).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(0).students.toString();
                text2 = newGeneration.get(0).Days.get(1).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(1).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(1).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(1).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(1).students.toString();
                text3 = newGeneration.get(0).Days.get(2).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(2).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(2).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(2).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(2).students.toString();
                text4 = newGeneration.get(0).Days.get(3).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(3).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(3).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(3).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(3).students.toString();
                text5 = newGeneration.get(0).Days.get(4).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(4).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(4).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(4).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(4).students.toString();

            } else if (time.equals("10")) {
                text1 = newGeneration.get(0).Days.get(5).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(5).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(5).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(5).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(5).students.toString();
                text2 = newGeneration.get(0).Days.get(6).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(6).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(6).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(6).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(6).students.toString();
                text3 = newGeneration.get(0).Days.get(7).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(7).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(7).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(7).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(7).students.toString();
                text4 = newGeneration.get(0).Days.get(8).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(8).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(8).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(8).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(8).students.toString();
                text5 = newGeneration.get(0).Days.get(9).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(9).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(9).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(9).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(9).students.toString();

            } else if (time.equals("11")) {
                text1 = newGeneration.get(0).Days.get(10).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(10).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(10).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(10).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(10).students.toString();
                text2 = newGeneration.get(0).Days.get(11).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(11).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(11).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(11).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(11).students.toString();
                text3 = newGeneration.get(0).Days.get(12).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(12).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(12).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(12).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(12).students.toString();
                text4 = newGeneration.get(0).Days.get(13).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(13).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(13).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(13).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(13).students.toString();
                text5 = newGeneration.get(0).Days.get(14).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(14).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(14).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(14).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(14).students.toString();

            } else if (time.equals("12")) {
                text1 = newGeneration.get(0).Days.get(15).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(15).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(15).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(15).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(15).students.toString();
                text2 = newGeneration.get(0).Days.get(16).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(16).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(16).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(16).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(16).students.toString();
                text3 = newGeneration.get(0).Days.get(17).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(17).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(17).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(17).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(17).students.toString();
                text4 = newGeneration.get(0).Days.get(18).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(18).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(18).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(18).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(18).students.toString();
                text5 = newGeneration.get(0).Days.get(19).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(19).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(19).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(19).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(19).students.toString();

            } else if (time.equals("1")) {
                text1 = newGeneration.get(0).Days.get(20).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(20).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(20).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(20).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(20).students.toString();
                text2 = newGeneration.get(0).Days.get(21).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(21).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(21).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(21).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(21).students.toString();
                text3 = newGeneration.get(0).Days.get(22).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(22).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(22).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(22).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(22).students.toString();
                text4 = newGeneration.get(0).Days.get(23).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(23).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(23).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(23).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(23).students.toString();
                text5 = newGeneration.get(0).Days.get(24).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(24).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(24).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(24).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(24).students.toString();

            } else if (time.equals("2")) {
                text1 = newGeneration.get(0).Days.get(25).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(25).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(25).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(25).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(25).students.toString();
                text2 = newGeneration.get(0).Days.get(26).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(26).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(26).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(26).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(26).students.toString();
                text3 = newGeneration.get(0).Days.get(27).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(27).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(27).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(27).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(27).students.toString();
                text4 = newGeneration.get(0).Days.get(28).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(28).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(28).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(28).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(28).students.toString();
                text5 = newGeneration.get(0).Days.get(29).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(29).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(29).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(29).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(29).students.toString();

            }

        } //Day2
        else {
            if (time.equals("9")) {
                text1 = newGeneration.get(0).Days.get(30).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(30).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(30).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(30).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(30).students.toString();;
                text2 = newGeneration.get(0).Days.get(31).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(31).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(31).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(31).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(31).students.toString();
                text3 = newGeneration.get(0).Days.get(32).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(32).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(32).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(32).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(32).students.toString();
                text4 = newGeneration.get(0).Days.get(33).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(33).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(33).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(33).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(33).students.toString();
                text5 = newGeneration.get(0).Days.get(34).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(34).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(34).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(34).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(34).students.toString();

            } else if (time.equals("10")) {
                text1 = newGeneration.get(0).Days.get(35).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(35).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(35).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(35).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(35).students.toString();
                text2 = newGeneration.get(0).Days.get(36).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(36).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(36).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(36).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(36).students.toString();
                text3 = newGeneration.get(0).Days.get(37).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(37).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(37).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(37).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(37).students.toString();
                text4 = newGeneration.get(0).Days.get(38).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(38).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(38).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(38).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(38).students.toString();
                text5 = newGeneration.get(0).Days.get(39).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(39).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(39).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(39).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(39).students.toString();

            } else if (time.equals("11")) {
                text1 = newGeneration.get(0).Days.get(40).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(40).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(40).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(40).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(40).students.toString();
                text2 = newGeneration.get(0).Days.get(41).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(41).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(41).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(41).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(41).students.toString();
                text3 = newGeneration.get(0).Days.get(42).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(42).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(42).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(42).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(42).students.toString();
                text4 = newGeneration.get(0).Days.get(43).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(43).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(43).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(43).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(43).students.toString();
                text5 = newGeneration.get(0).Days.get(44).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(44).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(44).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(44).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(44).students.toString();
            } else if (time.equals("12")) {
                text1 = newGeneration.get(0).Days.get(45).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(45).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(45).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(45).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(45).students.toString();
                text2 = newGeneration.get(0).Days.get(46).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(46).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(46).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(46).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(46).students.toString();
                text3 = newGeneration.get(0).Days.get(47).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(47).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(47).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(47).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(47).students.toString();
                text4 = newGeneration.get(0).Days.get(48).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(48).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(48).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(48).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(48).students.toString();
                text5 = newGeneration.get(0).Days.get(49).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(49).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(49).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(49).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(49).students.toString();

            } else if (time.equals("1")) {
                text1 = newGeneration.get(0).Days.get(50).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(50).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(50).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(50).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(50).students.toString();
                text2 = newGeneration.get(0).Days.get(51).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(51).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(51).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(51).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(51).students.toString();
                text3 = newGeneration.get(0).Days.get(52).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(52).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(52).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(52).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(52).students.toString();
                text4 = newGeneration.get(0).Days.get(53).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(53).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(53).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(53).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(53).students.toString();
                text5 = newGeneration.get(0).Days.get(54).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(54).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(54).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(54).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(54).students.toString();

            } else if (time.equals("2")) {
                text1 = newGeneration.get(0).Days.get(55).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(55).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(55).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(55).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(55).students.toString();
                text2 = newGeneration.get(0).Days.get(56).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(56).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(56).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(56).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(56).students.toString();
                text3 = newGeneration.get(0).Days.get(57).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(57).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(57).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(57).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(57).students.toString();
                text4 = newGeneration.get(0).Days.get(58).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(58).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(58).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(58).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(58).students.toString();
                text5 = newGeneration.get(0).Days.get(59).getName() + "\nSupervisor: " + newGeneration.get(0).Days.get(59).Supervisor + "\nExaminers: " + newGeneration.get(0).Days.get(59).Examiners.get(0).getName() + ", " + newGeneration.get(0).Days.get(59).Examiners.get(1).getName() + "\n" + newGeneration.get(0).Days.get(59).students.toString();

            }

        }
        room1Area.setText(text1);
        room2Area.setText(text2);
        room3Area.setText(text3);
        room4Area.setText(text4);
        room5Area.setText(text5);
        /*
        for (int i = 0; i < newGeneration.get(0).Days.size(); i++) {
            System.out.println("Slot " + i + " " + Chromosomes.get(0).Days.get(i).getName());
        }
         */

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < slots; i++) {
            slotsArray[i] = i;
        }
        populationSizeBox.getItems().addAll("10", "100", "1000", "10000");
        populationSizeBox.setValue("10");

        daysBox.getItems().addAll("Day1", "Day2");
        daysBox.setValue("Day1");

        timeBox.getItems().addAll("9", "10", "11", "12", "1", "2");
        timeBox.setValue("9");

    }

}
