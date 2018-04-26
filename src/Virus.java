import java.util.concurrent.ThreadLocalRandom;

public class Virus{

    private double infectionValue;
    private String name;

    public Virus(int infectionValue, String name) {
        this.infectionValue = infectionValue;
        this.name = name;
    }

    private boolean infect(){
        // infectionvalue C
        // C = 1
        // i < C
        // 1 hyvä 99 huono
        // C = 80
        // i < C
        // 80 hyvää 20 huonoa
        int i = ThreadLocalRandom.current().nextInt(0, 100);
        return i < this.infectionValue;
    }

    public String toString(){
        return this.name;
    }

    /*
    Käy läpi jokaisen Targetin 'targets' listasta. Jos infect() onnistuu ja targetin resist() epäonnistuu,
    target 'tartutetaan' tällä viruksella.
     */
    public void spread(Target[] targets){
        System.out.println(this.infectionValue + " infection before");
        for(Target target : targets){
            if(!target.isInfected()){
                if(infect() && !target.resist()){
                    target.infect(this);
                    this.infectionValue -= 0.02;
                }
                else {
                    this.infectionValue += 0.001;
                    //this.infectionValue += 0.0002;
                  //Tää aiheuttaa vielä ongelmia, koska Java FX
                  //ei tykkää kun sen alkiperäisarvoista poiketaan
                    //this.resistanceValue -= 0.1;
                // Voidaan esim laskea viruksen kykyä tarttua jos uhri onnistuu resistoimaan ym. laskutoimituksia
                //}
            }
        }
    }
        System.out.println(this.infectionValue + " Infection");
}
}
