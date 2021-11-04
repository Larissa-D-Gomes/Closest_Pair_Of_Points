/* Grupo: 
 *   Ana Laura Fernandes de Oliveira  
 *   Larissa Domingues Gomes
 *   Tarcila Fernanda Resende da Silva
 * Disciplina: Projeto e Análise de Algoritmos
 * Professor: Silvio Jamil
 * Questão: Par de pontos mais próximos - Divisão e Conquista
 * Complexidade: O(nlog^2 n)
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

class ParPontos{
    public Ponto ponto1;
    public Ponto ponto2;
    public double distancia;

    public ParPontos(Ponto ponto1, Ponto ponto2, double distancia){
        this.ponto1 = ponto1;
        this.ponto2 = ponto2;
        this.distancia = distancia;
    }

    public void atualizar(Ponto ponto1, Ponto ponto2, double distancia){
        this.ponto1 = ponto1;
        this.ponto2 = ponto2;
        this.distancia = distancia;
    }

    public void print(){
        System.out.println("Distância: " + distancia);
        System.out.println("Par de pontos: ");
        System.out.println("\t(" + ponto1.x + ", " + ponto1.y + ")");
        System.out.println("\t(" + ponto2.x + ", " + ponto2.y + ")");
    }
}


public class ClosestPointsDivNConquerLog2{

    static void merge(Ponto[] pontos, int init, int meio, int fim, boolean isToOrdByX){

        // Tamanho dos vetores
        int tamMet1 = meio + 1 - init;
        int tamMet2 = fim - meio;

        // Vetores para copiar a primeira e segunda metade do
        // vetor pontos
        Ponto[] metade1 = new Ponto[tamMet1 + 1];
        Ponto[] metade2 = new Ponto[tamMet2 + 1];

        // Copiando metade1 dos pontos
        for(int i = 0; i < tamMet1; i++){
            metade1[i] = pontos[init + i];
        }
        
        // Copiando metade2 dos pontos
        for(int j = 0; j < tamMet2; j++){
            metade2[j] = pontos[meio + j + 1];
        }

        // Evitar comparações caso um vetor seja copiado inteiramente 
        // antes do outro durante a intercalacao
        // Ultima posicao = infinito, logo ela nunca sera escolhida
        metade1[tamMet1] = new Ponto(Double.MAX_VALUE, Double.MAX_VALUE);
        metade2[tamMet2] = new Ponto(Double.MAX_VALUE, Double.MAX_VALUE);
        
        // Variavel para caminhar nos vetores de metade
        int m1, m2;
        // Variavel para armazenar intercalacao ordenada no verto pontos
        int i = init;

        // Ordenação por intercalação
        for(m1 = 0, m2 = 0; i <= fim ; i++){
            // Aramazenar menor valor na sequencia
            // Ordenação é feita em relação a X ou Y dependendo do booleano recebido como paramentro
            if(isToOrdByX ? metade2[m2].x < metade1[m1].x : metade2[m2].y < metade1[m1].y)
                pontos[i] =  metade2[m2++];
            else
                pontos[i] = metade1[m1++]; 
            
        }
    }

   /* Metodo recursivo merge sort
    * O metodo divide um problema maior (pontos) em problemas menores
    * dividindo os pontos em varios sub pontos ate atingir um tamanho muito pequeno,
    * que o programa consiga ordenar, depois volta recursivamente ordernando os problemas
    * menores resolvidos.
    * Metodo mergesort -> chama logn vezes o metodo merge que e o n, sendo n o tamanho da pontos
    * Complexidade  O(n logn)
    * @param int* pontos, int init, int fim, boolean isToOrdByX
    */
    static void mergeSort(Ponto[] pontos, int init, int fim, boolean isToOrdByX){
        // Base da recursao
        if(fim > init){

            // Dividir vertores
            int meio = (init+fim)/2;
            mergeSort(pontos, init, meio, isToOrdByX);
            mergeSort(pontos, meio + 1, fim, isToOrdByX); 
            
            // Ordenacao
            merge(pontos, init, meio, fim, isToOrdByX);
        }
    }

    /* Método para calcular menor distância entre pares de pontos
     * utilizando uma abordagem de Força Bruta, cuja complexidade 
     * é O(n^2)
     * @param Ponto[][] pontos, int quantPontos
     * @return double 
     */
    public static ParPontos calcularMenorDistancia(Ponto[] pontos, int quantPontos){

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
        // os pontos do connjunto de pontos recebido como parâmetro O(n^2)
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
        return new ParPontos(p1, p2, menor);
    }
    
    public static ParPontos calcularMenorDistanciaFaixa(Ponto[] pontos, int quantPontos, ParPontos menor){
        // Ordenando pontos em relação ao eixo Y
        mergeSort(pontos, quantPontos, 0, false);
        double menorDist = menor.distancia;
        double calc = menor.distancia;

        Ponto p1 = new Ponto(menor.ponto1.x, menor.ponto1.y);
        Ponto p2 = new Ponto(menor.ponto2.x, menor.ponto2.y);
        
        for(int i = 0; i < quantPontos; i++){
            for(int j = i + 1; j < quantPontos && (pontos[j].y - pontos[i].y) < menor.distancia; j++){
                // Calculo de distância entre pontos pela fórmula:
                // raizquadrada((pontojX - pontoiX)^2 + (pontojY - pontoiY)^2)
                calc = Math.pow( 
                        (
                            Math.pow(pontos[j].x - pontos[i].x, 2) + Math.pow(pontos[j].y - pontos[i].y, 2)
                        ),
                    0.5); 
                if(calc < menorDist){
                    // Atulizar valores caso nova distância calulada
                    // for menor que a anterior
                    menorDist = calc;

                    // Atualizar ponto 1
                    p1.atualizar(pontos[i].x, pontos[i].y);
                    // Atualizar ponto 2
                    p2.atualizar(pontos[j].x, pontos[j].y);
                }
            }
        }
        return new ParPontos(p1, p2, menorDist);
        
    }
    
    /* Método para calcular menor distancia entre dois pontos
     * utilizando uma abordagem de divisão e conquista
     * @param Ponto[] pontos, int quantPontos
     * @return double menor distancia 
     */
    static ParPontos encontrarParesMaisProximos(Ponto[] pontos, int quantPontos){
        // Calcular distancia por força bruta quando vertor conter de 2 a 3 pontos
        if(quantPontos < 4){
            return calcularMenorDistancia(pontos, quantPontos);
        }
        
        int meio = quantPontos/2;
        int tamDir = quantPontos - meio;

        // Separando vetor ponto em duas metades
        Ponto[] esq = new Ponto[meio];
        Ponto[] dir = new Ponto[tamDir];

        for(int i = 0; i < meio; i++)
            esq[i] = pontos[i];

        for(int i = 0; i < tamDir; i++)
            dir[i] = pontos[meio + i];

        // Calculando menor distancia de cada metade recursivamente
        ParPontos menorEsq = encontrarParesMaisProximos(esq, meio);
        ParPontos menorDir = encontrarParesMaisProximos(dir, tamDir);

        // Selecionando menor distancia entre as duas metades para
        // ser a faixa que irá considerar os pontos próximos do meio
        // que terão as distâncias calculadas
        ParPontos faixa = menorDir.distancia < menorEsq.distancia ? menorDir : menorEsq;

        Ponto[] pontosNaFaixa = new Ponto[quantPontos];
        int pf = 0; // Variavel para armazenar quantidade de pontos dentro da faixa

        for(int i = 0; i < quantPontos; i++){
           if(pontos[i].x - pontos[meio].x < faixa.distancia)
             pontosNaFaixa[pf++] = pontos[i];
        }
        
        ParPontos menorDistFaixa = calcularMenorDistanciaFaixa(pontosNaFaixa, pf, faixa);
        return faixa.distancia < menorDistFaixa.distancia ? faixa : menorDistFaixa;
    }


    /* Método para calcular menor distância entre pares de pontos
     * utilizando uma abordagem de Divisão e conquista, cuja complexidade 
     * é O(nlog^2 n)
     * @param Ponto[] pontos, int quantPontos
     * @return double 
     */
    public static ParPontos calcularParMaisProximoFB(Ponto[] pontos, int quantPontos){
        // Impossível calcular menor distância se |conjunto de pontos| < 2
        if(quantPontos < 2)
            System.out.println("Impossível calcular menor distância!");
        else{   
            // Ordenando vetor de pontos em relação ao eixo X
            mergeSort(pontos, 0, quantPontos - 1, true);                 
            return encontrarParesMaisProximos(pontos, quantPontos);
        }
        return null;
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
        calcularParMaisProximoFB(pontos, quantPontos).print();
        
    }
}