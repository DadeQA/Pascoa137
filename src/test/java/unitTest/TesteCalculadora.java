package unitTest;

import org.junit.jupiter.api.Test;
import iterasys.com.br.Calculadora;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCalculadora {


    @Test
    public void testeSomarDoisNumeros() {
        // Configura
        // Valores de entrada
        double num1 = 7;
        double num2 = 5;
        // Valores de saída
        double resultadoEsperado = 12;

        // Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(num1, num2);
        // Valida
        assertEquals(resultadoAtual, resultadoEsperado);

    }

    @Test
    public void testeSubtrairdoisnumeros() {
        double num1 = 10;
        double num2 = 5;
        double resultadoEsperado = 5;
        double resultadoAtual = Calculadora.subtrairDoisNumeros(num1, num2);
        assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeMultiplicarDoisNumeros() {
        double num1 = 6;
        double num2 = 5;
        double resultadoEsperado = 30;
        double resultadoAtual = Calculadora.multiplicarDoisNumeros(num1, num2);
        assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeDividirDoisNumeros() { //inicio do teste do dividir
        // Configura
        double num1 = 10;
        double num2 = 4;
        double resultadoEsperado = 2.5;

        // Executa
        double resultadoAtual = Calculadora.dividirDoisNumeros(num1, num2);
        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    } // final do teste do dividir

    @Test
    public void testeDividirDoisNumerosInteiros() { // inicío do teste dividir inteiro
        // Configura
        int numA = 8;
        int numB = 0;
        String resultadoEsperado = "Não é possível dividir por zero";

        // Executa
        String resultadoAtual = Calculadora.dividirDoisNumerosInteiros(numA, numB);

        // Valida
        assertEquals(resultadoEsperado,resultadoAtual);
        System.out.println(numA + " / " + numB + " = " + resultadoAtual);
        System.out.println("O resultado esperado: " + resultadoEsperado);
    }

    @Test
    public void testeDivisaorPorZero() { //inicio do teste do dividir
        // Configura
        double num1 = 10;
        double num2 = 0;
        double resultadoEsperado = 2.5;

        // Executa
        double resultadoAtual = Calculadora.dividirDoisNumeros(num1, num2);
        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);


    }
    @ParameterizedTest
    @CsvSource(value = {
            "7, 5, 12.0",
            "56,44, 100.0",
            "10, 0, 10.0",
            "15, -5, 10.0",
            "-8, -6, -14.0"
    }, delimiter = ',')
    public void testeSomarDoisNumerosLendoLista(String txtNum1, String txtNum2, String resultadoEsperado){
        // Configura
        // Valores de entrada


        // Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));
        // Valida
        assertEquals(resultadoAtual, Double.valueOf(resultadoEsperado));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/massaSomar.csv", numLinesToSkip = 1, delimiter = ',')
    public void testeSomarDoisNumerosLendoArquivo(String txtNum1, String txtNum2, String resultadoEsperado){
        // Configura
        // Valores de entrada


        // Executa
        double resultadoAtual = Calculadora.somarDoisNumeros(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));
        // Valida
        assertEquals(resultadoAtual, Double.valueOf(resultadoEsperado));

    }
}

