import java.util.ArrayList;
import java.util.StringJoiner;

public class Todo {
    private ArrayList<String> todoList;

    Todo(){
        this.todoList = new ArrayList<>();
    }

    public void addItem(String item) {
        this.todoList.add(item);
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner("\n");
        for (int i = 0; i < this.todoList.size(); i++) {
            result.add(String.format("%d. %s", i+1, this.todoList.get(i)));
        }
        return result.toString();
    }
}
