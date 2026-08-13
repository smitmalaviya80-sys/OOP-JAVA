import java.util.Objects;
public class Card {
    private String Rank;
    private String Suit;

    Card(String Rank,String Suit){
        this.Rank = Rank;
        this.Suit = Suit;
    }
    //when you want to get only string and modified string then we use tostring overiding method
    @Override
    public String toString(){
        return Rank + "of" + Suit;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        Card c=(Card)obj; //downcasting
        if(this.Rank.equals(c.Rank)&& this.Suit.equals(c.Suit)){
            return true;
        }
        return false;
    }

    public class Driver{

        public static void main(String[] args){
            Card[] card=new Card[5];
            int count=0;
            Card[] inputcard={
                new Card("2","Hearts"),
                new Card("3","Diamonds"),
                new Card("4","Clubs"),
                new Card("2","Hearts"),
                new Card("5","Spades")
            };

            for(int i=0;i<inputcard.length;i++){
                int flag=0;
                for(int j=0;j<i;j++){
                    if(inputcard[i].equals(inputcard[j])){
                        flag=1;
                        break;
                    }
                }
                if(flag==0){
                    card[count++]=inputcard[i];
                }
            }

            for(int i=0;i<count;i++){
                System.out.println(card[i]);
            }
        }
    }
}
