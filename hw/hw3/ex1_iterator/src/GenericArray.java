import java.util.Iterator;

public class GenericArray<T> {
    private Object[] arr;

    GenericArray(){
        arr = new Object[0];
    }

    public Object[] getArr() {
        return arr;
    }

    public void add(T t){
        Object[] temp_arr = new Object[arr.length + 1];

        for (int index = 0; index < arr.length; index++) {
            temp_arr[index] = arr[index];
        }
        temp_arr[temp_arr.length - 1] = t;

        arr = temp_arr;
    }

    public void remove(T t){
        Integer position = find(t);
        if (position != null) {
            Object[] temp_arr = new Object[arr.length - 1];

            for (int i = 0; i < position; i++) {
                temp_arr[i] = arr[i];
            }

            for (int i = position; i < arr.length -1; i++) {
                temp_arr[i] = arr[i + 1];
            }
            arr = temp_arr;
        }
    }

    public Integer find(T t){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(t)) return i;
        }
        return null;
    }

    public void info(Object[] arr){
        GenericIterator<Object> it = new GenericIterator<>(arr);
        System.out.println("[ -------------- List start");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("List finnish -----------]");
    }

    private static class GenericIterator<T> implements Iterator<T>, Iterable<T>{
        private final T[] arr;
        private Integer index = 0;

        GenericIterator(T[] arr){
            this.arr = arr;
        }

        @Override
        public boolean hasNext() {
            return (arr.length > index);
        }

        @Override
        public T next(){
            if (hasNext()) {
                return arr[index++];
            }
            return null;
        }

        @Override
        public Iterator<T> iterator() {
            return new GenericIterator<>(arr);
        }
    }
}
