import java.util.LinkedList;

public class TestForwardList {
    public static void main(String[] args) {
        ForwardList list = new ForwardList();
        list.add(5, 0);
        System.out.println(list);
        list.add(1, 1);
        System.out.println(list);
        list.add(4, 0);
        System.out.println(list);
        list.addToHead(77);
        System.out.println(list);
        System.out.println(list.getHead());
        System.out.println(list.get(3));
        list.remove(2);
        System.out.println(list);
        list.clear();
        System.out.println(list);
        list.addToHead(55);
        System.out.println(list);
    }
}
