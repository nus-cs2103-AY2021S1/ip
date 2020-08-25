import java.util.ArrayList;

public class ToDos extends Task {
    public ToDos(String taskTitle, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.TODO);
    }

    public static void addNewTodoTask(String taskTitle, ArrayList<Task> tasks) {
        ToDos newTodoTask = new ToDos(taskTitle, false);
        tasks.add(newTodoTask);
        Feedbacks.addTodoTaskMsg(tasks);
    }

    public static void loadTodoTask(String taskTitle, Boolean isDone, ArrayList<Task> tasks) {
        ToDos todoTask = new ToDos(taskTitle, isDone);
        tasks.add(todoTask);
    }

    @Override
    public String writeToFile() {
        return super.writeToFile();
    }
}
