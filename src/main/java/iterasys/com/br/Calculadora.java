// 1 - Pacote: conjunto de classes
package iterasys.com.br;

// 2 - Bibliotecas

import java.security.PublicKey;

// 3 - Classe
public class Calculadora {
    // 3.1 Atributos - Caracteristicas - Campos

    // 3.2 Funções e Metódos
    public static double somarDoisNumeros(double num1, double num2){
        return num1 + num2;
    }
    // ToDo: Criar o codigo para subtrair, multiplicar e dividir

    public static double subtrairDoisNumeros(double num1, double num2){
        return num1 - num2;
    }

    public static double multiplicarDoisNumeros(double num1, double num2){
        return num1 * num2;
    }
    public static double dividirDoisNumerosSimple(double num1, double num2){
        return  num1 / num2;
    }

    public static String dividirDoisNumerosInteiros(int numA, int numB){
        try{
            return String.valueOf(numA / numB);
        }
        catch(Exception e){
            return "Não é possível dividir por zero";
        }

    }

    public static double dividirDoisNumeros(double num1, double num2) { // inicio da função dividi
        try{
            if (num1/num2 < Double.MAX_VALUE && num1/num2 > Double.MIN_VALUE){
                return num1 / num2;
            } else {
                System.out.println("Não foi possível dividir por zero!");
                return 0;
            }
        }
        catch (RuntimeException e){
            System.out.println("Não foi possível dividir por zero");
            return 0;
        }

    } // final da função dividir
}









