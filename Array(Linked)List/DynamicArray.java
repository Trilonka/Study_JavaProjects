import java.util.Objects;

public class DynamicArray {
    private int[] array;
    private int size;
    private int capacity = 10;

    public DynamicArray() {
        array = new int[capacity];
    }

    public DynamicArray(int initialCapacity) {
        capacity = initialCapacity;
        array = new int[capacity];
    }

    public void add(int value, int index) {
        Objects.checkIndex(index, size+1);
        if(size == capacity)
            changeArray((int)(capacity*1.5)+1);
        shiftRight(index);
        array[index] = value;
        ++size;
    }
    public void addToEnd(int value) {
        add(value, size);
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        shiftLeft(index);
        --size;
    }

    public void removeFromEnd() {
        remove(size-1);
    }

    public int get(int index) {
        Objects.checkIndex(index, size);
        return array[index];
    }

    public void clear() {
        size = 0;
        capacity = 10;
        array = new int[capacity];
    }

    public void resize(int newSize) {
        if(newSize < size)
            for(int i = newSize; i<size; ++i) array[i] = 0;

        if(newSize > capacity)
            reserve(newSize);

        size = newSize;
    }

    public void reserve(int newCapacity) {
        if(newCapacity != capacity)
            changeArray(newCapacity);
    }

    private void changeArray(int newCapacity) {
        capacity = newCapacity;
        size = Math.min(capacity, size);
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
    private void shiftRight(int index) {
        for(int i = size; i>index; --i)
        array[i] = array[i-1];
    }

    private void shiftLeft(int index) {
        for(int i = index; i<size; ++i)
            array[i] = array[i+1];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(int i = 0; i<size; i++) {
            builder.append(array[i]);
            if(i == size-1) {
                return builder.append("]").toString();
            }
            builder.append(", ");
        }
        return builder.append("]").toString();
    }
}