import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
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

    /*
    Käy läpi jokaisen Targetin 'targets' listasta. Jos infect() onnistuu ja targetin resist() epäonnistuu,
    target 'tartutetaan' tällä viruksella.
     */
    public void spread(ArrayList<Target> targets){
        for(Target target : targets){
            if(!target.isInfected()){
                if(infect()){
                    target.infect(this);
                    this.infectionValue -= 0.02;
                }
                else {
                    this.infectionValue += 0.01;
                    //this.infectionValue += 0.0002;
                  //Tää aiheuttaa vielä ongelmia, koska Java FX
                  //ei tykkää kun sen alkiperäisarvoista poiketaan
                    //this.resistanceValue -= 0.1;
                // Voidaan esim laskea viruksen kykyä tarttua jos uhri onnistuu resistoimaan ym. laskutoimituksia
                //}
            }
        } else {
                target.tick();
            }
    }
    }
}
