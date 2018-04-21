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

    public void setGoal(int newGoal) {
        goal = newGoal;
    }

    public int getGoal() {
        return goal;
    }

    public void PrintDays() throws InterruptedException {

        int y = 9;
        int counter = 0;

        for (int i = 1; i < goal; i++) {
            int x = (int) (Math.random() * 11);
            System.out.println(i);
            if (x > y) {
                System.out.println("Woo!");
                y--;
                counter++;
            }
            Thread.sleep(100);

        }

        System.out.println("Goal is: " + goal + "\nWoo!-counter: " + counter);

    }
}
