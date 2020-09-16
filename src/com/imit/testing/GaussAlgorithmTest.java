package com.imit.testing;

import com.imit.linearSystemAlgorithms.GaussAlgorithm;
import com.imit.linearSystemAlgorithms.LinearEquationSystem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

@RunWith(Parameterized.class)
public class GaussAlgorithmTest {

    // Constant to double equals method
    private static final double DELTA = 1e-10;

    // Parameters
    public double[][] matrix;
    public double[] expectedResult;

    public GaussAlgorithmTest(double[][] matrix, double[] expectedResult){
        this.matrix = matrix;
        this.expectedResult = expectedResult;
    }

    @Test
    public void getDecisionVector(){
        LinearEquationSystem system = new LinearEquationSystem(this.matrix);
        GaussAlgorithm algorithm = new GaussAlgorithm(system);
        algorithm.setDebugMode(true);

        double[] actual = algorithm.getDecisionVector();
        Assert.assertArrayEquals(this.expectedResult, actual, DELTA);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> dataForTest() throws FileNotFoundException {

        ArrayList<Object[]> parameters = new ArrayList<>(); // Лист параметров

        // Создание тестовых матриц
        int varNumber;
        double[][] matrix;
        double[] answer;

        // Считаывание данных из файла
        Scanner in = new Scanner(new File("src/com/imit/testing/GaussAlgorithmTestingParams"));
        while (in.hasNext()){
            varNumber = in.nextInt();
            matrix = new double[varNumber][varNumber+1];
            answer = new double[varNumber];

            // Первый цикл - считывание исходной матрицы
            for (int i = 0; i < varNumber; i++){
                for (int j = 0; j <= varNumber; j++) {
                    matrix[i][j] = in.nextDouble();
                }
            }

            // Считывание ответа на систему уравнений
            for (int i = 0; i < varNumber; i++) {
                answer[i] = in.nextDouble();
            }

            // Добавление в параметры
            parameters.add(new Object[]{matrix, answer});
        }

        return parameters;
    }

}