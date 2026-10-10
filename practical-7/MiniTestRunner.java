import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MyTests {

    @Run
    public void testLogin() {
        System.out.println("testLogin ran");
    }

    @Run
    public void testSignup() {
        System.out.println("testSignup ran");
    }

    public void normalMethod() {
        System.out.println("normalMethod ran");
    }

    @Run
    public void testCalculation() {
        System.out.println("testCalculation ran");
    }
}

public class MiniTestRunner {

    public static void main(String[] args) throws Exception {

        MyTests tests = new MyTests();

        int count = 0;

        for (Method method : MyTests.class.getDeclaredMethods()) {

            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {

                method.invoke(tests);
                count++;
            }
        }

        System.out.println("Tests ran: " + count);
    }
}