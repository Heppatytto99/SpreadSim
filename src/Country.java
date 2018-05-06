import java.util.ArrayList;

public class Country {

    private String name;
    private int resistance;
    private double birthRate;
    private int targets;
    private int lastBorn = 0;
    private int dead = 0;
    private int infected = 0;
    private int healthy = 0;

    private ArrayList<Target> population = new ArrayList<>();

    public Country(int population, int resistance, double birthRate, String name) {
        this.targets = population;
        Target.fill(this.population, population, resistance);
        this.birthRate = birthRate / 100;
        this.resistance = resistance;
        this.name = name;
    }

    public Country(int population, double birthRate, String name) {
        this.targets = population;
        Target.fill(this.population, population, resistance);
        this.birthRate = birthRate / 100;
        this.resistance = 30;
        this.name = name;
    }

    public String toString(){
        return this.name;
    }

    public ArrayList<Target> getPopulation() {
        return this.population;
    }

    public int getTotalPopulationAmount(){
        return this.population.size();
    }

    public int getHealthyAmount(){
        return this.healthy;
    }

    public int getInfectedAmount(){
        return this.infected;
    }

    public int getDeadAmount(){
        return this.dead;
    }

    public int getLastBorn(){
        return this.lastBorn;
    }

    public int getResistance(){
        return this.resistance;
    }

    public int getTargets(){
        return this.targets;
    }

    public double getBirthRate(){
        return this.birthRate;
    }

    public void cleanDead(){
        this.population.removeIf((Target t) -> t.getStatus().equals(Status.STATUS_DEAD));
    }

    public void update(){
        this.dead = countDead();
        this.infected = countInfected();
        this.healthy = countHealthy();
        this.lastBorn = (int) (birthRate * (getHealthyAmount() + getInfectedAmount()));
        Target.fill(this.population, lastBorn, resistance);
    }

    private int countInfected(){
        int infected = 0;
        for(Target target : this.population){
            if (target.getStatus() == Status.STATUS_INFECTED){
                infected++;
            }
        }
        return infected;
    }

    private int countHealthy(){
        int healthy = 0;
        for(Target target : this.population){
            if (target.getStatus() == Status.STATUS_HEALTHY){
                healthy++;
            }
        }
        return healthy;
    }

    private int countDead(){
        int dead = 0;
        for(Target target : this.population){
            if (target.getStatus() == Status.STATUS_DEAD){
                dead++;
            }
        }
        return dead;
    }
}
