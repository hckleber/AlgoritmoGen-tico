/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico.Classes;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author Milene
 */
public class Constantes {

    public static int sizeCromossomo = 9;                                           //Tamanho do Individuo
    public static int sizePopulacao = 500;                                          //Tamanho da populaçao
    public static int functionXSize = (int) Math.pow(2, sizeCromossomo);              //Função como em 0 até 2 exponencial tamanho do cromossomo

    //***********************************//
    public static Random random = new Random(System.currentTimeMillis());           //Objeto randomico, gera numeros pelo clock do processado
    //***********************************//

    public static double function1(double x) {
        return (double) (100 + Math.abs(x * Math.sin(Math.sqrt(Math.abs(x)))));
    }

    
    
}
