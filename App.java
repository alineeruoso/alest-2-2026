import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main (String[] args) throws Exception{
        Scanner leEntrada = new Scanner(System.in);

        boolean executando = true; //trocar nome variavel

        processaDados("centro.txt");

        while(executando){
            System.out.println("Pennywise> ");

            String entrada = leEntrada.nextLine().toLowerCase().trim();

            if(entrada.isEmpty()){
                continue;
            }

            String[] partes = entrada.trim().split("\\s+", 2);

            String comando = partes[0]; 
            String nomeArquivo = partes.length > 1 ? partes[1] : "";

            switch(comando){
                case "consultar" -> {
                    if(nomeArquivo.isEmpty()){
                        System.out.println("Digite o nome do arquivo após 'consultar'");
                    }
                    processaDados(nomeArquivo);
                    System.out.println("Top-10 atualizado.");

                //Lê o arquivo da região, 
                // uma criança por vez, e atualiza o Top-10.
            }

        }
        leEntrada.close();
    }
}


             
                    
        //         case "mostrar"  ->  //Exibe as até 10 crianças atualmente armazenadas, da mais covarde para
        //                             //a menos covarde (escore crescente).

        //         case "limpar"   ->  MaxPQ maxheap = new MaxPQ(10);
        //                             // 2. Limpa dados auxiliares
        //                             // 3. Imprime: "Sistema resetado com sucesso!"
        //                         //loop continua ativo

        //         case "ajuda"    -> menu();

        //         case "sair"     -> System.out.println("Fechando o sistema...");
        //                             executando = false; // .sair()

        //         default         -> System.out.println("Comando inválido!");
        //     }
        // }
    

    public static void menu(){
        System.out.println("consultar <arquivo> -> Lê o arquivo da região e atualiza o Top-10");
        System.out.println("mostrar -> Exibe o Top-10");
        System.out.println("limpar -> Limpa consultas realizadas");
        System.out.println("ajuda -> Lista os comandos disponíveis");
        System.out.println("sair -> Encerra o programa");
    }

    public void sair(){
        
    }

    public static void processaDados(String caminhoArquivo) throws Exception{
        File arquivo = new File(caminhoArquivo); //precisa desse arquivo??

        if (!arquivo.exists()) {
            System.out.println("Erro: Arquivo '" + caminhoArquivo + "' não encontrado.");
            return;
        }

        String regiao = arquivo.getName().replace(".txt", "");
        System.out.println("Lendo dados da região: " + regiao);
    
        Scanner leitorArquivo = new Scanner(arquivo);

        while (leitorArquivo.hasNext()) {
            String nome = leitorArquivo.next();
            int score = leitorArquivo.nextInt();
                
            Crianca novaCrianca = new Crianca(nome, score);
            System.out.println(novaCrianca.toString());
        }
        
        leitorArquivo.close();
    }
}




// NO
// hashMap<strin, int> global = {}

// ler arquivo
//     le linha
//     coloca no global

// global tem equivalente a 50 linhas
// processa global
// for i in global:
//      heap.add(i)

// YES
// heap(hashmap<string,int>) heap

// ler arquivo
//      le linha
//      adiciona no heap direto heap.add(hash<string, int>)

