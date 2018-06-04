/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico.Classes;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author Milene
 */
public class Populacao {

    private Individuo[] populacao;              //Grupo de individuos, vetor com N individuos

    //Construtor de classe
    public Populacao() {
        this.populacao = new Individuo[Constantes.sizePopulacao];

        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            populacao[i] = new Individuo();
        }
        
        //Avaliação da população
        calcularFitness();;
        calcularFitnessPercent();
        calcularRangRoleta();
        
    }

    public void calcularFitness() {
        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            //Para calcular, utilizaro valor inteiro do individuo na fç aptidao
            this.populacao[i].setFitness(Constantes.function1(this.populacao[i].getInt()));
        }
    }

    public void calcularFitnessPercent() {
        double somatoriaFitness = 0;

        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            somatoriaFitness += populacao[i].getFitness();
        }

        for (int i = 0; i < Constantes.sizePopulacao; i++) {

            // somatoriaFitness --------------- 100%
            // Fitness do ind(i) -------------------------  X?
            populacao[i].setFitnessPercent((populacao[i].getFitness() * 100) / somatoriaFitness);
        }
    }

    //Calculcar o range da roleta em que o individuo faz parte
    public void calcularRangRoleta() {
        //Primeiro deve-se ordenar a população em ordem crescente
        //Chamar meétodo para ordenar a população
        OrdenarPopulacao();
        double somatoria = 0;

        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            if (i == 0) {
                somatoria += populacao[i].getFitnessPercent();
                populacao[i].setRangeRoleta(0, somatoria);
            } else if (i == (Constantes.sizePopulacao - 1)) {
                populacao[i].setRangeRoleta(somatoria, 100f);
            } else {
                populacao[i].setRangeRoleta(somatoria, somatoria + populacao[i].getFitnessPercent());
                somatoria += populacao[i].getFitnessPercent();
            }
        }
    }

    public void atuaizarValores() {
        //Calcular Fitness
        calcularFitness();
        //Calcular FitnessPercet
        calcularFitnessPercent();
        //Calcular RangeRoleta
        calcularRangRoleta();
    }

    public double getMediaPopulacao() {
        double sum = 0;

        for (Individuo ind : populacao) {
            sum += ind.getFitness();
        }
        
        
        return sum / Constantes.sizePopulacao;
    }

    public void OrdenarPopulacao() {
        Individuo aux = new Individuo();

        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            for (int j = 0; j < Constantes.sizePopulacao; j++) {
                if (populacao[i].getFitnessPercent() < populacao[j].getFitnessPercent()) {
                    aux = populacao[i];
                    populacao[i] = populacao[j];
                    populacao[j] = aux;

                }
            }
        }
    }

    public Individuo[] getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int posicao, Individuo populacao) {
        this.populacao[posicao] = populacao;
    }

    public String printPop(){
        String result = "";
        
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
        
        for(int i=0; i< Constantes.sizePopulacao;i++){
            result = result + populacao[i].printIndividuo() + "   |   "
//                            + populacao[i].getInt() + "   |   "
//                            + populacao[i].getFitnessPercent()+ "   |   "
                            + "Range da Roleta:  "+ df.format(populacao[i].getRangeRoleta()[0]) + " : " + df.format(populacao[i].getRangeRoleta()[1]) + "\n";
        }
        
        return result;
    }

    
    
}
