import java.util.Objects;

public class ForwardList {
    private Node headNode;
    private int size;

    public void add(int value, int index) {
        Objects.checkIndex(index, size+1);
        Node newNode = new Node(value, headNode);
        if(index == 0) {
            headNode = newNode;
        }
        else {
            Node pomNode = getPrevNode(index);
            newNode.nextNode = pomNode.nextNode;
            pomNode.nextNode = newNode;
        }
        ++size;
    }

    public void addToHead(int value) {
        add(value, 0);
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        if(index == 0)
            headNode = headNode.nextNode;
        else {
            Node pomNode = getPrevNode(index);
            pomNode.nextNode = pomNode.nextNode.nextNode;
        }
        --size;
    }

    public int get(int index) {
        Objects.checkIndex(index, size);
        Node pomNode = getPrevNode(index+1);
        return pomNode.value;
    }

    public int getHead() {
        return get(0);
    }

    public void clear() {
        headNode = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    private Node getPrevNode(int index) {
        Node pomNode = headNode;
        for (int i = 0; i < index - 1; i++)
            pomNode = pomNode.nextNode;
        return pomNode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node pomNode = headNode;
        for(int i = 0; i<size; i++) {
            builder.append(pomNode.value);
            pomNode = pomNode.nextNode;
            if(i == size-1) {
                return builder.append("]").toString();
            }
            builder.append(", ");
        }
        return builder.append("]").toString();
    }

    private static class Node {
        int value;
        Node nextNode;

        public Node(int value, Node nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }
    }
}
