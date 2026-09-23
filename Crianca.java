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
        if (this == outra) {
            return 0;
        }

        if (outra == null) {
            return 1; 
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
