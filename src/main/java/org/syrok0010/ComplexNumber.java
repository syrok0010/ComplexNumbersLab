package org.syrok0010;

public class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re) {
        this.re = re;
        im = 0;
    }

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getIm() {
        return im;
    }

    public double getRe() {
        return re;
    }

    public ComplexNumber add(ComplexNumber other) {
        if (other == null)
            return this;
        return new ComplexNumber(re + other.getRe(), im + other.getIm());
    }

    public ComplexNumber add(double other) {
        return new ComplexNumber(re + other, im);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        if (other == null)
            return this;
        return new ComplexNumber(re - other.getRe(), im - other.getIm());
    }

    public ComplexNumber subtract(double other) {
        return new ComplexNumber(re - other, im);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        if (other == null)
            return this;
        return new ComplexNumber(re * other.re - im * other.im, im * other.re + re * other.im);
    }

    public ComplexNumber multiply(double other) {
        return multiply(new ComplexNumber(other, 0));
    }

    public ComplexNumber divide(ComplexNumber other) {
        if (other == null)
            return this;
        var a = re;
        var b = im;
        var c = other.re;
        var d = other.im;
        var re = (a * c + b * d) / (c * c + d * d);
        var im = (b * c - a * d) / (c * c + d * d);
        return new ComplexNumber(re, im);
    }

    public ComplexNumber divide(double other) {
        return divide(new ComplexNumber(other, 0));
    }

    @Override
    public String toString() {
        if (im == 0) return Double.toString(re);
        return re + " + " + im + "i";
    }
}
