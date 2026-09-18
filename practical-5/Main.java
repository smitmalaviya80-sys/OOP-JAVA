abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }
}

class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    double area() {
        return 0.5 * base * height;
    }
}

public class Main {

    public static void main(String[] args) {

        double total = 0;
        double largest = 0;

        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 8)
        };

        for (Shape shape : shapes) {

            double currentArea = shape.area();

            System.out.println("Area: %.2f%n" , currentArea);

            total += currentArea;

            if (currentArea > largest) {
                largest = currentArea;
            }
        }

        System.out.printf("Total Area: %.2f%n", total);
        System.out.printf("Largest Area: %.2f%n", largest);
    }
}