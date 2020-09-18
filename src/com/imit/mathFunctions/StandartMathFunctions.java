package com.imit.mathFunctions;

import java.util.Arrays;

public class StandartMathFunctions {

    private static final double EPS = 1e-12;

    public static boolean isEqual(double x, double y){
        return Math.abs(x-y) < EPS;
    }

    public static boolean isEqual(double x, double y, double eps){
        return Math.abs(x-y) < eps;
    }

    public static void printMatrix(double[][] matrix){
        for ( double[] arr:
                matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }

}
