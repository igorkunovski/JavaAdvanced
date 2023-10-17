
public class Pair <K, V>{
    private final K key;
    private V value;
    Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getFirst(){
        return key;
    }

    public V getSecond(){
        return value;
    }

    public void setValue(V value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key.toString() +
                ", value=" + value.toString() +
                '}';
    }
}
