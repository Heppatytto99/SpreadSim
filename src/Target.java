import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Target{

    private int resistance;
    private boolean infected;
    private Virus infection;
    private int timer;
    private Status status;

    public Target(int resistance){
        this.resistance = resistance;
        this.infected = false;
        this.status = Status.STATUS_HEALTHY;
    }

    /*
    Laskee 'targets' listan tarttuneet ja palauttaa lukumäärän.
     */
    public static int countInfected(ArrayList<Target> targets){
        int infected = 0;
        for(Target target : targets){
            if(target.status == Status.STATUS_INFECTED){
                infected++;
            }
        }
        return infected;
    }
    /*
    Sama kuin infect()
     */
    // 100 lukua
    // resistance 30
    // i < resistance = 30 lukua jolla true
    // 30 / 100 = 30%

    public boolean resist(){
        int i = ThreadLocalRandom.current().nextInt(0, 100);
        return i < this.resistance;
    }

    public boolean isInfected(){
        return this.infected;
    }

    public Status getStatus(){return this.status; }
    /*
    Vaihtaa 'infected' arvon true ja liittää viruksen tiedot tähän targettiin.
     */
    public void infect(Virus virus){
        this.infected = true;
        this.status = Status.STATUS_INFECTED;
        this.infection = virus;
        this.timer = virus.getIncubationTime();
    }

    public void tick(){
        if(this.timer  == 0){
            if(this.resist()){
                this.timer = this.infection.getIncubationTime();
            } else {
                this.status = Status.STATUS_DEAD;
            }
        } else {
            this.timer--;
        }
    }

    public static void fill(ArrayList<Target> targets, int amount, int resistance){
        for(int i = 0; i < amount; i++){
            targets.add(new Target(resistance));
        }
    }
}
