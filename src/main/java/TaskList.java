import java.util.LinkedList;

public class TaskList<T> extends LinkedList<T> {
    TaskList() {
        super();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size(); i++) {
            result += String.format("      %s. %s\n", i + 1, this.get(i));
        }
        return result;
    }
}