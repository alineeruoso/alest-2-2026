public class Crianca implements Comparable<Crianca> {
    private String nome;
    private int score;

    public Crianca(String nome, int score){
        this.nome = nome;
        this.score = score;
    }

    public String getNome(){
        return nome;
    }

    public int getScore(){
        return score;
    }

    @Override
    public int compareTo(Crianca outra) { 
        //proteção contra comparação do objeto atual com ele mesmo
        if (this == outra) {
            return 0;
        }

        //proteção contra objeto nulo recebido por parâmetro
        if (outra == null) {
            return 1; //this é maior que null (considera elementos válidos prioritários)
        }

        //compara score
        int integerCompare = Integer.compare(this.score, outra.score);
        if (integerCompare != 0){
            return integerCompare;
        }

        //se score igual, compara nome lexicograficamente
        return this.nome.compareTo(outra.nome);
    }

    @Override
    public String toString() {
        return String.format("%-25s %4d", nome, score);
    }
}
