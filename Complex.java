public class Complex {
    private double real= 0;
    private double imaginary = 0;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {

        return real + "+" + imaginary + "i";
    }

    public Complex add(Complex a, Complex b){
        Complex result = new Complex(0,0);
        result.setReal(a.real + b.real);
        result.setImaginary(a.imaginary + b.imaginary);

        return result;
    }

    //the first parameter is what we subtract
    public Complex subtract(Complex a, Complex b){
        Complex result = new Complex(0,0);
        result.setReal(a.real - b.real);
        result.setImaginary(a.imaginary - b.imaginary);

        return result;

    }
    public Complex multiply(Complex a, Complex b){
        Complex result = new Complex(0,0);
        result.setReal((a.real * b.real)- (a.imaginary*b.imaginary));
        result.setImaginary((a.real*b.imaginary) + (a.imaginary*b.real));
        return result;
    }

    //this takes the first parameter as numerator and second as denomination
    //thus c or d should not be zero

    public Complex divide(Complex a, Complex b){
        Complex result = new Complex(0,0);
        if (b.real != 0 || b.imaginary != 0) {
            result.setReal((a.real * b.real + a.imaginary * b.imaginary) / (Math.pow(b.real,2)+Math.pow(b.imaginary,2)));
            result.setImaginary ((a.imaginary * b.real - a.real * b.imaginary) / (Math.pow(b.real,2)+Math.pow(b.imaginary,2)));
        }
        return result;
    }


}
