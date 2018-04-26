import java.util.concurrent.ThreadLocalRandom;

public class Target{

    private int resistance;
    private boolean infected;
    private Virus infection;

    public Target(int resistance){
        this.resistance = resistance;
        this.infected = false;
    }
    /*
    Laskee 'targets' listan tarttuneet ja palauttaa lukumäärän.
     */
    public static int countInfected(Target[] targets){
        int infected = 0;
        for(Target target : targets){
            if(target.infected){
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
    // i < resistance = 30 lukua
    // 30 / 100 = 30%

    public boolean resist(){
        int i = ThreadLocalRandom.current().nextInt(0, 100);
        return i < this.resistance;
    }

    public boolean isInfected(){
        return this.infected;
    }
    /*
    Vaihtaa 'infected' arvon true ja liittää viruksen tiedot tähän targettiin.
     */
    public void infect(Virus virus){
        this.infected = true;
        this.infection = virus;
    }
}
