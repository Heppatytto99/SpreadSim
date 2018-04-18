import java.util.concurrent.ThreadLocalRandom;

public class Virus{

    private int infectionChance;

    public Virus(int infectionChance) {
        this.infectionChance = infectionChance;
    }

    /*
    Luo satunnaisen kokonaisluvun 'i' väliltä 0 - 100 ja palauttaa true
    jos i ei ole suurempi kuin tämän mahdollisuus tartuttaa.
    ESIM. Tämän mahdollisuus tartuttaa on 70% eli infectionChance on 70
    randomilla on 70 lukua jotka ovat alle infectionChance ja 30 lukua
    jotka ovat suuremmat kuin infectionChance eli 70/100 = 70% ja 30/100 = 30%
     */
    public boolean infect(){
        int i = ThreadLocalRandom.current().nextInt(0, 100);
        return !(i > this.infectionChance);
    }
}
