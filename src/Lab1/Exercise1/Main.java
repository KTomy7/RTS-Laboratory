package Lab1.Exercise1;

public class Main {
    public static void main(String[] args) {
        ComplexNumber c1 = new ComplexNumber(2.0, 5.0);
        ComplexNumber c2 = new ComplexNumber(4.0, -1.0);

        ComplexNumber.Print(ComplexNumber.Sum(c1, c2));
        ComplexNumber.Print(ComplexNumber.Product(c1, c2));
    }
}
