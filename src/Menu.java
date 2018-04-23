import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class Menu {

    private static VBox vbox = new VBox(10);
    private static Slider targetCount = new Slider(1.0, 1000.0, 1.0);
    private static Slider infectionChance = new Slider(1.0, 100.0, 1.0);

    public static void createMenu(){

        vbox.setMaxWidth(300.0);
        targetCount.setShowTickLabels(true);
        targetCount.setShowTickMarks(true);
        targetCount.setMajorTickUnit(50);
        targetCount.setMinorTickCount(5);
        targetCount.setSnapToTicks(true);
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

        vbox.getChildren().addAll(targetCountValue, targetCount,infectionChanceValue, infectionChance);
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
