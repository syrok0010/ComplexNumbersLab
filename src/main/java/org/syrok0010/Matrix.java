package org.syrok0010;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Matrix {
    private final int rows;
    private final int cols;
    private final ComplexNumber[][] data;

    public Matrix(double[][] matrix) {
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        data = Arrays
            .stream(matrix)
            .map(a -> Arrays
                .stream(a)
                .mapToObj(ComplexNumber::new)
                .toArray(ComplexNumber[]::new)
            )
            .toArray(ComplexNumber[][]::new);
    }

    public Matrix(int rows, int cols) {
        if (rows < 0 || cols < 0)
            throw new IllegalArgumentException("Negative rows or cols count");
        this.rows = rows;
        this.cols = cols;
        this.data = new ComplexNumber[rows][cols];
        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data[i].length; j++)
                data[i][j] = new ComplexNumber(0);
    }

    public Matrix(ComplexNumber[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = data.clone();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public ComplexNumber get(int i, int j) {
        return data[i][j];
    }

    public void set(int i, int j, ComplexNumber value) {
        data[i][j] = value;
    }

    public void set(int i, int j, double value) {
        data[i][j] = new ComplexNumber(value);
    }

    public Matrix add(Matrix other) {
        if (other == null)
            return this;
        if (this.rows != other.rows || this.cols != other.cols)
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j).add(other.get(i, j)));
            }
        }
        return result;
    }

    public Matrix add(double value) {
        return add(new Matrix(this.rows, this.cols).fill(value));
    }

    public Matrix subtract(Matrix other) {
        if (other == null)
            return this;
        if (this.rows != other.rows || this.cols != other.cols)
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j).subtract(other.get(i, j)));
            }
        }
        return result;
    }

    public Matrix subtract(double value) {
        return subtract(new Matrix(this.rows, this.cols).fill(value));
    }

    public Matrix multiply(Matrix other) {
        if (other == null)
            return this;
        if (this.cols != other.rows)
            throw new IllegalArgumentException("Matrices must have compatible dimensions");
        Matrix result = new Matrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                ComplexNumber sum = new ComplexNumber(0);
                for (int k = 0; k < this.cols; k++) {
                    sum = sum.add(this.get(i, k).multiply(other.get(k, j)));
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public Matrix multiply(double other) {
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.cols; j++)
                result.set(i, j, this.get(i, j).multiply(other));
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(this.cols, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(j, i, this.get(i, j));
            }
        }
        return result;
    }

    public Matrix fill(double value) {
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.cols; j++)
                this.set(i, j, value);
        return this;
    }

    public Matrix fill(ComplexNumber value) {
        for (int i = 0; i < this.data.length; i++)
            for (int j = 0; j < this.cols; j++)
                this.set(i, j, value);
        return this;
    }

    public ComplexNumber determinant() {
        if (this.rows != this.cols)
            throw new IllegalArgumentException("Matrix must be square");
        return switch (this.rows) {
            case 1 -> this.get(0, 0);
            case 2 -> this.get(0, 0).multiply(this.get(1, 1))
                    .subtract(this.get(0, 1).multiply(this.get(1, 0)));
            default -> {
                ComplexNumber det = new ComplexNumber(0);
                for (int j = 0; j < this.cols; j++)
                    det = det.add(this.get(0, j).multiply(this.cofactor(0, j)));
                yield det;
            }
        };
    }

    public Matrix cofactorMatrix() {
        if (this.rows != this.cols)
            throw new IllegalArgumentException("Matrix must be square");
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.cols; j++)
                result.set(i, j, this.cofactor(i, j));
        return result;
    }

    private ComplexNumber cofactor(int i, int j) {
        return this.minor(i, j).multiply(Math.pow(-1, i + j));
    }

    private ComplexNumber minor(int i, int j) {
        return this.matrixWithoutRowAndColumn(i, j).determinant();
    }

    private Matrix matrixWithoutRowAndColumn(int rowToDelete, int columnToDelete) {
        Matrix result = new Matrix(this.rows - 1, this.cols - 1);
        int resultRow = 0;
        for (int r = 0; r < this.rows; r++) {
            if (r == rowToDelete)
                continue;
            int resultColumn = 0;
            for (int c = 0; c < this.cols; c++) {
                if (c == columnToDelete)
                    continue;
                result.set(resultRow, resultColumn, this.get(r, c));
                resultColumn++;
            }
            resultRow++;
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays
                .stream(this.data)
                .map(row -> Arrays
                    .stream(row)
                    .map(ComplexNumber::toString)
                    .collect(Collectors.joining(", "))
                )
                .collect(Collectors.joining("\n")) + "\n";
    }
}
