import java.util.Scanner;
public class MiniBank {

    //used to declare constent
    enum MenuOption{
        OPEN_ACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        EXIT
    }
    //record store banking info work as database 
    record Bankinfo(String name , String branch){}
    public static void main(String[] args) {

        //used to take input from the user if i declared scanner packet that mandatory to declare this liner this work as take a input from system
        Scanner sc=new Scanner(System.in);
        //created record objrct here
        Bankinfo bank=new Bankinfo("axis", "surat");


        System.out.println("==================================");
        System.out.println("        MINI BANK SYSTEM");
        System.out.println("==================================");
        System.out.println(bank);
        System.out.println();


        while (true) {
            System.out.println("1.open account");
            System.out.println("2.deposit");

            System.out.println("3.withdraw");

            System.out.println("4.transfer");

            System.out.println("5.exit");
            //take a input from user nextINT mean only int input will be consider
            int input= sc.nextInt();


            if(input >5 || input<1){
                System.out.println("invalid input");
                continue;
            }
            //created enum class object
            MenuOption menu= MenuOption.values()[input-1];
            //used switch loop
            String message=switch(menu){
                case OPEN_ACCOUNT->"open account to be implimented in later lab";

                case DEPOSIT->"open account to be implimented in later lab";
                case WITHDRAW->"open account to be implimented in later lab";
                case TRANSFER->"open account to be implimented in later lab";
                case EXIT->"Good Bye";
            };

            System.out.println(message);
            System.out.println();
            //here show if the given input from user is exit then break the loop
            if(menu==MenuOption.EXIT){
                break;
            }

        }

        sc.close();
        
    }
}

