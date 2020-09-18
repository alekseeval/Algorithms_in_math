package com.imit.linearSystemAlgorithms;

import com.imit.interfaces.EquationSystem;

import static com.imit.mathFunctions.StandartMathFunctions.isEqual;

// TODO: Разобрать возможные ошибки, выбрасываемые программой и обработать
public class LinearEquationSystem implements EquationSystem {

    protected double[][] equationSystem;

    public LinearEquationSystem() {
        this.equationSystem = null;
    }

    public LinearEquationSystem(double[][] equationSystem) {
        this.equationSystem = equationSystem;
    }

    @Override
    public void mul(double coefficient) {

        // Check for exceptions
        if (this.equationSystem == null){
            throw new NullPointerException("Equation instance equal NULL");
        }
        if (this.equationSystem.length == 0){
            throw new ArithmeticException("Empty equation exception");
        }

        // Multiplying
        for (int i = 0; i < this.equationSystem.length; i++) {
            for (int j = 0; j < this.equationSystem[i].length; j++) {
                this.equationSystem[i][j] *= coefficient;
            }
        }

    }

    @Override
    public void mul(double coefficient, int lineNumber) {

        // Check for exceptions
        if (this.equationSystem == null){
            throw new NullPointerException("Equation instance equal NULL");
        }
        if (this.equationSystem.length == 0){
            throw new ArithmeticException("Empty equation exception");
        }

        // Multiplying

        for (int j = 0; j < this.equationSystem[lineNumber].length; j++) {
            this.equationSystem[lineNumber][j] *= coefficient;
        }

    }

    @Override
    public int getSize() {

        // Check for exceptions
        if (this.equationSystem == null){
            throw new NullPointerException("Equation instance equal NULL");
        }
        if (this.equationSystem.length == 0){
            throw new ArithmeticException("Empty equation exception");
        }

        return this.equationSystem[0].length - 1;
    }

    @Override
    public void swapLines(int i, int j) {
        double[] temp = this.equationSystem[i];
        this.equationSystem[i] = this.equationSystem[j];
        this.equationSystem[j] = temp;
    }

    @Override
    public void addLineByCoefficient(int i, int j, double coefficient) {

        for (int k = 0; k < this.equationSystem[i].length; k++) {
            if (isEqual(this.equationSystem[i][k], 0)){
                continue;
            }
            this.equationSystem[j][k] += this.equationSystem[i][k]*coefficient;
        }

    }

    public double[][] getEquationSystem() {
        return equationSystem;
    }

    public void setEquationSystem(double[][] equationSystem) {
        this.equationSystem = equationSystem;
    }
}
