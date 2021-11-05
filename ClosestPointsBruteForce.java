/* Grupo: 
 *   Ana Laura Fernandes de Oliveira  
 *   Larissa Domingues Gomes
 *   Tarcila Fernanda Resende da Silva
 * Disciplina: Projeto e Análise de Algoritmos
 * Professor: Silvio Jamil
 * Questão: Par de pontos mais próximos - Força Bruta
 * Complexidade: O(n^2)
 */

import java.util.Scanner;
    
class Ponto{
    public double x;
    public double y;

    public Ponto(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void atualizar(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void printPonto(){
        System.out.println("(" + this.x + ", " + this.y + ")");
    }
}

public class ClosestPointsBruteForce{



    /* Método para calcular menor distância entre pares de pontos
     * utilizando uma abordagem de Força Bruta, cuja complexidade 
     * é O(n^2), sendo n = quantPontos
     * @param Ponto[] pontos, int quantPontos
     * @return double 
     */
    public static double CalcularParMaisProximoFB(Ponto[] pontos, int quantPontos){

        // Impossível calcular menor distância se |conjunto de pontos| < 2
        if(quantPontos < 2)
            System.out.println("Impossível calcular menor distância!");
        else{   
            // Calculo de distância entre pontos pela fórmula:
            // raizquadrada((pontojX - pontoiX)^2 + (pontojY - pontoiY)^2)      
            double menor = Math.pow( 
                            (
                                Math.pow(pontos[1].x - pontos[0].x, 2) + Math.pow(pontos[1].y - pontos[0].y, 2)
                            ),
                        0.5);

            // Variável auxiiar para armazenar calculo de distância 
            double distancia;
            Ponto p1 = new Ponto(pontos[0].x, pontos[0].y);
            Ponto p2 = new Ponto(pontos[1].x, pontos[1].y);

            // Estrutura de repetição dupla para calcular a distância entre todos
            // os pontos do conjunto de pontos recebido como parâmetro O(n^2)
            for(int i = 0; i < quantPontos; i++){
                for(int j = i + 1; j < quantPontos; j++){                    
                    // Calculo de distância entre pontos pela fórmula:
                    // raizquadrada((pontojX - pontoiX)^2 + (pontojY - pontoiY)^2)
                    distancia = Math.pow( 
                            (
                                Math.pow(pontos[j].x - pontos[i].x, 2) + Math.pow(pontos[j].y - pontos[i].y, 2)
                            ),
                        0.5);

                    if(distancia < menor){
                        // Atulizar valores caso nova distância calulada
                        // for menor que a anterior
                        menor = distancia;

                        // Atualizar ponto 1
                        p1.atualizar(pontos[i].x, pontos[i].y);
                        // Atualizar ponto 2
                        p2.atualizar(pontos[j].x, pontos[j].y);
                    }
                }
            }
            // Print resultados
            System.out.println( "Menor distancia: " + menor);
            System.out.print( "Pontos: [" + "(" + p1.x + ", " + p1.y + ")");
            System.out.println( "; " + "(" + p2.x + ", " + p2.y + ")]");
            return menor;
        }
        return -1;
    }
    
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        double x, y;

        System.out.print("Quantidade de pontos: ");
        int quantPontos = leitor.nextInt();

        Ponto[] pontos = new Ponto[quantPontos];

        for(int i = 0; i < quantPontos; i++){
            System.out.print("Valor x ponto [" + (i + 1) + "]: ");
            x = leitor.nextDouble();

            System.out.print("Valor y ponto [" + (i + 1) + "]: ");
            y = leitor.nextDouble();

            System.out.println("Ponto[" + (i + 1) + "]: (" + x + ", " + y + ") \n" );

            pontos[i] = new Ponto(x, y);
        }          
        CalcularParMaisProximoFB(pontos, quantPontos);
    }
}