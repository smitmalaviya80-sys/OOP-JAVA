public class Thermostat {
    private String location;
    private int temperature;
    private static final int MIN=16;
    private static final int MAX=30;
    private static int activecount=0;

    Thermostat(String location,  int StartTemp){
        this.location=location;
        if(StartTemp>=MIN && StartTemp<=MAX){
            this.temperature=StartTemp;
        }else{
            this.temperature=StartTemp;
        }

        activecount++;
    }

    // if user dont give temperature then we need to call full constructorthen use defauld constructor again
    Thermostat(String location){
        this(location,22);
    }

    public void raise(){
        if(temperature<MAX){
            temperature+=1;

        }else{
            System.out.println("already at max 30c");
        }
    }

    public void lower(){
        if(temperature>MIN){
            temperature-=1;
        }else{
            System.out.println("already temperature at min 16c");
        }
    }

    public int gettemperature(){
        return temperature;
    }

    public static  int getactivecount(){
        return activecount;
    }


    public static void main(String[] args) {
        Thermostat t1=new Thermostat("LivingRoom", 20);
        Thermostat t2 = new Thermostat("bedroom");

          System.out.println("t1 raising:");
        for (int i = 0; i < 10; i++) {
            t1.raise();
            System.out.println(t1.gettemperature());
        }

        System.out.println("t1 lowering:");
        for (int i = 0; i < 20; i++) {
            t1.lower();
            System.out.println(t1.gettemperature());
        }


        System.out.println("total active Thermostat" + getactivecount());
    }
}
