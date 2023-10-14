import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class StackArray<T> {
    private final List<T> array;
    private int top;

    public StackArray() {
        array = new ArrayList<>();
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(T object) {
        array.add(++top, object);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array.remove(top--);
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array.get(top);
    }

    public int size() {
        return top + 1;
    }

    @Override
    public String toString() {
        return array.subList(0, top + 1).toString();
    }
}
