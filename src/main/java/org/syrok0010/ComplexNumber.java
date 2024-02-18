package org.syrok0010;

public class ComplexNumber {
    private final double Re;
    private final double Im;

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

    public ComplexNumber Add(ComplexNumber other) {
        if (other == null)
            return this;
        return new ComplexNumber(this.Re + other.getRe(), this.Im + other.getIm());
    }

    public ComplexNumber Add(double other) {
        return new ComplexNumber(this.Re + other, this.Im);
    }

    public ComplexNumber Subtract(ComplexNumber other) {
        if (other == null)
            return this;
        return new ComplexNumber(this.Re - other.getRe(), this.Im - other.getIm());
    }

    public ComplexNumber Subtract(double other) {
        return new ComplexNumber(this.Re - other, this.Im);
    }

    public ComplexNumber Multiply(ComplexNumber other) {
        if (other == null)
            return this;
        return new ComplexNumber(this.Re * other.Re - this.Im * other.Im, this.Im * other.Re + this.Re * other.Im);
    }

    public ComplexNumber Multiply(double other) {
        return Multiply(new ComplexNumber(other, 0));
    }

    public ComplexNumber Divide(ComplexNumber other) {
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

    public ComplexNumber Divide(double other) {
        return Divide(new ComplexNumber(other, 0));
    }
}
