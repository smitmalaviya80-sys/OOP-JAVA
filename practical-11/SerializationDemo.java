
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationDemo {

    public static void main(String[] args) {

        Student[] students = {
            new Student(1, "Smit", "pass123"),
            new Student(2, "Rahul", "rahul456"),
            new Student(3, "Priya", "priya789")
        };

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("students.dat"));

            out.writeObject(students);
            out.close();

            System.out.println("Objects saved successfully.");

            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("students.dat"));

            Student[] loadedStudents = (Student[]) in.readObject();
            in.close();

            System.out.println("\nObjects loaded successfully:");

            for (Student student : loadedStudents) {
                student.display();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
