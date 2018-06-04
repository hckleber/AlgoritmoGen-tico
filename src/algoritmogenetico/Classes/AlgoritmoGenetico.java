/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico.Classes;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Milene
 */
public class AlgoritmoGenetico {

    private double taxaCrossover;
    private double taxaMutacao;
    private static int PAI = 0;
    private static int MAE = 1;

    public AlgoritmoGenetico(double taxaCrossover, double taxaMutacao) {
        this.taxaCrossover = taxaCrossover;
        this.taxaMutacao = taxaMutacao;
    }

    public Populacao executaAG(Populacao pop) {
        // 1 - Inicio do AG
        // 2 - Avaliação da População

        Populacao novaPopulacao = new Populacao();
        List<Individuo> popBuffer = new ArrayList<Individuo>();

        for (int i = 0; i < (Constantes.sizePopulacao) / 2; i++) {
        // 3 - Selecionar os pais para cruzamento
            Individuo pai = Roleta(pop); //Individuos mais aptos serão selecionados
            Individuo mae = Roleta(pop); //Individuos mais aptos serão selecionados

        // 4 - Realizar o cruzamento
            Individuo[] filhos = Crossover(pai, mae);
        // 5 - Aplicar a mutação (se necessário)
            Individuo filhoA = Mutacao(filhos[0]);
            Individuo filhoB = Mutacao(filhos[1]);

            popBuffer.add(filhoA);
            popBuffer.add(filhoB);
            
        }


        //Apagar velhos membros
        //Inserir novos membros
        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            novaPopulacao.setPopulacao(i, popBuffer.get(i));
        }
        
        popBuffer = null;
        
        //Re-avaliar população
        novaPopulacao.atuaizarValores();
        
        return novaPopulacao;
    }

    public Individuo[] Crossover(Individuo pai, Individuo mae) {
        Individuo[] novoInd = new Individuo[2];

        //rand.nextInt((max - min) + 1) + min;
        int pontoDeCorte = Constantes.random.nextInt(((Constantes.sizeCromossomo - 0) + 1) + 0);

        Individuo paiBuffer = new Individuo();
        Individuo maeBuffer = new Individuo();

        novoInd[PAI] = new Individuo();
        novoInd[MAE] = new Individuo();

        //Duplica (genes) um PAI e uma MAE para executar o CrossOver
        for (int i = 0; i < Constantes.sizeCromossomo; i++) {
            paiBuffer.setGene(i, pai.getGene(i));
            maeBuffer.setGene(i, mae.getGene(i));

            novoInd[PAI].setGene(i, pai.getGene(i));
            novoInd[MAE].setGene(i, mae.getGene(i));
        }

        if (Constantes.random.nextDouble() < taxaCrossover) {
//            System.out.println("Caiu na taxa de crossover! Taxa de CrossOver: " + pontoDeCorte );
            for (int i = pontoDeCorte; i < Constantes.sizeCromossomo; i++) {
                novoInd[PAI].setGene(i, maeBuffer.getGene(i));
                novoInd[MAE].setGene(i, paiBuffer.getGene(i));
            }
        }
        return novoInd;
    }

    public Individuo Mutacao(Individuo ind) {
        if (Constantes.random.nextDouble() <= taxaMutacao) {
            //rand.nextInt((max - min) + 1) + min;
            int genePosicao = Constantes.random.nextInt((Constantes.sizeCromossomo - 0) + 1 + 0);
            ind.mutarBit(genePosicao);
            return ind;
        }
        return ind;
    }

    public Individuo Roleta(Populacao pop) {
        double numSorteado = Constantes.random.nextDouble() * 100; //nextDouble retorna valores de 0 a 1 e precizamos de 0 a 100%

        for (Individuo ind : pop.getPopulacao()) {
            //Verifica se o número sorteado está entre o intervalo da roleta criado para cada individuo
            if (numSorteado >= ind.getRangeRoleta()[0] && numSorteado <= ind.getRangeRoleta()[1]) {
                return ind;
            }
        }
        //Nunca vai acontecer
        return null;
    }

}
