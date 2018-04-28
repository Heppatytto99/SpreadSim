import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import java.util.Arrays;
import java.util.*;

public class Simulation {

    private Target[] targets;
    private Virus virus;
    private NumberAxis xAxis = new NumberAxis();
    private NumberAxis yAxis = new NumberAxis();
    private LineChart<Number,Number> lineChart = new LineChart<Number, Number>(xAxis,yAxis);
    private XYChart.Series series = new XYChart.Series();
    private XYChart.Series series2 = new XYChart.Series();
    private boolean next;
    private boolean auto;
    private int day;
    private int infectedCount;
    private int targetCount;

    public Simulation(int TARGETSIZE, int INFECTIONCHANCE){
        this.targets = new Target[TARGETSIZE];
        this.virus = new Virus(INFECTIONCHANCE, "kakkavirus");
        this.next = true;
        this.auto = true;
        this.day = 0;
        this.infectedCount = 0;
        xAxis.setLabel("TIME IN DAYS: " + this.day);
        yAxis.setLabel("POPULATION");
        lineChart.setTitle("VIRUS DATA");
        fillTargets(5);
        lineChart.setMinSize(1024, 768);
        lineChart.getData().add(series);
        lineChart.getData().add(series2);
        addData();
    }

    /*
    Täyttää targets listan täyteen uusia targetteja valitulla 'resistancella'
     */
    private void fillTargets(int resistance){
        for(int i = 0; i < this.targets.length; i++){
            this.targets[i] = new Target(resistance);
        }
    }

    /*
    Luuppaa niin pitkään kunnes 'next' muuttuu falseksi. Kerää ja tulostaa saastutettujen määrän
    sekä lisää päiviä. Jos kaikki listan targetit on saastutettu luuppaaminen lopetetaan ja 'next' asetetaan falseksi.
    */
    public void next(){
        if(this.next){


            this.virus.spread(this.targets);

            int newInfectedCount = Target.countInfected(this.targets) - this.infectedCount;
            this.infectedCount = Target.countInfected(this.targets);

            if(this.infectedCount + newInfectedCount == this.targets.length){
                this.next = false;
                System.out.println("It took " + this.day + " days to infect everything");
            }
            this.day++;
            addData();
        } else {
            //endSimulation();
        }
    }

    public void auto()throws InterruptedException {


            if(this.auto) {
            while(auto){
                Thread.sleep(500);
            this.virus.spread(this.targets);

            int newInfectedCount = Target.countInfected(this.targets) - this.infectedCount;
            this.infectedCount = Target.countInfected(this.targets);

            if(this.infectedCount + newInfectedCount == this.targets.length){
                this.next = false;
                System.out.println("It took " + this.day + " days to infect everything");
            }
            this.day++;
            addData();
        }
        }
        }


    private void addData(){
        xAxis.setLabel("TIME IN DAYS: " + this.day);
        series.setName("Infected: " + this.infectedCount);
        series.getData().add(new XYChart.Data(this.day, this.infectedCount));
        series2.setName("Healthy: " + (this.targets.length - this.infectedCount));
        series2.getData().add(new XYChart.Data(this.day, (this.targets.length - this.infectedCount)));
    }

    public GridPane getGrid(){

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0,10,0,10));
        grid.add(lineChart, 2,2);

        return grid;
    }

    public GridPane getGridTwo(){
        XYChart.Series series = new XYChart.Series();
        series.setName("Healthy: " + this.day);
        series.getData().add(new XYChart.Data(this.day, (this.targets.length - this.infectedCount)));
        lineChart.getData().add(series);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        lineChart.setMinSize(800, 600);

        grid.setPadding(new Insets(0,10,0,10));

        grid.add(lineChart, 2, 2);

        return grid;
    }
}
