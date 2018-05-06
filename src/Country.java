import java.util.ArrayList;

public class Country {

    private int resistance;
    private double birthRate;
    private int lastBorn = 0;
    private int dead = 0;
    private int infected = 0;
    private int healthy = 0;

    private ArrayList<Target> population = new ArrayList<>();

    public Country(int population, int resistance, double birthRate) {
        Target.fill(this.population, population, resistance);
        this.birthRate = birthRate;
        this.resistance = resistance;
    }

    public Country(int population, int resistance) {
        Target.fill(this.population, population, resistance);
        this.birthRate = 0.03;
        this.resistance = resistance;
    }

    public void update(){
        this.dead = countDead();
        this.infected = countInfected();
        this.healthy = countHealhty();
        this.lastBorn = (int) birthRate * this.population.size();
        Target.fill(this.population, lastBorn, resistance);
    }

    public void cleanDead(){
        this.population.removeIf((Target t) -> t.getStatus().equals(Status.STATUS_DEAD));
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

    private int countInfected(){
        int infected = 0;
        for(Target target : this.population){
            if (target.getStatus() == Status.STATUS_INFECTED){
                infected++;
            }
        }
        return infected;
    }

    private int countHealhty(){
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
