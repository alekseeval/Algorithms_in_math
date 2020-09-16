package com.imit;

import com.imit.linearSystemAlgorithms.GaussAlgorithm;
import com.imit.linearSystemAlgorithms.LinearEquationSystem;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();

        LinearEquationSystem system = readLinearSystemFromConsole(size, in);
        printMatrix(system.getEquationSystem()); // Print input data
        GaussAlgorithm algorithm = new GaussAlgorithm(system);

        double[] b = algorithm.getDecisionVector();

        System.out.println("-----------------------------------------------");
        printMatrix(system.getEquationSystem());
        System.out.println("\n Decision is: " + Arrays.toString(b));

    }

    // Just reading matrix (n)_(n+1)
    public static LinearEquationSystem readLinearSystemFromConsole(int size, Scanner in){

        double[][] matrix = new double[size][size+1];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = in.nextDouble();
            }
        }

        return new LinearEquationSystem(matrix);
    }

    public static void printMatrix(double[][] matrix){
        for ( double[] arr:
             matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }

}
