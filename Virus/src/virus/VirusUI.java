/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus;

import java.util.Scanner;

/**
 *
 * @author Petra
 */
public class VirusUI {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        //Call the functions to get things started
        intro();
        askParameters();

    }

    public static void intro() {

        //Some welcome words for the user here. 
        //Guidelines and how this simulation works.
        System.out.println("HI! Welcome to the virus simulation! This is a placeholder message.");

    }

    //We use switch here to ask the user about what paremeters
    //we will use in the simulation.
    //For example, choose 1. to choose x country and 2. to choose y etc.
    //More parameters might be added as time goes on
    public static void askParameters() throws InterruptedException {

        VirusPrintDay printDay = new VirusPrintDay();

        String parameterOne = null;

        while (!"0".equals(parameterOne)) {
            //Ask the player to choose a parameter
            //and grab it as a variable parameterOne.
            System.out.print("Choose 1, 2 or 0: ");
            parameterOne = scan.nextLine();

            switch (parameterOne) {

                case "0":
                    break;

                case "1":
                    System.out.println("Give the amount of days: ");
                    //There will be a different class that makes
                    //use of these parameters.
                    int days = scan.nextInt();
                    printDay.setGoal(days);
                    printDay.PrintDays();
                    break;
                    
                case "2":
                    System.out.println("Case 2");
                    break;

                default:
                    System.out.println("Default stuff here.");
                    break;

            }
        }

    }

}
