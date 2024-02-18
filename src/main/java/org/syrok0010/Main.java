package org.syrok0010;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var rnd = new Random();
        var data1 = new ComplexNumber[2][2];
        var data2 = new ComplexNumber[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                data1[i][j] = new ComplexNumber(rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10));
                data2[i][j] = new ComplexNumber(rnd.nextDouble(-10, 10), rnd.nextDouble(-10, 10));
            }
        var m1 = new Matrix(data1);
        var m2 = new Matrix(data2);

        System.out.println(
            "Matrix 1:\n" + m1 +
            "Matrix 2:\n" + m2 +
            "Determinant 1:\n" + m1.determinant() + "\n" +
            "Matrix 1 transposed:\n" + m1.transpose() +
            "Matrix 1+2:\n" + m1.add(m2) +
            "Matrix 1-2:\n" + m1.subtract(m2) +
            "Matrix 1*2:\n" + m1.multiply(m2) +
            "Matrix 1 * number:\n" + m1.multiply(2) +
            "Matrix 1 cofactor:\n" + m1.cofactorMatrix()
        );
    }
}