package org.syrok0010;

import java.util.Objects;

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
        if (other.equals(new ComplexNumber(0, 0)))
            throw new ArithmeticException("Zero division");
        double divisor = other.re * other.re + other.im * other.im;
        var resRe = (re * other.re + im * other.im) / divisor;
        var resIm = (im * other.re - re * other.im) / divisor;
        return new ComplexNumber(resRe, resIm);
    }

    public ComplexNumber divide(double other) {
        if (other == 0)
            throw new ArithmeticException("Zero division");
        return divide(new ComplexNumber(other, 0));
    }

    @Override
    public String toString() {
        if (im == 0) return Double.toString(re);
        return re + " + " + im + "i";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexNumber that)) return false;
        return Double.compare(re, that.re) == 0 && Double.compare(im, that.im) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }
}
