import java.util.Scanner;
import java.util.Objects;
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
            MenuOption menu= MenuOption.values()[input-1];//here two types of input we will take 1 valuesof which contain single value like valuesof("OPEN_ACCOUNT") and 2 values() which contain all the values of enum class
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
 class Account  {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static int accountcounter=1;
    private static String generateAccountNumber(){
        return String.format("AC%04d", accountcounter++);
    }
   
    public Account(String ownerName, long balance , boolean active){
        this.ownerName=ownerName;
        this.balance=balance;
        this.active=active;
        accountNumber=generateAccountNumber();
    }
    public Account(String ownerName , long balance){
        this(ownerName,balance,true);
    }
    public Account(String ownerName){
        this(ownerName,0);
    }

    @Override
    public  String toString(){
        return "Account Number: "+accountNumber+" | Owner Name: "+ownerName+" | Balance: "+balance+" | Active: "+active;
    }

    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        Account a=(Account)o;
        return this.accountNumber.equals(a.accountNumber);

    }

    @Override
    //check hashcode is unique for each object of account class
    public int hashCode(){
        
        return accountNumber.hashCode();
    }

    

    
    public String getaccountNumber(){
        return accountNumber;
    }
    public String getownerName(){
        return ownerName;
    }
    public long getbalance(){
        return balance;
    }
    public boolean getactive(){
        return active;
    }

    public void deposite(long amount){
        if(amount>0){
        balance+=amount;
        }
    }
    public  boolean withdraw(long amount){
        if(amount>0 && amount<=balance){
        balance-=amount;
        return true;
        }
        return false;
    }
    
    public static void main(String[] args){
        Account account[]=new Account[3];
        account[0]=new Account("smit",1000);
        account[1]=new Account("smit",1000,false);
        account[2]=new Account("smit");
        account[0].deposite(1000);
        account[0].withdraw(500);
        account[0].withdraw(5000);
        System.out.println(account[0].getbalance());
        System.out.println(account[0].getaccountNumber());
        System.out.println(account[1].getaccountNumber());
        System.out.println(account[2].getaccountNumber());

    }    
} 


 public class Customer implements Cloneable{
    private String name;
    private String email;
    private String mobile;
    private final String customerid;

    private static long customerCounter = 101;

    private static String generateCustomerId(){
        return "CUST"+ customerCounter++;

    }

    public customer(String name,String email,String mobile  ){
        this.name=name;
        this.email=email;
        this.mobile=mobile;
        customerid=generateCustomerId();
    }

    
    public String getname(){
        return name;
    }

    public String getemail(){
        return email;
    }


    public String getmobile(){
        return mobile;
    }

    
    public String getcustomerid(){
        return customerid;
    }
    public static class Address {
       private String line;
        private String city;
        private String pincode;
        Address(String line, String city, String pincode){
            this.line=line;
            this.city=city;
            this.pincode=pincode;
        }

       public String getline(){
            return line;
        }
        public String getcity(){
            return city;
        }
        public String getpincode(){
            return pincode;
       }

       @Override
       public String toString(){
            return "Address: "+line+", "+city+", "+pincode;
       }

       public String getAddress(){
            return line+", "+city+", "+pincode;
       }
    }
    public static void main(String[] args){
        customer a1=new customer("smit", "smitmalaviya", "95106306541561");
        System.out.println(a1.getcustomerid());
        System.out.println(a1.getname());
        System.out.println(a1.getemail());
        System.out.println(a1.getmobile());
    }
}

