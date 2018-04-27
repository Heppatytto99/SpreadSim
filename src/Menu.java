import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class Menu {

    private static VBox vbox = new VBox(5);
    private static Slider targetCount = new Slider(1.0, 1000000.0, 1.0);
    private static Slider infectionChance = new Slider(1.0, 100.0, 1.0);
    private static Button next = new Button("NEXT");
    private static Button prev = new Button("PREVIOUS");
    private static Button newSimulation = new Button("NEW SIMULATION");
    private static Button closeSimulation = new Button("CLOSE SIMULATION");

    public static Button getNextButton(){
        return next;
    }

    public static Button getPrevButton(){
        return prev;
    }

    public static Button getNewSimButton(){
        return newSimulation;
    }

    public static Button getCloseSimButton(){
        return closeSimulation;
    }

    public static void createMenu(){

        vbox.setMaxWidth(500.0);
        //targetCount.setShowTickLabels(true);
        //targetCount.setShowTickMarks(true);
        //targetCount.setMajorTickUnit(499);
        //targetCount.setMinorTickCount(100);
        //targetCount.setSnapToTicks(true);
        Label targetCountValue = new Label("Targets: " + Double.toString(targetCount.getValue()));
        targetCount.valueProperty().addListener((observable, oldValue, newValue) ->{
            targetCount.setValue(newValue.intValue());
            targetCountValue.setText("Targets: " + Double.toString(targetCount.getValue()));
        });

        infectionChance.setShowTickLabels(true);
        infectionChance.setShowTickMarks(true);
        infectionChance.setMajorTickUnit(49);
        infectionChance.setMinorTickCount(1);
        infectionChance.setSnapToTicks(true);
        Label infectionChanceValue = new Label("Infection Chance %: " + Double.toString(infectionChance.getValue()));
        infectionChance.valueProperty().addListener((observable, oldValue, newValue) ->{
            infectionChance.setValue(newValue.intValue());
            infectionChanceValue.setText("Infection Chance %: " + Double.toString(infectionChance.getValue()));
        });

        vbox.getChildren().addAll(targetCountValue, targetCount,infectionChanceValue, infectionChance, next,prev,newSimulation,closeSimulation);
    }

    public static VBox getBox(){
        return Menu.vbox;
    }

    public static Slider getTargetSlider(){
        return Menu.targetCount;
    }

    public static Slider getInfectionChanceSlider(){
        return Menu.infectionChance;
    }
}
