
public class customer {
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
        return customerId;
    }

    public static void main(String[] args){
        customer a1=new customer("smit", "smitmalaviya", "95106306541561");
        System.out.println(a1.getcustomerid());
        System.out.println(a1.getname());
        System.out.println(a1.getemail());
    }
}

