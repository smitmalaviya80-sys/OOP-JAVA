public class Account  {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static int accountcounter=1;
    public static String generateAccountNumber(){
        return "AC00"+ accountcounter++;
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

        

    }    
} 
