package com.imit.linearSystemAlgorithms;

import com.imit.interfaces.Algorithm;

import java.util.Arrays;

// TODO: Создать дополнительный метод, для вывода в консоль последовательности преобразований матрицы при решении
// TODO: Переделать метод решения, под задание в домике
public class GaussAlgorithm implements Algorithm {

    private LinearEquationSystem system;
    private boolean isSolutionProcessDisplayed;

    public GaussAlgorithm(LinearEquationSystem system) {
        this.system = system;
    }

    @Override
    public int getNumberOfVariables() {
        return system.getSize();
    }

    @Override
    public void calculate() {

        int numberOfVariables = this.system.getSize();

        if (this.isSolutionProcessDisplayed){
            System.out.println("Start matrix: ");
            printSlae();
        }

        // Algorithm forward
        for (int i = 0; i < numberOfVariables; i++) {

            // Checking the main element and repositioning if required
            if (this.system.equationSystem[i][i] == 0){
                for(int j = i+1; j < numberOfVariables; j++){
                    if (this.system.equationSystem[j][i] != 0){
                        this.system.swapLines(i, j);
                        if (this.isSolutionProcessDisplayed){
                            System.out.println("Swap lines: ");
                            printSlae();
                        }
                        break;
                    }
                }
            }

            this.system.mul(1 / this.system.equationSystem[i][i], i); // Normalization
            if (this.isSolutionProcessDisplayed){
                System.out.println("Normalization: ");
                printSlae();
            }
            for (int j = i + 1; j < numberOfVariables; j++) {
                if (this.system.equationSystem[j][i] == 0){
                    continue;
                }
                double coefficient = (-1) * this.system.equationSystem[j][i] / this.system.equationSystem[i][i];
                this.system.addLineByCoefficient(i, j, coefficient);
                if (this.isSolutionProcessDisplayed){
                    System.out.printf("Zero the %d line, %d column: \n", j+1, i+1);
                    printSlae();
                }
            }
        }

        // Algorithm back
        for (int i = numberOfVariables-1; i > 0; i--) {
            for (int j = 0; j < i; j++){
                this.system.addLineByCoefficient(i, j, -this.system.equationSystem[j][i]);
                if (this.isSolutionProcessDisplayed){
                    System.out.println("Final part: ");
                    printSlae();
                }
            }
        }
    }

    @Override
    public double[] getDecisionVector() {
        this.calculate();
        double[] b = new double[this.getNumberOfVariables()];
        for (int i = 0; i < b.length; i++) {
            b[i] = this.system.equationSystem[i][b.length];
        }
        return b;
    }

    public LinearEquationSystem getSystem() {
        return system;
    }

    public void setSystem(LinearEquationSystem system) {
        this.system = system;
    }

    public void setDebugMode(boolean value){
        this.isSolutionProcessDisplayed = value;
    }

    public void printSlae(){
        for ( double[] arr:
                this.system.equationSystem) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("--------------------------------------------------------\n");
    }
}
