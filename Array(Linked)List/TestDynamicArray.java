public class TestDynamicArray {
    public static void main(String[] args) {
        DynamicArray array = new DynamicArray(3);
        for(int i = 0; i<5; i++) array.addToEnd(i);
        System.out.println(array);
        array.add(23, 4);
        System.out.println(array);
        array.removeFromEnd();
        System.out.println(array);
        array.remove(0);
        System.out.println(array);
        array.reserve(3);
        System.out.println(array);
        /* array.reserve(10);
        System.out.println(array); */
        array.resize(2);
        System.out.println(array);
        array.resize(5);
        System.out.println(array);
        System.out.println(array.get(1));
        array.clear();
        System.out.println(array);
        array.addToEnd(44);
        System.out.println(array);
    }
}