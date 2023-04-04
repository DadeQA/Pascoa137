package unitTest;

import iterasys.com.br.Calculadora;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testAreaTriángulo {

    @Test @Order(1)

    public void areaTriangulo() {
        double base = 4;
        double altura = 4;
        double resultadoEsperado = 8;
        double resultadoAtual = Calculadora.areaTriangulo(base, altura);
        assertEquals(resultadoAtual, resultadoEsperado);
        System.out.println("Area do Triangulo: " + resultadoEsperado + "cm");


    }


    @ParameterizedTest @Order(2)
    @CsvSource(value = {
            "6, 5, 15.0",
            "6, 8, 24.0",
            "10, 5, 25.0",
            "15, 4, 30.0",
            "4, 4, 8.0"
    }, delimiter = ',')
    public void testeAreaTrianguloLendoLista(String txtNum1, String txtNum2, String resultadoEsperado) {
        double resultadoAtual = Calculadora.areaTriangulo(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));
        assertEquals(resultadoAtual, Double.valueOf(resultadoEsperado));
        System.out.println("Resultado da Área do Triángulo: " + resultadoAtual + "cm");

    }





    @ParameterizedTest @Order(3)
    @CsvFileSource(resources = "csv/massaAreaTriangulo.csv", numLinesToSkip = 1, delimiter = ',')

    public void testeAreaTrianguloLendoArquivo(
            String txtNum1,
            String txtNum2,
            String resultadoEsperado) {
        double resultadoAtual = Calculadora.areaTriangulo(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));
               assertEquals(resultadoAtual, Double.valueOf(resultadoEsperado));


    }
}
