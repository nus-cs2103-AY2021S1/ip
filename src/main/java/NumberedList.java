import java.util.LinkedList;

public class NumberedList<T> extends LinkedList<T> {
    NumberedList() {
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