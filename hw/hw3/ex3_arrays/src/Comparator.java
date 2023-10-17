
public class Comparator{

    public static <T> boolean compareArrays (T[] arr1, T[] arr2){

        if (!arr1.getClass().isInstance(arr2)) return false;
        if (arr1.length != arr2.length) return false;

        for (int i = 0; i < arr1.length; i++){
            if (!arr1[i].equals(arr2[i])) return false;
        }
        return true;
    }
}
