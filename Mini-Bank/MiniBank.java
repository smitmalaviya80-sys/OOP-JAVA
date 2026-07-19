import java.util.Scanner;
public class MiniBank {


    enum MenuOption{
        OPEN_ACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        EXIT
    }
    record Bankinfo(String name , String branch){}
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

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

            int input= sc.nextInt();


            if(input >5 && input<1){
                System.out.println("invalid input");
                continue;
            }

            MenuOption menu= MenuOption.values()[input-1];

            String message=switch(menu){
                case OPEN_ACCOUNT->"open account to be implimented in later lab";

                case DEPOSIT->"open account to be implimented in later lab";
                case WITHDRAW->"open account to be implimented in later lab";
                case TRANSFER->"open account to be implimented in later lab";
                case EXIT->"Good Bye";
            };

            System.out.println(message);
            System.out.println();

            if(menu==MenuOption.EXIT){
                break;
            }

        }

        sc.close();
        
    }
}