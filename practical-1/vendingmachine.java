import java.util.Scanner;
public class vendingmachine{
    enum Coin{
        ONE,
        TWO,
        FIVE,
        TEN
    }


    public static void main(String[] args) {

         int total=0;
        int snackprice=15;
        Scanner sc=new Scanner(System.in);
       


        while (total<snackprice) {
            System.out.println("enter the coin:");

           String input = sc.next().toUpperCase();
            Coin coin = Coin.valueOf(input);

            int value=switch(coin){
                case ONE-> 1;
                case TWO-> 2;
                case FIVE-> 5;
                case TEN-> 10;
            };
            total+=value;
        }

        System.out.println("total change: " + (total-snackprice));
        sc.close();
    }
}
