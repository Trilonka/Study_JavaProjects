import java.util.Objects;

public class ForwardList {
    private Node headNode;
    private int size;
    //add(value, index), remove(index), get(index), clear(), getHead(), addToHead(value) //int

    public void add(int value, int index) {
        Objects.checkIndex(index, size+1);
        Node newNode = new Node(value);
        if(index == 0) {
            newNode.setNextNode(headNode);
            headNode = newNode;
        }
        else {
            Node pomNode = getPrevNode(index);
            newNode.setNextNode(pomNode.getNextNode());
            pomNode.setNextNode(newNode);
        }
        ++size;
    }

    public void addToHead(int value) {
        add(value, 0);
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        if(index == 0)
            headNode = headNode.getNextNode();
        else {
            Node pomNode = getPrevNode(index);
            pomNode.setNextNode(pomNode.getNextNode().getNextNode());
        }
        --size;
    }

    public int get(int index) {
        Objects.checkIndex(index, size);
        Node pomNode = headNode;
        for (int i = 0; i < index; i++)
            pomNode = pomNode.getNextNode();
        return pomNode.getValue();
    }

    public int getHead() {
        return get(0);
    }

    public void clear() {
        headNode = null;
        size = 0;
    }

    private Node getPrevNode(int index) {
        Node pomNode = headNode;
        for (int i = 0; i < index - 1; i++)
            pomNode = pomNode.getNextNode();
        return pomNode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node pomNode = headNode;
        for(int i = 0; i<size; i++) {
            builder.append(pomNode.getValue());
            pomNode = pomNode.getNextNode();
            if(i == size-1) {
                return builder.append("]").toString();
            }
            builder.append(", ");
        }
        return builder.append("]").toString();
    }
}

class Node {
    private int value;
    private Node nextNode;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node getNextNode() {
        return nextNode;
    }
}
