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
    public int compareTo(Crianca outra) { //pq precisa disso???
        // Ordena por escore
        return Integer.compare(this.score, outra.score);
    }

    @Override
    public String toString() {
        return nome + " " + score;
    }
}
