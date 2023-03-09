package Lab1.Exercise1;

public class ComplexNumber {
    private double real;
    private double imaginary;

    ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public static ComplexNumber Sum(ComplexNumber x, ComplexNumber y) {
        double sumReal = x.real + y.real;
        double sumImaginary = x.imaginary + y.imaginary;
        return new ComplexNumber(sumReal, sumImaginary);
    }

    public static ComplexNumber Product(ComplexNumber x, ComplexNumber y) {
        double productReal = x.real * y.real - x.imaginary * y.imaginary;
        double productImaginary = x.real * y.imaginary + x.imaginary * y.real;
        return new ComplexNumber(productReal, productImaginary);
    }

    public static void Print(ComplexNumber x) {
        if (x.imaginary > 0) {
            System.out.println(x.real + " + " + Math.abs(x.imaginary) + "i");
        }
        else if (x.imaginary == 0) {
            System.out.println(x.real);
        }
        else {
            System.out.println(x.real + " - " + x.imaginary + "i");
        }
    }
}
