/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico.Classes;

/**
 *
 * @author Milene
 */
public class Individuo {

    private int[] cromossomo;                //Vetor de bits, possui apenas dois estados, 1 ou fase
    private double fitness;                     //Nota de aptidão do individuo
    private double fitnessPercent;              //A % do individuo, o quão ele é apto para reproduzir
    private double[] faixaRoleta = {0, 0};       //Faixa da pizza em que é provavel que ele seja selecionado

    //Construtores da classe
    public Individuo() {
        //Instancia o cromossomo com a quantidade de bits informados na classe Constantes
        this.cromossomo = new int[Constantes.sizeCromossomo];

        for (int i = 0; i < cromossomo.length; i++) {
            this.cromossomo[i] = (Constantes.random.nextDouble() > 0.5f) ? 1 : 0;        //Popula o vetor com bits aleatórios
        }
    }

    public int[] getCromossomo() {
        return cromossomo;
    }

    //Inserir valor booleano em um determinado local do vetor do cromossomo
    public void setGene(int posicao, int gene) {
        this.cromossomo[posicao] = gene;
    }

    public int getGene(int posicao) {
        return this.cromossomo[posicao];
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }

    public double getFitnessPercent() {
        return fitnessPercent;
    }

    public void setFitnessPercent(double fitnessPercent) {
        this.fitnessPercent = fitnessPercent;
    }

    public void mutarBit(int posicao) {
        if (posicao < cromossomo.length) {
            cromossomo[posicao] = cromossomo[posicao] == 0 ? 1 : 0;
        }
    }

    public void setRangeRoleta(double inicio, double fim) {
        faixaRoleta[0] = inicio;
        faixaRoleta[1] = fim;
    }

    public double[] getRangeRoleta() {
        return this.faixaRoleta;
    }

    public int getInt() {
//        if (this.cromossomo.length > 32) {
//            throw new IllegalArgumentException("O comprimento do cromossomo deve ser no máximo 32 bits");
//        }

        int[] array = new int[1];

        //Conversor BinDec
        array[0] = 0;

        for (int i = 0; i < this.cromossomo.length; i++) {
            array[0] = (int) (array[0] + this.cromossomo[i] * Math.pow(2, i));
//            System.out.println(this.cromossomo[i] + "* 2 ^" + i + " = " + this.cromossomo[i] * Math.pow(2, i));
        }

        return array[0];
    }

    public String printIndividuo() {

        String result = "Bits:  ";
        for (int i = this.cromossomo.length - 1; i >= 0; i--) {
            result = result + this.cromossomo[i];
        }

        result += "  INT:  " + getInt() + "  Aptdão:  "+ getFitness()+"  Porcentagem:  "+ getFitnessPercent();
        
        return result;
    }

}
