import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class Menu {

    private static VBox vbox = new VBox(5);
    private static HBox hbox = new HBox();
    private static ChoiceBox<Country> cbBox = new ChoiceBox<>();

    private static Slider targetCount = new Slider(1.0, 10000000.0, 1.0);
    private static Slider infectionChance = new Slider(1.0, 100.0, 1.0);
    private static Slider birth = new Slider(1.0, 10, 0.1);
    private static Slider incubation = new Slider(1.0, 10, 1);
    
    private static Button next = new Button("NEXT");
    private static Button prev = new Button("PREVIOUS");
    private static Button newSimulation = new Button("NEW SIMULATION");
    private static Button closeSimulation = new Button("CLOSE SIMULATION");
    private static Button auto = new Button("AUTOMATIC");
    //private static Image imageOk = new Image(Menu.class.getResourceAsStream("Red_Cross.png"));
    private static ToggleButton clearSelect = new ToggleButton("   "); //antaa välien olla tuolla

    public static VBox getVbox(){
        return Menu.vbox;
    }

    public static ChoiceBox<Country> getCbBox() { return Menu.cbBox; }

    public static ToggleButton getClearSelect(){
        return clearSelect;
    }
    
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

    public static Slider getBirthSlider(){
        return Menu.birth;
    }

    public static Slider getTargetSlider(){
        return Menu.targetCount;
    }

    public static Slider getInfectionChanceSlider(){
        return Menu.infectionChance;
    }

    public static Slider getIncubationSlider() {return Menu.incubation;}

    public static void createMenu(){

        vbox.setMaxWidth(500.0);

        Label presets = new Label("POPULATION PRESETS:");
        cbBox.setConverter(new StringConverter<Country>() {
            @Override
            public String toString(Country object) {
                return object.toString();
            }

            @Override
            public Country fromString(String string) {
                return cbBox.getItems().stream().filter(
                        country -> country.toString().equals(string)).findFirst().orElse(null);
            }
        });

        //TargetCount Slider
        targetCount.setShowTickLabels(true);
        targetCount.setShowTickMarks(true);
        targetCount.setMajorTickUnit(499999);
        Label targetCountValue = new Label("TARGET POPULATION: " + Double.toString(targetCount.getValue()));
        targetCount.valueProperty().addListener((observable, oldValue, newValue) ->{
            targetCount.setValue(newValue.intValue());
            targetCountValue.setText("TARGET POPULATION: " + Double.toString(targetCount.getValue()));
        });

        //Infection Chance Slider
        infectionChance.setShowTickLabels(true);
        infectionChance.setShowTickMarks(true);
        infectionChance.setMajorTickUnit(49);
        Label infectionChanceValue = new Label("VIRUS INEFECTION CHANCE %: " + Double.toString(infectionChance.getValue()));
        infectionChance.valueProperty().addListener((observable, oldValue, newValue) ->{
            infectionChance.setValue(newValue.intValue());
            infectionChanceValue.setText("INFECTION CHANCE %: " + Double.toString(infectionChance.getValue()));
        });

        //Birthrate Slider
        birth.setShowTickLabels(true);
        birth.setShowTickMarks(true);
        birth.setMajorTickUnit(49);
        Label birthValue = new Label("BIRTHRATE %: " + Double.toString(birth.getValue()));
        birth.valueProperty().addListener((observable, oldValue, newValue) ->{
            birth.setValue(newValue.intValue());
            birthValue.setText("BIRTHRATE %: " + Double.toString(birth.getValue()));
        });

        //Virus incubation Slider
        incubation.setShowTickLabels(true);
        incubation.setShowTickMarks(true);
        Label incubationValue = new Label("VIRUS INCUBATION TIME: " + Double.toString(incubation.getValue()));
        incubation.valueProperty().addListener((observable, oldValue, newValue) ->{
            incubation.setValue(newValue.intValue());
            incubationValue.setText("VIRUS INCUBATION TIME: " + Double.toString(incubation.getValue()));
        });

       // ChoiceBox<String> choiceBox = new ChoiceBox<>();
        //choiceBox.getItems().addAll("FINLAND", "SWEDEN", "LATVIA", "FIJI", "LUXEMBOURG", "ICELAND", "GREENLAND");
        //choiceBox.setOnAction(e -> setChoice(choiceBox));
        
        Label clear = new Label("CLEAR VALUES:");
        Label options = new Label("SIMULATION CONTROLS:");

        hbox.setSpacing(6); 
        hbox.getChildren().addAll(
                 cbBox, clearSelect
               
        );
        
        vbox.getChildren().addAll(
                targetCountValue, targetCount,
                infectionChanceValue, infectionChance,
                birthValue, birth,
                incubationValue,incubation,
                presets, hbox, options,
                next,prev, auto, newSimulation,closeSimulation
                
        );
        
        
    }

    //dropdown menu tästä alaspäin

    public static void setChoice(ChoiceBox<String> choiceBox){
        switch (choiceBox.getValue()){
            case "FINLAND": targetCount.setValue(5523231);
                            break;
            case "SWEDEN":  targetCount.setValue(9910701);
                            break;
            case "LATVIA": targetCount.setValue(1973530);
                            break;
            case "FIJI": targetCount.setValue(884887);
                            break;
            case "LUXEMBOURG":  targetCount.setValue(602005);
                            break;
            case "ICELAND": targetCount.setValue(348580);
                            break;
            case "GREENLAND": targetCount.setValue(58186);
                            break;
        }
    }

}