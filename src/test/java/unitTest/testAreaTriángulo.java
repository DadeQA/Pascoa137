package unitTest;

import iterasys.com.br.Calculadora;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testAreaTriángulo {

    @Test

    public void testMultiplicarDoisNumeros(){
        double num1 = 4;
        double num2 = 4;
        double resultadoEsperado = 16;
        double resultadoAtual = Calculadora.multiplicarDoisNumeros(num1, num2);
        assertEquals(resultadoAtual, resultadoEsperado);


    }
    @Test

    public void testeDividirDoisNumeros(){
        double num1 = 16;
        double num2 = 2;
        double resultadoEsperado = 8;
        double resultadoAtual = Calculadora.dividirDoisNumeros(num1, num2);
        assertEquals(resultadoAtual, resultadoEsperado);
        System.out.println("Formula da Área do triángulo: Base * Altura / 2");
        System.out.println("Área do Triángulo:  " + resultadoEsperado);

    }

}
