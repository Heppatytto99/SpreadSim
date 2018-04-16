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
     */
    public boolean infect(){
        int i = ThreadLocalRandom.current().nextInt(0, 100);
        return !(i > this.infectionChance);
    }
}
