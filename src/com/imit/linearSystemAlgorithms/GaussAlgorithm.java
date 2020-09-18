package com.imit.linearSystemAlgorithms;

import com.imit.interfaces.Algorithm;

import java.util.Arrays;

import static com.imit.mathFunctions.StandartMathFunctions.isEqual;
import static com.imit.mathFunctions.StandartMathFunctions.printMatrix;

// TODO: Переделать метод решения, под задание в домике
// TODO: Предусмотреть случай, когда система несовместна (ошибка решения)
// TODO: Разобрать возможные ошибки, выбрасываемые программой и обработать
public class GaussAlgorithm implements Algorithm {

    private LinearEquationSystem system;
    private int numberOfVariables;
    private boolean isSolutionProcessDisplayed;

    public GaussAlgorithm(LinearEquationSystem system) {
        this.system = system;
        this.numberOfVariables = getNumberOfVariables();
    }

    @Override
    public int getNumberOfVariables() {
        return system.getSize();
    }

    @Override
    public void calculate() {

        if (this.isSolutionProcessDisplayed){
            System.out.println("Start matrix: ");
            printSlae();
        }

        // Algorithm forward
        this.doAlgorithmForward();

        // Algorithm back
        this.doAlgorithmBack();
    }

    private void doAlgorithmForward(){
        for (int i = 0; i < this.numberOfVariables; i++) {

            // Priority line selection
            // TODO: Put your code here :)

            // Checking the main element and repositioning if required
            if (isEqual(this.system.equationSystem[i][i], 0)){
                for(int j = i+1; j < this.numberOfVariables; j++){
                    if (!isEqual(this.system.equationSystem[j][i], 0)){
                        this.system.swapLines(i, j);
                        if (this.isSolutionProcessDisplayed){
                            System.out.println("Swap lines: ");
                            printSlae();
                        }
                        break;
                    }
                }
            }
            // Converting to triangular matrix
            for (int j = i + 1; j < this.numberOfVariables; j++) {
                if (isEqual(this.system.equationSystem[j][i], 0)){
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
    }

    private void doAlgorithmBack(){
        for (int i = this.numberOfVariables-1; i >= 0; i--) {
            // Normalization if required
            if (!isEqual(this.system.equationSystem[i][i], 1)) {
                this.system.mul(1 / this.system.equationSystem[i][i], i);
                if (this.isSolutionProcessDisplayed) {
                    System.out.println("Normalization: ");
                    printSlae();
                }
            }
            // Converting to a diagonal matrix
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
        this.numberOfVariables = getNumberOfVariables();
    }

    public void setDebugMode(boolean value){
        this.isSolutionProcessDisplayed = value;
    }

    public void printSlae(){
        printMatrix(this.system.equationSystem);
        System.out.println("--------------------------------------------------------\n");
    }
}
