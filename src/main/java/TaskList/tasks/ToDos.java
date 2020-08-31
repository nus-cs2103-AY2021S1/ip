package main.java.TaskList.tasks;

public class ToDos extends Task {
    private String type = "Todos";
    public ToDos(String string) {
        super(string.substring(5), string);
    }
    public String toString() {
        return "[T] " + super.toString();
    }
}
