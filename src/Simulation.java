import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import java.util.*;

public class Simulation {

    private Virus virus;
    private NumberAxis xAxis = new NumberAxis();
    private NumberAxis yAxis = new NumberAxis();
    private LineChart<Number,Number> lineChart = new LineChart<Number, Number>(xAxis,yAxis);

    private ArrayList<Target> targets = new ArrayList<>();

    private XYChart.Series totalPopulation = new XYChart.Series();
    private XYChart.Series infected = new XYChart.Series();
    private XYChart.Series healthy = new XYChart.Series();
    private XYChart.Series born = new XYChart.Series();
    //private XYChart.Series dead = new XYChart.Series();

    private ArrayList<XYChart.Data> totalPopulationData = new ArrayList<>();
    private ArrayList<XYChart.Data> infectedData = new ArrayList<>();
    private ArrayList<XYChart.Data> healthyData = new ArrayList<>();
    private ArrayList<XYChart.Data> bornData = new ArrayList<>();
    //private ArrayList<XYChart.Data> deadData = new ArrayList<>();

    private boolean auto;
    private int dayz;
    private int lastDay;
    private int infectedCount;
    private int targetCount;

    public Simulation(int TARGETSIZE, int INFECTIONCHANCE){
        Target.fill(this.targets, TARGETSIZE, 30);
        this.virus = new Virus(INFECTIONCHANCE, "kakkavirus");
        this.auto = true;
        this.dayz = 0;
        this.infectedCount = 0;
        xAxis.setLabel("TIME IN DAYS: " + this.dayz);
        yAxis.setLabel("POPULATION");
        lineChart.setTitle("VIRUS DATA");
        lineChart.setMinSize(1024, 768);

        totalPopulation.setName("Total Population");
        infected.setName("Total infected");
        healthy.setName("Total healthy");
        born.setName("Total Born");

        lineChart.getData().add(totalPopulation);
        lineChart.getData().add(infected);
        lineChart.getData().add(healthy);
        lineChart.getData().add(born);

        addData();
        next();
    }

    public int getDayz(){
        return this.dayz;
    }

    public int getLastDay(){
        return this.lastDay;
    }

    public void auto()throws InterruptedException {


            if(this.auto) {
            while(auto){
                Thread.sleep(500);
            this.virus.spread(this.targets);

            int newInfectedCount = Target.countInfected(this.targets) - this.infectedCount;
            this.infectedCount = Target.countInfected(this.targets);

            //if(this.infectedCount + newInfectedCount == this.targets.length){
            //    this.next = false;
            //    System.out.println("It took " + this.day + " days to infect everything");
            //}
            this.dayz++;
            addData();
        }
        }
        }

    private void addData(){

        int day = 0;
        int resistance = 30;
        double birthRate = 0.002;
        boolean next = true;

        while(next){
            this.virus.spread(this.targets);

            int totalPopulation = this.targets.size();
            int totalInfected = Target.countInfected(this.targets);
            int totalHealthy = Target.countHealthy(this.targets);
            int newPopulation = (int) (birthRate * totalHealthy);

            this.totalPopulationData.add(new XYChart.Data<>(day, totalPopulation));
            this.infectedData.add(new XYChart.Data<>(day, totalInfected));
            this.healthyData.add(new XYChart.Data<>(day, totalHealthy));
            this.bornData.add(new XYChart.Data<>(day, newPopulation));
            //this.deadData.add(new XYChart.Data<>(day, data));

            next = !(totalHealthy == 0);
            Target.fill(this.targets, newPopulation, resistance);
            day++;
        }
        this.lastDay = day - 1;
    }

    public void next(){
        if(!(this.dayz >= this.lastDay)){
            totalPopulation.getData().add(totalPopulationData.get(this.dayz));
            infected.getData().add(infectedData.get(this.dayz));
            healthy.getData().add(healthyData.get(this.dayz));
            born.getData().add(bornData.get(this.dayz));
            this.dayz++;
        }
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
        series.setName("Healthy: " + this.dayz);
        //series.getData().add(new XYChart.Data(this.day, (this.targets.length - this.infectedCount)));
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
