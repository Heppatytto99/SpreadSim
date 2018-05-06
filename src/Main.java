import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

    private Stage stage;
    private Simulation simulation;
    private SimpleBooleanProperty ready = new SimpleBooleanProperty(true);

    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        stage.setTitle("VIRUS SIMULATION");
        BorderPane border = new BorderPane();
        Menu.createMenu();

        Menu.getAutoButton().setOnAction(e ->{
            automatic();
        });

        Menu.getNextButton().setOnAction(e ->{
            simulation.next();
            border.setCenter(simulation.getGrid());
            //border.setCenter(simulation.getGridTwo());
        });
        Menu.getAutoButton().setDisable(this.ready.getValue());
        Menu.getNextButton().setDisable(this.ready.getValue());
        Menu.getPrevButton().setDisable(this.ready.getValue());
        Menu.getNewSimButton().setOnAction(e -> {
            this.simulation = new Simulation(
                    new Country((int) Menu.getTargetSlider().getValue(),(int) Menu.getBirthSlider().getValue()),
                    new Virus((int) Menu.getInfectionChanceSlider().getValue(),(int) Menu.getIncubationSlider().getValue())
            );
            ready.set(false);
            border.setCenter(this.simulation.getGrid());
            //border.setCenter(this.simulation.getGridTwo());
        });
        Menu.getCloseSimButton().setOnAction(e ->{
            ready.setValue(true);
            border.setCenter(null);
            //this.simulation.endSimulation();
        });
        Menu.getCloseSimButton().setDisable(this.ready.getValue());
        border.setLeft(Menu.getBox());

        ready.addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            Menu.getAutoButton().setDisable(newValue);
            Menu.getNextButton().setDisable(newValue);
            Menu.getPrevButton().setDisable(newValue);
            Menu.getCloseSimButton().setDisable(newValue);
        });

        Scene scene = new Scene(border,1200 ,800);
        stage.setScene(scene);

        // Add css for styling
        scene.getStylesheets().clear();
        scene.getStylesheets().add("http://users.metropolia.fi/~petrasil/style.css");

        stage.show();
    }

    private void automatic(){
            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(() -> simulation.next(), 0, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args){
        launch(args);
    }
}
