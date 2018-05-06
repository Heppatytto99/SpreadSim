import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Virus{

    private double infectionValue;
    private int incubationTime;

    public Virus(int infectionValue, int incubationTime) {
        this.infectionValue = infectionValue;
        this.incubationTime = incubationTime;
    }

    public int getIncubationTime(){
        return this.incubationTime;
    }

    private boolean infect(){
        int i = ThreadLocalRandom.current().nextInt(0, 100);
        return i < this.infectionValue;
    }

    public void spread(ArrayList<Target> targets){
        for(Target target : targets){
            if(!target.isInfected()){
                if(infect()){
                    target.infect(this);
                    this.infectionValue -= 0.02;
                }
                else {
                    this.infectionValue += 0.01;
            }
        } else {
                target.tick();
            }
    }
    }
}
