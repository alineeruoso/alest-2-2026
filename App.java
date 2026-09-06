import java.io.File;
import java.util.Scanner;

public class App {
    public static final int CAPACITY = 10;
    public static void main (String[] args) throws Exception{

        Scanner leEntrada = new Scanner(System.in);

        boolean executando = true; 

        MaxPQ<Crianca> maxheap = new MaxPQ<>(CAPACITY);

        while(executando){
            System.out.print("\n Pennywise> ");

            String entrada = leEntrada.nextLine().toLowerCase().trim();

            if(entrada.isEmpty()){
                continue;
            }

            String[] partes = entrada.trim().split("\\s+", 2);

            String comando = partes[0]; 
            String nomeArquivo = partes.length > 1 ? partes[1] : "";


            switch(comando){

            //Lê o arquivo da região, uma criança por vez, e atualiza o Top-10.
                case "consultar" -> consultar(nomeArquivo, maxheap);
                    
            //Exibe as até 10 crianças atualmente armazenadas, 
            // da mais covarde para a menos covarde (escore crescente)
                case "mostrar"  -> mostrar(maxheap);
        
                case "limpar"   -> maxheap = limpar();

                case "ajuda" -> menu(); 

                case "sair"  -> { System.out.println("\n Fechando o sistema...");
                                executando = sair();
                }

                default -> System.out.println("\n Comando inválido!");
            }
        }
        leEntrada.close();
    }   
    
    public static void consultar(String nomeArquivo, MaxPQ<Crianca> maxheap) throws Exception {
        if(nomeArquivo.isEmpty()){
            System.out.println("\n Digite o nome do arquivo após 'consultar'");
            return;
        }
        processaDados(nomeArquivo,maxheap);
    }
    
    public static void mostrar(MaxPQ<Crianca> maxheap){
        if(maxheap.isEmpty()){
            System.out.println("\n Nenhuma criança na lista.");
            return;
        }

        Crianca[] arrayCriancas = new Crianca[CAPACITY];

        int heapSize = maxheap.size();
        for(int i = 0; i < heapSize; i++){
            arrayCriancas[i] = maxheap.delMax(); 
        }
        
        int count = 1;
        for(int j = (heapSize -1); j >= 0; j--){
            System.out.printf("%2d. %s%n", count, arrayCriancas[j]);
            count++;
            maxheap.insert(arrayCriancas[j]);
        }
    }

    public static MaxPQ<Crianca> limpar(){ 
        System.out.println("\n Sistema resetado com sucesso!");

        return new MaxPQ<>(CAPACITY);
        //loop continua ativo
    }

    public static void menu(){
        System.out.println("\n");
        System.out.println("consultar <arquivo> -> Lê o arquivo da região e atualiza o Top-10");
        System.out.println("mostrar -> Exibe o Top-10");
        System.out.println("limpar -> Limpa consultas realizadas");
        System.out.println("ajuda -> Lista os comandos disponíveis");
        System.out.println("sair -> Encerra o programa");
    }

    public static boolean sair(){
        return false;
    }

    public static void processaDados(String caminhoArquivo, MaxPQ<Crianca> maxheap) throws Exception{
        File arquivo = new File(caminhoArquivo);  

        if (!arquivo.exists()) {
            System.out.println("\n Erro: Arquivo '" + caminhoArquivo + "' não encontrado.");
            return;
        }

        String regiao = arquivo.getName().replace(".txt", "");
        System.out.println("\n Região " + regiao + ".txt lida. Crianças no Top-10: " + maxheap.size());
    
        Scanner leitorArquivo = new Scanner(arquivo);

        while (leitorArquivo.hasNext()) {
            String nome = leitorArquivo.next();
            int score = leitorArquivo.nextInt();
                
            Crianca novaCrianca = new Crianca(nome, score);

            //comparar c a raíz do heap
            if(maxheap.size() >= CAPACITY){

                if(novaCrianca.compareTo(maxheap.max()) < 0){
                    maxheap.delMax(); 
                    maxheap.insert(novaCrianca);
                }

            } else {
                maxheap.insert(novaCrianca); 
            }
            
            System.out.println(novaCrianca.toString());
        } 
        System.out.println("\n Top-10 atualizado.");
        
        leitorArquivo.close();
    }
}