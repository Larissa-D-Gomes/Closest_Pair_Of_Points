/* Grupo: 
 *   Ana Laura Fernandes de Oliveira  
 *   Larissa Domingues Gomes
 *   Tarcila Fernanda Resende da Silva
 * Disciplina: Projeto e Análise de Algoritmos
 * Professor: Silvio Jamil
 * Questao: Par de pontos mais próximos - Força Bruta
 * Complexidade: O(n^2)
 */

import java.util.Scanner;

public class ClosestPointsBruteForce{

    /* Método para calcular menor distância entre pares de pontos
     * utilizando uma abordagem de Força Bruta, cuja complexidade 
     * é O(n^2)
     * @param double[][] pontos, int quantPontos
     * @return double 
     */
    public static void CalcularParMaisProximoFB(double[][] pontos, int quantPontos){

        // Impossível calcular menor distância se |conjunto de pontos| < 2
        if(quantPontos < 2)
            System.out.println("Impossível calcular menor distância!");
        else{   
                 
            double menor = Math.pow( 
                            (
                                Math.pow(pontos[1][0] - pontos[0][0], 2) + Math.pow(pontos[1][1] - pontos[0][1], 2)
                            ),
                        0.5);

            // Variável auxiiar para armazenar calculo de distância 
            double distancia;
            double x1 = pontos[0][0];
            double x2 = pontos[1][0];
            double y1 = pontos[0][1];
            double y2 = pontos[1][1];

            // Estrutura de repetição dupla para calcular a distância entre todos
            // os pontos do connjunto de pontos recebido como parâmetro O(n^2)
            for(int i = 1; i < quantPontos; i++){
                for(int j = i + 1; j < quantPontos; j++){
                    // Calculo de distância entre pontos pela fórmula:
                    // raizquadrada((pontojX - pontoiX)^2 + (pontojY - pontoiY)^2)
                    distancia = Math.pow( 
                            (
                                Math.pow(pontos[j][0] - pontos[i][0], 2) + Math.pow(pontos[j][1] - pontos[i][1], 2)
                            ),
                        0.5); 
                    if(distancia < menor){
                        // Atulizar valores caso nova distância calulada
                        // for menor que a anterior
                        menor = distancia;

                        // Atualizar ponto 1
                        x1 = pontos[i][0], y1 = pontos[i][1];
                        // Atualizar ponto 2
                        x2 = pontos[j][0], y2 = pontos[j][1];
                    }
                }
            }

            System.out.println( "Menor distancia: " + menor);
            System.out.print( "Pontos: [" + "(" + x1 + ", " + y1 + ")");
            System.out.println( "; " + "(" + x2 + ", " + y2 + ")]");
        }
    }
    
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        double x, y;

        System.out.print("Quantidade de pontos: ");
        int quantPontos = leitor.nextInt();

        // Matriz para amazenar pontos
        // Ponto 1  2  3  4 
        //     X [] [] [] []
        //     Y [] [] [] []
        double[][] pontos = new double[quantPontos][2];

        for(int i = 0; i < quantPontos; i++){
            System.out.print("Valor x ponto [" + (i + 1) + "]: ");
            x = leitor.nextDouble();

            System.out.print("Valor y ponto [" + (i + 1) + "]: ");
            y = leitor.nextDouble();

            System.out.println("Ponto[" + (i + 1) + "]: (" + x + ", " + y + ") \n" );

            pontos[i][0] = x;
            pontos[i][1] = y;
        }          
        CalcularParMaisProximoFB(pontos, quantPontos);
    }
}