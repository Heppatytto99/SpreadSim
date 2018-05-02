import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class Menu {

    private static VBox vbox = new VBox(5);

    private static Slider targetCount = new Slider(1.0, 1000000.0, 1.0);
    private static Slider infectionChance = new Slider(1.0, 100.0, 1.0);
    private static Slider birth = new Slider(1.0, 10, 0.1);

    private static Button next = new Button("NEXT");
    private static Button prev = new Button("PREVIOUS");
    private static Button newSimulation = new Button("NEW SIMULATION");
    private static Button closeSimulation = new Button("CLOSE SIMULATION");
    private static Button auto = new Button("AUTOMATIC");



    public static Button getAutoButton(){
        return auto;
    }

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

    public static VBox getBox(){
        return Menu.vbox;
    }

    public static Slider getBirthSlider(){
        return Menu.birth;
    }

    public static Slider getTargetSlider(){
        return Menu.targetCount;
    }

    public static Slider getInfectionChanceSlider(){
        return Menu.infectionChance;
    }

    public static void createMenu(){

        vbox.setMaxWidth(500.0);
        targetCount.setShowTickLabels(true);
        targetCount.setShowTickMarks(true);
        targetCount.setMajorTickUnit(499999);
        //targetCount.setMinorTickCount(100);
        //targetCount.setSnapToTicks(true);
        Label targetCountValue = new Label("TARGET POPULATION: " + Double.toString(targetCount.getValue()));
        targetCount.valueProperty().addListener((observable, oldValue, newValue) ->{
            targetCount.setValue(newValue.intValue());
            targetCountValue.setText("TARGET POPULATION: " + Double.toString(targetCount.getValue()));
        });

        infectionChance.setShowTickLabels(true);
        infectionChance.setShowTickMarks(true);
        infectionChance.setMajorTickUnit(49);
        //infectionChance.setMinorTickCount(1);
        //infectionChance.setSnapToTicks(true);
        Label infectionChanceValue = new Label("INEFECTION CHANCE %: " + Double.toString(infectionChance.getValue()));
        infectionChance.valueProperty().addListener((observable, oldValue, newValue) ->{
            infectionChance.setValue(newValue.intValue());
            infectionChanceValue.setText("INFECTION CHANCE %: " + Double.toString(infectionChance.getValue()));
        });

        birth.setShowTickLabels(true);
        birth.setShowTickMarks(true);
        birth.setMajorTickUnit(49);
        //targetCount.setMinorTickCount(100);
        //targetCount.setSnapToTicks(true);
        Label birthValue = new Label("BIRTHRATE %: " + Double.toString(birth.getValue()));
        birth.valueProperty().addListener((observable, oldValue, newValue) ->{
            birth.setValue(newValue.intValue());
            birthValue.setText("BIRTHRATE %: " + Double.toString(birth.getValue()));
        });

        vbox.getChildren().addAll(targetCountValue, targetCount,infectionChanceValue, infectionChance, birthValue, birth, next,prev, auto, newSimulation,closeSimulation);
    }


}
