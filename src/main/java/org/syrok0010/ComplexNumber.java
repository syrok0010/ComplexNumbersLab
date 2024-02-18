package org.syrok0010;

public class ComplexNumber {
    private final double Re;
    private final double Im;

    public ComplexNumber(double re) {
        Re = re;
        Im = 0;
    }

    public ComplexNumber(double re, double im) {
        Re = re;
        Im = im;
    }

    public double getIm() {
        return Im;
    }

    public double getRe() {
        return Re;
    }

    public ComplexNumber add(ComplexNumber other) {
        if (other == null)
            return this;
        return new ComplexNumber(this.Re + other.getRe(), this.Im + other.getIm());
    }

    public ComplexNumber add(double other) {
        return new ComplexNumber(this.Re + other, this.Im);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        if (other == null)
            return this;
        return new ComplexNumber(this.Re - other.getRe(), this.Im - other.getIm());
    }

    public ComplexNumber subtract(double other) {
        return new ComplexNumber(this.Re - other, this.Im);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        if (other == null)
            return this;
        return new ComplexNumber(this.Re * other.Re - this.Im * other.Im, this.Im * other.Re + this.Re * other.Im);
    }

    public ComplexNumber multiply(double other) {
        return multiply(new ComplexNumber(other, 0));
    }

    public ComplexNumber divide(ComplexNumber other) {
        if (other == null)
            return this;
        var a = this.Re;
        var b = this.Im;
        var c = other.Re;
        var d = other.Im;
        var re = (a * c + b * d) / (c * c + d * d);
        var im = (b * c - a * d) / (c * c + d * d);
        return new ComplexNumber(re, im);
    }

    public ComplexNumber divide(double other) {
        return divide(new ComplexNumber(other, 0));
    }

    @Override
    public String toString() {
        if (Im == 0) return Double.toString(Re);
        return Re + " + " + Im + "i";
    }
}
