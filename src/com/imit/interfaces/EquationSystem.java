package com.imit.interfaces;

public interface EquationSystem {

    void mul(double coefficient);
    void mul(double coefficient, int lineNumber);
    int getSize();
    void swapLines(int i, int j);

    /**
     * Add two lines by coefficient: line[j] = line[i]*coefficient + line[j]
     * @param i - number of major stroke
     * @param j - number of minor stroke
     * @param coefficient of adding
     */
    void addLineByCoefficient(int i, int j, double coefficient);

}
