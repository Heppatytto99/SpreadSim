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

    private XYChart.Series<Number, Number> totalPopulation = new XYChart.Series<>();
    private XYChart.Series<Number, Number>  infected = new XYChart.Series<>();
    private XYChart.Series<Number, Number>  healthy = new XYChart.Series<>();
    private XYChart.Series<Number, Number>  born = new XYChart.Series<>();
    private XYChart.Series<Number, Number>  dead = new XYChart.Series<>();

    private ArrayList<XYChart.Data<Number, Number>> totalPopulationData = new ArrayList<>();
    private ArrayList<XYChart.Data<Number, Number>> infectedData = new ArrayList<>();
    private ArrayList<XYChart.Data<Number, Number>> healthyData = new ArrayList<>();
    private ArrayList<XYChart.Data<Number, Number>> bornData = new ArrayList<>();
    private ArrayList<XYChart.Data<Number, Number>> deadData = new ArrayList<>();

    private int nextDay = 0;
    private int lastDay;
    private Country country;

    public Simulation(int targets, int infectionChance, double birthRate){
        this.virus = new Virus(infectionChance,  3);
        xAxis.setLabel("TIME IN DAYS: " + this.nextDay);
        yAxis.setLabel("POPULATION");
        lineChart.setTitle("VIRUS DATA");
        lineChart.setMinSize(1024, 768);

        totalPopulation.setName("Total Population");
        infected.setName("Total infected");
        healthy.setName("Total healthy");
        born.setName("Total Born");
        dead.setName("Total dead");

        lineChart.getData().add(totalPopulation);
        lineChart.getData().add(infected);
        lineChart.getData().add(healthy);
        lineChart.getData().add(born);
        lineChart.getData().add(dead);

        addData();
        next();
    }

    public Simulation(Country country, Virus virus){
        this.virus = virus;
        this.country = country;

        xAxis.setLabel("TIME IN DAYS: ");
        yAxis.setLabel("POPULATION");
        lineChart.setTitle("VIRUS DATA");
        lineChart.setMinSize(800, 800);

        totalPopulation.setName("Total Population");
        infected.setName("Total infected");
        healthy.setName("Total healthy");
        born.setName("Total Born");
        dead.setName("Total dead");

        lineChart.getData().add(totalPopulation);
        lineChart.getData().add(infected);
        lineChart.getData().add(healthy);
        lineChart.getData().add(born);
        lineChart.getData().add(dead);
        addData();
    }

    public Country getCountry(){
        return this.country;
    }

    private void addData(){

        int day = 0;
        boolean next = true;

        while(next){

            this.totalPopulationData.add(getTotalpopulationData(day));
            this.infectedData.add(getInfectedData(day));
            this.healthyData.add(getHealhtyData(day));
            this.bornData.add(getBornData(day));
            this.deadData.add(getDeadData(day));

            day++;
            virus.spread(this.country.getPopulation());
            this.country.update();
            this.country.cleanDead();
            System.out.println(day);
            next = (this.country.getTotalPopulationAmount() > 1000);

            if(day > 200) next = false;

        }
        this.lastDay = day - 1;
        next();
    }

    public void next(){
        if(this.nextDay <= this.lastDay){
            totalPopulation.getData().add(totalPopulationData.get(this.nextDay));
            infected.getData().add(infectedData.get(this.nextDay));
            healthy.getData().add(healthyData.get(this.nextDay));
            born.getData().add(bornData.get(this.nextDay));
            dead.getData().add(deadData.get(this.nextDay));
            this.nextDay++;
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

    private XYChart.Data<Number, Number> getTotalpopulationData(int day){
        return new XYChart.Data<>(day, this.country.getTotalPopulationAmount());
    }

    private XYChart.Data<Number, Number> getInfectedData(int day){
        return new XYChart.Data<>(day, this.country.getInfectedAmount());
    }

    private XYChart.Data<Number, Number> getHealhtyData(int day){
        return new XYChart.Data<>(day, this.country.getHealthyAmount());
    }

    private XYChart.Data<Number, Number> getBornData(int day){
        return new XYChart.Data<>(day, this.country.getLastBorn());
    }

    private XYChart.Data<Number, Number> getDeadData(int day){
        return new XYChart.Data<>(day, this.country.getDeadAmount());
    }
}
