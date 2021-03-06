import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProblemaDaMochila {
    /*****************************************************************************************************************************************
    Esse método imprime a tabela que foi montada
    *****************************************************************************************************************************************/
    public static void imprimirTabelaConstruida(int[][] tabela) {
        for(int i = 0; i < tabela.length; i++){
            for (int j = 0; j < tabela[i].length; j++) {
                System.out.print(tabela[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
    
    /*****************************************************************************************************************************************
    Esse método retorna o valor máximo obtido se os melhores itens forem escolhidos
    *****************************************************************************************************************************************/
    public static int valorMaximoNaMochila(int[][] tabela) {
        return tabela[tabela.length-1][tabela[0].length-1];
    }
    
    /*****************************************************************************************************************************************
    Esse método retorna um conjunto de índices das linhas da tabela, desde que aquela linha tenha sido escolhida
    *****************************************************************************************************************************************/
    public static ArrayList<Integer> indiceDosProdutosEscolhidos(int[][] tabela, int[] pesos) {
        ArrayList<Integer> escolhidos = new ArrayList<>();
        int ultimaCelula = tabela[tabela.length-1][tabela[0].length-1];
        
      
        int linha=tabela.length-1, coluna=tabela[0].length-1;
        while (linha != 0) {
            if(tabela[linha][coluna] != tabela[linha-1][coluna]) {
                escolhidos.add(linha);
                coluna = coluna - pesos[linha-1];
                --linha;
            } else {
                --linha;
            }
        }
        
        Collections.sort(escolhidos);
        
        return escolhidos;
    }
    
    /*****************************************************************************************************************************************
    Esse método preenche a tabela com valores necessários para realização dos passos seguintes
    *****************************************************************************************************************************************/
    public static void gerarConstrucaoDaTabela(int linhaDePesos, int colunaCapacidadeDaMochila, int[] valores, int[] pesos, int[][] tabela) {
        /*Realiza os passos necessários para preencher a tabela*/
        for (int i = 1; i < linhaDePesos; i++) {//linhas  
            //System.out.println("Entrou no primeiro for " + i + " vezes");
            int valorDoItemAtual = valores[i-1];
            
            for (int j = 1; j < colunaCapacidadeDaMochila; j++) {//colunas
                //System.out.println("    Entrou no segundo for " + j + " vezes");
                if(pesos[i-1] <= j) {//pesoItem < capacidadeDaMochilaNessePonto
                    int acrescimoAoValor = tabela[i-1][j-pesos[i-1]];
                    tabela[i][j] = valorDoItemAtual + acrescimoAoValor;
                }
                
                if(tabela[i][j] < tabela[i-1][j]){
                    tabela[i][j] = tabela[i-1][j];
                }
            }
        }
    }
    
    /*****************************************************************************************************************************************
    Mátodo main
    *****************************************************************************************************************************************/
    public static void main(String[] args) {
        /****************************************************************************************************
         * Pocessamento da Entrada
        ****************************************************************************************************/
        Scanner input = new Scanner(System.in);
        
        int linhaDePesos = input.nextInt()+1, colunaCapacidadeDaMochila = input.nextInt()+1;
        
        String pesosTexto, valoresTexto;
        String[] pesosTextoArray, valoresTextoArray;
        int[] pesos, valores;
        
        pesosTexto = input.nextLine();//esvazia o buffer, pois estavamos usando o mesmo scanner para ler inteiros, o próximo nextLine() é quem lê a String
        pesosTexto = input.nextLine();//Captura o texto  
        pesosTextoArray = pesosTexto.split(" ");//Divide o texto em um array, onde o espaço em branco é o separador
        pesos = new int[pesosTextoArray.length];
        for (int i = 0; i < pesosTextoArray.length; i++) {
            pesos[i] = Integer.parseInt(pesosTextoArray[i]);
        }
        
        
        valoresTexto = input.nextLine();//Captura o texto  
        valoresTextoArray = valoresTexto.split(" ");//Divide o texto em um array, onde o espaço em branco é o separador
        valores = new int[valoresTextoArray.length];
        for (int i = 0; i < valoresTextoArray.length; i++) {
            valores[i] = Integer.parseInt(valoresTextoArray[i]);
        }
       
        /****************************************************************************************************
         * Parte para resolução do problema
        ****************************************************************************************************/
        int[][] tabela = new int[linhaDePesos][colunaCapacidadeDaMochila];
        
        gerarConstrucaoDaTabela(linhaDePesos, colunaCapacidadeDaMochila, valores, pesos, tabela);
        
        System.out.println("Valor : " + valorMaximoNaMochila(tabela));
        System.out.println("Produtos escolhidos : " + indiceDosProdutosEscolhidos(tabela, pesos));
    }  
       
}
