import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

    private Stage stage;
    private Simulation simulation;
    private SimpleBooleanProperty ready = new SimpleBooleanProperty(true);

    public void start(Stage primaryStage) throws Exception{

        //Menu Init
        BorderPane border = new BorderPane();
        Menu.createMenu();

        //Button Configuration
        //Auto
        Menu.getAutoButton().setOnAction(e ->{
            automatic();
        });
        Menu.getAutoButton().setDisable(this.ready.getValue());

        //Next
        Menu.getNextButton().setOnAction(e ->{
            simulation.next();
            border.setCenter(simulation.getGrid());
        });
        Menu.getNextButton().setDisable(this.ready.getValue());

        //Prev
        Menu.getPrevButton().setDisable(this.ready.getValue());

        //New Simulation
        Menu.getNewSimButton().setOnAction(e -> {
            this.simulation = new Simulation(
                    new Country(
                            (int) Menu.getTargetSlider().getValue(),
                            (int) Menu.getBirthSlider().getValue(),
                            Menu.getTextField().getText()),
                    new Virus(
                            (int) Menu.getInfectionChanceSlider().getValue(),
                            (int) Menu.getIncubationSlider().getValue())
            );
            Menu.getCbBox().getItems().add(this.simulation.getCountry());
            ready.set(false);
            border.setCenter(this.simulation.getGrid());
        });

        //Close Simulation
        Menu.getCloseSimButton().setOnAction(e ->{
            ready.setValue(true);
            border.setCenter(null);
        });
        Menu.getCloseSimButton().setDisable(this.ready.getValue());

        //Preset & ClearButton
        addPresets();

        Menu.getClearSelectButton().setOnAction(e -> {
            Menu.getCbBox().setValue(null);
            ready.setValue(true);
            Menu.getTargetSlider().setDisable(false);
            Menu.getBirthSlider().setDisable(false);
        });

        //Add Listener for BooleanProperty ready
        ready.addListener((observable, oldValue, newValue) -> {
            Menu.getAutoButton().setDisable(newValue);
            Menu.getNextButton().setDisable(newValue);
            Menu.getPrevButton().setDisable(newValue);
            Menu.getCloseSimButton().setDisable(newValue);
            Menu.getClearSelectButton().setDisable(newValue);
        });

        //Add Buttons to Left Border
        border.setLeft(Menu.getVbox());
        border.setRight(Menu.getRightVbox());

        //Scene & CSS
        Scene scene = new Scene(border,1200 ,800);
        scene.getStylesheets().clear();
        scene.getStylesheets().add("http://users.metropolia.fi/~petrasil/style.css");

        //Stage Configuration
        stage = primaryStage;
        stage.setTitle("VIRUS SIMULATION");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.setMaximized(true);
        stage.show();
    }

    private void automatic(){
            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(() -> {
                if(simulation.hasNext())simulation.next();
                else executorService.shutdownNow();
            }, 0, 1, TimeUnit.SECONDS);

    }

    private void addPresets(){

        Country finland = new Country(5523231, 30, 3,"Finland");
        Country sweden = new Country(9910701, 10, 9, "Sweden");
        Country latvia = new Country(1973530, 20, 4, "Latvia");
        Country fiji = new Country(884887, 50, 7, "Fiji");
        Country luxemburg = new Country(602005, 16, 8, "Luxemburg");
        Country iceland = new Country(348580, 77, 2, "Iceland");
        Country greenland = new Country(58186, 33, 10, "Greenland");

        Menu.getCbBox().getItems().addAll(finland,sweden,latvia,fiji,luxemburg,iceland,greenland);
    }

    public static void main(String[] args){
        launch(args);
    }
}
