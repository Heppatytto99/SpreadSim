class Main{

    private final static int TARGETSIZE = 60;
    private final static int INFECTIONCHANCE = 50;
    private Target[] targets;
    private Virus virus;
    private boolean next;

    private Main(){
        this.targets = new Target[TARGETSIZE];
        this.virus = new Virus(INFECTIONCHANCE);
        this.next = true;
        start();
    }

    /*
    Täyttää targets listan täyteen uusia targetteja valitulla 'resistancella'
     */
    private void fillTargets(int resistance){
        for(int i = 0; i < this.targets.length; i++){
            this.targets[i] = new Target(resistance);
        }
    }
    /*
    Luuppaa niin pitkään kunnes 'next' muuttuu falseksi. Kerää ja tulostaa saastutettujen määrän
    sekä lisää päiviä. Jos kaikki listan targetit on saastutettu luuppaaminen lopetetaan ja 'next' asetetaan falseksi.
    */
    private void start(){
        fillTargets(30);
        int day = 0;

        while(this.next){
            int infectedCount = Target.countInfected(this.targets);
            System.out.println("\nDay: " + day + "\nCurrently infected: " + infectedCount);

            this.virus.spread(this.targets);
            int newInfectedCount = Target.countInfected(this.targets) - infectedCount;

            System.out.println("The virus spreads...\nNew infected targets: " + newInfectedCount);

            if(infectedCount + newInfectedCount == this.targets.length){
                this.next = false;
                System.out.println("It took " + day + " days to infect everything");
            }
            day++;
        }
    }

    public static void main(String[] args){
        new Main();
    }
}