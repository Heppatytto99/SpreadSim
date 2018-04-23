import java.util.concurrent.ThreadLocalRandom;

public class Virus{

    private int infectionChance;
    private String name;

    public Virus(int infectionChance, String name) {
        this.infectionChance = infectionChance;
        this.name = name;
    }

    /*
    Luo satunnaisen kokonaisluvun 'i' väliltä 0 - 100 ja palauttaa true
    jos i ei ole suurempi kuin tämän mahdollisuus tartuttaa.
    ESIM. Viruksen mahdollisuus tartuttaa on 70% eli infectionChance on 70.
    Random funktiolla voi saada 70 lukua jotka ovat alle infectionChance ja 30 lukua
    jotka on suurempia kuin infectionChance. Eli 70/100 = 70% ja 30/100 = 30%
     */
    private boolean infect(){
        int i = ThreadLocalRandom.current().nextInt(0, 10000);
        return !(i > this.infectionChance);
    }

    public String toString(){
        return this.name;
    }

    /*
    Käy läpi jokaisen Targetin 'targets' listasta. Jos infect() onnistuu ja targetin resist() epäonnistuu,
    target 'tartutetaan' tällä viruksella.
     */
    public void spread(Target[] targets){
        for(Target target : targets){
            if(!target.isInfected()){
                if(infect() != target.resist()){
                    target.infect(this);
                }
                //else {
                // Voidaan esim laskea viruksen kykyä tarttua jos uhri onnistuu resistoimaan ym. laskutoimituksia
                //}
            }
        }
    }
}
