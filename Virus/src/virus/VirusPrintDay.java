/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virus;

/**
 *
 * @author Petra
 */
public class VirusPrintDay {

    private int goal = 0;

    // Get goal from VirusUI
    public void setGoal(int newGoal) {
        goal = newGoal;
    }

    public int getGoal() {
        return goal;
    }
    
    // Loop through each day and see what happens
    public void PrintDays() throws InterruptedException {
        
        // y increases the chances of Woo! happening
        int y = 9;
        int counter = 0;

        for (int i = 1; i < goal; i++) {
            // Random x and compare it to y
            int x = (int) (Math.random() * 11);
            System.out.println(i);
            if (x > y) {
                System.out.println("Woo!");
                y--;
                counter++;
            }
            // delay between each day for better visual effect
            Thread.sleep(100);

        }
        
        // See what we have left and print the variables
        System.out.println("Goal is: " + goal + "\nWoo!-counter: " + counter);

    }
}
