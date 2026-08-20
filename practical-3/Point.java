import java.util.Objects;
public class Point{
    private int x;
    private int y;
    Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override//override attonomus
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override//compare the valu not a address
    //equals method is used to compare the value of two objects
    //equals method already define in object class but we can override it to compare the value of two objects
    //this keyword is used to refer the current object
    //with the help of downcasting we can convert the object of parent class to child class
    //downcasting is used to convert the object of parent class to child class
    //downcasting is used to access the methods and variables of child class

    public boolean equals(Object obj) {
        if(this==obj)//here this is a current class object and obj is a object class object if both are same then return true
        return true;

    

    Point p=(Point)obj; //downcasting
    //here we are comparing the value of two objects if both are same then return true else return false
    return this.x==p.x && this.y==p.y;
    }
//genrate the unique hashcode for each object of point class
    
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