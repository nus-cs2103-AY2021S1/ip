import java.util.ArrayList;

public class TodoList {
    ArrayList<Todo> list;

    TodoList() {
        this.list = new ArrayList<>();
    }

    public void add(Todo todo) {
        this.list.add(todo);
    }

    public Todo getTodo(int index) {
        return this.list.get(index - 1);
    }

    public void listAllTodos() {
        this.list.forEach(todo -> {
            String index = String.valueOf(this.list.indexOf(todo) + 1);
            System.out.println(index + ". " + todo);
        });
    }


}
