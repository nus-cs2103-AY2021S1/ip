import java.util.ArrayList;

public class ToDos extends Task {
    public ToDos(String taskTitle) {
        super(taskTitle, TaskTypes.TODO);
    }

    public static void addTodoTask(String taskTitle, ArrayList<Task> tasks) {
        ToDos newTodoTask = new ToDos(taskTitle);
        tasks.add(newTodoTask);
        Feedbacks.addTodoTaskMsg(tasks);
    }
}
