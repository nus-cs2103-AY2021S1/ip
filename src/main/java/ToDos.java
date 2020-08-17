import java.util.ArrayList;

public class ToDos extends Task{
    public ToDos(String taskTitle) {
        super(taskTitle);
    }

    public static void createTodoTask(String taskTitle, ArrayList<Task> tasks) {
        ToDos newTodoTask = new ToDos(taskTitle);
        tasks.add(newTodoTask);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
