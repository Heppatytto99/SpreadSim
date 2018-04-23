import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Simulation {

    private Target[] targets;
    private Virus virus;
    private NumberAxis xAxis = new NumberAxis();
    private NumberAxis yAxis = new NumberAxis();
    private LineChart<Number,Number> lineChart = new LineChart<Number, Number>(xAxis,yAxis);
    private boolean next;
    private int day;
    private int infectedCount;
    private int targetCount;

    public Simulation(int TARGETSIZE, int INFECTIONCHANCE){
        this.targets = new Target[TARGETSIZE];
        this.virus = new Virus(INFECTIONCHANCE, "kakkavirus");
        this.next = true;
        this.day = 0;
        this.infectedCount = 0;
        xAxis.setLabel("LOL");
        lineChart.setTitle("Kaavio 1");
        fillTargets(30);
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
            this.infectedCount = Target.countInfected(this.targets);
            System.out.println("\nDay: " + this.day + "\nCurrently infected: " + this.infectedCount);

            this.virus.spread(this.targets);
            int newInfectedCount = Target.countInfected(this.targets) - this.infectedCount;

            System.out.println("The virus spreads...\nNew infected targets: " + newInfectedCount);

            if(this.infectedCount + newInfectedCount == this.targets.length){
                this.next = false;
                System.out.println("It took " + this.day + " days to infect everything");
            }
            this.day++;
        } else {
            //endSimulation();
        }
    }

    public GridPane getGrid(){
        XYChart.Series series = new XYChart.Series();
        series.setName("hellou");
        series.getData().add(new XYChart.Data(this.day, this.infectedCount));
        lineChart.getData().add(series);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Text dayText = new Text("Day: " + this.day);
        Text infectedText = new Text("Infected: " + this.infectedCount);
        Text targetText = new Text("Targets: ");


        grid.setPadding(new Insets(0,10,0,10));
        grid.add(dayText, 1,2);
        grid.add(infectedText, 1,1);
        grid.add(lineChart, 2, 2);

        return grid;
    }
}
