public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    @SuppressWarnings("unchecked")
    public MaxPQ(int capacity){ 
        pq = (Key[]) new Comparable[capacity+1];
    }

    public boolean isEmpty(){ 
        return N == 0;  // N é quantos estão inseridos já
    }

    public int size(){ //quantos já foram inseridos no heap
        return N; 
    }

    public void insert(Key key){ //começa a guardar no array na posição 1
        N = N + 1;
        pq[N] = key;
        swim(N);
    }

    public Key max(){
        if(isEmpty()){
            return null;
        }
        return pq[1];
    }

    public Key delMax(){ 
        if(isEmpty()){
            return null;
        }

        Key maiorElemento = pq[1];

        exch(1, N);
        N = N - 1;

        sink(1);

        pq[N+1] = null;

        return maiorElemento;
    }

    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            exch(k, k/2);
            k = k/2;
        }
    }


    private void sink(int indicePai){ 
        while (2 * indicePai <= N){
            int indiceFilho = 2 * indicePai;

            if (indiceFilho < N && less(indiceFilho, indiceFilho+1)){
                indiceFilho++;
            } 
            if (!less(indicePai, indiceFilho)){ 
                break;
            }
            exch(indicePai, indiceFilho);
            
            indicePai = indiceFilho; 
       }
    }

    private boolean less(int i, int j){ 
        return pq[i].compareTo(pq[j]) < 0; 
    }

    private void exch(int i, int j){ 
        Key aux = pq[i]; 
        pq[i] = pq[j]; 
        pq[j] = aux; 
    }
}