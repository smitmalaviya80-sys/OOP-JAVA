import java.util.Objects;
public class Point{
    private int x;
    private int y;
    Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) 
        return true;

    

    Point p=(Point)obj; //downcasting
    return this.x==p.x && this.y==p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
 public class Driver{
    public static void main(String[] args){
        Point[] cord={

            new Point(1,2),
            new Point(3,4),
            new Point(5,6),
            new Point(1,2),
            new Point(5,7),
            new Point(5,6)
        };

        int diffcord=0;

        for(int i=0;i<cord.length;i++){
            int flag=0;
            for(int j=0;j<i;j++){
                if(cord[i].equals(cord[j])){
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                diffcord++;
            }
        }
        for(Point i:cord){
            System.out.println(i);
        }

        System.out.println("Total different coordinates are: "+ diffcord);
    }
 }
}