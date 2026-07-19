import java.util.Scanner;

public class TollBooth {

    record Vehicle(String number , String type){}

    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);

        int totaltoll=0;
        int bikecount=0;
        int carcount=0;
        int truckcount=0;

        while (true) {
            System.out.println("enter the vehicle number or done");
            String number=sc.next();

            if(number.equalsIgnoreCase("done")){
                break;
            }

            System.out.println("enter the type of vehicle");
            String type=sc.next().toLowerCase();

            Vehicle vehicle=new Vehicle(number, type);
            int input=switch(vehicle.type()){
                case "bike"->{
                    bikecount++;
                    yield 20;
                }

                case "car"->{
                    carcount++;
                    yield 50;
                }

                case "truck"->{
                    truckcount++;
                    yield 120;
                }

                default->{
                    System.out.println("invalid input ");
                    yield 0;
                }


            
            };
            totaltoll+=input;
        }

        String mostfeq;


        if(bikecount>=carcount && bikecount>=truckcount){
            mostfeq="bike";
        }else if(carcount>=bikecount && carcount>=truckcount){
            mostfeq="car";
        }else{
            mostfeq="truck";
        }

        System.out.println("totaltoll: " + totaltoll);
        System.out.println("most frequent:- " + mostfeq);

    }
}